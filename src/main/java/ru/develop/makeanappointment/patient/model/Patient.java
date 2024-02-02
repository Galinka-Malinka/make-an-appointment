package ru.develop.makeanappointment.patient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "patients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "uuid", nullable = false)
    String uuid;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "birthday", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    Date birthday;
}
