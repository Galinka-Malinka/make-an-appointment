package ru.develop.makeanappointment.tickets.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;
import ru.develop.makeanappointment.tickets.service.TicketService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/slots")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSlots(@RequestBody @Valid Rule rule) {
        ticketService.createSlots(rule);
    }

    @GetMapping("/slots/{doctorId}")
    public List<TicketDto> getFreeSlotsToTheDoctor(@PathVariable Long doctorId,
                                                   @RequestBody LocalDate date) {
        return ticketService.getFreeSlotsToTheDoctor(doctorId, date);
    }

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

    @GetMapping("/ticket")
    public List<TicketDto> getTicketForPatient(@RequestParam String uuid) {
        return ticketService.getTicketsForPatient(uuid);
    }
}
