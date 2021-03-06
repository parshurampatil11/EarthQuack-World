package com.example.earthquakereport.ConnectAll;

public class Model {

    private double mag;
    private String place;
    private String url;
    private long time;


    public Model(double mag,String place, String url, long time) {
        this.mag = mag;
        this.place = place;
        this.url = url;
        this.time = time;
    }

    public Model(String place){
        this.place = place;
    }

    public Double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public String getUrl() {
        return url;
    }

    public long getTime() {
        return time;
    }
}
