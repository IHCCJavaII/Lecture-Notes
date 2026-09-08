package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ArrayList<Song> songs = new ArrayList<>(Arrays.asList(
                        //if you set the length to NaN it will throw custom Ex
                        new Song("320", "My Name Is Jonas"),
                        new Song(200, "Buddy Holly")
                ));
        Album blue = new Album(songs, "Blue Album" , "Weezer", Genre.ROCK);
        System.out.println(blue);
    }
}
