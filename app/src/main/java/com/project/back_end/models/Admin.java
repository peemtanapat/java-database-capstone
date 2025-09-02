package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends BaseModel {

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Size(min = 3, max = 100)
    @NotNull
    private String name;

    private String permissionsLevel;

}
