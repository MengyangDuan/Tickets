package tickets.povo;

import tickets.model.Orders;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class OrderVO {
    private int id;
    private int userId;
    private Set<TicketVO> tickets = new HashSet<TicketVO>();
    private int status;     //0表示未付款，1表示已付款，2表示未及时支付取消订单，3表示退订，4表示配票不成功取消，5表示已消费
    private Timestamp orderTime;
    private double totalPrice;
    private double afterDiscountPrice;
    private int seatType;  //O表示不选座购买，1表示选座购买
    private int matchingStatus;    //配票状态：-1表示不需要配票，0表示等待配票，1表示配票成功，2表示配票失败
    private String selectRequire;
    private int activityId;
    private String activityName;
    private int venueId;
    private String venueName;
    private Timestamp activityTime;
    private String venueAddress;

    public OrderVO(){

    }
    public OrderVO(Orders orders){
        this.id=orders.getId();
        this.status=orders.getStatus();
        this.orderTime=orders.getOrderTime();
        this.totalPrice=orders.getTotalPrice();
        this.afterDiscountPrice=orders.getAfterDiscountPrice();
        this.seatType=orders.getSeatType();
        this.matchingStatus=orders.getMatchingStatus();
        this.selectRequire=orders.getSelectRequire();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<TicketVO> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketVO> tickets) {
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Timestamp getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Timestamp activityTime) {
        this.activityTime = activityTime;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }



}
