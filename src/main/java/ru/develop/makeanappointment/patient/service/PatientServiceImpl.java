package ru.develop.makeanappointment.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.develop.makeanappointment.exception.NotFoundException;
import ru.develop.makeanappointment.patient.dto.PatientDto;
import ru.develop.makeanappointment.patient.mapper.PatientMapper;
import ru.develop.makeanappointment.patient.storage.PatientStorage;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientStorage patientStorage;

    @Override
    public PatientDto create(PatientDto patientDto) {
        return PatientMapper.toPatientDto(patientStorage.save(PatientMapper.toPatient(patientDto)));
    }

    @Override
    public PatientDto get(Long id) {
        return PatientMapper.toPatientDto(patientStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Пациента с id " + id + " не существует.")));
    }

    @Override
    public void delete(Long id) {
        if (!patientStorage.existsById(id)) {
            throw new NotFoundException("Пациента с id " + id + " не существует.");
        }
        patientStorage.deleteById(id);
    }
}
