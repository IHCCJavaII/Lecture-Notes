package com.soundboard;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// TODO make this a button
public abstract class AnimalNode extends StackPane {
    public AnimalNode(String name, String noise, String color) {
        Rectangle box = new Rectangle(100, 100);
        box.setStyle("-fx-fill: " + color);
        Text label = new Text(name);

        this.getChildren().addAll(box, label);

        // Inherited logic: click to see noise, move mouse away to reset
        this.setOnMouseClicked(e -> label.setText(noise));
        this.setOnMouseExited(e -> label.setText(name));
    }
}