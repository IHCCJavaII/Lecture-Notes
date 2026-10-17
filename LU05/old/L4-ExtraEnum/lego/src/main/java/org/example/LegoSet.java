package org.example;

public class LegoSet {
    private Theme theme;

    public LegoSet(Theme theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme.toString();
    }
}
