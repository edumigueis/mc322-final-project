package ui.controllers;

public class ItemPair {
    private final String name;
    private final String additionalInfo;

    public ItemPair(String name, String additionalInfo) {
        this.name = name;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return name;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
