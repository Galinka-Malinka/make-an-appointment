package ru.develop.makeanappointment.tickets.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.Interval;
import org.hibernate.annotations.Type;
import ru.develop.makeanappointment.doctor.model.Doctor;
import ru.develop.makeanappointment.patient.model.Patient;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    @Column(name = "date_start_of_admission")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime dateStartOfAdmission;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "duration", columnDefinition = "interval")
    Duration duration;

    @Column(name = "cabinet_number")
    Integer cabinetNumber;
}
