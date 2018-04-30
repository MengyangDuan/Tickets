package tickets.povo;

import tickets.model.PlatformAccount;

import java.sql.Timestamp;

public class PlatformAccountVO {
    private int id;
    private int venueId;
    private String venueName;
    private Timestamp timestamp;
    private double money;

    public PlatformAccountVO(){

    }
    public PlatformAccountVO(PlatformAccount platformAccount){
        this.id=platformAccount.getAccountId();
        if(platformAccount.getVenue()!=null) {
            this.venueId = platformAccount.getVenue().getId();
            this.venueName = platformAccount.getVenue().getName();
        }
        this.timestamp=platformAccount.getTimestamp();
        this.money=platformAccount.getMoney();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}
