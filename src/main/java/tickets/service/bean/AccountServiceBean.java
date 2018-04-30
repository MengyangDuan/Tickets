package tickets.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.AccountDao;
import tickets.model.*;
import tickets.povo.PlatformAccountVO;
import tickets.service.AccountService;
import tickets.util.SettleAccountUtil;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AccountServiceBean implements AccountService{

    @Autowired
    AccountDao accountDao;

    public UserAccount getUserAccount(int userid) {
        int accountId=accountDao.getAccountId(userid);
        return accountDao.getUserAccount(accountId);
    }

    public Boolean checkPassword(int accountId, String password) {
        return accountDao.checkPassword(accountId,password);
    }

    public Boolean updateMoney(int userid, double price){
        int accountId=accountDao.getAccountId(userid);
        return accountDao.updateMoney(accountId,price);
    }

    public Boolean checkBalance(int accountId, double price) {
        double balance=accountDao.getMoney(accountId);
        if(balance>=price)
            return true;
        else
            return false;
    }

    public Boolean updatePlatformMoney(UserInfo userInfo,Venue venue, double money) {
        PlatformAccount platformAccount=new PlatformAccount();
        platformAccount.setMoney(money);
        platformAccount.setTimestamp(new Timestamp(new Date().getTime()));
        venue.addPlatformAccount(platformAccount);
        platformAccount.setUserInfo(userInfo);
        return accountDao.savePlatformMoney(platformAccount);
    }

    public List<PlatformAccount> getPlatformAccount(int venueId) {
        return accountDao.getPlatformAccount(venueId);
    }

    public List<PlatformAccountVO> getUnhandledAccount() {
        List<PlatformAccount>platformAccounts=accountDao.getUnhandledAccount();
        List<PlatformAccountVO>platformAccountVOS=new ArrayList<>();
        for(int i=0;i<platformAccounts.size();i++){
            PlatformAccount platformAccount=platformAccounts.get(i);
            PlatformAccountVO platformAccountVO=new PlatformAccountVO(platformAccount);
            if(platformAccountVO.getVenueId()>0)
                platformAccountVOS.add(platformAccountVO);
        }
        return platformAccountVOS;
    }

    public Boolean settleAccount(int accountId) {
        PlatformAccount platformAccount=accountDao.getPlatformAccountById(accountId);
        double rate= SettleAccountUtil.getRate();
        double payVenue=platformAccount.getMoney()*rate;
        Venue venue=platformAccount.getVenue();
        Boolean result=accountDao.recordPlatformAccount(venue,0-payVenue);
        if(result==false)
            return result;
        else {
            VenueAccount venueAccount=new VenueAccount();
            venueAccount.setMoney(payVenue);
            venueAccount.setTimestamp(new Timestamp(new Date().getTime()));
            venue.addVenueAccount(venueAccount);
            platformAccount.setStatus(1);
            return accountDao.saveVenueAccount(venueAccount)&&accountDao.updatePlatformAccount(platformAccount);
        }

    }

    public Map<String, Double> getEarningByVenue() {
        return accountDao.getEarningByVenue();
    }

    public Map<Integer, Double> getEarningByUser() {
        return accountDao.getEarningByUser();
    }

    public Map<Integer, Double> getPlatformEarningByMonth() {
        return null;
    }



}
