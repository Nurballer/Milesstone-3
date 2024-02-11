package com.example.ticketondemo.controller;

import com.example.ticketondemo.dto.EventDto;
import com.example.ticketondemo.models.Cinema;
import com.example.ticketondemo.models.Concert;
import com.example.ticketondemo.models.Event;
import com.example.ticketondemo.models.EventType;
import com.example.ticketondemo.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(eventRepository.getEventById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("no such element");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createEvent(@RequestBody EventDto eventDto) {
        try {
            if (eventDto.premiereDate() == null
            || eventDto.eventType() == null) {

                return ResponseEntity.badRequest().build();
            }
            Event event = new Event(eventDto.price(), eventDto.ageRestriction(), eventDto.premiereDate(), eventDto.eventType().toString());
            if (eventDto.eventType().equals(EventType.CINEMA)) {
                if (eventDto.countryOfManufacture() == null ||
                        eventDto.director() == null || eventDto.releaseYear() == null) {

                    return ResponseEntity.badRequest().build();
                }
                Cinema cinema = (Cinema) event;
                cinema.setCountryOfManufacture(eventDto.countryOfManufacture());
                cinema.setDirector(eventDto.director());
                cinema.setReleaseYear(eventDto.releaseYear());
                eventRepository.createEvent(cinema);
            } else if (eventDto.eventType().equals(EventType.CONCERT)) {
                if (eventDto.address() == null ||
                        eventDto.startingTime() == null || eventDto.duration() == null) {
                    return ResponseEntity.badRequest().build();
                }
                Concert concert = (Concert) event;
                concert.setAddress(eventDto.address());
                concert.setStartingTime(eventDto.startingTime());
                concert.setDuration(eventDto.duration());
                eventRepository.createEvent(concert);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }



}
