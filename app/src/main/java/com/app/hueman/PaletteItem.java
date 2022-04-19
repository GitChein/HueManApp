package com.app.hueman;

public class PaletteItem {
    private String[] hexColors;
    private String name;

    public PaletteItem(String[] hexVals, String name) {
        hexColors = hexVals;
        this.name = name;
    }

    public String[] getHexColors() {
        return hexColors;
    }

    public String getName() {
        return name;
    }

}
