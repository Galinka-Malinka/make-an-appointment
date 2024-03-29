package ru.develop.makeanappointment.slot.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.develop.makeanappointment.slot.service.SlotService;
import ru.develop.makeanappointment.tickets.model.Rule;

@Endpoint
public class SlotEndpoint {
    private static final String NAMESPACE_URI = "http://www.our_uri.ru";

    private final SlotService slotService;

    @Autowired
    public SlotEndpoint(SlotService slotService) {
        this.slotService = slotService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createSlotRequest")
    @ResponsePayload
    public String createSlot(@RequestPayload Rule rule) {
        return slotService.createSlots(rule);
    }
}
