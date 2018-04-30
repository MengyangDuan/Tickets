package tickets.util;

import tickets.model.Activity;
import tickets.model.Orders;
import tickets.model.UserInfo;
import tickets.model.Venue;
import tickets.service.AccountService;
import tickets.service.ActivityService;
import tickets.service.OrderService;

import java.util.Date;
import java.util.Iterator;

public class MatchingTicketsThread extends Thread{
    public OrderService orderService;
    public ActivityService activityService;
    public AccountService accountService;


    public MatchingTicketsThread(OrderService orderService,ActivityService activityService,AccountService accountService) {
        this.orderService=orderService;
        this.activityService=activityService;
        this.accountService=accountService;
    }
    public MatchingTicketsThread() {

    }

    @Override
    public void run() {
        Iterator<Orders> it =  OrderThread.getMatchingOrders().iterator();
        while(it.hasNext()){
            Orders ord=it.next();
            int activityId=Integer.parseInt(ord.getSelectRequire().split(",")[0]);
            Activity activity=activityService.getActivity(activityId);
            if((activity.getTime().getTime()-new Date().getTime())<14*24*60*60*1000){
                it.remove();
                Boolean result=activityService.matchingTickets(ord);
                if(result==false) {
                    int id = ord.getId();
                    double price = ord.getAfterDiscountPrice();
                    UserInfo userInfo = orderService.getUserByOrder(id);
                    Venue venue = orderService.getVenueByOrder(id);
                    accountService.updateMoney(userInfo.getId(), price);
                    accountService.updatePlatformMoney(userInfo, venue, 0 - price);
                }

            }
        }
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run();
    }
}
