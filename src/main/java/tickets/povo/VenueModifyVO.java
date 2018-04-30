package tickets.povo;

import tickets.model.Venue;
import tickets.model.VenueModify;

/**
 * Created by disinuo on 17/3/12.
 */

public class VenueModifyVO {
    private int id;
    private int venueId;
    private String email;
    private String seat_original;
    private String address_original;
    private String name_original;
    private String seat_new;
    private String address_new;
    private String name_new;

    public VenueModifyVO(VenueModify venueModify, Venue venue){
        this.id=venueModify.getId();
        this.venueId=venue.getId();
        this.email=venue.getEmail();
        this.seat_original=venue.getSeatMap();
        this.address_original=venue.getAddress();
        this.name_original=venue.getName();

        this.seat_new=venueModify.getNewSeatMap();
        this.address_new=venueModify.getNewAddress();
        this.name_new=venueModify.getNewName();
    }

//    public static List<RequestModifyVO> entityToVO(List<RequestModify> requests){
//        List<RequestModifyVO> res=new ArrayList<RequestModifyVO>();
//        for(RequestModify request:requests){
//            res.add(new RequestModifyVO(request));
//        }
//        return res;
//    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeat_original() {
        return seat_original;
    }

    public void setSeat_original(String seat_original) {
        this.seat_original = seat_original;
    }

    public String getAddress_original() {
        return address_original;
    }

    public void setAddress_original(String address_original) {
        this.address_original = address_original;
    }

    public String getName_original() {
        return name_original;
    }

    public void setName_original(String name_original) {
        this.name_original = name_original;
    }

    public String getSeat_new() {
        return seat_new;
    }

    public void setSeat_new(String seat_new) {
        this.seat_new = seat_new;
    }

    public String getAddress_new() {
        return address_new;
    }

    public void setAddress_new(String address_new) {
        this.address_new = address_new;
    }

    public String getName_new() {
        return name_new;
    }

    public void setName_new(String name_new) {
        this.name_new = name_new;
    }


}
