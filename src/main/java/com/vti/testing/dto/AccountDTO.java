package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.testing.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@NoArgsConstructor
public class AccountDTO {
    @NonNull
    private int id;
    @NonNull
    private String userName;
    @NonNull
    private String fullName;
    @NonNull
    private Role role;
    @NonNull
    private String departmentName;

}
