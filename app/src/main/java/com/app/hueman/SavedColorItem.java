package com.app.hueman;

public class SavedColorItem {
    private String hexColor;
    private String name;

    public SavedColorItem(String hexVal, String name) {
        hexColor = hexVal;
        this.name = name;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getName() {
        return name;
    }

}
