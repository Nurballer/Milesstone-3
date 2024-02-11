package com.example.ticketondemo.repository;

import com.example.ticketondemo.dto.EventDto;
import com.example.ticketondemo.models.Cinema;
import com.example.ticketondemo.models.Concert;
import com.example.ticketondemo.models.Event;
import com.example.ticketondemo.models.EventType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class EventRepository {
    final
    JdbcTemplate jdbcTemplate;

    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createEvent(Event event) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into events (price, age_restriction, premiere_date, event_type)" +
                    "VALUES (?, ?, ?, ?)", new String[]{"id"});
            ps.setFloat(1, event.getPrice());
            ps.setInt(2, event.getAgeRestriction());
            ps.setDate(3, Date.valueOf(event.getPremiereDate()));
            ps.setString(4, event.getEventType());
            return ps;
        }, keyHolder);

        if (event instanceof Cinema) {
            jdbcTemplate.update("insert into cinemas(event_id ,country_manufacture, director, release_year)" +
                    "VALUES (?, ?, ?)", keyHolder.getKey(), ((Cinema) event).getCountryOfManufacture(),
                    ((Cinema) event).getDirector(), ((Cinema) event).getReleaseYear());
        } else {
            Concert concert = (Concert) event;
            jdbcTemplate.update("insert into events(event_id, address, starting_time, duration)" +
                    "VALUES (?, ?, ?, ?)", keyHolder.getKey(), concert.getAddress(), concert.getStartingTime(), concert.getDuration());
        }
    }

    public List<Event> getAllEvents() {
        return jdbcTemplate.query(
                "SELECT price, age_restriction, date, event_type FROM events",
                (rs, rowNum) -> new Event(rs.getFloat("price"),
                        rs.getInt("age_restriction"), rs.getDate("date").toLocalDate(),
                        rs.getString("event_type")));
    }

    public Event getEventById(int id) {
        List<Event> events = jdbcTemplate.query("SELECT price, age_restriction, date, event_type FROM events where id=?",
                (rs, rowNum) -> new Event(rs.getFloat("price"),
                        rs.getInt("age_restriction"), rs.getDate("date").toLocalDate(),
                        rs.getString("event_type")), id);
        if (events.isEmpty()) {
            throw new NoSuchElementException();
        }
        Event event = events.get(0);

        if (event.getEventType().equals(EventType.CINEMA)) {
            List<Cinema> cinemas = jdbcTemplate.query("SELECT country_manufacture, director, release_year FROM cinemas where event_id=?",
                    (rs, rowNum) -> new Cinema(event, rs.getString("country_manufacture"),
                            rs.getString("director"), rs.getInt("release_year")), id);
            if (cinemas.isEmpty()) {
                throw new NoSuchElementException();
            }
            return cinemas.get(0);
        }
        List<Concert> concerts = jdbcTemplate.query("SELECT address, starting_time, duration FROM concerts where event_id=?",
                (rs, rowNum) -> new Concert(event, rs.getString("address"),
                        rs.getString("starting_time"), rs.getString("duration")), id);
        if (concerts.isEmpty()) {
            throw new NoSuchElementException();
        }
        return concerts.get(0);

    }
}
