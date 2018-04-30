package tickets.dao;

import tickets.model.Orders;
import tickets.model.Ticket;
import tickets.model.UserInfo;

import java.util.List;
import java.util.Set;

public interface OrderDao {

    int saveOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    List<Orders> getAllOrders(int userid);

    List<Orders> getBookOrders(int userid);

    List<Orders> getCancelOrders(int userid);

    List<Orders> getNoPayOrders(int userid);

    Ticket getTicket(int ticketId);

    Boolean updateTicket(Ticket ticket);

    List<Ticket> getPreTicketsByVenue(int venueId);

    List<Ticket> getCancelTicketsByVenue(int venueId);

    Boolean updateUserInfo(UserInfo userInfo);

    List<Ticket>getTicketByOrder(int orderId);

    Orders getOrder(int orderId);

    UserInfo getUserByOrder(int orderId);

    List<Orders>getMatchingFailedOrder();

    List<Orders>getAll();
}
