package com.example.museum;

import java.io.Serializable;

public class song implements Serializable {
    private String Name;
    private String Album;
    private String Url;

    public song(String name, String album, String url) {
        Name = name;
        Album = album;
        Url = url;
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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String artist) {
        Url = artist;
    }





}
