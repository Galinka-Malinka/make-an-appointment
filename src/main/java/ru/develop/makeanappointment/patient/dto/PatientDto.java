package ru.develop.makeanappointment.patient.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientDto {
    @NotNull(message = "Необходимо указать имя пользователя")
    @NotBlank(message = "Имя пользователя не может состоять из пустой строки")
    String name;

    @NotNull
    Date birthday;
}
