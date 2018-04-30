package tickets.dao;

import tickets.model.Manager;
import tickets.model.PlatformAccount;
import tickets.model.Venue;
import tickets.model.VenueAccount;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
    Boolean Login(int id,String password);

    Boolean updateVenue(Venue venue);

    Manager getManager(int id);

}
