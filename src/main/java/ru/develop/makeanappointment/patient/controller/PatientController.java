package ru.develop.makeanappointment.patient.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.develop.makeanappointment.patient.dto.PatientDto;
import ru.develop.makeanappointment.patient.service.PatientService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/patient")
@Slf4j
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto create(@RequestBody @Valid PatientDto patientDto) {
        log.info("Добавление пациента {}", patientDto);
        return patientService.create(patientDto);
    }

    @GetMapping("/{id}")
    public PatientDto get(@PathVariable Long id) {
        log.info("Получение данных пациента с id {}", id);
        return patientService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Удаление пациента с id {}", id);
        patientService.delete(id);
    }
}
