package ru.develop.makeanappointment.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.develop.makeanappointment.patient.controller.PatientController;
import ru.develop.makeanappointment.patient.dto.PatientDto;
import ru.develop.makeanappointment.patient.service.PatientService;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @MockBean
    PatientService service;

    private final PatientDto patientDto = PatientDto.builder().name("Patient").build();

    @Test
    void shouldCreate() throws Exception {
        when(service.create(any())).thenReturn(patientDto);

        mvc.perform(post("/patient").content(objectMapper.writeValueAsString(patientDto))
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(patientDto.getName()), String.class));
    }

    @Test
    void shouldGet() throws Exception {
        when(service.get(anyLong())).thenReturn(patientDto);

        mvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(patientDto.getName()), String.class));
    }
}
