package tickets.dao;

import tickets.model.Activity;
import tickets.model.Ticket;

import java.util.List;

public interface ActivityDao {

    List<Activity>getActivities();

    List<Activity>getActivityByVenue(int venueId);

    Activity getActivity(int id);

    List<Ticket>getTicketsByActivity(int activityId);

    List<Ticket> getUnavailableTickets(int activityId);

    List<Ticket>getAvailableTickets(int activityId);


}
