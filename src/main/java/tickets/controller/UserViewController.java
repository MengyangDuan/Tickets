package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tickets.model.UserInfo;
import tickets.service.UserService;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserViewController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("login");
        return new ModelAndView("redirect:/user/login");
    }

    @RequestMapping(value = "/login",method= RequestMethod.GET)
    public ModelAndView showLoginPage(HttpSession session) {
        return new ModelAndView("/user/loginPage");
    }

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public ModelAndView showRegisterPage(){
        return new ModelAndView("/user/registerPage");
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView showIndexPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/indexPage")):model;
    }

    @RequestMapping(value="/modifyPassword",method = RequestMethod.GET)
    public ModelAndView showUserPasswordPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/modifyPasswordPage")):model;
    }

    @RequestMapping(value="/activeServlet",method = RequestMethod.GET)
    public ModelAndView showActivePage(HttpSession session,String code){
        if(userService.activeUser(code)==true)
            return new ModelAndView("/user/loginPage");      //应该返回激活成功
        else
            return new ModelAndView("/404");
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public ModelAndView userInfoPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/modifyInfoPage")):model;
    }

    @RequestMapping(value = "/userAccount")
    public ModelAndView showUserAccountPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/userAccountPage")):model;
    }

    @RequestMapping(value = "/exchangeCoupon")
    public ModelAndView exChangeConponPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/exchangeCouponPage")):model;
    }

    @RequestMapping(value = "/payOrder")
    public ModelAndView showPayView(HttpSession session,int orderId,double money){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/payOrderPage")):model;
    }

    @RequestMapping(value = "/OrderList")
    public ModelAndView showOrderList(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/orderListPage")):model;
    }

    @RequestMapping(value = "/OrderInfo")
    public ModelAndView getOrderInfo(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/orderInfoPage")):model;
    }

    @RequestMapping(value = "/selectSeat")
    public ModelAndView showSelectSeatPage(HttpSession session,int activityId){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/user/selectSeatPage")):model;
    }

    @RequestMapping(value = "/activityList")
    public ModelAndView showActivityListPage(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?( new ModelAndView("/user/activityListPage")):model;
    }

    @RequestMapping(value = "/activityInfo")
    public ModelAndView showActivityInfoPage(HttpSession session,int activityId){
        ModelAndView model=checkRole(session);
        return model==null?( new ModelAndView("/user/activityDetailPage")):model;
    }

    @RequestMapping(value = "/book",method = RequestMethod.GET)
    public ModelAndView showBookPage(HttpSession session,int activityId,String seatNo,double seatPrice){
        ModelAndView modelAndView=new ModelAndView("/user/bookPage");
        return modelAndView;
    }



    public ModelAndView checkRole(HttpSession session){
        if(session.getAttribute("login")==null)
            return new ModelAndView("redirect:/user/login");
        int id=(Integer)session.getAttribute("login");
        if(id>0){
            UserInfo userInfo=userService.getUserInfo(id);
            if(userInfo!=null)
                return null;
            else
                return new ModelAndView("redirect:/user/login");
        }else{
            return new ModelAndView("redirect:/user/login");
        }
    }
}
