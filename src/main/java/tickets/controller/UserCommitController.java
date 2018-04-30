package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tickets.model.*;
import tickets.service.AccountService;
import tickets.service.ActivityService;
import tickets.service.OrderService;
import tickets.service.UserService;
import tickets.util.OrderThread;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserCommitController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    AccountService accountService;
    @Autowired
    ActivityService activityService;


    @RequestMapping(value = "/cancel")
    @ResponseBody
    public Map<String,Object> delUser(HttpSession session){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        if(userService.delUser(id)==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(HttpSession session, String userName,String password){
        int userId=userService.checkUser(userName,password);
        if(userId<0){
            return new ModelAndView("redirect:/user/login");
        }else {//验证通过
            session.setAttribute("login",userId);
            return new ModelAndView("redirect:/user/modify");
        }
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)     //应该针对不同情路昂给出不同提示信息
    @ResponseBody
    public Map<String,Object> handleRegisterRequest(String userName, String password, String email){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Boolean result=userService.register(userName,password,email);
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value="/modify",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleModifyInfoRequest(HttpSession session, String username, String email){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        UserInfo userInfo=userService.getUserInfo(id);
        userInfo.setUserName(username);
        userInfo.setEmail(email);
        Boolean result=userService.updateUserInfo(userInfo);
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value="/modifyPassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleModifyPasswordRequest(HttpSession session,String oldpass,String newpass){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        UserInfo userInfo=userService.getUserInfo(id);
        if(userInfo.getPassword().equals(oldpass)) {
            userInfo.setPassword(newpass);
            Boolean result = userService.updateUserInfo(userInfo);
            if (result == true)
                resultMap.put("result", "success");
            else
                resultMap.put("result", "error");
        }
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value="exchangeCoupon",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleExchangeCouponRequest(HttpSession session, int couponId){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        Boolean result=userService.creditExchange(id,couponId);
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

    @RequestMapping(value = "/bookSeat", method = RequestMethod.POST)
    @ResponseBody
    public int handleBookBySeatRequest(HttpSession session, Integer activityId,String seatNo,double totalPrice, double afterDiscountPirce){
        int userId=(Integer)session.getAttribute("login");
        UserInfo userInfo=userService.getUserInfo(userId);
        List<Ticket>tickets=new ArrayList<Ticket>();
        int result;
        Boolean selected=true;
        if(seatNo.indexOf("_")!=-1){               //未选座购买
            result=orderService.setOrderWithNoTicket(activityId,seatNo,userInfo,totalPrice,afterDiscountPirce);
            selected=false;
        }
        else {                                     //选座购买
            String[] seatList = seatNo.split(",");
            for (String seat : seatList) {
                tickets.add(activityService.getTicketBySeat(activityId, seat));
            }
            result = orderService.buyTickets(userInfo, tickets, totalPrice, afterDiscountPirce);
        }
        if (result >= 0) {
            Orders order = orderService.getOrder(result);
            OrderThread.addOrder(order);
            if(selected==false)
                OrderThread.addMatchingOrder(order);
            return result;
        } else
            return -1;
    }

    @RequestMapping(value="payOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>handlePayOrderRequest(HttpSession session, int orderId, String accountId, String password,String money){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        double payMoney=Double.parseDouble(money);
        if(accountService.checkPassword(Integer.parseInt(accountId),password)==false){
            resultMap.put("result","error");
        }
        else{
            if(accountService.checkBalance(Integer.parseInt(accountId),payMoney)==false)
                resultMap.put("result","error");
            else {
                double updatePrice = 0 - payMoney;
                UserInfo userInfo = userService.getUserInfo(id);
                List<Ticket> tickets = orderService.getTicketByOrder(orderId);
                Venue venue = null;
                Orders orders=orderService.getOrder(orderId);
                if(tickets.size()>0){
                    Ticket ticket = tickets.get(0);
                    venue = ticket.getActivity().getVenue();
                }
                else{
                   int activityId=Integer.parseInt(orders.getSelectRequire().split(",")[0]);
                   venue=activityService.getActivity(activityId).getVenue();
                }
                orders.setStatus(1);
                if(accountService.updateMoney(id,updatePrice)&&accountService.updatePlatformMoney(userInfo,venue,payMoney)==true) {
                    orderService.updateOrder(orders);
                    OrderThread.removeOrder(orders);
                    resultMap.put("result", "success");
                }
                else
                    resultMap.put("result", "error");
            }
        }
        return resultMap;
    }


    @RequestMapping(value="useCoupon",method = RequestMethod.POST)
    @ResponseBody
    public String handleUseCouponRequest(HttpSession session, String couponId){
        int id=(Integer)session.getAttribute("login");
        int couponid=Integer.parseInt(couponId);
        int money=userService.useCoupon(id,couponid);
        return String.valueOf(money);
    }

    @RequestMapping(value="cancelOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleCancelOrderRequest(HttpSession session, int orderId){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        int id=(Integer)session.getAttribute("login");
        UserInfo userInfo=userService.getUserInfo(id);
        double refunds=orderService.cancelTicketAndCalculateRefunds(orderId);
        Venue venue=orderService.getVenueByOrder(orderId);
        if(accountService.updatePlatformMoney(userInfo,venue,0-refunds)) {
            if(accountService.updateMoney(id,refunds))
                resultMap.put("result", "success");
            else
                resultMap.put("result", "error");
        }
        else
            resultMap.put("result", "error");
        return resultMap;
    }






}
