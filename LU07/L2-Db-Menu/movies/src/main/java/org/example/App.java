package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final MovieDatabaseService MOVIE_DB = new MovieDatabaseService();

    // TODO update all validation to use exceptions from Movie setters? Maybe?

    public static void main(String[] args) {
        try {
            MOVIE_DB.createTableIfNotExists();
        } catch (SQLException ex) {
            System.out.println("Could not initialize database: " + ex.getMessage());
            return;
        }

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readIntRange("Choose an option: ", 0, 5);

            switch (choice) {
                case 1:
                    handleCreateMovie();
                    break;
                case 2:
                    handleListMovies();
                    break;
                case 3:
                    handleDeleteMovie();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Movie Menu ===");
        System.out.println("1. Create Movie");
        System.out.println("2. List Movies");
        System.out.println("3. Delete Movie");
        System.out.println("0. Exit");
    }

    private static void handleCreateMovie() {
        String title = readNonBlankLine("Title");
        int releaseYear = readIntRange("Release year: ", 1888, 2100);
        // TODO set min to 90
        int durationMinutes = readIntRange("Duration (minutes): ", 90, 600);
        String genre = readGenre("Genre (ACTION, COMEDY, DRAMA): ");

        try {
            // TODO pass a Movie object instead of individual fields
            int newMovieId = MOVIE_DB.createMovie(title, releaseYear, durationMinutes, genre);
            System.out.println("Movie created with ID: " + newMovieId);
        } catch (SQLException ex) {
            System.out.println("Failed to create movie: " + ex.getMessage());
        }
    }

    private static void handleListMovies() {
        try {
            List<Movie> movies = MOVIE_DB.listMovies();
            if (movies.isEmpty()) {
                System.out.println("No movies found.");
                return;
            }

            for (Movie movie : movies) {
                System.out.println(formatMovie(movie));
            }
        } catch (SQLException ex) {
            System.out.println("Failed to list movies: " + ex.getMessage());
        }
    }

    private static void handleDeleteMovie() {
        int id = readIntRange("Movie ID to delete: ", 1, Integer.MAX_VALUE);

        try {
            boolean deleted = MOVIE_DB.deleteMovieById(id);
            if (deleted) {
                System.out.println("Movie deleted.");
            } else {
                System.out.println("No movie found with ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Failed to delete movie: " + ex.getMessage());
        }
    }

    private static int readIntRange(String prompt, int min, int max) {
        while(true){
            String value = SCANNER.nextLine();
            try {
                int intValue = Integer.parseInt(value);
                if(intValue < min || intValue > max){
                    System.out.println(prompt + " must be between " + min + " and " + max + ". Please try again.");
                }else{
                    return intValue;
                }
            } catch (NumberFormatException e) {
                System.out.println(prompt + " must be a valid integer. Please try again.");
            }
        }
    }

    // private static int readPositiveInt(String prompt) {
    //     while (true) {
    //         int value = readInt(prompt);
    //         if (value > 0) {
    //             return value;
    //         }
    //         System.out.println("Please enter a positive number.");
    //     }
    // }

    // private static String readLine(String prompt) {
    //     System.out.print(prompt);
    //     return SCANNER.nextLine().trim();
    // }

    private static String readNonBlankLine(String prompt) {
        while(true){
            System.out.print(prompt + ": ");
            String value = SCANNER.nextLine();
            if(!value.isBlank()){
                return value;
            }
            System.out.println(prompt + " cannot be blank. Please try again.");
        }
    }

    private static String readGenre(String prompt) {
        while (true) {
            String value = readNonBlankLine("Genre").toUpperCase();
            if (value.equals("ACTION") || value.equals("COMEDY") || value.equals("DRAMA")) {
                return value;
            }
            System.out.println("Genre must be one of: ACTION, COMEDY, DRAMA.");
        }
    }

    // TODO move this to Movie class as a toString() method
    private static String formatMovie(Movie movie) {
        return "ID=" + movie.getId()
                + ", title='" + movie.getTitle() + "'"
                + ", year=" + movie.getReleaseYear()
                + ", duration=" + movie.getDurationMinutes()
                + ", genre=" + movie.getGenre();
    }
}
