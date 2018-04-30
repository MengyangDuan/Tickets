package tickets.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.povo.PlatformAccountVO;
import tickets.povo.VenueModifyVO;
import tickets.povo.VenueVO;
import tickets.service.AccountService;
import tickets.service.OrderService;
import tickets.service.UserService;
import tickets.service.VenueService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/data/boss")
@ResponseBody
public class ManagerDataController {

    @Autowired
    VenueService venueService;
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/getNonCheckedVenues")
    public List<VenueVO>getNonCheckedVenues(){
        return venueService.getNonCheckedVenues();
    }

    @RequestMapping(value = "/getNonCheckedModifies")
    public List<VenueModifyVO> getUnhandledModifies(){
        List<VenueModifyVO>venueModifyVOS=venueService.getNonCheckedModifies();
        return venueModifyVOS;
    }

    @RequestMapping(value = "/getUnhandledAccount")
    public List getUnhandledAccount(){
        List<PlatformAccountVO>platformAccounts=accountService.getUnhandledAccount();
        return platformAccounts;
    }

    @RequestMapping(value = "/getEarningByVenue")
    public Map<String,Double> getEarningByVenue(){
        return accountService.getEarningByVenue();
    }

    @RequestMapping(value = "/getEarningBuUser")
    public Map<Integer,Double> getEarningByUser(){
        return accountService.getEarningByUser();
    }

    @RequestMapping
    public Map<Integer,Double>getPlatformAccountByMonth(){
        return accountService.getPlatformEarningByMonth();
    }

    @RequestMapping(value = "/getVipNum/level")
    public JSONObject getVipNumByLevel(){
        return userService.getVipNumByLevel();
    }

    @RequestMapping(value = "/getGuestNum/month")
    public JSONObject getGuestLiveNumByMonth(){return orderService.getGuestLiveNumByMonth();}

    @RequestMapping(value = "/getSummaryNumOfAllHostels")
    public JSONObject getSummaryNumOfAllHostels() {
        return venueService.getSummaryNumOfAllVenues();
    }
}
