package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tickets.povo.VenueVO;
import tickets.service.VenueService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/venue")
public class VenueViewController {
    @Autowired
    VenueService venueService;

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("login");
        return new ModelAndView("redirect:/venue/login");
    }

    @RequestMapping(value = "/login",method= RequestMethod.GET)
    public ModelAndView showLoginPage(HttpSession session) {
        return new ModelAndView("/venue/loginPage");
    }

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public ModelAndView showRegisterPage(){
        return new ModelAndView("/venue/registerPage");
    }

    @RequestMapping(value="/info",method = RequestMethod.GET)
    public ModelAndView showVenueInfoPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/venueInfoPage")):model;
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public ModelAndView showVenueModifyPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/modifyInfoPage")):model;
    }

    @RequestMapping(value = "/releaseActivity")
    public ModelAndView showReleaseActivityPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/releaseActivityPage")):model;
    }

    @RequestMapping(value = "/checkTickets")
    public ModelAndView showCheckTicketPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/checkTicketPage")):model;
    }

    @RequestMapping(value = "/selectSeat")
    public ModelAndView showSelectSeatPage(HttpSession session,int activityId){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/selectSeatPage")):model;
    }

    @RequestMapping(value = "/book")
    public ModelAndView showBookPage(HttpSession session,int activityId,String seatNo,double seatPrice){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/bookPage")):model;
    }

    @RequestMapping(value = "/activityList")
    public ModelAndView showActivityListPage(HttpSession session){
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/venue/activityListPage")):model;
    }

    @RequestMapping(value="/analyzeBook")
    public ModelAndView showAnalyzeBookPage(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?( new ModelAndView("/venue/analyzeBookBillPage")):model;
    }

    @RequestMapping(value="/analyzePay")
    public ModelAndView showAnalyzePayPage(HttpSession session){
        ModelAndView model=checkRole(session);
        return model==null?( new ModelAndView("/venue/analyzePayBillPage")):model;
    }


    public ModelAndView checkRole(HttpSession session){
        if(session.getAttribute("login")==null)
            return new ModelAndView("redirect:/venue/login");
        int id=(Integer)session.getAttribute("login");
        if(id<0)
            return new ModelAndView("redirect:/venue/login");
        VenueVO venue=venueService.getVenueVO(id);
        if(venue==null){
            return new ModelAndView("redirect:/venue/login");
        }else{
            return null;
        }
    }
}
