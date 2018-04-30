package tickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.model.Activity;
import tickets.model.PlatformAccount;
import tickets.model.Ticket;
import tickets.model.Venue;
import tickets.povo.ActivityVO;
import tickets.povo.DataVO;
import tickets.povo.UserInfoVO;
import tickets.povo.VenueVO;
import tickets.service.AccountService;
import tickets.service.ActivityService;
import tickets.service.OrderService;
import tickets.service.VenueService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/data/venue")
@ResponseBody
public class VenueDataController {
    @Autowired
    OrderService orderService;
    @Autowired
    VenueService venueService;
    @Autowired
    AccountService accountService;
    @Autowired
    ActivityService activityService;

    @RequestMapping(value = "/getVenue")
    public VenueVO getVenueVO(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        VenueVO venueVO=venueService.getVenueVO(id);
        return venueVO;
    }

    @RequestMapping(value = "/getVenueById")
    public VenueVO getSeatMapById(String activityId){
        int activityid=Integer.parseInt(activityId);
        Activity activity=activityService.getActivity(activityid);
        Venue venue=activity.getVenue();
        VenueVO venueVO=new VenueVO(venue);
        return venueVO;
    }

    @RequestMapping(value = "/getPre")
    public List<Ticket> getPreTickets(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        List<Ticket>tickets=orderService.getPreTicketsByVenue(id);
        return tickets;
    }

    @RequestMapping(value = "/getCancel")
    public List<Ticket> getCancelTickets(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        List<Ticket>tickets=orderService.getCancelTicketsByVenue(id);
        return tickets;
    }

    @RequestMapping(value = "/getUnavailable")
    public List<Ticket> getUnavailableTickets(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        List<Ticket>tickets=activityService.getUnavailableTickets(id);
        return tickets;
    }

    @RequestMapping(value = "/getFinanace")
    public List<PlatformAccount> getFinance(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        List<PlatformAccount>accounts=accountService.getPlatformAccount(id);
        return accounts;
    }

    @RequestMapping(value = "/getRate")
    public Map<String,Double> getRate(HttpSession session){
        Map<String,Double> rateMap=new HashMap<String, Double>();
        int id=(Integer)session.getAttribute("login");
        List<Ticket>tickets=venueService.getTicketByVenue(id);
        int size=tickets.size();
        int occupancyCounter=0;
        int validCounter=0;
        for(int i=0;i<size;i++){
            Ticket ticket=tickets.get(i);
            if(ticket.getIsSeated()==1)
                occupancyCounter++;
            if(ticket.getStatus()==1)
                validCounter++;
        }
        rateMap.put("occupancyRate",occupancyCounter*1.0/size);
        rateMap.put("validRate",validCounter*1.0/size);
        return rateMap;
    }

    @RequestMapping(value = "/getActivityList")
    public List<ActivityVO> getActivities(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        List<ActivityVO>activities=activityService.getActivitiesByVenue(id);
        List<ActivityVO>result=new ArrayList<>();
        for(ActivityVO activityVO:activities){
            if(activityVO.getTime().after(new Timestamp(new Date().getTime())))
                result.add(activityVO);
        }
        return result;
    }

    @RequestMapping(value = "/getSeatedBookRate")
    public List<DataVO> getSeatedBookRate(HttpSession session) {
        int id=(Integer)session.getAttribute("login");
        return venueService.getSeatedBookRate(id);
    }

    @RequestMapping(value = "/getAllBookNum")
    public List<DataVO> getAllBookNumByWeek(HttpSession session) {
        int id=(Integer)session.getAttribute("login");
        return venueService.getAllBookNum(id);
    }

    @RequestMapping(value = "/getAllBookNum/activityType")
    public List<DataVO> getNotCancelledBookNumByRoomType(HttpSession session) {
        int id=(Integer)session.getAttribute("login");
        return venueService.getNotCancelledBookNumByActivityType(id);
    }

    @RequestMapping(value = "/getMoneyVipRate")
    public List<Object[]> getMoneyVipRateByMonth(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        return venueService.getMoneyVipRate(id);
    }

    @RequestMapping(value = "/getIncomeAvg")
    public List<DataVO> getIncomeAvgByYear(HttpSession session){
        int id=(Integer)session.getAttribute("login");
        return venueService.getIncomeAvg(id);
    }




}
