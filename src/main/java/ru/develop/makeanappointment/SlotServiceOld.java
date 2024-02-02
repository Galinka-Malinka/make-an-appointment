//package ru.develop.makeanappointment;
//
//import jakarta.xml.ws.RequestWrapper;
//import jakarta.xml.ws.ResponseWrapper;
//import ru.develop.makeanappointment.tickets.model.Rule;
//
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebResult;
//import javax.jws.WebService;
//
//@WebService(targetNamespace = "http://service.ws.sample/", name = "SlotService")
//public interface SlotService {
//
//    @WebResult(name = "return", targetNamespace = "")
//    @RequestWrapper(localName = "createSlots",
//            targetNamespace = "http://service.ws.sample/",
//            className = "ru.develop.makeanappointment.ws.SlotService")
//    @WebMethod(action = "urn:createSlots")
//    @ResponseWrapper(localName = "createSlotsResponse",
//            targetNamespace = "http://service.ws.sample/",
//            className = "ru.develop.makeanappointment.CreateSlotsResponse")
//    void createSlots(@WebParam(name = "rule", targetNamespace = "") Rule rule);
//}
