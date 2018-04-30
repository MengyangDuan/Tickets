package tickets.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 优惠卷
 */
@Entity
@Table(name = "coupon")
public class Coupon {
    private int id;
    private int rewardPoints;
    private int money;
    private Set<UserInfo> userInfo=new HashSet<UserInfo>();

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "userLevel",
            joinColumns =
                    @JoinColumn(name = "couponId",referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "userId",referencedColumnName = "id")
    )
    public Set<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Set<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }
}
