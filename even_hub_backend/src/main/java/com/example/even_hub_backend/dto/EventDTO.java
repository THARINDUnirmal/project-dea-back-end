package com.example.even_hub_backend.dto;

import lombok.Data;

@Data // Lombok පාවිච්චි කරනවා නම් (Getters/Setters සඳහා)
public class EventDTO {
    private Long id;
    private String eventTitle;
    private String description;
    private Double ticketPrice;
    private String location;
    // මෙතනට ඔයාට අවශ්‍ය fields විතරක් එකතු කරන්න
}