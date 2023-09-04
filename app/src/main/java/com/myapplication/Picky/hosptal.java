package com.myapplication.Picky;

public class hosptal {
    String username,lon,lad;

    hosptal(){

    }
// it is made to create a new hospital in the doctor side with (name, latitude,longitude)

    public hosptal(String username, String lon, String lad) {
        this.username = username;
        this.lon = lon;
        this.lad = lad;

    }

    public String getUsername() {
        return username;
    }

    public String getLon() {
        return lon;
    }

    public String getLad() {
        return lad;
    }

}
