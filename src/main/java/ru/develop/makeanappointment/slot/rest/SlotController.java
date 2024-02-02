package ru.develop.makeanappointment.slot.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.develop.makeanappointment.slot.service.SlotService;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/slots")
public class SlotController {
    private final SlotService slotService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSlots(@RequestBody @Valid Rule rule) {
        slotService.createSlots(rule);
    }

    @GetMapping("/{doctorId}")
    public List<TicketDto> getFreeSlotsToTheDoctor(@PathVariable Long doctorId,
                                                   @RequestBody LocalDate date) {
        return slotService.getFreeSlotsToTheDoctor(doctorId, date);
    }
}
