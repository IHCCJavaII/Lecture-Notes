package org.example;

/**
 * @link https://en.wikipedia.org/wiki/List_of_music_genres_and_styles
 * @link https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
 * <h1>Wher i got dates</h1>
 * <p>I got the dates from the wiki. Might want to go back and find more reliable sources</p>
 *
 * @version 1.0
 * @author Luke Matheis
 */
public enum Genre {
    ROCK(1860),
    HIP_HOP(1970),
    POP(1950),
    COUNTRY(1920),
    BLUES(1860);

    final int origins;

    Genre(int origins){
        this.origins = origins;
    }
}
