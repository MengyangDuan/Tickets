package tickets.service.bean;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.OrderDao;
import tickets.dao.UserDao;
import tickets.model.Coupon;
import tickets.model.UserAccount;
import tickets.model.UserInfo;
import tickets.model.UserLevel;
import tickets.povo.CouponVO;
import tickets.povo.UserInfoVO;
import tickets.service.OrderService;
import tickets.service.UserService;
import tickets.util.LevelUtil;
import tickets.util.MailUtil;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Transactional
@Service
public class UserServiceBean implements UserService{
    @Autowired
    UserDao userDao;
    @Autowired
    OrderService orderService;



    public Boolean register(String userName,String password,String email) {
        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
            return false;
        }
        //生成激活码
        String code= UUID.randomUUID().toString().replaceAll("-", "");
        UserInfo userInfo=new UserInfo(userName,password,email,code);
        //将用户保存到数据库
        //保存成功则通过线程的方式给用户发送一封邮件
        if(userDao.save(userInfo)){
            new Thread(new MailUtil(email, code)).start();
            return true;
        }
        return false;
    }

    public Boolean activeUser(String code) {
        return userDao.activeUser(code);
    }

    public int checkUser(String userName, String password) {
        return userDao.checkUser(userName,password);
    }

    public Boolean updateUserInfo(UserInfo userInfo) {
        return userDao.updateUserInfo(userInfo);
    }

    public Boolean delUser(int id) {
        return userDao.delUser(id);
    }


    public List<CouponVO> getAvailableCoupons(int id) {
        int points=userDao.find(id).getRewardPoints();
        List<Coupon>list=userDao.getAvailableCoupons(points);
        List<CouponVO>result=new ArrayList<CouponVO>();
        for(int i=0;i<list.size();i++){
            result.add(new CouponVO(list.get(i)));
        }
        return result;
    }

    public Boolean creditExchange(int userId, int couponId) {
        UserInfo userInfo=userDao.find(userId);
        int points=userInfo.getRewardPoints();
        Coupon coupon=userDao.getCoupon(couponId);
        int rewardPoints=coupon.getRewardPoints();
        int afterPoints=points-rewardPoints;
        userInfo.setRewardPoints(afterPoints);
        Boolean updateUser=this.updateUserInfo(userInfo);
        if(updateUser==true) {
            UserLevel userLevel = new UserLevel(userInfo, coupon);
            return userDao.saveLevel(userLevel);
        }
        else
            return false;
    }

    public Boolean addAccount(int id,int accountId,String password){
        UserInfo userInfo=userDao.find(id);
        UserAccount userAccount=userDao.checkAccount(accountId,password);
        if(userAccount!=null) {
            userInfo.addUserAccount(userAccount);
            return userDao.updateUserInfo(userInfo);
        }
        else
            return false;
    }

    public Boolean delAccount(int id,UserAccount userAccount){
        UserInfo userInfo=userDao.find(id);
        userInfo.delUserAccount(userAccount);
        return userDao.updateUserInfo(userInfo);
    }

    public UserInfoVO getUserInfoVO(int id,double totalMoney) {
        UserInfo userInfo=userDao.find(id);
        List<Coupon>coupons=userDao.getCoupons(id);
        UserInfoVO userInfoVO=InfoToVO(userInfo,totalMoney,coupons);
        return userInfoVO;
    }

    private UserInfoVO InfoToVO(UserInfo userInfo,double totalMoney,List<Coupon>coupons){
        int id=userInfo.getId();
        UserInfoVO userInfoVO=new UserInfoVO();
        userInfoVO.setUserName(userInfo.getUserName());
        userInfoVO.setEmail(userInfo.getEmail());
        userInfoVO.setPassword(userInfo.getPassword());
        userInfoVO.setId(id);
        userInfoVO.setRewardPoints(userInfo.getRewardPoints());
        userInfoVO.setLevel( LevelUtil.getLevel(totalMoney));
        List<CouponVO>couponVOS=new ArrayList<CouponVO>();
        for(Coupon coupon:coupons){
            couponVOS.add(new CouponVO(coupon));
        }
        userInfoVO.setCoupons(couponVOS);
        return userInfoVO;
    }


    public UserInfo getUserInfo(int id) {
        return userDao.find(id);
    }

    public int useCoupon(int userId, int couponId) {
        Boolean result=userDao.useCoupon(userId, couponId);
        if(result==true){
            Coupon coupon=userDao.getCoupon(couponId);
            return coupon.getMoney();
        }
        else
            return -1;
    }

    @Override
    public JSONObject getVipNumByLevel() {
        JSONObject jsonObject=new JSONObject();
        List<UserInfo> userInfos=userDao.getAll();
        Map<String,Integer> map=LevelUtil.createVipLevelMap();
        for(UserInfo userInfo:userInfos){
            double totalMoney=orderService.getTotalMoney(userInfo.getId());
            String level=String.valueOf(getUserInfoVO(userInfo.getId(),totalMoney).getLevel());
            int num=map.get(level);
            map.put(level,++num);
        }
        jsonObject.put("total",userInfos.size());
        jsonObject.put("level",map);
        return jsonObject;
    }

}
