package com.project.back_end.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
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

    @Future
    @NotNull
    private LocalDateTime scheduleStart;

    @Future
    @NotNull
    private LocalDateTime scheduleEnd;

    @Column(columnDefinition = "bit(1) default true")
    private boolean isActive;

    private String department;

}
