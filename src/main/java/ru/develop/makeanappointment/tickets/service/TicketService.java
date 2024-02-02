package ru.develop.makeanappointment.tickets.service;

import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    void createSlots(Rule rule);

    List<TicketDto> getFreeSlotsToTheDoctor(Long id, LocalDate date);

    TicketDto makeAnAppointment(Long slotId, Long patientId);

    void cancelAnAppointment(Long ticketId);

    List<TicketDto> getTicketsForPatient(Long patientId);

    List<TicketDto> getTicketsForPatient(String uuid);
}
