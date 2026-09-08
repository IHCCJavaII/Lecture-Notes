package org.example;

public class Laptop extends Product {
    private String screenSize;
    private String ramSize;

    public Laptop(String screenSize, String ramSize, float price, int UPC, String aisle) {
        this.screenSize = screenSize;
        this.ramSize = ramSize;
        this.setPrice(price);
        this.setUPC(UPC);
        this.setAisle(aisle);
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    @Override
    public String tryMe() {
        return "Boots up laptop and displays programs";
    }
}
