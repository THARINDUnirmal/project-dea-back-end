package com.example.even_hub_backend.controllers;

import com.example.even_hub_backend.models.Event;
import com.example.even_hub_backend.models.User;
import com.example.even_hub_backend.repository.EventRepository;
import com.example.even_hub_backend.repository.UserRepository;
import com.example.even_hub_backend.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository; 

 
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  
    @GetMapping("/my-events")
    public ResponseEntity<List<Event>> getMyEvents(Authentication authentication) {
        String currentUserName = authentication.getName();
        List<Event> myEvents = eventRepository.findByUserUserName(currentUserName);
        return ResponseEntity.ok(myEvents);
    }


    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails, Authentication authentication) {
      
        String currentUserName = authentication.getName();
        User currentUser = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new RuntimeException("User not found"));

       
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

       
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase("ADMIN") ||
                        a.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));

       
        if (isAdmin || existingEvent.getUser().getId().equals(currentUser.getId())) {

           
            eventDetails.setUser(existingEvent.getUser());

            Event updatedEvent = eventService.updateEvent(id, eventDetails);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Permission Denied!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id, Authentication authentication) {
        String currentUserName = authentication.getName();
        User currentUser = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase("ADMIN") ||
                        a.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));

        if (isAdmin || existingEvent.getUser().getId().equals(currentUser.getId())) {
            eventRepository.deleteById(id);
            return ResponseEntity.ok("Removed Done!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Permission Denied!");
        }
    }
}