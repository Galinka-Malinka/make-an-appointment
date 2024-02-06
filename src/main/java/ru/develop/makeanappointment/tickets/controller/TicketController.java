package ru.develop.makeanappointment.tickets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.service.TicketService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final TicketService ticketService;

    @PatchMapping ("/ticket/{slotId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto makeAnAppointment(@PathVariable Long slotId, @RequestParam @NotNull Long patientId) {
        log.info("Запись на приём пациента с id {} на слот с id {}", patientId, slotId);
        return ticketService.makeAnAppointment(slotId, patientId);
    }

    @DeleteMapping("/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelAnAppointment(@PathVariable Long ticketId) {
        log.info("Отмена записи на слот с id {}", ticketId);
        ticketService.cancelAnAppointment(ticketId);
    }

    @GetMapping("/ticket/patient/id")
    public List<TicketDto> getTicketsForPatient(@RequestParam @NotNull Long patientId) {
        log.info("Получение всех записей на приём пациента с id {}", patientId);
        return ticketService.getTicketsForPatient(patientId);
    }

    @GetMapping("/ticket/patient/uuid")
    public List<TicketDto> getTicketForPatient(@RequestParam @NotNull String uuid) {
        log.info("Получение всех записей на приём пациента с uuid {}", uuid);
        return ticketService.getTicketsForPatient(uuid);
    }
}
