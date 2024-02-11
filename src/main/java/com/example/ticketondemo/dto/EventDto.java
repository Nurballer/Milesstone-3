package com.example.ticketondemo.dto;

import com.example.ticketondemo.models.EventType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public final class EventDto {
    private final float price;
    private final int ageRestriction;
    private final LocalDate premiereDate;
    private final EventType eventType;
    private final String address;
    private final String startingTime;
    private final String duration;
    private final String countryOfManufacture;
    private final String director;
    private final Integer releaseYear;

    public EventDto(float price, int ageRestriction, LocalDate premiereDate, EventType eventType, String address,
                    String startingTime, String duration, String countryOfManufacture, String director,
                    Integer releaseYear) {
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.premiereDate = premiereDate;
        this.eventType = eventType;
        this.address = address;
        this.startingTime = startingTime;
        this.duration = duration;
        this.countryOfManufacture = countryOfManufacture;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public float price() {
        return price;
    }

    public int ageRestriction() {
        return ageRestriction;
    }

    public LocalDate premiereDate() {
        return premiereDate;
    }

    public EventType eventType() {
        return eventType;
    }

    public String address() {
        return address;
    }

    public String startingTime() {
        return startingTime;
    }

    public String duration() {
        return duration;
    }

    public String countryOfManufacture() {
        return countryOfManufacture;
    }

    public String director() {
        return director;
    }

    public Integer releaseYear() {
        return releaseYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (EventDto) obj;
        return Float.floatToIntBits(this.price) == Float.floatToIntBits(that.price) &&
                this.ageRestriction == that.ageRestriction &&
                Objects.equals(this.premiereDate, that.premiereDate) &&
                Objects.equals(this.eventType, that.eventType) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.startingTime, that.startingTime) &&
                Objects.equals(this.duration, that.duration) &&
                Objects.equals(this.countryOfManufacture, that.countryOfManufacture) &&
                Objects.equals(this.director, that.director) &&
                Objects.equals(this.releaseYear, that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, ageRestriction, premiereDate, eventType, address, startingTime, duration, countryOfManufacture, director, releaseYear);
    }

    @Override
    public String toString() {
        return "EventDto[" +
                "price=" + price + ", " +
                "ageRestriction=" + ageRestriction + ", " +
                "premiereDate=" + premiereDate + ", " +
                "eventType=" + eventType + ", " +
                "address=" + address + ", " +
                "startingTime=" + startingTime + ", " +
                "duration=" + duration + ", " +
                "countryOfManufacture=" + countryOfManufacture + ", " +
                "director=" + director + ", " +
                "releaseYear=" + releaseYear + ']';
    }


}
