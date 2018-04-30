package tickets.povo;

import tickets.model.Venue;

import java.util.HashSet;
import java.util.Set;

public class VenueVO {
    private int id;
    private String venueName;
    private String email;
    private String password;
    private String address;
    private String seatMap;
    private Set<Character> seatType;

    public VenueVO(){

    }
    public VenueVO(String venueName,String address,String seatMap,String email,String password){
        this.venueName=venueName;
        this.email=email;
        this.password=password;
        this.address=address;
        this.seatMap=seatMap;
        this.seatType=getSeatType(seatMap);
    }
    public VenueVO(Venue venue) {
        this.id = venue.getId();
        this.venueName = venue.getName();
        this.email = venue.getEmail();
        this.password = venue.getPassword();
        this.address = venue.getAddress();
        this.seatMap = venue.getSeatMap();
        this.seatType = getSeatType(venue.getSeatMap());
    }

    private Set<Character> getSeatType(String str){
        if(str==null)
            return null;
        char[] array=str.toCharArray();
        Set<Character>set=new HashSet<Character>();
        for(int i=0;i<array.length;i++){
            if(array[i]>='a'&&array[i]<='z')
                set.add(array[i]);
        }
        return set;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Character> getSeatType() {
        return seatType;
    }

    public void setSeatType(Set<Character>seatType) {
        this.seatType = seatType;
    }



}
