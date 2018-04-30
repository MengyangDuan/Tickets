package tickets.dao;

import tickets.model.Coupon;
import tickets.model.UserAccount;
import tickets.model.UserInfo;
import tickets.model.UserLevel;

import java.util.List;

public interface UserDao {
    Boolean save(UserInfo userInfo);

    Boolean activeUser(String code);

    int checkUser(String userName, String password);

    UserInfo find(int id);

    Boolean updateUserInfo(UserInfo userInfo);

    Boolean delUser(int id);

    List<Coupon>getCoupons(int userId);

    Boolean saveLevel(UserLevel userLevel);

    UserAccount checkAccount(int accountId, String password);

    List<Coupon> getAvailableCoupons(int points);

    Coupon getCoupon(int id);

    Boolean useCoupon(int userId, int couponId);

    List<UserInfo>getAll();



}
