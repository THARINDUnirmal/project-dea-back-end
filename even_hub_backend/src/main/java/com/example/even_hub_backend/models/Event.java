package com.example.even_hub_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTitle;
    private String eventImageUrl;
    private String date;
    private String startTime;
    private String endTime;

    @Column(length = 1000)
    private String description;

    private double ticketPrice;
    private String contactDetails;
    private String location;

 
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false) 
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Speaker> speakers;
}