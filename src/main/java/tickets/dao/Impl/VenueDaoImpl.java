package tickets.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.BaseDao;
import tickets.dao.VenueDao;
import tickets.model.Activity;
import tickets.model.Ticket;
import tickets.model.Venue;
import tickets.model.VenueModify;

import java.util.List;

@Repository
@Transactional
public class VenueDaoImpl implements VenueDao{

    @Autowired
    BaseDao baseDao;

    public Boolean checkVenue(int id, String password){
        String hql="SELECT venue FROM Venue as venue WHERE venue.auditState=1 and venue.id= '"+id+"' and venue.password= '"+password+"'";
        List<Venue> venues=baseDao.getByHql(Venue.class,hql);
        if(venues.size()==0)
            return false;
       else
        return true;
    }

    public Boolean saveVenue(Venue venue) {
        return baseDao.saveNoId(venue);
    }


    public Venue getVenue(int id) {
        return baseDao.getEntity(Venue.class,id);
    }

    public Boolean updateVenue(Venue venue) {
        return baseDao.update(venue);
    }

    public List<Venue> getNonCheckedVenues() {
        String hql="SELECT venue FROM Venue as venue WHERE venue.auditState=0 ";
        List<Venue> venues=baseDao.getByHql(Venue.class,hql);
        return venues;
    }

    @Override
    public List<VenueModify> getNonCheckedModifies() {
        String hql="SELECT modify FROM VenueModify as modify WHERE modify.auditState=0 ";
        List<VenueModify> venueModifies=baseDao.getByHql(VenueModify.class,hql);
        return venueModifies;
    }

    public Boolean saveTicket(Ticket ticket) {
        return baseDao.saveNoId(ticket);
    }

    public int saveActivity(Activity activity) {
        int id=-1;
        try {
            id=baseDao.save(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }

    public Activity getActivity(int activityId) {
        return baseDao.getEntity(Activity.class,activityId);
    }

    public Boolean updateActivity(Activity activity) {
        return baseDao.update(activity);
    }

    @Override
    public List<Ticket> getTicketsByVenue(int venueId) {
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.activity activity left join activity.venue venue where venue.id="+venueId;
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

    @Override
    public VenueModify getVenueModify(int modifyId) {
        return baseDao.getEntity(VenueModify.class,modifyId);
    }

    @Override
    public Boolean updateVenueModify(VenueModify venueModify) {
        return baseDao.update(venueModify);
    }

    @Override
    public Boolean saveVenueModify(VenueModify venueModify) {
        return baseDao.saveNoId(venueModify);
    }

    public List<Venue> getCheckedVenues() {
        String hql="SELECT venue FROM Venue as venue WHERE venue.auditState=1 ";
        List<Venue> venues=baseDao.getByHql(Venue.class,hql);
        return venues;
    }

    @Override
    public List<Activity> getActivityByVenue(int venueId) {
        String hql="SELECT activity FROM Activity as activity left join activity.venue venue where venue.id="+venueId;
        List<Activity>activities=baseDao.getByHql(Activity.class,hql);
        return activities;
    }
}
