package ru.develop.makeanappointment.patient.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.develop.makeanappointment.patient.model.Patient;

public interface PatientStorage extends JpaRepository<Patient, Long> {
}
