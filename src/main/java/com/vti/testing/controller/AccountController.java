package com.vti.testing.controller;

import com.vti.testing.dto.AccountDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountFilter;
import com.vti.testing.form.CreatingAccountForm;
import com.vti.testing.form.UpdatingAccountForm;
import com.vti.testing.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@CrossOrigin("*")
@Validated
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public Page<AccountDTO> getAllAccount(Pageable pageable, AccountFilter filter ){
       Page<Account> page =  accountService.getAllAccounts(pageable, filter);
       List<AccountDTO> accountDTOS = modelMapper.map(page.getContent(),new TypeToken<List<AccountDTO>>(){

       }.getType());
    return new PageImpl<>(accountDTOS,pageable,accountDTOS.size());

    }
    @PostMapping
    public void createAccount(@RequestBody CreatingAccountForm form) throws Exception {
        accountService.createAccount(form);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteAccount(@PathVariable(name = "id") int id ){
        accountService.deleteAccount(id);
    }
    @PutMapping(value = "/{id}")
    public void updateAccount(@PathVariable(name = "id")  int id, @RequestBody @Valid UpdatingAccountForm form){
        form.setId(id);
        accountService.updateAccount(form);

    }
}
