package com.example.unittestdemoo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepo doctorRepo;

    public Doctor save(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    public List<Doctor> getAll() {
        return doctorRepo.findAll();
    }
}
