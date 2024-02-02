//package ru.develop.makeanappointment;
//
//import lombok.RequiredArgsConstructor;
//import ru.develop.makeanappointment.doctor.model.Doctor;
//import ru.develop.makeanappointment.doctor.storage.DoctorStorage;
//import ru.develop.makeanappointment.exception.NotFoundException;
//import ru.develop.makeanappointment.tickets.model.Rule;
//import ru.develop.makeanappointment.tickets.model.Ticket;
//import ru.develop.makeanappointment.tickets.storage.TicketStorage;
//
//import javax.jws.WebService;
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//@WebService(serviceName = "Slot",
//        portName = "SlotsPort",
//        targetNamespace = "http://service.ws.sample/",
//        endpointInterface = "ru.develop.makeanappointment.SlotsService")
//@RequiredArgsConstructor
//public class SlotServiceImpl implements SlotService {
//    private final DoctorStorage doctorStorage;
//    private final TicketStorage ticketStorage;
//
//    @Override
//    public void createSlots(Rule rule) {
//        LocalDateTime dateStartOfAdmission = rule.getBeginningOfTheSchedule();
//        Doctor doctor = rule.getDoctorId() != null ? doctorStorage.findById(rule.getDoctorId())
//                .orElseThrow(() -> new NotFoundException("Врач с id " + rule.getDoctorId() + " не найден.")) : null;
//        Duration duration = Duration.ofMinutes(rule.getSessionDuration());
//
//        for (int i = 1; i <= rule.getQuantityOfTickets(); i++) {
//            ticketStorage.save(Ticket.builder()
//                    .doctor(doctor)
//                    .patient(null)
//                    .dateStartOfAdmission(dateStartOfAdmission)
//                    .duration(duration)
//                    .cabinetNumber(rule.getCabinetNumber())
//                    .build());
//            dateStartOfAdmission = dateStartOfAdmission.plus(duration);
//        }
//    }
//}
