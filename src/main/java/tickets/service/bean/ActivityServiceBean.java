package tickets.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.ActivityDao;
import tickets.dao.OrderDao;
import tickets.model.Activity;
import tickets.model.Orders;
import tickets.model.Ticket;
import tickets.povo.ActivityVO;
import tickets.povo.TicketVO;
import tickets.service.ActivityService;

import java.sql.Timestamp;
import java.util.*;

@Transactional
@Service
public class ActivityServiceBean implements ActivityService{

    @Autowired
    ActivityDao activityDao;
    @Autowired
    OrderDao orderDao;

    public List<ActivityVO> getActivities() {
        List<Activity>activities=new ArrayList<Activity>();
        List<ActivityVO>activityVOS=new ArrayList<ActivityVO>();
        activities=activityDao.getActivities();
        for(int i=0;i<activities.size();i++){
            activityVOS.add(new ActivityVO(activities.get(i)));
        }
        return activityVOS;
    }

    @Override
    public List<ActivityVO> getPreActivities() {
        List<ActivityVO>activityVOS=getActivities();
        List<ActivityVO>result=new ArrayList<>();
        for(ActivityVO activityVO:activityVOS){
            if(activityVO.getTime().after(new Timestamp(new Date().getTime())))
                result.add(activityVO);
        }
        return result;
    }

    @Override
    public List<ActivityVO> getActivitiesByVenue(int venueId) {
        List<Activity>activities=new ArrayList<Activity>();
        List<ActivityVO>activityVOS=new ArrayList<ActivityVO>();
        activities=activityDao.getActivityByVenue(venueId);
        for(int i=0;i<activities.size();i++){
            activityVOS.add(new ActivityVO(activities.get(i)));
        }
        return activityVOS;
    }

    public ActivityVO getActivityVO(int activityId) {
        Activity activity=activityDao.getActivity(activityId);
        ActivityVO activityVO=new ActivityVO(activity);
        return activityVO;
    }

    public Activity getActivity(int activityId){
        Activity activity=activityDao.getActivity(activityId);
        return activity;
    }

    public List<TicketVO> getTicketsByActivity(int activityId) {
        List<Ticket>tickets=activityDao.getTicketsByActivity(activityId);
        List<TicketVO>ticketVOS=new ArrayList<TicketVO>();
        for(int i=0;i<tickets.size();i++){
            ticketVOS.add(new TicketVO(tickets.get(i)));
        }
        return ticketVOS;
    }

    public Ticket getTicketBySeat(int activityId, String seat) {
        List<Ticket>tickets=activityDao.getTicketsByActivity(activityId);
        for(int i=0;i<tickets.size();i++){
            if(tickets.get(i).getSeat().equals(seat))
                return tickets.get(i);
        }
        return null;
    }

    @Override
    public List<Ticket> getUnavailableTickets(int activityId) {
        List<Ticket>unavailableTickets=activityDao.getUnavailableTickets(activityId);
        return unavailableTickets;
    }

    public Boolean matchingTickets(Orders orders) {
        String[]splitArray=orders.getSelectRequire().split(",");
        int activityId=Integer.parseInt(splitArray[0]);
        Map<Double,Integer> seatRequireMap=new HashMap<Double,Integer>();
        for(int i=1;i<splitArray.length;i++){
            String seatRequire=splitArray[i];
            String[]temp=seatRequire.split("_");
            int number=Integer.parseInt(temp[0]);
            double price=Double.parseDouble(temp[1]);
            seatRequireMap.put(price,number);
        }
        List<Ticket>tickets=getAvailableTickets(activityId);
        for(int i=0;i<tickets.size();i++){
            Ticket ticket=tickets.get(i);
            double price=ticket.getPrice();
            if(seatRequireMap.containsKey(price)){
                int number=seatRequireMap.get(price);
                if(number>0) {
                    seatRequireMap.replace(price, number, number - 1);
                    ticket.setOrders(orders);
                }
            }
        }
        for (Map.Entry<Double,Integer> entry : seatRequireMap.entrySet()) {
            if(entry.getValue()>0) {
                orders.setMatchingStatus(2);
                orders.setSelectRequire(String.valueOf(activityId));
                orderDao.updateOrder(orders);
                return false;
            }
        }
        for(int i=0;i<tickets.size();i++){
            Ticket ticket=tickets.get(i);
            if(ticket.getOrders()!=null)
                orderDao.updateTicket(ticket);
        }
        orders.setMatchingStatus(1);
        orderDao.updateOrder(orders);
        return true;
    }

    @Override
    public List<Ticket> getAvailableTickets(int activityId) {
        List<Ticket>tickets=activityDao.getAvailableTickets(activityId);
        return tickets;
    }
}
