package tickets.povo;

import tickets.model.Coupon;

import java.util.List;

public class LevelVO {
    private int level;
    private int points;
    private List<Coupon> coupons;

    public LevelVO(int level, int points, List<Coupon>coupons){
     this.level=level;
     this.points=points;
     this.coupons=coupons;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

}
