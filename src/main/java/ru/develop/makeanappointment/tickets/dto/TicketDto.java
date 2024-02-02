package ru.develop.makeanappointment.tickets.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    Long id;

    Long doctorId;

    Long patientId;

    LocalDateTime dateStartOfAdmission;

    Duration duration;

    Integer cabinetNumber;
}
