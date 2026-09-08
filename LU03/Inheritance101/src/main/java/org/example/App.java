package org.example;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        YogaMat deluxeYogaMat = new YogaMat("Foam", 19.99f, 1234, "F9");

        Laptop macbookAir15 = new Laptop("15", "24gb", 1199.99f, 1235, "T60");

        // YogaMat IS A Product
        // Laptop IS A Product
        List<Product> shoppingCart = new ArrayList<>();
        shoppingCart.add(macbookAir15);
        shoppingCart.add(deluxeYogaMat);

        for (Product product : shoppingCart) {
            System.out.println(product.getAisle());
            System.out.println(product.getPrice());
            System.out.println(product.tryMe());
        }


    }
}