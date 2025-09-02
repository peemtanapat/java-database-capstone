package com.project.back_end.DTO;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {

    private String email;
    private String password;

    private String name;
    private String phoneNumber;
    private String specialty;
    private List<String> availableTimes;
    // private List<LocalTime> availableTimes;

}
