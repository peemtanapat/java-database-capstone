package com.project.back_end.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserAccount extends BaseModel {

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "bit(1) default true")
    private boolean isActive = true;

    private LocalDateTime lastLogin;

    @OneToOne(mappedBy = "userAccount")
    private Admin admin;

    @OneToOne(mappedBy = "userAccount")
    private Doctor doctor;

    @OneToOne(mappedBy = "userAccount")
    private Patient patient;

    @PrePersist
    private void onCreateIsActive() {
        this.isActive = true;
    }
}
