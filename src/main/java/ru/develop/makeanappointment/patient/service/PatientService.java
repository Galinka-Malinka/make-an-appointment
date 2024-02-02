package ru.develop.makeanappointment.patient.service;

import ru.develop.makeanappointment.patient.dto.PatientDto;

public interface PatientService {
    PatientDto create(PatientDto patientDto);

    PatientDto get(Long id);

    void delete(Long id);
}
