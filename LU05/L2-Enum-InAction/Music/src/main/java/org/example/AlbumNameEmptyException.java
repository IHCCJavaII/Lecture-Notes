package org.example;

public class AlbumNameEmptyException extends Exception {
    public AlbumNameEmptyException(String message, Throwable cause) {super(message, cause);}

    public AlbumNameEmptyException(String message) {super(message);}
}
