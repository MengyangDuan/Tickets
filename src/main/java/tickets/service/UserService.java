package tickets.service;


import net.sf.json.JSONObject;
import tickets.model.UserAccount;
import tickets.model.UserInfo;
import tickets.povo.CouponVO;
import tickets.povo.UserInfoVO;

import java.util.List;

public interface UserService {
    Boolean register(String userName,String password,String email);

    Boolean activeUser(String code);


    /**
     *
     * @param userName
     * @param password
     * @return 返回用户id,如果返回值小于0，说明用户不存在
     */
    int checkUser(String userName, String password);

    Boolean updateUserInfo(UserInfo userInfo);

    Boolean delUser(int id);

    List<CouponVO> getAvailableCoupons(int id);

    Boolean creditExchange(int userId, int couponId);

    Boolean addAccount(int id, int accountId, String password);

    Boolean delAccount(int id, UserAccount userAccount);

    UserInfoVO getUserInfoVO(int id,double totalMoney);

    UserInfo getUserInfo(int id);

    int useCoupon(int userId, int couponId);

    JSONObject getVipNumByLevel();


}
