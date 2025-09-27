package com.example.microservicios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;


    @Column(nullable = false, length = 200)
    private String title;


    @Column(columnDefinition = "text")
    private String description;


    private String category; // e.g. "CS", "Math"
    private String level; // e.g. "Intro", "Advanced"
    private Integer hours;
    private Integer credits;


    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = OffsetDateTime.now();
    }

}
