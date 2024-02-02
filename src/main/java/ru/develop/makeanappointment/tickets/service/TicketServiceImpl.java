package ru.develop.makeanappointment.tickets.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.develop.makeanappointment.exception.ConflictException;
import ru.develop.makeanappointment.exception.NotFoundException;
import ru.develop.makeanappointment.patient.model.Patient;
import ru.develop.makeanappointment.patient.storage.PatientStorage;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.mapper.TicketMapper;
import ru.develop.makeanappointment.tickets.model.Ticket;
import ru.develop.makeanappointment.tickets.storage.TicketStorage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketStorage ticketStorage;
    private final PatientStorage patientStorage;

    @Override
    public TicketDto makeAnAppointment(Long slotId, Long patientId) {
        Patient patient = patientStorage.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Пациент с id " + patientId + " не найден."));
        Ticket ticket = ticketStorage.findById(slotId)
                .orElseThrow(() -> new NotFoundException("Слот с id " + slotId + " не найден."));
        if (ticket.getPatient() != null) {
            throw new ConflictException("Слот с id " + slotId + " уже занят.");
        }
        ticket.setPatient(patient);
        return TicketMapper.toTicketDto(ticketStorage.save(ticket));
    }

    @Override
    public void cancelAnAppointment(Long ticketId) {
        Ticket ticket = ticketStorage.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Талон с id " + ticketId + " не найден."));
        ticket.setPatient(null);
        TicketMapper.toTicketDto(ticketStorage.save(ticket));
    }

    @Override
    public List<TicketDto> getTicketsForPatient(Long patientId) {
        if (!patientStorage.existsById(patientId)) {
            throw new NotFoundException("Пациент с id " + patientId + " не найден.");
        }
        return TicketMapper.toListTicketDto(ticketStorage.findAllByPatientId(patientId));
    }

    @Override
    public List<TicketDto> getTicketsForPatient(String uuid) {
        if (!patientStorage.existsByUuid(uuid)) {
            throw new NotFoundException("Пациент с uuid " + uuid + " не найден.");
        }
        return TicketMapper.toListTicketDto(ticketStorage.findAllByPatientUuid(uuid));
    }
}
