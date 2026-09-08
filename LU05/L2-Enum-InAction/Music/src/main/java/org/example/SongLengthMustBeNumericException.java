package org.example;

/**
 *
 * This exception is to be thrown when the Song length is tired to be set as not a number
 *
 * @Author Luke Matheis
 * @Verson 1.0v
 */
public class SongLengthMustBeNumericException extends Exception {
    public SongLengthMustBeNumericException(String message){
        super(message);
    }
}
