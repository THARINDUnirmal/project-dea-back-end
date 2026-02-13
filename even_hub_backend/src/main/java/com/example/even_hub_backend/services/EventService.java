package com.example.even_hub_backend.services;


import com.example.even_hub_backend.models.Event;
import com.example.even_hub_backend.models.Speaker;
import com.example.even_hub_backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        // 1. Speakers ලැයිස්තුව null නෙමෙයි නම් විතරක් මේක කරන්න
        if (event.getSpeakers() != null) {
            for (Speaker speaker : event.getSpeakers()) {
                // 2. හැම speaker කෙනෙක්ටම මේ current event එක set කරන්න
                speaker.setEvent(event);
            }
        }
        // 3. දැන් event එක save කරන්න (CascadeType.ALL නිසා speakers ලාත් save වෙයි)
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setEventTitle(eventDetails.getEventTitle());
        event.setEventImageUrl(eventDetails.getEventImageUrl());
        event.setDate(eventDetails.getDate());
        event.setStartTime(eventDetails.getStartTime());
        event.setEndTime(eventDetails.getEndTime());
        event.setDescription(eventDetails.getDescription());
        event.setTicketPrice(eventDetails.getTicketPrice());
        event.setContactDetails(eventDetails.getContactDetails());
        event.setLocation(eventDetails.getLocation());

        return eventRepository.save(event);
    }


}