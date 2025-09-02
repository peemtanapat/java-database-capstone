package com.project.back_end.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private String doctorName;
    private String patientName;
    private String patientPhoneNumber;
    private String start;
    private String end;

}
