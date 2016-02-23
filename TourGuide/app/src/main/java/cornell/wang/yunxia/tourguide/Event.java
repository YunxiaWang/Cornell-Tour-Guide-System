package cornell.wang.yunxia.tourguide;

/**
 * Created by Lele on 2015/11/29.
 */
public class Event {
    private String name;
    private Integer imageID;
    private String startTime;
    private String endTime;
    private String location;
    private String category;
    private String description;

    public Event(String name, Integer imageID, String startTime, String endTime, String location, String category, String description){
        this.name = name;
        this.imageID = imageID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.category = category;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public Integer getImageID(){
        return imageID;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getLocation(){
        return location;
    }

    public String getCategory() { return category; }

    public String getDescription(){
        return description;
    }
}
