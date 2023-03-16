package com.vti.testing.service;

import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountFilter;
import com.vti.testing.form.CreatingAccountForm;
import com.vti.testing.form.UpdatingAccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {


    Account getAccountByUsername(String userName);

    Page<Account> getAllAccounts(Pageable pageable, AccountFilter filter);

    void createAccount(CreatingAccountForm form) throws Exception;

    boolean isAccountExistsByUsername(String userName);
    void deleteAccount(int id);

    void updateAccount(UpdatingAccountForm form);
}
