package ru.develop.makeanappointment.tickets.service;

import ru.develop.makeanappointment.tickets.dto.TicketDto;

import java.util.List;

public interface TicketService {

    TicketDto makeAnAppointment(Long slotId, Long patientId);

    void cancelAnAppointment(Long ticketId);

    List<TicketDto> getTicketsForPatient(Long patientId);

    List<TicketDto> getTicketsForPatient(String uuid);
}
