package ru.develop.makeanappointment.tickets.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.develop.makeanappointment.doctor.model.Doctor;
import ru.develop.makeanappointment.doctor.storage.DoctorStorage;
import ru.develop.makeanappointment.exception.ConflictException;
import ru.develop.makeanappointment.exception.NotFoundException;
import ru.develop.makeanappointment.patient.model.Patient;
import ru.develop.makeanappointment.patient.storage.PatientStorage;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.mapper.TicketMapper;
import ru.develop.makeanappointment.tickets.model.Rule;
import ru.develop.makeanappointment.tickets.model.Ticket;
import ru.develop.makeanappointment.tickets.storage.TicketStorage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketStorage ticketStorage;
    private final DoctorStorage doctorStorage;
    private final PatientStorage patientStorage;

    @Override
    public void createSlots(Rule rule) {
        LocalDateTime dateStartOfAdmission = rule.getBeginningOfTheSchedule();
        Doctor doctor = rule.getDoctorId() != null ? doctorStorage.findById(rule.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Врач с id " + rule.getDoctorId() + " не найден.")) : null;
        Duration duration = Duration.ofMinutes(rule.getSessionDuration());

        for (int i = 1; i <= rule.getQuantityOfTickets(); i++) {
            ticketStorage.save(Ticket.builder()
                    .doctor(doctor)
                    .patient(null)
                    .dateStartOfAdmission(dateStartOfAdmission)
                    .duration(duration)
                    .cabinetNumber(rule.getCabinetNumber())
                    .build());
           dateStartOfAdmission = dateStartOfAdmission.plus(duration);
        }
    }

    @Override
    public List<TicketDto> getFreeSlotsToTheDoctor(Long id, LocalDate date) {
        return TicketMapper.toListTicketDto(ticketStorage
                .findAllByDoctorIdAndDateStartOfAdmissionAfterAndDateStartOfAdmissionBeforeAndPatientIsNull(id,
                        LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX)));
    }

    @Override
    public TicketDto makeAnAppointment(Long slotId, Long patientId) {
        Patient patient = patientStorage.findById(patientId)
                .orElseThrow(() -> new  NotFoundException("Пациент с id " + patientId + " не найден."));
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
        return null;
    }
}
