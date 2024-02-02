package ru.develop.makeanappointment.tickets.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.develop.makeanappointment.doctor.model.Doctor;
import ru.develop.makeanappointment.patient.model.Patient;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;
import ru.develop.makeanappointment.tickets.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketMapper {

    public static TicketDto toTicketDto(Ticket ticket) {
        Long doctorId = ticket.getDoctor() != null ? ticket.getDoctor().getId() : null;
        Long patientId = ticket.getPatient() != null ? ticket.getPatient().getId() : null;
        Integer cabinetNumber = ticket.getCabinetNumber() != null ? ticket.getCabinetNumber() : null;

        return TicketDto.builder()
                .id(ticket.getId())
                .doctorId(doctorId)
                .patientId(patientId)
                .dateStartOfAdmission(ticket.getDateStartOfAdmission())
                .duration(ticket.getDuration())
                .cabinetNumber(cabinetNumber)
                .build();
    }

    public static List<TicketDto> toListTicketDto(List<Ticket> ticketList) {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDtoList.add(toTicketDto(ticket));
        }
        return ticketDtoList;
    }

//    public static Ticket toTicket(Doctor doctor, LocalDateTime dataStartOfAdmission, Duration duration, Integer cabinetNumber) {
//        return Ticket.builder()
//                .doctor(doctor)
//                .patient(null)
//                .dateStartOfAdmission(dataStartOfAdmission)
//                .duration(duration)
//                .cabinetNumber(cabinetNumber)
//                .build();
//    }

}
