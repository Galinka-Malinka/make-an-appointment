package ru.develop.makeanappointment.doctor.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.develop.makeanappointment.doctor.dto.DoctorDto;
import ru.develop.makeanappointment.doctor.model.Doctor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorMapper {
    public static Doctor toDoctor(@NotNull DoctorDto doctorDto) {
        return Doctor.builder()
                .uuid(UUID.randomUUID().toString())
                .name(doctorDto.getName())
                .build();
    }

    public static DoctorDto toDoctorDto(@NotNull Doctor doctor) {
        return DoctorDto.builder()
                .name(doctor.getName())
                .build();
    }

}
