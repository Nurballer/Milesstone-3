package com.example.ticketondemo.models;

import java.time.LocalDate;

public class Cinema extends Event {
    private String countryOfManufacture;
    private String director;
    private int releaseYear;

    public Cinema(Event event, String countryOfManufacture, String director, int releaseYear) {
        super(event.getPrice(), event.getAgeRestriction(), event.getPremiereDate(), event.getEventType());
        this.countryOfManufacture = countryOfManufacture;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public Cinema() {
    }

    public String getCountryOfManufacture() {
        return countryOfManufacture;
    }

    public void setCountryOfManufacture(String countryOfManufacture) {
        this.countryOfManufacture = countryOfManufacture;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
