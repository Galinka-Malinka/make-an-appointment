//package ru.develop.makeanappointment;
//
//
//import lombok.RequiredArgsConstructor;
//import org.apache.cxf.Bus;
//import javax.xml.ws.Endpoint;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ru.develop.makeanappointment.doctor.storage.DoctorStorage;
//import ru.develop.makeanappointment.tickets.storage.TicketStorage;
//
//@Configuration
//@RequiredArgsConstructor
//public class WSConfig {
//
//    @Autowired
//    private Bus bus;
//
//    private final DoctorStorage doctorStorage;
//    private final TicketStorage ticketStorage;
//
//    @Bean
//    public Endpoint slotsEndpoint() {
//        EndpointImpl endpoint = new EndpointImpl(bus, new SlotServiceImpl(doctorStorage, ticketStorage));
//        endpoint.publish("/Slots");
//        return endpoint;
//    }
//}
