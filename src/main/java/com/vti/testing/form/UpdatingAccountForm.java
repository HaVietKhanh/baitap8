package com.vti.testing.form;

import com.vti.testing.entity.Role;
import com.vti.testing.validate.AccountUsernameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class UpdatingAccountForm {
    private Integer id;
    @NotBlank(message = "{Account.createAccount.form.username.NotBlank}")
    @AccountUsernameNotExists
    private String userName;

    @NotBlank(message = "{Account.createAccount.form.username.NotBlank}")
    private String firstName;
    @NotBlank(message = "{Account.createAccount.form.username.NotBlank}")
    private String lastName;

    private String password;
//    @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
    private Role role;
    @Positive   // tác dụng là yêu cầu DepartmentId là số dương lớn hơn 0
    private Integer departmentId;
}
