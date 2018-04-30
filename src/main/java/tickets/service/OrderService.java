package tickets.service;

import net.sf.json.JSONObject;
import tickets.model.Orders;
import tickets.model.Ticket;
import tickets.model.UserInfo;
import tickets.model.Venue;
import tickets.povo.OrderVO;
import tickets.povo.TicketVO;


import java.util.List;

public interface OrderService {

    Orders getOrder(int orderId);

    int buyTickets(UserInfo userInfo, List<Ticket> tickets,double totalPrice, double afterDiscountPirce);

    int setOrderWithNoTicket(int activityId,String seatRequire,UserInfo userInfo,double totalPrice,double afterDiscountPrice);

    Boolean updateOrder(Orders orders);

    Boolean checkTicket(int ticketId);

    Boolean payFailed(int orderId);

    double cancelTicketAndCalculateRefunds(int orderId);

    List<Ticket>getTicketByOrder(int orderId);

    List<OrderVO> getAllOrders(int userid);

    List<OrderVO> getPreOrders(int userid);

    List<OrderVO> getCancelOrders(int userid);

    List<OrderVO> getConcumedOrders(int userid);

    List<OrderVO> getNoPayOrders(int userid);

    List<Ticket> getPreTicketsByVenue(int venueId);

    List<Ticket> getCancelTicketsByVenue(int venueId);

    double getTotalMoney(int userId);

    OrderVO getOrderVO(int orderId);

    Venue getVenueByOrder(int orderId);

    UserInfo getUserByOrder(int orderId);

    JSONObject getGuestLiveNumByMonth();







}
