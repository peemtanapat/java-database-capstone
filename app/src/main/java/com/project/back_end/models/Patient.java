package com.project.back_end.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Patient extends BaseModel {

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference("patient-appointment")
    private List<Appointment> appointments;

    @Size(min = 3, max = 50)
    @NotNull
    private String firstName;

    @Size(min = 3, max = 50)
    @NotNull
    private String lastName;

    @NotNull
    private LocalDate dob;

    @Pattern(regexp = "^(?:\\+66|0)(6|8|9|2)\\d{7,8}$")
    @NotNull
    private String phoneNumber;
}
