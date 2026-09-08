package org.example;

public class Movie {
    private int id;
    private String title;
    private int releaseYear;
    private int durationMinutes;
    private Genre genre;

    public Movie(String title, int releaseYear, int durationMinutes, Genre genre)
            throws InvalidMovieDurationException, InvalidMovieGenreException {
        setTitle(title);
        setReleaseYear(releaseYear);
        setDurationMinutes(durationMinutes);
        setGenre(genre);
    }

    public Movie(int id, String title, int releaseYear, int durationMinutes, Genre genre)
            throws InvalidMovieDurationException, InvalidMovieGenreException {
        this(title, releaseYear, durationMinutes, genre);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            this.title = "Untitled";
        }else{
            this.title = title;
        }
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) throws InvalidMovieDurationException {
        if (durationMinutes <= 0) {
            throw new InvalidMovieDurationException("Duration must be greater than 0 minutes.");
        }
        if (durationMinutes > 600) {
            throw new InvalidMovieDurationException("Duration cannot be greater than 600 minutes.");
        }
        this.durationMinutes = durationMinutes;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) throws InvalidMovieGenreException {
        if (genre == null) {
            throw new InvalidMovieGenreException("Genre is required.");
        }
        this.genre = genre;
    }
}
