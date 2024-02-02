package ru.develop.makeanappointment.slot.service;

import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;

import java.time.LocalDate;
import java.util.List;

public interface SlotService {
    String createSlots(Rule rule);

    List<TicketDto> getFreeSlotsToTheDoctor(Long id, LocalDate date);
}
