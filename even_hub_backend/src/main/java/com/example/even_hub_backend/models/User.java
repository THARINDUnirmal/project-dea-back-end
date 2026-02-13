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
    private String password; // Login වලට අත්‍යවශ්‍යයි
    private String role; // ex: admin, user

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user") // Event ඇතුළේ තියෙන user ආයෙත් ගන්න එපා කියන්න
    private List<Event> events;

    // User.java ඇතුළත
    public Long getUserId() {
        return this.id; // ඔබේ variable එකේ නම 'id' නම් 'return this.id;' ලෙස යොදන්න
    }
}