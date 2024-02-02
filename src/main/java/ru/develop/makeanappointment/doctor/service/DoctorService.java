package ru.develop.makeanappointment.doctor.service;

import ru.develop.makeanappointment.doctor.dto.DoctorDto;

public interface DoctorService {

    public DoctorDto create(DoctorDto doctorDto);

    public DoctorDto get(Long id);

    public void delete(Long id);
}
