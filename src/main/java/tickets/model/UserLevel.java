package tickets.model;

import javax.persistence.*;


@Entity
@Table(name = "userLevel")
public class UserLevel {
    private int id;
    private UserInfo userInfo;
    private Coupon coupon;
    private int couponState;

    public UserLevel(){

    }

    public UserLevel(UserInfo userInfo,Coupon coupon){
        this.userInfo=userInfo;
        this.coupon=coupon;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "couponId")
    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public int getCouponState() {
        return couponState;
    }

    public void setCouponState(int couponState) {
        this.couponState = couponState;
    }
}
