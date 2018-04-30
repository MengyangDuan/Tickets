package tickets.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.AccountDao;
import tickets.dao.BaseDao;
import tickets.model.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

    @Autowired
    BaseDao baseDao;


    public UserAccount getUserAccount(int accountId) {
        return baseDao.getEntity(UserAccount.class,accountId);
    }

    public Boolean checkPassword(int accountId, String password) {
        String hql="SELECT account FROM UserAccount as account WHERE account.accountId= '"+accountId+"' and account.password= '"+password+"'";
        List<UserAccount> accounts=baseDao.getByHql(UserAccount.class,hql);
        if(accounts.size()==0)
            return false;
        else
            return true;
    }

    public Boolean updateMoney(int accountId, double price) {
        UserAccount userAccount=baseDao.getEntity(UserAccount.class,accountId);
        double balance=userAccount.getBalance()+price;
        userAccount.setBalance(balance);
        return baseDao.update(userAccount);
    }

    public double getMoney(int accountId) {
        UserAccount userAccount=baseDao.getEntity(UserAccount.class,accountId);
        return userAccount.getBalance();
    }

    public int getAccountId(int userid) {
        String hql="SELECT account FROM UserAccount as account left join fetch account.userInfo userinfo WHERE userinfo.id= "+userid;
        List<UserAccount> accounts=baseDao.getByHql(UserAccount.class,hql);
        if(accounts.size()==0)
            return -1;
        UserAccount userAccount=accounts.get(0);
        return userAccount.getAccountId();
    }

    public Boolean savePlatformMoney(PlatformAccount platformAccount) {
        return baseDao.saveNoId(platformAccount);
    }

    public Boolean updateVenue(Venue venue) {
        return baseDao.update(venue);
    }

    public Boolean updateUserInfo(UserInfo userInfo) {
        return baseDao.update(userInfo);
    }

    public List<PlatformAccount> getPlatformAccount(int venueId) {
        String hql="SELECT account FROM PlatformAccount as account left join account.venue venue WHERE venue.id= '"+venueId+"'";
        List<PlatformAccount> accounts=baseDao.getByHql(PlatformAccount.class,hql);
        return accounts;
    }

    @Override
    public Boolean updatePlatformAccount(PlatformAccount platformAccount) {
        return baseDao.update(platformAccount);
    }

    public List<PlatformAccount> getUnhandledAccount() {
        String hql="SELECT account FROM PlatformAccount as account WHERE account.status= 0";
        List<PlatformAccount> accounts=baseDao.getByHql(PlatformAccount.class,hql);
        return accounts;
    }

    @Override
    public PlatformAccount getPlatformAccountById(int accountId) {
        return baseDao.getEntity(PlatformAccount.class,accountId);
    }

    public Boolean updatePlatformAccountState(PlatformAccount platformAccount,double money) {
        platformAccount.setStatus(1);
        Boolean result=baseDao.update(platformAccount);
        return result;
    }

    public Boolean recordPlatformAccount(Venue venue, double money) {
        PlatformAccount platformAccount1=new PlatformAccount();
        platformAccount1.setMoney(money);
        platformAccount1.setVenue(venue);
        platformAccount1.setTimestamp(new Timestamp(new Date().getTime()));
        platformAccount1.setStatus(1);
        return baseDao.saveNoId(platformAccount1);
    }

    public Boolean saveVenueAccount(VenueAccount venueAccount) {
        return baseDao.saveNoId(venueAccount);
    }

    public Map<String, Double> getEarningByVenue() {
        Map<String,Double> result=new HashMap<String, Double>();
        String hql="SELECT account.venue,sum(account.money) FROM PlatformAccount as account group by account.venue";
        List<Object[]> list=baseDao.getByHql(Object[].class,hql);
        for (Object[] objects:list){
            Venue venue=(Venue)objects[0];
            String name=venue.getName();
            double money=(Double)objects[1];
            result.put(name,money);
        }
        return result;
    }

    public Map<Integer, Double> getEarningByUser() {
        Map<Integer,Double> result=new HashMap<Integer, Double>();
        String hql="SELECT account.userInfo,sum(account.money) FROM PlatformAccount as account group by account.userInfo";
        List<Object[]> list=baseDao.getByHql(Object[].class,hql);
        for (Object[] objects:list){
            UserInfo userInfo=(UserInfo) objects[0];
            int userId=userInfo.getId();
            double money=(Double)objects[1];
            result.put(userId,money);
        }
        return result;
    }

    @Override
    public List<VenueAccount> getVenueAccount(int venueId) {
        String hql="SELECT account FROM VenueAccount as account left join account.venue venue WHERE venue.id="+venueId;
        List<VenueAccount> accounts=baseDao.getByHql(VenueAccount.class,hql);
        return accounts;
    }
}
