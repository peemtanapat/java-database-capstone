package com.project.back_end.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Appointment extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference("patient-appointment")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference("doctor-appointment")
    private Doctor doctor;

    @NotNull
    private LocalDateTime scheduleStart;

    @NotNull
    private LocalDateTime scheduleEnd;

    private boolean isActive;

    private String department;

}
