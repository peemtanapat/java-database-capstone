package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.DTO.AppointmentDTO;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @GetMapping("/{date}/{patientName}/{token}")
    public List<AppointmentDTO> getAllAppointments(
            @PathVariable String date,
            @PathVariable String patientName,
            @PathVariable String token) {
        return List.of(new AppointmentDTO("Dr. Emily Adams", "Jane Doe", "0939452459", "2025-08-31 13:30",
                "2025-08-31 14:30"));
    }

}
