package com.project.back_end.models;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Doctor extends BaseModel {

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ElementCollection
    @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "available_time")
    private List<LocalTime> availableTimes;

    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference("doctor-appointment")
    private List<Appointment> appointments;

    @Size(min = 3, max = 50)
    @NotNull
    private String firstName;

    @Size(min = 3, max = 50)
    @NotNull
    private String lastName;

    @Size(min = 5, max = 50)
    @NotNull
    @Column(unique = true)
    // TODO: update licenseNumber must be approved from Admin
    private String licenseNumber;

    @NotNull
    private String specialization;

    private String department;

}
