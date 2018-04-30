package tickets.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tickets.model.Orders;
import tickets.service.AccountService;
import tickets.service.ActivityService;
import tickets.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component("DeleteInvalidOrder")
public class OrderThread implements ApplicationListener<ContextRefreshedEvent>{
    private static List<Orders> orders = new ArrayList<Orders>();
    private static List<Orders>matchingOrders=new ArrayList<Orders>();
    private MyThread myThread;
    private MatchingTicketsThread matchingTicketsThread;

    @Resource
    public OrderService orderService;
    @Resource
    public ActivityService activityService;
    @Resource
    public AccountService accountService;


    public static List<Orders> getOrders() {
        return orders;
    }

    public static void setOrders(List<Orders> order) {
        OrderThread.orders = order;
    }

    public static void addOrder(Orders order){
        orders.add(order);
    }
    public static void removeOrder(Orders order){
        for(Orders orders1:orders){
            if(orders1.getId()==order.getId()) {
                orders.remove(orders1);
                break;
            }
        }
        System.out.println("aaa"+orders.size());
    }


    public static List<Orders> getMatchingOrders() {
        return matchingOrders;
    }

    public static void setMatchingOrders(List<Orders> matchingOrders) {
        OrderThread.matchingOrders = matchingOrders;
    }

    public static void addMatchingOrder(Orders order){
        matchingOrders.add(order);
    }
    public static void removeMatchingOrder(Orders order){
        matchingOrders.remove(order);
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
// TODO Auto-generated method stub
        if(arg0.getApplicationContext().getParent()==null){
            if (myThread == null) {
                myThread = new MyThread(orderService);
                myThread.start(); // servlet 上下文初始化时启动 socket
            }
            if(matchingTicketsThread==null){
                matchingTicketsThread=new MatchingTicketsThread(orderService,activityService,accountService);
                matchingTicketsThread.start();
            }
        }else{
        }
    }

}
