package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tickets.model.PlatformAccount;
import tickets.povo.PlatformAccountVO;
import tickets.service.AccountService;
import tickets.service.ManagerService;
import tickets.service.VenueService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/boss")
public class ManagerCommitController {
    @Autowired
    ManagerService managerService;
    @Autowired
    AccountService accountService;
    @Autowired
    VenueService venueService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(HttpSession session, int id, String password){
        Boolean result=managerService.Login(id, password);
        if(result==false){
            return new ModelAndView("redirect:/boss/login");
        }else {//验证通过
            session.setAttribute("login",id);
            return new ModelAndView("redirect:/boss/checkVenue");
        }
    }

    @RequestMapping(value = "/checkNewVenue", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleCheckVenueRequest(int venueId, Boolean result){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Boolean updateResult=venueService.updateRegisterState(venueId,result);
        if(updateResult==true) {
            resultMap.put("result", "success");
            managerService.sendEmail(true,venueService.getVenueVO(venueId).getEmail(), venueId);
        }
        else {
            resultMap.put("result", "error");
            managerService.sendEmail(false,venueService.getVenueVO(venueId).getEmail(), venueId);
        }
        return resultMap;
    }

    @RequestMapping(value = "/checkModifyVenue", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleCheckModifyRequest(int modifyid,Boolean result,String email){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Boolean updateResult=venueService.updateModifyState(modifyid,result);

        if(updateResult==true) {
            resultMap.put("result", "success");
            managerService.sendModifyEmail(true, email);
        }
        else {
            resultMap.put("result", "error");
            managerService.sendModifyEmail(false,email);
        }
        return resultMap;
    }

    @RequestMapping(value = "/settleAccount",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleSettleAccountRequest(){
        Boolean result=true;
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<PlatformAccountVO>platformAccountVOS=accountService.getUnhandledAccount();
        for(int i=0;i<platformAccountVOS.size();i++){
            result=accountService.settleAccount(platformAccountVOS.get(i).getId());
        }
        if(result==true)
            resultMap.put("result", "success");
        else
            resultMap.put("result", "error");
        return resultMap;
    }

}
