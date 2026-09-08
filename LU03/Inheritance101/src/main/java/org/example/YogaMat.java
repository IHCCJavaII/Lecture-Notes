package org.example;

public class YogaMat extends Product {
    // add what is specific to this class

    private String material;

    public YogaMat(String material, float price, int UPC, String aisle) {
        this.material = material;
        super.setPrice(price);
        super.setUPC(UPC);
        super.setAisle(aisle);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String tryMe() {
        return "Lay out yoga mat on floor and customer practices yoga";
    }
}
