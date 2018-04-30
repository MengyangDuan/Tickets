package tickets.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders implements Serializable{
    private int id;
    private UserInfo userInfo;
    private Set<Ticket> tickets = new HashSet<Ticket>();
    private int status;     //0表示未付款，1表示已付款，2表示未及时支付取消订单，3表示退订，4表示配票不成功取消，5表示已消费
    private Timestamp orderTime;
    private double totalPrice;
    private double afterDiscountPrice;
    private int seatType;  //O表示不选座购买，1表示选座购买
    private int matchingStatus;    //配票状态：-1表示不需要配票，0表示等待配票，1表示配票成功，2表示配票失败
    private String selectRequire;   //格式是第一个是activityId，后面为  票价_数字  中间用逗号隔开

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.REMOVE,optional = true)
    @JoinColumn(name = "userId")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @OneToMany(mappedBy = "orders",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAfterDiscountPrice() {
        return afterDiscountPrice;
    }

    public void setAfterDiscountPrice(double afterDiscountPrice) {
        this.afterDiscountPrice = afterDiscountPrice;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public int getMatchingStatus() {
        return matchingStatus;
    }

    public void setMatchingStatus(int matchingStatus) {
        this.matchingStatus = matchingStatus;
    }


    public String getSelectRequire() {
        return selectRequire;
    }

    public void setSelectRequire(String selectRequire) {
        this.selectRequire = selectRequire;
    }




}
