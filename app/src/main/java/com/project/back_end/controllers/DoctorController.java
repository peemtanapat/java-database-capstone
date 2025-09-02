package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.DTO.DoctorDTO;
import com.project.back_end.DTO.DoctorRequestDTO;
import com.project.back_end.models.Role;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.LoginService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final LoginService loginService;

    @GetMapping("/filter")
    public List<DoctorDTO> filterDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String availableTimes,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber) {
        List<DoctorDTO> doctors = doctorService.getDoctors();
        if (name != null && !name.trim().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
        if (specialty != null && !specialty.trim().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getSpecialty().toLowerCase().contains(specialty.toLowerCase()))
                    .toList();
        }
        if (availableTimes != null && !availableTimes.trim().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getAvailableTimes() != null &&
                            d.getAvailableTimes().stream().anyMatch(time -> time.toString().contains(availableTimes)))
                    .toList();
        }
        if (email != null && !email.trim().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getEmail().toLowerCase().contains(email.toLowerCase()))
                    .toList();
        }
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getPhoneNumber() != null
                            && d.getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()))
                    .toList();
        }
        return doctors;
    }

    @PostMapping
    public DoctorDTO saveDoctor(
            @RequestBody DoctorRequestDTO request,
            @RequestHeader("Authorization") String bearerToken) {
        String token = loginService.getRawToken(bearerToken);

        loginService.validateToken(token, Role.ADMIN);

        return doctorService.saveDoctor(request);
    }

}
