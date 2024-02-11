package com.example.ticketondemo.models;

import java.text.DateFormat;
import java.time.LocalDate;

public class Event {
    private Integer id;
    private float price;
    private int ageRestriction;
    private LocalDate premiereDate;
    private String eventType;

    public Event(Integer id, float price, int ageRestriction, LocalDate premiereDate, String eventType) {
        this.id = id;
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.premiereDate = premiereDate;
        this.eventType = eventType;
    }

    public Event() {
    }

    public Event(float price, int ageRestriction, LocalDate premiereDate, String eventType) {
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.premiereDate = premiereDate;
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDate premiereDate) {
        this.premiereDate = premiereDate;
    }
}
