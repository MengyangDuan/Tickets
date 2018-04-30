package tickets.controller;

import net.sf.json.JSONObject;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tickets.model.*;
import tickets.povo.VenueVO;
import tickets.service.*;
import tickets.util.OrderThread;
import tickets.util.TimeMethod;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/venue")
public class VenueCommitController {
    @Autowired
    VenueService venueService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ActivityService activityService;
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(HttpSession session,String id, String password){
        if(id.length()!=7)
            return new ModelAndView("redirect:/venue/login");
        int venueId=Integer.parseInt(id);
        Boolean result=venueService.login(venueId,password);
        if(result==false){
            return new ModelAndView("redirect:/venue/login");
        }else {//验证通过
            session.setAttribute("login",venueId);
            return new ModelAndView("redirect:/venue/info");
        }
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleRegisterRequest(String venueName,String address,String seatMap,String email,String password){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        //String[] actualSeat= PublicMethod.addSeatGroups(seatMap);
        VenueVO venueVO=new VenueVO(venueName,address,seatMap,email,password);
        Boolean result=venueService.register(venueVO);
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value="/modify",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleModifyInfoRequest(int id,String venuename,String email,String address,String seat){
        Venue venue=venueService.getVenue(id);
        VenueModify venueModify=new VenueModify(venue,venuename,address,seat);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Boolean result=venueService.saveVenueModify(venueModify);
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value="/releaseActivity",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleReleaseActivityRequest(HttpSession session,String name, String time,String type,String description){
        int id=(Integer)session.getAttribute("login");
        int activityType=Integer.parseInt(type);
        Date date= TimeMethod.strToDate(time);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int result=venueService.releaseActivity(id,name,date,activityType,description);
        if(result>=0)
            resultMap.put("result", result);
        else
            resultMap.put("result", -1);
        return resultMap;
    }

    @RequestMapping(value="/makeTickets",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleMakeTicketsRequest(HttpSession session,HttpServletRequest request, @RequestParam int activityId ,@RequestParam String seatMap){
        JSONObject jb = JSONObject.fromObject(seatMap);
        Map map = (Map)jb;
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        Boolean result=venueService.makeTickets(id,activityId,map);
        if(result==true)
            resultMap.put("result", result);
        else
            resultMap.put("result", -1);
        return resultMap;
    }

    @RequestMapping(value = "/checkTicket",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleCheckTicketRequest(String ticketId){
        int id= Integer.parseInt(ticketId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Boolean result=orderService.checkTicket(id);
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public int handleUserLoginRequest(HttpSession session, String userName,String password){
        int userId=userService.checkUser(userName,password);
        return userId;
    }

    @RequestMapping(value = "/buyTicketAtScene",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleBuyTickeAtScenetRequest(String userName,String password, Ticket ticket){
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        UserInfo userInfo;
//        int id=userService.checkUser(userName,password);
//        if(id<0){
//            userInfo=null;
//        }
//        else{
//            userInfo=userService.getUserInfo(id);
//            double totalMoney=orderService.getTotalMoney(id);
//
//        }
//        orderService.buyTickets()
//        if(result==true)
//            resultMap.put("result", "success");
//        else
//            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value = "/vipPay", method = RequestMethod.POST)
    @ResponseBody
    public int handleVipPayRequest(int userId, Integer activityId,String seatNo,double totalPrice, double afterDiscountPirce){
        UserInfo userInfo=userService.getUserInfo(userId);
        List<Ticket> tickets=new ArrayList<Ticket>();
        int orderId;
        String[] seatList = seatNo.split(",");
        for (String seat : seatList) {
            tickets.add(activityService.getTicketBySeat(activityId, seat));
        }
        orderId = orderService.buyTickets(userInfo, tickets, totalPrice, afterDiscountPirce);
        if (orderId >= 0) {
            Orders order = orderService.getOrder(orderId);
            OrderThread.addOrder(order);
            Ticket ticket=tickets.get(0);
            Venue venue=ticket.getActivity().getVenue();
            order.setStatus(1);
            if(accountService.updatePlatformMoney(userInfo,venue,afterDiscountPirce)==true) {
                orderService.updateOrder(order);
                OrderThread.removeOrder(order);
                return order.getId();
            }
            else
                return -1;
        } else
            return -1;
    }

    @RequestMapping(value="visitorPay",method = RequestMethod.POST)
    @ResponseBody
    public int handleVisitorPayRequest(Integer activityId,String seatNo, double totalPrice){
        List<Ticket> tickets=new ArrayList<Ticket>();
        int orderId;
        String[] seatList = seatNo.split(",");
        for (String seat : seatList) {
            tickets.add(activityService.getTicketBySeat(activityId, seat));
        }
        orderId = orderService.buyTickets(null, tickets, totalPrice, totalPrice);
        if (orderId >= 0) {
            Orders orders = orderService.getOrder(orderId);
            OrderThread.addOrder(orders);
            Ticket ticket=tickets.get(0);
            Venue venue=ticket.getActivity().getVenue();
            orders.setStatus(1);
            if(accountService.updatePlatformMoney(null,venue,totalPrice)==true) {
                orderService.updateOrder(orders);
                OrderThread.removeOrder(orders);
                return orders.getId();
            }
            else
                return -1;
        } else
            return -1;
    }


}
