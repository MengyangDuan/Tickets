package tickets.service;


import tickets.model.PlatformAccount;
import tickets.model.UserAccount;
import tickets.model.UserInfo;
import tickets.model.Venue;
import tickets.povo.PlatformAccountVO;

import java.util.List;
import java.util.Map;


public interface AccountService {
    UserAccount getUserAccount(int userid);

    Boolean checkPassword(int accountId,String password);

    Boolean updateMoney(int userid, double price);

    Boolean checkBalance(int accountid,double price);

    Boolean updatePlatformMoney(UserInfo userInfo, Venue venue, double money);

    List<PlatformAccount> getPlatformAccount(int venueId);

    List<PlatformAccountVO> getUnhandledAccount();

    Boolean settleAccount(int accountId);

    Map<String,Double> getEarningByVenue();

    Map<Integer,Double>getEarningByUser();

    Map<Integer,Double>getPlatformEarningByMonth();

}
