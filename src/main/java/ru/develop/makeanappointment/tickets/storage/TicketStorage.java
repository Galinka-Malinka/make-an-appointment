package ru.develop.makeanappointment.tickets.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.develop.makeanappointment.tickets.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketStorage extends JpaRepository<Ticket, Long> {

    List<Ticket>
    findAllByDoctorIdAndDateStartOfAdmissionAfterAndDateStartOfAdmissionBeforeAndPatientIsNull(Long doctorId,
                                                                                               LocalDateTime min,
                                                                                               LocalDateTime max);

    List<Ticket> findAllByPatientId(Long patientId);
}
