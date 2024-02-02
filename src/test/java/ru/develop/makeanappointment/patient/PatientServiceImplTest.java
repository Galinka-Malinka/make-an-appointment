package ru.develop.makeanappointment.patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.develop.makeanappointment.exception.NotFoundException;
import ru.develop.makeanappointment.patient.dto.PatientDto;
import ru.develop.makeanappointment.patient.model.Patient;
import ru.develop.makeanappointment.patient.service.PatientService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = "db.name=test",
webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PatientServiceImplTest {

    private final EntityManager entityManager;

    private final PatientService service;

    @Test
    void shouldCreate() {
        PatientDto patientDto = PatientDto.builder()
                .name("Patient")
                .build();

        service.create(patientDto);
        TypedQuery<Patient> query = entityManager
                .createQuery("Select p from Patient p where p.id = :id", Patient.class);
        Patient patient = query.setParameter("id", 1L).getSingleResult();
        assertThat(patient.getId(), notNullValue());
        assertThat(patient.getId(), is(1L));
        assertThat(patient.getUuid(), notNullValue());
        assertThat(patient.getName(), equalTo(patientDto.getName()));
    }

    @Test
    void shouldGet() {
        PatientDto patientDto = PatientDto.builder()
                .name("Patient")
                .build();
        service.create(patientDto);
        PatientDto foundPatient = service.get(1L);
        assertThat(foundPatient, notNullValue());
        assertThat(foundPatient.getName(), equalTo(patientDto.getName()));

        assertThrows(NotFoundException.class, () -> service.get(2L), "Пациента с id 2 не существует.");
    }

    @Test
    void shouldDelete() {
        service.create(PatientDto.builder().name("Patient").build());
        service.delete(1L);

        assertThrows(NotFoundException.class, () -> service.delete(1L), "Пациента с id 1 не существует.");
    }
}
