package ru.develop.makeanappointment.tickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.develop.makeanappointment.ws.SlotService;
import ru.develop.makeanappointment.tickets.dto.TicketDto;
import ru.develop.makeanappointment.tickets.model.Rule;

import java.util.List;

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

//private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";
//
//    private CountryRepository countryRepository;
//
//    @Autowired
//    public CountryEndpoint(CountryRepository countryRepository) {
//        this.countryRepository = countryRepository;
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
//    @ResponsePayload
//    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//        GetCountryResponse response = new GetCountryResponse();
//        response.setCountry(countryRepository.findCountry(request.getName()));
//
//        return response;
//    }
