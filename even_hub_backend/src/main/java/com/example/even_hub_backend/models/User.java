package com.example.even_hub_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String userEmail;
    private String password;
    private String role; 

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user") 
    private List<Event> events;


    public Long getUserId() {
        return this.id; 
    }
}