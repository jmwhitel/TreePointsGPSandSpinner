package com.example.josephwhiteley.treepointsgpsandspinner;

public class User {
    private String Northing;
    private String Easting;
    private String Elevation;

    public User(String northing, String easting, String elevation){
        Northing = northing;
        Easting = easting;
        Elevation = elevation;
    }

    public String getNorthing() {
        return Northing;
    }

    public String getEasting() {
        return Easting;
    }

    public String getElevation() {
        return Elevation;
    }
}
