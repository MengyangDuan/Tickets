package tickets.dao.Impl;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.BaseDao;
import tickets.dao.UserDao;
import tickets.model.Coupon;
import tickets.model.UserAccount;
import tickets.model.UserInfo;
import tickets.model.UserLevel;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    BaseDao baseDao;

    public Boolean save(UserInfo userInfo) {
        return baseDao.saveNoId(userInfo);
    }

    public Boolean activeUser(String code) {
        String hql="SELECT user FROM UserInfo as user WHERE user.activeCode= '"+code+"'";
        List<UserInfo> users=baseDao.getByHql(UserInfo.class,hql);
        if(users.size()==0)
            return false;
        else{
            UserInfo userInfo=users.get(0);
            userInfo.setUserState(1);
            baseDao.update(userInfo);
            return true;
        }
    }

    public int checkUser(String userName, String password) {
        String hql="SELECT user FROM UserInfo as user WHERE user.userName= '"+userName+"' and user.password= '"+password+"'";
        List<UserInfo> users=baseDao.getByHql(UserInfo.class,hql);
        if(users.size()==0)
            return -1;
        UserInfo userInfo=users.get(0);
        if(userInfo.getUserState()==1)
            return userInfo.getId();
        else
            return -1;

    }

    public UserInfo find(int id){
        return baseDao.getEntity(UserInfo.class,id);
    }

    public Boolean updateUserInfo(UserInfo userInfo) {
        return baseDao.saveOrUpdate(userInfo);
    }

    public Boolean delUser(int id) {
        UserInfo userInfo=this.find(id);
        userInfo.setUserState(2);
        return baseDao.update(userInfo);
    }

    public List<Coupon> getCoupons(int id) {
        String hql="SELECT level FROM UserLevel as level left join level.userInfo userInfo WHERE userInfo.id= '"+id+"' and level.couponState=0";
        List<UserLevel> levels=baseDao.getByHql(UserLevel.class,hql);
        List<Coupon> coupons=new ArrayList<Coupon>();
        for(int i=0;i<levels.size();i++)
            coupons.add(levels.get(i).getCoupon());
        return coupons;
    }

    public Boolean saveLevel(UserLevel userLevel) {
        return baseDao.saveNoId(userLevel);
    }

    public UserAccount checkAccount(int accountId, String password) {
        String hql="SELECT account FROM UserAccount as account WHERE account.accountId= '"+accountId+"' and account.password= '"+password+"'";
        List<UserAccount> accounts=baseDao.getByHql(UserAccount.class,hql);
        if(accounts.size()==0)
            return null;
        else
            return accounts.get(0);
    }

    public List<Coupon> getAvailableCoupons(int points) {
        String hql="SELECT coupon FROM Coupon as coupon WHERE coupon.rewardPoints<="+points;
        List<Coupon> coupons=baseDao.getByHql(Coupon.class,hql);
        return coupons;
    }

    public Coupon getCoupon(int id) {
        Coupon coupon=baseDao.getEntity(Coupon.class,id);
        return coupon;
    }

    public Boolean useCoupon(int userId, int couponId) {
        String hql="SELECT level FROM UserLevel as level left join level.userInfo userinfo left join level.coupon coupon WHERE userinfo.id= '"+userId+"' and coupon.id= '"+couponId+"'and level.couponState=0";
        List<UserLevel> userLevels=baseDao.getByHql(UserLevel.class,hql);
        if(userLevels.size()==0)
            return false;
        else{
            UserLevel userLevel=userLevels.get(0);
            userLevel.setCouponState(1);
            return baseDao.update(userLevel);
        }
    }

    @Override
    public List<UserInfo> getAll() {
        return baseDao.getAll(UserInfo.class);
    }
}
