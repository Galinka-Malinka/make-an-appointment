package ru.develop.makeanappointment.ws;

import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;
import ru.develop.makeanappointment.tickets.model.Ticket;

import java.util.List;

public interface SlotService {
    String createSlots(Rule rule);
}
