package objects;

import java.util.List;

public class City {
    private String name;
    private String description;
    private String thumbImageUrl;
    private List<Activity> thingsToDo;

    public City(String name, String description, String thumbImageUrl, List<Activity> thingsToDo) {
        this.name = name;
        this.description = description;
        this.thumbImageUrl = thumbImageUrl;
        this.thingsToDo = thingsToDo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public List<Activity> getThingsToDo() {
        return thingsToDo;
    }

    public void setThingsToDo(List<Activity> thingsToDo) {
        this.thingsToDo = thingsToDo;
    }
}
