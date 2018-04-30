package tickets.model;

import javax.persistence.*;


@Entity
@Table(name = "venueModify")

public class VenueModify {
    private int id;
    private Venue venue;
    private String newName;
    private String newAddress;
    private String newSeatMap;
    private int auditState;

    public VenueModify(){

    }
    public VenueModify(Venue venue,String name,String address,String seatMap){
        this.venue=venue;
        this.newAddress=address;
        this.newName=name;
        this.newSeatMap=seatMap;
        this.auditState=0;
    }
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "venueId")
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venueOriginal) {
        this.venue = venueOriginal;
    }


    public String getNewSeatMap() {
        return newSeatMap;
    }

    public void setNewSeatMap(String newSeatMap) {
        this.newSeatMap = newSeatMap;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

}
