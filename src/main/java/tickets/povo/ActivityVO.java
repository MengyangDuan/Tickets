package tickets.povo;

import tickets.model.Activity;
import tickets.util.ActivityMap;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ActivityVO {
    private int id;
    private String name;
    private Timestamp time;
    private String place;
    private String showType;
    private String description;

    public ActivityVO(){

    }
    public ActivityVO(Activity activity){
        this.id=activity.getId();
        this.name=activity.getName();
        this.time=activity.getTime();
        this.showType= ActivityMap.getTypeName(activity.getShowType());
        this.description=activity.getDescription();
        this.place=activity.getVenue().getAddress();
    }

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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
