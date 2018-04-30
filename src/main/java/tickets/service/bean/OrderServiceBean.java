package tickets.service.bean;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.ActivityDao;
import tickets.dao.OrderDao;
import tickets.model.*;
import tickets.povo.OrderVO;
import tickets.povo.TicketVO;
import tickets.service.OrderService;
import tickets.util.CouponUtil;
import tickets.util.DateHandler;
import tickets.util.OrderUtil;

import java.sql.Timestamp;
import java.util.*;

@Transactional
@Service
public class OrderServiceBean implements OrderService{
    @Autowired
    OrderDao orderDao;
    @Autowired
    ActivityDao activityDao;

    public Orders getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    public int buyTickets(UserInfo userInfo, List<Ticket> tickets,double totalPrice, double afterDiscountPirce){
        Orders orders =new Orders();
        orders.setOrderTime(new Timestamp(new Date().getTime()));
        orders.setTotalPrice(totalPrice);
        orders.setAfterDiscountPrice(afterDiscountPirce);
        orders.setSeatType(1);
        orders.setMatchingStatus(-1);
        orders.setUserInfo(userInfo);
        updateUserPoints(userInfo,afterDiscountPirce);
        for (Ticket tic : tickets) {
            tic.setStatus(1);
        }
        orders.setTickets(new HashSet<Ticket>(tickets));
        //orderDao.updateUserInfo(userInfo);
        int result=orderDao.saveOrder(orders);
        Orders newOrders=orderDao.getOrder(result);
        for (Ticket tic : tickets) {
            tic.setOrders(newOrders);
            orderDao.updateTicket(tic);
        }
        return result;
     }

    @Override
    public int setOrderWithNoTicket(int activityId,String seatRequire,UserInfo userInfo, double totalPrice, double afterDiscountPrice) {
        Orders orders =new Orders();
        orders.setOrderTime(new Timestamp(new Date().getTime()));
        orders.setTotalPrice(totalPrice);
        orders.setAfterDiscountPrice(afterDiscountPrice);
        orders.setSeatType(0);
        orders.setUserInfo(userInfo);
        orders.setMatchingStatus(0);
        updateUserPoints(userInfo,afterDiscountPrice);
        String selectRequire=String.valueOf(activityId)+","+seatRequire;
        orders.setSelectRequire(selectRequire);
        int result=orderDao.saveOrder(orders);
        return result;
    }

    private Boolean updateUserPoints(UserInfo userInfo,double afterDiscountPrice){
        int newPoints= CouponUtil.getActualPoints(afterDiscountPrice);
        userInfo.setRewardPoints(userInfo.getRewardPoints()+newPoints);
        return orderDao.updateUserInfo(userInfo);
    }

    public Boolean updateOrder(Orders orders) {
        return orderDao.updateOrder(orders);
    }

    public Boolean checkTicket(int ticketId) {
        Ticket ticket=orderDao.getTicket(ticketId);
        ticket.setIsSeated(1);
        return orderDao.updateTicket(ticket);
    }

    @Override
    public Boolean payFailed(int orderId) {
        Orders order=orderDao.getOrder(orderId);
        order.setStatus(2);
        Boolean result=orderDao.updateOrder(order);
        List<Ticket>tickets=getTicketByOrder(orderId);
        for(Ticket ticket:tickets){
            if(order.getSelectRequire()==null){
                order.setSelectRequire(String.valueOf(ticket.getActivity().getId()));
            }
            ticket.setOrders(null);
            ticket.setStatus(0);
            orderDao.updateTicket(ticket);
        }
        //order.setTickets(null);
        return result;
    }

    public double cancelTicketAndCalculateRefunds(int orderId) {
        Boolean updateResult=true;
        Orders orders =orderDao.getOrder(orderId);
        orders.setStatus(3);
        Set<Ticket>tickets= orders.getTickets();
        for(Ticket ticket:tickets){
            if(orders.getSelectRequire()==null){
                orders.setSelectRequire(String.valueOf(ticket.getActivity().getId()));
            }
            ticket.setStatus(0);
            ticket.setOrders(null);
            updateResult=orderDao.updateTicket(ticket);
            if(updateResult==false)
                break;
        }
        if(updateResult==true) {
            if (orderDao.updateOrder(orders) == true) {
                Timestamp timestamp = orders.getOrderTime();
                double totalPrice = orders.getTotalPrice();
                double percentage = OrderUtil.cancelPercentage(timestamp);
                double refund = totalPrice * percentage;
                return refund;
            } else
                return -1;
        }
        else
            return -1;
    }

