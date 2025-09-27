package com.example.microservicios.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "section",
        uniqueConstraints = @UniqueConstraint(name="uk_section_code_per_run", columnNames = {"course_run_id","section_code"})
)
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // EAGER para que se serialice sin proxys
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_run_id", nullable = false)
    private CourseRun courseRun;

    @Column(name = "section_code", nullable = false, length = 20)
    private String sectionCode; // p.ej. "A", "B1"

    @Column(name = "instructor_uid", nullable = false, length = 50)
    private String instructorUid; // lo dejamos String (sin UUID)

    private String room;

    // opcional: guarda JSON como String (más simple)
    @Column(name = "schedule", columnDefinition = "text")
    private String schedule; // ej: {"days":["MON","WED"],"time":"18:00-20:00"}
}