package ru.develop.makeanappointment.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.develop.makeanappointment.doctor.dto.DoctorDto;
import ru.develop.makeanappointment.doctor.service.DoctorService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto create(@RequestBody @Valid DoctorDto doctorDto) {
        return doctorService.create(doctorDto);
    }

    @GetMapping("/{id}")
    public DoctorDto get(@PathVariable Long id) {
        return doctorService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}
