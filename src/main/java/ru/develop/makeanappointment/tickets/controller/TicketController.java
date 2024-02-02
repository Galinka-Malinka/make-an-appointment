package ru.develop.makeanappointment.tickets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.service.TicketService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket/{slotId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto makeAnAppointment(@PathVariable Long slotId, @RequestParam @NotNull Long patientId) {
        return ticketService.makeAnAppointment(slotId, patientId);
    }

    @DeleteMapping("/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelAnAppointment(@PathVariable Long ticketId) {
        ticketService.cancelAnAppointment(ticketId);
    }

    @GetMapping("/ticket/patient/id")
    public List<TicketDto> getTicketsForPatient(@RequestParam @NotNull Long patientId) {
        return ticketService.getTicketsForPatient(patientId);
    }

    @GetMapping("/ticket/patient/uuid")
    public List<TicketDto> getTicketForPatient(@RequestParam @NotNull String uuid) {
        return ticketService.getTicketsForPatient(uuid);
    }
}
