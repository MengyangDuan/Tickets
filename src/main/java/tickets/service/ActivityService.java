package tickets.service;

import tickets.model.Activity;
import tickets.model.Orders;
import tickets.model.Ticket;
import tickets.povo.ActivityVO;
import tickets.povo.TicketVO;

import java.util.List;

public interface ActivityService {

    List<ActivityVO>getActivities();

    List<ActivityVO>getPreActivities();

    List<ActivityVO>getActivitiesByVenue(int venueId);

    ActivityVO getActivityVO(int activityId);

    Activity getActivity(int activityId);

    List<TicketVO>getTicketsByActivity(int activityId);

    Ticket getTicketBySeat(int activityId, String seat);

    List<Ticket> getUnavailableTickets(int activityId);

    Boolean matchingTickets(Orders orders);

    List<Ticket>getAvailableTickets(int activityId);

}
