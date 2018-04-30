package tickets.povo;

import tickets.model.Ticket;

public class TicketVO {
    private int type;          //0表示不选座，1表示选座
    private int activityId;
    private String seat;
    private double price;

    public TicketVO(){

    }
    public TicketVO(Ticket ticket){
        this.seat=ticket.getSeat();
        this.activityId=ticket.getActivity().getId();
        this.price=ticket.getPrice();
        if(!(seat.equals("")||seat==null)){
            type=1;
        }
        else
            type=0;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
