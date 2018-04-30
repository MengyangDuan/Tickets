package tickets.util;

import tickets.model.Orders;
import tickets.service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

class MyThread extends Thread{

    public OrderService orderService;

    public MyThread(OrderService orderService){
        this.orderService=orderService;
    }

    public MyThread(){

    }


    public void run(){
        HashMap<String,Object> map=new HashMap<String,Object>();
        Iterator<Orders> it =  OrderThread.getOrders().iterator();
        while(it.hasNext()){
            Orders ord=it.next();
            System.out.println("等待支付……");
            if((new Date().getTime()-ord.getOrderTime().getTime())>=300*600){
                map.put("orderId", ord.getId());
                orderService.payFailed(ord.getId());
                it.remove();
                System.out.println("支付失败");
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
