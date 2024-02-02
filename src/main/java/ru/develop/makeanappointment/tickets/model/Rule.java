package ru.develop.makeanappointment.tickets.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rule {

    @NotNull
    @Future
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime beginningOfTheSchedule;

    @NotNull
    Long sessionDuration;

    @NotNull
    Integer quantityOfTickets;

    Long doctorId;

    Integer cabinetNumber;

}
