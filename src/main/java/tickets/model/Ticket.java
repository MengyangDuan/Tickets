package tickets.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    private int id;
    private Activity activity;
    private Orders orders;
    private double price;
    private String seat;
    private int status;   //0表示未购买，1表示已购买
    private int isSeated; //0表示未上座，1表示上座

    public Ticket(){

    }
    public Ticket(double price,String seat){
        this.price=price;
        this.seat=seat;
    }


    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.MERGE,optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "activityId")
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


    @ManyToOne(cascade = CascadeType.MERGE,optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getIsSeated() {
        return isSeated;
    }

    public void setIsSeated(int isSeated) {
        this.isSeated = isSeated;
    }

}
