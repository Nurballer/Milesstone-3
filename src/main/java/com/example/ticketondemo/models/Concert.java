package com.example.ticketondemo.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {

    private String address;
    private String startingTime;
    private String duration;

    public Concert(Event event, String address, String startingTime, String duration) {
        super(event.getPrice(), event.getAgeRestriction(), event.getPremiereDate(), event.getEventType());
        this.address = address;
        this.startingTime = startingTime;
        this.duration = duration;
    }

    public Concert() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
