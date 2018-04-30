package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tickets.model.Manager;
import tickets.service.ManagerService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/boss")
public class ManagerViewController {
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("login");
        return new ModelAndView("redirect:/boss/login");
    }

    @RequestMapping(value = "/login",method= RequestMethod.GET)
    public ModelAndView showLoginPage(HttpSession session) {
        return new ModelAndView("/boss/loginPage");
    }

    @RequestMapping(value = "/checkVenue",method= RequestMethod.GET)
    public ModelAndView showCheckVenuePage(HttpSession session) {
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/boss/venueOpenPage")):model;
    }

    @RequestMapping(value = "/checkModify",method= RequestMethod.GET)
    public ModelAndView showCheckModifyPage(HttpSession session) {
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/boss/venueModifyPage")):model;
    }

    @RequestMapping(value = "/settleAccount",method= RequestMethod.GET)
    public ModelAndView showSettleAccountPage(HttpSession session) {
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/boss/countPage")):model;
    }

    @RequestMapping(value = "/analyzeVenue",method= RequestMethod.GET)
    public ModelAndView showAnalyzeVenuePage(HttpSession session) {
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/boss/analyseVenuesPage")):model;
    }

    @RequestMapping(value = "/analyzeVip",method= RequestMethod.GET)
    public ModelAndView showAnalyzeVipPage(HttpSession session) {
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/boss/analyseVipsPage")):model;
    }

    @RequestMapping(value = "/analyzeCompany",method= RequestMethod.GET)
    public ModelAndView showAnalyzeCompoanyPage(HttpSession session) {
        ModelAndView model=checkRole(session);         return model==null?( new ModelAndView("/boss/analyseCompanyPage")):model;
    }

    public ModelAndView checkRole(HttpSession session){
        if(session.getAttribute("login")==null)
            return new ModelAndView("redirect:/boss/login");
        int id=(Integer)session.getAttribute("login");
        if(id<0)
            return new ModelAndView("redirect:/boss/login");
        Manager manager=managerService.getManager(id);
        if(manager==null){
            return new ModelAndView("redirect:/boss/login");
        }else{
            return null;
        }
    }
}
