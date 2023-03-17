package com.vti.testing.service;

import com.vti.testing.entity.Account;

import com.vti.testing.filter.AccountFilter;
import com.vti.testing.form.CreatingAccountForm;
import com.vti.testing.form.UpdatingAccountForm;
import com.vti.testing.repository.IAccountRepository;

import com.vti.testing.repository.IDepartmentRepository;
import com.vti.testing.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IDepartmentRepository departmentRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(userName);
        if (account == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new User(userName, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }

    @Override
    public Account getAccountByUsername(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public Page<Account> getAllAccounts(Pageable pageable, AccountFilter filter) {
        Specification<Account> where = AccountSpecification.buildWhere(filter);
        return accountRepository.findAll(where, pageable);

    }

    @Override
    public void createAccount(CreatingAccountForm form) throws Exception {
        TypeMap<CreatingAccountForm, Account> typeMap = modelMapper.getTypeMap(CreatingAccountForm.class, Account.class);
        if (typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreatingAccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Account account = modelMapper.map(form, Account.class);
        account.setDepartment(departmentRepository.findById(form.getDepartmentId()).get());
        accountRepository.save(account);
    }

    @Override
    public boolean isAccountExistsByUsername(String userName) {
        return accountRepository.findByUserName(userName) == null;
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void updateAccount(UpdatingAccountForm form) {
accountRepository.save(modelMapper.map(form,Account.class));
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findAllById(id);
    }

}
