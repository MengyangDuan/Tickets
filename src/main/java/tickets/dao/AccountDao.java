package tickets.dao;

import tickets.model.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public interface AccountDao {
    UserAccount getUserAccount(int accountId);

    Boolean checkPassword(int accountId,String password);

    Boolean updateMoney(int accountId, double price);

    double getMoney(int accountId);

    int getAccountId(int userid);

    Boolean savePlatformMoney(PlatformAccount platformAccount);

    Boolean updateVenue(Venue venue);

    Boolean updateUserInfo(UserInfo userInfo);

    List<PlatformAccount> getPlatformAccount(int venueId);

    Boolean updatePlatformAccount(PlatformAccount platformAccount);

    List<PlatformAccount> getUnhandledAccount();

    PlatformAccount getPlatformAccountById(int accountId);

    Boolean updatePlatformAccountState(PlatformAccount platformAccount,double money);

    Boolean recordPlatformAccount(Venue venue,double money);

    Boolean saveVenueAccount(VenueAccount venueAccount);

    Map<String,Double> getEarningByVenue();

    Map<Integer,Double>getEarningByUser();

    List<VenueAccount>getVenueAccount(int venueId);

}
