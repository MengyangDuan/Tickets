package tickets.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "venueAccount")
public class VenueAccount {
    private Venue venue;
    private int accountId;
    private Timestamp timestamp;
    private double money;

    @ManyToOne(cascade = CascadeType.REMOVE,optional = true)
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Id
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
