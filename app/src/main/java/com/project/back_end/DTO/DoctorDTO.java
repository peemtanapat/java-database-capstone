package com.project.back_end.DTO;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private long id;
    private String name;
    private String specialty;
    private String phoneNumber;
    private String email;
    private List<LocalTime> availableTimes;

}
