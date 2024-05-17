package entities.activities;

public class CulturalEvent extends Attraction {
    private CulturalEventType type;
    private String linkToBuy;

    public CulturalEventType getType() {
        return type;
    }

    public void setType(CulturalEventType type) {
        this.type = type;
    }

    public String getLinkToBuy() {
        return linkToBuy;
    }

    public void setLinkToBuy(String linkToBuy) {
        this.linkToBuy = linkToBuy;
    }
}
