package tickets.model;

import tickets.povo.VenueVO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "venue")
public class Venue {
    private int id;
    private String name;
    private String password;
    private String address;
    private String seatMap;
    private int auditState;             //0代表未审查，1代表审查批准，2代表未批准
    private String email;
    private Set<Activity> activities=new HashSet<Activity>();
    private Set<PlatformAccount> platformAccounts =new HashSet<PlatformAccount>();
    private Set<VenueAccount>venueAccounts=new HashSet<VenueAccount>();
    private Set<VenueModify>venueModifies=new HashSet<>();

    public Venue(){

    }
    public Venue(VenueVO venueVO){
        this.name=venueVO.getVenueName();
        this.password=venueVO.getPassword();
        this.address=venueVO.getAddress();
        this.seatMap=venueVO.getSeatMap();
        this.email=venueVO.getEmail();
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String place) {
        this.address = place;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "venue",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity){
        activity.setVenue(this);
        activities.add(activity);
    }


    @OneToMany(mappedBy = "venue",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<PlatformAccount> getPlatformAccounts() {
        return platformAccounts;
    }

    public void setPlatformAccounts(Set<PlatformAccount> venueAccounts) {
        this.platformAccounts = venueAccounts;
    }

    public void addPlatformAccount(PlatformAccount platformAccount){
        platformAccount.setVenue(this);
    }

    @OneToMany(mappedBy = "venue",cascade =CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<VenueAccount> getVenueAccounts() {
        return venueAccounts;
    }

    public void setVenueAccounts(Set<VenueAccount> venueAccounts) {
        this.venueAccounts = venueAccounts;
    }

    public void addVenueAccount(VenueAccount venueAccount){
        venueAccount.setVenue(this);
        venueAccounts.add(venueAccount);
    }

    @OneToMany(mappedBy = "venue",cascade =CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<VenueModify> getVenueModifies() {
        return venueModifies;
    }

    public void setVenueModifies(Set<VenueModify> venueModifies) {
        this.venueModifies = venueModifies;
    }

    public void addVenueMdodify(VenueModify venueModify){
        venueModify.setVenue(this);
        venueModifies.add(venueModify);
    }


}
