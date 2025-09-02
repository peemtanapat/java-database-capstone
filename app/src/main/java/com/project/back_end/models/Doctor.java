package com.project.back_end.models;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@ToString
@Setter
@Getter
@Entity
public class Doctor extends BaseModel {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "available_time")
    private List<LocalTime> availableTimes;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    @JsonManagedReference("doctor-appointment")
    private List<Appointment> appointments;

    @Size(min = 3, max = 100)
    @NotNull
    private String name;

    @NotNull
    private String specialty;

    private String department;

    @Pattern(regexp = "^(?:\\+66|0)(6|8|9|2)\\d{7,8}$")
    @NotNull
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(
            @Size(min = 3, max = 100) @NotNull String name,
            @NotNull String specialty,
            List<LocalTime> availableTimes) {
        this.availableTimes = availableTimes;
        this.name = name;
        this.specialty = specialty;
    }

}
