package com.example.museum;

public class song {
    String Name;
    String Album;
    String Artist;

    public song(String name, String album, String artist) {
        Name = name;
        Album = album;
        Artist = artist;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }



}
