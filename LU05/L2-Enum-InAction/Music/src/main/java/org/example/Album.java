package org.example;

import java.util.ArrayList;

public class Album {
    ArrayList<Song> songs;

    String albumName;

    String artist;

    Genre genre;

    public Album(ArrayList<Song> songs, String albumName, String artist, Genre genre) {
        this.songs = songs;
        setAlbumName(albumName);
        this.artist = artist;
        this.genre = genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        if(albumName != null && !albumName.trim().isEmpty() ){
            this.albumName = albumName;
        }else{
            try {
                throw new AlbumNameEmptyException("Can't set Album Name to Empty");
            } catch (AlbumNameEmptyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        String songsString = "Songs: \n\t";
        for(Song s: songs){
            songsString += s.toString() + "\n\t";
        }
        return albumName + ":" +
            "\n\tArtist: " + artist +
            "\n\tGenre: " + genre + "\n\t" +
            songsString
        ;
    }
}
