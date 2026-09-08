package org.example;

import java.util.Scanner;

public class App {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LegoSet batmanArkhamAsylum = new LegoSet(Theme.ABSOLUTE_BATMAN);
        System.out.println(batmanArkhamAsylum.getTheme());

        LegoSet starWarsMillenniumFalcon = new LegoSet(Theme.STAR_WARS);
        System.out.println(starWarsMillenniumFalcon.getTheme());

        System.out.println("Pick a theme:");
        int count = 1;

        for (Theme theme : Theme.values()) {
            System.out.println("[" + count + "] " + theme);
            count++;
        }

        int themeChoice = scanner.nextInt();
        System.out.println("You picked: " + Theme.values()[themeChoice - 1]);
    }
}
