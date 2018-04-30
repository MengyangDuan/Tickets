package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.model.*;
import tickets.povo.*;
import tickets.service.AccountService;
import tickets.service.ActivityService;
import tickets.service.OrderService;
import tickets.service.UserService;
import tickets.util.LevelUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/data/user")
@ResponseBody
public class UserDataController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;
    @Autowired
    ActivityService activityService;

    @RequestMapping(value = "/getInfo")
    public UserInfoVO getUserInfo(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        double totalMoney=orderService.getTotalMoney(id);
        UserInfoVO userInfoVO=userService.getUserInfoVO(id,totalMoney);
        return userInfoVO;
    }

    @RequestMapping(value = "/getUserInfo")
    public UserInfoVO getUserInfoById(int id){
        double totalMoney=orderService.getTotalMoney(id);
        UserInfoVO userInfoVO=userService.getUserInfoVO(id,totalMoney);
        return userInfoVO;
    }

    @RequestMapping(value = "/getUserAccount")
    public UserAccountVO getUserAccount(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        UserAccount userAccount=accountService.getUserAccount(id);
        UserAccountVO userAccountVO=new UserAccountVO(userAccount);
        return userAccountVO;
    }

    @RequestMapping(value = "/getAvailableCoupons")
    public List<CouponVO>getAvailableCoupons(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        List<CouponVO>coupons=userService.getAvailableCoupons(id);
        return coupons;
    }

    @RequestMapping(value = "/getAllOrders")
    public List<OrderVO> getAllOrders(HttpSession session){
        int userid=(Integer)session.getAttribute("login");
        List<OrderVO> orders=orderService.getAllOrders(userid);
        return orders;
    }

    @RequestMapping(value = "/getPreOrders")
    public List<OrderVO> getPreOrders(HttpSession session){
        int userid=(Integer)session.getAttribute("login");
        List<OrderVO> orders=orderService.getPreOrders(userid);
        return orders;
    }

    @RequestMapping(value = "/getCancelOrders")
    public List<OrderVO> getCancelOrders(HttpSession session){
        int userid=(Integer)session.getAttribute("login");
        List<OrderVO> orders=orderService.getCancelOrders(userid);
        return orders;
    }

    @RequestMapping(value = "/getCousumedOrders")
    public List<OrderVO> getCousumedOrders(HttpSession session){
        int userid=(Integer)session.getAttribute("login");
        List<OrderVO> orders=orderService.getConcumedOrders(userid);
        return orders;
    }

    @RequestMapping(value = "/getNoPayOrders")
    public List<OrderVO> getNoPayOrders(HttpSession session){
        int userid=(Integer)session.getAttribute("login");
        List<OrderVO> orders=orderService.getNoPayOrders(userid);
        return orders;
    }


    @RequestMapping(value = "/getActivityList")
    public List<ActivityVO> getActivities(){
        List<ActivityVO>activities=activityService.getActivities();
        return activities;
    }


    @RequestMapping(value = "/getPreActivityList")
    public List<ActivityVO> getPreActivities(){
        List<ActivityVO>activities=activityService.getPreActivities();
        return activities;
    }


    @RequestMapping(value = "/getActivityInfo")
    public ActivityVO getActivitiesInfo(Integer activityId){
        ActivityVO activityVO=activityService.getActivityVO(activityId);
        return activityVO;
    }

    @RequestMapping(value = "/getUnavailableSeats")
    public List<String> getUnavailableSeats(Integer activityId){
        List<String>result=new ArrayList<String>();
        List<Ticket>tickets=activityService.getUnavailableTickets(activityId);
        Set<Integer> ticketsNo=new HashSet<Integer>();
        String seatMap=activityService.getActivity(activityId).getVenue().getSeatMap();
        for(int i=0;i<tickets.size();i++){
            int ticketNo=Integer.parseInt(tickets.get(i).getSeat());
            ticketsNo.add(ticketNo);
        }
        String[]seatArray=seatMap.split(",");
        int counter=0;
        for(int i=0;i<seatArray.length;i++){
            char[] rowArray=seatArray[i].toCharArray();
            for(int j=0;j<rowArray.length;j++){
                if(rowArray[j]>='a'&&rowArray[j]<='z'){
                    counter++;
                    if(ticketsNo.contains(counter)){
                        String str=String.valueOf(i+1)+"_"+String.valueOf(j+1);
                        result.add(str);
                    }
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "/getTicketPriceBySeat")
    public double getTicketPrice(int activityId,String seatNo){
        List<TicketVO>tickets=activityService.getTicketsByActivity(activityId);
        for(TicketVO ticket:tickets){
            if(ticket.getSeat().equals(seatNo)){
                return ticket.getPrice();
            }
        }
        return 0;
    }

    @RequestMapping(value = "/getDiscountPrice")
    public double getDiscountPrice(int level,double originalPrice){
        double discount= LevelUtil.getDiscount(level);
        return originalPrice*discount;
    }

    @RequestMapping(value = "/getTicketPrice")
    public Set<Double> getTicketPrice(int activityId){
        Set<Double>priceSet=new HashSet<Double>();
        List<TicketVO>ticketVOS=activityService.getTicketsByActivity(activityId);
        for(int i=0;i<ticketVOS.size();i++){
            priceSet.add(ticketVOS.get(i).getPrice());
        }
        return priceSet;
    }

}

