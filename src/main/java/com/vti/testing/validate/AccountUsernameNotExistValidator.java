package com.vti.testing.validate;

import com.vti.testing.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountUsernameNotExistValidator implements ConstraintValidator<AccountUsernameNotExists, String> {
    @Autowired
    private IAccountService accountService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return accountService.isAccountExistsByUsername(name);
    }
}
