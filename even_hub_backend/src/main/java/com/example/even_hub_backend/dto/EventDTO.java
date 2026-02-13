package com.example.even_hub_backend.dto;

import lombok.Data;

@Data 
public class EventDTO {
    private Long id;
    private String eventTitle;
    private String description;
    private Double ticketPrice;
    private String location;
    
}