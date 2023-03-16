package com.vti.testing.form;

import com.vti.testing.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilter {
    private String search;
    private Role role;
    private String departmentId;

}
