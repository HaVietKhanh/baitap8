package com.vti.testing.controller;

import com.vti.testing.dto.AccountDTO;
import com.vti.testing.dto.DepartmentDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.entity.Department;
import com.vti.testing.form.AccountFilter;
import com.vti.testing.form.DepartmentFilter;
import com.vti.testing.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<DepartmentDTO> getAllDepartment(@PageableDefault(size = 10) Pageable pageable, DepartmentFilter filter ){
        Page<Department> page =  departmentService.getAllDepartment(pageable, filter);
        List<DepartmentDTO> departmentDTOS = modelMapper.map(page.getContent(),new TypeToken<List<DepartmentDTO>>(){

        }.getType());
        return new PageImpl<>(departmentDTOS,pageable,departmentDTOS.size());

    }
}
