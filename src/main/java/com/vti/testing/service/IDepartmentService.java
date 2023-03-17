package com.vti.testing.service;

import com.vti.testing.entity.Department;
import com.vti.testing.filter.DepartmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepartmentService {
    Page<Department> getAllDepartment(Pageable pageable, DepartmentFilter filter);

}
