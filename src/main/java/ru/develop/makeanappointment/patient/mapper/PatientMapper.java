package ru.develop.makeanappointment.patient.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.develop.makeanappointment.patient.dto.PatientDto;
import ru.develop.makeanappointment.patient.model.Patient;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientMapper {
    public static Patient toPatient(@NotNull PatientDto patientDto) {
        return Patient.builder()
                .name(patientDto.getName())
                .uuid(UUID.randomUUID().toString())
                .birthday(patientDto.getBirthday())
                .build();
    }

    public static PatientDto toPatientDto(@NotNull Patient patient) {
        return PatientDto.builder()
                .name(patient.getName())
                .birthday(patient.getBirthday())
                .build();
    }
}
