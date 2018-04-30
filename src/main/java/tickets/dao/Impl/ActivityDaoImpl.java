package tickets.dao.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.ActivityDao;
import tickets.dao.BaseDao;
import tickets.model.Activity;
import tickets.model.Ticket;

import java.util.List;

@Repository
@Transactional
public class ActivityDaoImpl implements ActivityDao {
    @Autowired
    BaseDao baseDao;

    public List<Activity> getActivities() {
        return baseDao.getAll(Activity.class);
    }

    @Override
    public List<Activity> getActivityByVenue(int venueId) {
        String hql="SELECT activity FROM Activity as activity left join activity.venue venue WHERE venue.id= '"+venueId+"'";
        List<Activity> activities=baseDao.getByHql(Activity.class,hql);
        return activities;
    }

    public Activity getActivity(int id) {
        return baseDao.getEntity(Activity.class,id);
    }

    public List<Ticket>getTicketsByActivity(int activityId){
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.activity activity WHERE activity.id= '"+activityId+"'";
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

    public List<Ticket> getUnavailableTickets(int activityId) {
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.activity activity WHERE activity.id= '"+activityId+"' and ticket.status=1";
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

    @Override
    public List<Ticket> getAvailableTickets(int activityId) {
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.activity activity WHERE activity.id= '"+activityId+"' and ticket.status=0";
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

}
