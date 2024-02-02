package ru.develop.makeanappointment.doctor.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    @NotNull(message = "Необходимо указать имя врача")
    @NotBlank(message = "Имя врача не может состоять из пустой строки")
    String name;
}
