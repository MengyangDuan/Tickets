package tickets.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tickets.dao.BaseDao;
import tickets.dao.OrderDao;
import tickets.model.Orders;
import tickets.model.Ticket;
import tickets.model.UserInfo;

import java.util.*;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @Autowired
    BaseDao baseDao;


    public int saveOrder(Orders orders) {
        try {
            return baseDao.save(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Orders getOrder(int orderid) {
        return baseDao.getEntity(Orders.class,orderid);
    }

    public Boolean updateOrder(Orders orders) {
        return baseDao.update(orders);
    }

    public List<Orders> getAllOrders(int userid) {
        String hql="SELECT orders FROM Orders as orders left join orders.userInfo userInfo WHERE userInfo.id= '"+userid+"'";
        List<Orders> orders=baseDao.getByHql(Orders.class,hql);
        return orders;
    }

    public List<Orders> getBookOrders(int userid) {
        String hql="SELECT orders FROM Orders as orders left join orders.userInfo userInfo WHERE userInfo.id= '"+userid+"' and orders.status=1";
        List<Orders> orders=baseDao.getByHql(Orders.class,hql);
        return orders;
    }

    public List<Orders> getCancelOrders(int userid) {
        String hql="SELECT o FROM Orders as o left join fetch o.userInfo userInfo WHERE userInfo.id= '"+userid+"' and o.status=3 or o.status=2 or o.status=4";
        List<Orders> orders=baseDao.getByHql(Orders.class,hql);
        return orders;
    }

    @Override
    public List<Orders> getNoPayOrders(int userid) {
        String hql = "SELECT o FROM Orders as o left join fetch o.userInfo userInfo WHERE o.status=0 and userInfo.id= " + userid;
        List<Orders> orders = baseDao.getByHql(Orders.class, hql);
        return orders;
    }

    public Ticket getTicket(int ticketId) {
        return baseDao.getEntity(Ticket.class,ticketId);
    }

    public Boolean updateTicket(Ticket ticket) {
        return baseDao.update(ticket);
    }

    public List<Ticket> getPreTicketsByVenue(int venueId) {
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.activity activity left join ticket.activity.venue venue WHERE venue.id= '"+venueId+"' and ticket.status=0 or ticket.status=1";
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

    public List<Ticket> getCancelTicketsByVenue(int venueId) {
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.activity activity left join ticket.activity.venue venue WHERE venue.id= '"+venueId+"' and ticket.status=2";
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

    public Boolean updateUserInfo(UserInfo userInfo) {
        return baseDao.update(userInfo);
    }

    public List<Ticket> getTicketByOrder(int orderId) {
        String hql="SELECT ticket FROM Ticket as ticket left join ticket.orders orders WHERE orders.id= "+orderId;
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return tickets;
    }

    @Override
    public List<Orders> getMatchingFailedOrder() {
        String hql="SELECT orders FROM Orders as orders WHERE orders.matchingStatus= 2";
        List<Ticket> tickets=baseDao.getByHql(Ticket.class,hql);
        return null;
    }

    @Override
    public UserInfo getUserByOrder(int orderId) {
        String hql="SELECT userInfo FROM Orders orders left join orders.userInfo userInfo WHERE orders.id= "+orderId;
        List<UserInfo>userInfos=baseDao.getByHql(UserInfo.class,hql);
        return userInfos.get(0);
    }

    @Override
    public List<Orders> getAll() {
        List<Orders>orders=baseDao.getAll(Orders.class);
        List<Orders>result=new ArrayList<>();
        Map<Integer,Integer>idMap=new HashMap<>();
        for(int i=0;i<orders.size();i++){
            Orders order=orders.get(i);
            if(!idMap.containsKey(order.getId()))
                idMap.put(order.getId(),i);
        }
        for (Map.Entry<Integer, Integer> entry : idMap.entrySet()) {
            result.add(orders.get(entry.getValue()));
        }
        return result;
    }
}
