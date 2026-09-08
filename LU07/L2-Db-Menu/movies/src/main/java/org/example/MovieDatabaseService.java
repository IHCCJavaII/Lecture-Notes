package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabaseService {

    //TODO remove helper methods
    //TODO simplify method names
    private static final Dotenv DOTENV = Dotenv.configure().load();

    private static final String DB_USER = getConfig("MOVIES_DB_USER", "root");
    private static final String DB_PASSWORD = getConfig("MOVIES_DB_PASSWORD", "");

    private static final String SERVER_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies_db";

    public void createTableIfNotExists() throws SQLException {
        createDatabaseIfNotExists();

        String sql = """
                CREATE TABLE IF NOT EXISTS movies (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR(150) NOT NULL,
                    release_year INT NOT NULL,
                    duration_minutes INT NOT NULL,
                    genre VARCHAR(30) NOT NULL
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    //TODO make this void
    public int createMovie(String title, int releaseYear, int durationMinutes, String genre) throws SQLException {
        String sql = "INSERT INTO movies (title, release_year, duration_minutes, genre) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, title);
            statement.setInt(2, releaseYear);
            statement.setInt(3, durationMinutes);
            statement.setString(4, genre);
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        throw new SQLException("Movie insert succeeded but no generated ID was returned.");
    }

    public List<Movie> listMovies() throws SQLException {
        String sql = "SELECT id, title, release_year, duration_minutes, genre FROM movies ORDER BY id";
        List<Movie> movies = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Movie movie = mapRowToMovie(resultSet);
                if (movie != null) {
                    movies.add(movie);
                }
            }
        }

        return movies;
    }

    public boolean deleteMovieById(int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private void createDatabaseIfNotExists() throws SQLException {
        String sql = "CREATE DATABASE IF NOT EXISTS movies_db";

        try (Connection connection = DriverManager.getConnection(SERVER_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static String getConfig(String key, String defaultValue) {
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        String dotenvValue = DOTENV.get(key);
        if (dotenvValue != null && !dotenvValue.isBlank()) {
            return dotenvValue;
        }

        return defaultValue;
    }

    private Movie mapRowToMovie(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        int releaseYear = resultSet.getInt("release_year");
        int durationMinutes = resultSet.getInt("duration_minutes");
        String genreValue = resultSet.getString("genre");

        try {
            Genre genre = Genre.valueOf(genreValue.toUpperCase());
            return new Movie(id, title, releaseYear, durationMinutes, genre);
        } catch (IllegalArgumentException | InvalidMovieDurationException | InvalidMovieGenreException ex) {
            return null;
        }
    }
}
