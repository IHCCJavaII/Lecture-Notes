package org.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DVDLogo {
    private String color;
    private ImageView dvdImageView;
    private double volocityY;
    private double volocityX;

    public DVDLogo(){
        this.color = "black";
        this.dvdImageView = new ImageView("dvd-" + this.color + ".png");
        this.dvdImageView.setFitHeight(100);
        this.dvdImageView.setPreserveRatio(true);
        this.dvdImageView.setX(100);
        this.dvdImageView.setY(100);
        this.volocityY = 5; //pixels per event
        this.volocityX = 7;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ImageView getDvdImageView() {
        return dvdImageView;
    }

    public void setDvdImageView() {
        this.dvdImageView.setImage(new Image("dvd-" + this.color + ".png"));
    }

    public double getVolocityY() {
        return volocityY;
    }

    public void setVolocityY(double volocityY) {
        this.volocityY = volocityY;
    }

    public double getVolocityX() {
        return volocityX;
    }

    public void setVolocityX(double volocityX) {
        this.volocityX = volocityX;
    }

    //Don't change this to random, this is a good example of the enhanced switch
    private void changeColor(){
        this.color = switch (this.color){
            case "black" -> "blue";
            case "blue" -> "green";
            case "green" -> "pink";
            case "pink" -> "red";
            default -> "black";
        };
    }

    public void moveLogo(){
        //update current position
        this.dvdImageView.setX(this.dvdImageView.getX() + this.volocityX);
        this.dvdImageView.setY(this.dvdImageView.getY() + this.volocityY);
        //check if hit edge of the screen, if so turn around and change image
        //TODO remove magic numbers
        //TODO fix this
        if(this.dvdImageView.getX() >= 650 || this.dvdImageView.getX() <= 0){
            volocityX *= -1; //flip the direction, positive to negative and vice versa
            changeColor();
            setDvdImageView();
        }
        //copied and change 'Y'
        if(this.dvdImageView.getY() >= 650 || this.dvdImageView.getY() <= 0){
            volocityY *= -1; //flip the direction, positive to negative and vice versa
            changeColor();
            setDvdImageView();
        }
    }
}
