package tickets.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "platformAccount")
public class PlatformAccount {
    private Venue venue;
    private UserInfo userInfo;
    private int accountId;
    private Timestamp timestamp;
    private double money;
    private int status;        //0表示未处理，1表示已处理

    @ManyToOne(cascade = CascadeType.REMOVE,optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id")
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @ManyToOne(cascade = CascadeType.REMOVE,optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfo_id")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Id
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




}
