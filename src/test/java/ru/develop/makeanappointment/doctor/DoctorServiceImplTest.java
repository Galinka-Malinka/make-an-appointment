package ru.develop.makeanappointment.doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.develop.makeanappointment.doctor.dto.DoctorDto;
import ru.develop.makeanappointment.exception.NotFoundException;
import ru.develop.makeanappointment.doctor.model.Doctor;
import ru.develop.makeanappointment.doctor.service.DoctorService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        properties = "db.name=test",
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DoctorServiceImplTest {

    private final EntityManager entityManager;

    private final DoctorService service;

    @Test
    void shouldCreate() {
        DoctorDto doctorDto = DoctorDto.builder()
                .name("Doctor")
                .build();

        service.create(doctorDto);
        TypedQuery<Doctor> query = entityManager.createQuery("Select d from Doctor d where d.id = :id", Doctor.class);
        Doctor doctor = query.setParameter("id", 1).getSingleResult();
        assertThat(doctor.getId(), notNullValue());
        assertThat(doctor.getId(), is(1L));
        assertThat(doctor.getUuid(), notNullValue());
        assertThat(doctor.getName(), equalTo(doctor.getName()));
    }

    @Test
    void shouldGet() {
        DoctorDto doctorDto = DoctorDto.builder()
                .name("Doctor")
                .build();

        service.create(doctorDto);
        DoctorDto foundDoctor = service.get(1L);

        assertThat(foundDoctor.getName(), equalTo(doctorDto.getName()));

        assertThrows(NotFoundException.class, () -> service.get(2L), "Врач с id 2 не найден.");
    }

    @Test
    void shouldDelete() {
        service.create(DoctorDto.builder().name("Doctor").build());
        service.delete(1L);

        assertThrows(NotFoundException.class, () -> service.delete(1L), "Врач с id 1 не найден.");
    }

}