    public List<OrderVO> getAllOrders(int userid) {
        List<Orders>orders=orderDao.getAllOrders(userid);
        List<OrderVO>orderVOS=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            OrderVO orderVO=orderToVo(orders.get(i),userid);
            if(orderVO==null)
                continue;
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    public List<OrderVO> getPreOrders(int userid) {
        List<Orders>orders=orderDao.getBookOrders(userid);
        List<OrderVO>orderVOS=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            OrderVO orderVO=orderToVo(orders.get(i),userid);
            if(orderVO==null)
                continue;
            if(orderVO.getActivityTime().after(new Timestamp(new Date().getTime())))
                orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    public List<OrderVO> getCancelOrders(int userid) {
        List<Orders>orders=orderDao.getCancelOrders(userid);
        List<OrderVO>orderVOS=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            OrderVO orderVO=orderToVo(orders.get(i),userid);
            if(orderVO==null)
                continue;
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    public List<OrderVO> getConcumedOrders(int userid) {
        List<Orders>orders=orderDao.getBookOrders(userid);
        List<OrderVO>orderVOS=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            OrderVO orderVO=orderToVo(orders.get(i),userid);
            if(orderVO==null)
                continue;
            if(orderVO.getActivityTime().before(new Timestamp(new Date().getTime())))
                orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    public List<OrderVO> getNoPayOrders(int userid) {
        List<Orders>orders=orderDao.getNoPayOrders(userid);
        List<OrderVO>orderVOS=new ArrayList<>();
        for(int i=0;i<orders.size();i++){
            OrderVO orderVO=orderToVo(orders.get(i),userid);
            if(orderVO==null)
                continue;
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    private OrderVO orderToVo(Orders order,int userid){
        OrderVO orderVO=new OrderVO(order);
        int orderid=orderVO.getId();
        orderVO.setUserId(userid);
        List<Ticket>tickets=getTicketByOrder(orderid);
        Activity activity=null;
        if(tickets.size()>0) {
            Set<TicketVO> ticketVOS = new HashSet<>();
            for (int i = 0; i < tickets.size(); i++) {
                ticketVOS.add(new TicketVO(tickets.get(i)));
            }
            orderVO.setTickets(ticketVOS);
            activity = tickets.get(0).getActivity();
        }
        else {
            activity=activityDao.getActivity(getActivityidWithNoTicket(order.getSelectRequire()));
        }
        orderVO.setActivityId(activity.getId());
        orderVO.setActivityName(activity.getName());
        orderVO.setActivityTime(activity.getTime());
        Venue venue=activity.getVenue();
        orderVO.setVenueId(venue.getId());
        orderVO.setVenueName(venue.getName());
        orderVO.setVenueAddress(venue.getAddress());
        return orderVO;
    }
    private int getActivityidWithNoTicket(String selectRequire){
        String[]array=selectRequire.split(",");
        String id=array[0];
        return Integer.parseInt(id);
    }

    public List<Ticket> getPreTicketsByVenue(int venueId) {
        return orderDao.getPreTicketsByVenue(venueId);
    }

    public List<Ticket> getCancelTicketsByVenue(int venueId) {
        return orderDao.getCancelTicketsByVenue(venueId);
    }

    public double getTotalMoney(int id) {
        List<Orders>orders=orderDao.getBookOrders(id);
        double total=0.0;
        for(int i=0;i<orders.size();i++){
            total+=orders.get(i).getTotalPrice();
        }
        return total;
    }

    public OrderVO getOrderVO(int orderId) {
        return null;
    }

    public List<Ticket> getTicketByOrder(int orderId) {
        return orderDao.getTicketByOrder(orderId);
    }

    public Venue getVenueByOrder(int orderId){
        List<Ticket>tickets=getTicketByOrder(orderId);
        if(tickets.size()>0)
            return tickets.get(0).getActivity().getVenue();
        else {
            Orders orders=orderDao.getOrder(orderId);
            int activityId=getActivityidWithNoTicket(orders.getSelectRequire());
            Activity activity=activityDao.getActivity(activityId);
            return activity.getVenue();
        }
    }

    @Override
    public UserInfo getUserByOrder(int orderId) {
        return orderDao.getUserByOrder(orderId);
    }


    public JSONObject getGuestLiveNumByMonth(){
        String currentYear="2018";
        JSONObject ans=new JSONObject();
        List<Orders> orders=orderDao.getAll();
        Map thirdYear=getGuestNumByMonth_Helper(orders);
        JSONObject vipNums=new JSONObject();
        JSONObject unvipNums=new JSONObject();
        vipNums.put(currentYear,thirdYear.get("vip"));
        unvipNums.put(currentYear,thirdYear.get("unvip"));
        ans.put("vip",vipNums);
        ans.put("unvip",unvipNums);
        return ans;
    }

    private Map getGuestNumByMonth_Helper(List<Orders> orders){
        Map map=new HashMap();
        int[] vipNumArray=new int[12];
        int[] unvipNumArray=new int[12];
        for(Orders orders1:orders){
            Timestamp timestamp=orders1.getOrderTime();
            int month=timestamp.getMonth();
            if(orders1.getUserInfo()==null) unvipNumArray[month]++;
            else vipNumArray[month]++;
        }
        map.put("vip",vipNumArray);
        map.put("unvip",unvipNumArray);
        return map;
    }




}
