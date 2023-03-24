package com.example.unittestdemoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unit")
public class DoctorController {

    private final DoctorService doctorService;

    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<DoctorDto> save(@RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(objectMapper
                .convertValue(doctorService.save(objectMapper.convertValue(doctorDto, Doctor.class)), DoctorDto.class));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        return ResponseEntity.ok(doctorService.getAll());
    }
}
