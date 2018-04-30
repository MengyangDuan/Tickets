package tickets.service;

import net.sf.json.JSONObject;
import tickets.model.Ticket;
import tickets.model.Venue;
import tickets.model.VenueModify;
import tickets.povo.DataVO;
import tickets.povo.VenueModifyVO;
import tickets.povo.VenueVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface VenueService {

    Boolean register(VenueVO venueVO);

    Boolean login(int venueId, String password);

    VenueVO getVenueVO(int venueId);

    Venue getVenue(int venueId);

    Boolean updateVeunue(VenueVO venueVO);

    Boolean updateRegisterState(int venueId, Boolean result);

    Boolean updateModifyState(int venueId,Boolean result);

    int releaseActivity(int id, String name, Date date, int type, String description);

    List<VenueVO> getNonCheckedVenues();

    List<VenueModifyVO>getNonCheckedModifies();

    Boolean makeTickets(int venueId,int activityId, Map<Character,Double> priceMap);

    double getOccupancyRate(int venueId);

    List<Ticket>getTicketByVenue(int venueId);

    Boolean saveVenueModify(VenueModify venueModify);

    JSONObject getSummaryNumOfAllVenues();

    List<DataVO> getSeatedBookRate(int id);

    List<DataVO> getAllBookNum(int id);

    List<DataVO>  getNotCancelledBookNumByActivityType(int venueId);

    List<Object[]> getMoneyVipRate(int venueId);

    List<DataVO> getIncomeAvg(int venueId);




}
