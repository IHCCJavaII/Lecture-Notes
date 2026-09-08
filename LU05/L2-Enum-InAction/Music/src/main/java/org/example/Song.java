package org.example;

public class Song {
    int length;
    String name;

    public Song(String length, String name) {
        setLength(length);
        this.name = name;
    }

    public Song(int length, String name) {
        setLength(length);
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLength(String length) {
        try{
            this.length = Integer.parseInt(length);
        }catch(NumberFormatException e){
            try {
                throw new SongLengthMustBeNumericException("You entered non numeric Song length");
            } catch (SongLengthMustBeNumericException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                 name  +
                "length: " + length + "s";
    }
}

