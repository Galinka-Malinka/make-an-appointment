package ru.develop.makeanappointment.doctor.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.develop.makeanappointment.doctor.model.Doctor;

public interface DoctorStorage extends JpaRepository<Doctor, Long> {
}
