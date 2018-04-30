package tickets.povo;

import tickets.model.Coupon;

/**
 * 优惠卷
 */

public class CouponVO {
    private int id;
    private int rewardPoints;
    private int money;

    public CouponVO(Coupon coupon){
        this.id=coupon.getId();
        this.rewardPoints=coupon.getRewardPoints();
        this.money=coupon.getMoney();
    }


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
}
