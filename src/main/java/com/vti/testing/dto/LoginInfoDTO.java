package com.vti.testing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInfoDTO {
    private int id;
    private String fullName;
    private String departmentName;
}
