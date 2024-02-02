package ru.develop.makeanappointment.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.develop.makeanappointment.doctor.controller.DoctorController;
import ru.develop.makeanappointment.doctor.dto.DoctorDto;
import ru.develop.makeanappointment.doctor.service.DoctorService;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DoctorController.class)
public class DoctorControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @MockBean
    DoctorService service;

    private final DoctorDto doctorDto = DoctorDto.builder().name("Doctor").build();

    @Test
    void shouldCreate() throws Exception {
        when(service.create(any())).thenReturn(doctorDto);

        mvc.perform(post("/doctor").content(objectMapper.writeValueAsString(doctorDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(doctorDto.getName()), String.class));
    }

    @Test
    void shouldGet() throws Exception {
        when(service.get(anyLong())).thenReturn(doctorDto);

        mvc.perform(get("/doctor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(doctorDto.getName()), String.class));
    }
}
