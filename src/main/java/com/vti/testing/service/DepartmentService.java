package com.vti.testing.service;

import com.vti.testing.entity.Department;
import com.vti.testing.filter.DepartmentFilter;
import com.vti.testing.repository.IDepartmentRepository;
import com.vti.testing.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService{
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Department> getAllDepartment(Pageable pageable, DepartmentFilter filter) {
        Specification<Department> where = DepartmentSpecification.buildWhere(filter);
        return departmentRepository.findAll(where,pageable);
    }

}
