package ru.develop.makeanappointment.doctor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.develop.makeanappointment.doctor.dto.DoctorDto;
import ru.develop.makeanappointment.doctor.mapper.DoctorMapper;
import ru.develop.makeanappointment.doctor.storage.DoctorStorage;
import ru.develop.makeanappointment.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorStorage doctorStorage;

    @Override
    public DoctorDto create(DoctorDto doctorDto) {
        return DoctorMapper.toDoctorDto(doctorStorage.save(DoctorMapper.toDoctor(doctorDto)));
    }

    @Override
    public DoctorDto get(Long id) {
        return DoctorMapper.toDoctorDto(doctorStorage.findById(id)
                .orElseThrow(() -> new NotFoundException("Врач с id " + id + " не найден.")));
    }

    @Override
    public void delete(Long id) {
        if (!doctorStorage.existsById(id)) {
            throw new NotFoundException("Врач с id " + id + " не найден.");
        }
        doctorStorage.deleteById(id);
    }
}
