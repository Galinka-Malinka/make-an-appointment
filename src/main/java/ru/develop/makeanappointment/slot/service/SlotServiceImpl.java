package ru.develop.makeanappointment.slot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.develop.makeanappointment.doctor.model.Doctor;
import ru.develop.makeanappointment.doctor.storage.DoctorStorage;
import ru.develop.makeanappointment.exception.NotFoundException;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.mapper.TicketMapper;
import ru.develop.makeanappointment.tickets.model.Rule;
import ru.develop.makeanappointment.tickets.model.Ticket;
import ru.develop.makeanappointment.tickets.storage.TicketStorage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {
    private final DoctorStorage doctorStorage;
    public final TicketStorage ticketStorage;

    @Override
    public String createSlots(Rule rule) {
        List<Ticket> result = new ArrayList<>();
        LocalDateTime dateStartOfAdmission = rule.getBeginningOfTheSchedule();
        Doctor doctor = rule.getDoctorId() != null ? doctorStorage.findById(rule.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Врач с id " + rule.getDoctorId() + " не найден.")) : null;
        Duration duration = Duration.ofMinutes(rule.getSessionDuration());

        for (int i = 1; i <= rule.getQuantityOfTickets(); i++) {
            result.add(ticketStorage.save(Ticket.builder()
                    .doctor(doctor)
                    .patient(null)
                    .dateStartOfAdmission(dateStartOfAdmission)
                    .duration(duration)
                    .cabinetNumber(rule.getCabinetNumber())
                    .build()));
            dateStartOfAdmission = dateStartOfAdmission.plus(duration);
        }
        return "Слоты времени по заданым правилам " + rule + " созданы.";
    }

    @Override
    public List<TicketDto> getFreeSlotsToTheDoctor(Long id, LocalDate date) {
        return TicketMapper.toListTicketDto(ticketStorage
                .findAllByDoctorIdAndDateStartOfAdmissionAfterAndDateStartOfAdmissionBeforeAndPatientIsNull(id,
                        LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX)));
    }
}
