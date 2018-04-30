package tickets.dao;

import tickets.model.Activity;
import tickets.model.Ticket;
import tickets.model.Venue;
import tickets.model.VenueModify;

import java.util.List;

public interface VenueDao {

    Boolean checkVenue(int id, String password);

    Boolean saveVenue(Venue venue);

    Venue getVenue(int id);

    Boolean updateVenue(Venue venue);

    List<Venue> getNonCheckedVenues();

    List<VenueModify> getNonCheckedModifies();

    int saveActivity(Activity activity);

    Boolean saveTicket(Ticket ticket);

    Activity getActivity(int activityId);

    Boolean updateActivity(Activity activity);

    List<Ticket>getTicketsByVenue(int venueId);

    VenueModify getVenueModify(int modifyId);

    Boolean updateVenueModify(VenueModify venueModify);

    Boolean saveVenueModify(VenueModify venueModify);

    List<Venue>getCheckedVenues();

    List<Activity> getActivityByVenue(int venueId);
}
