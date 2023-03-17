package com.vti.testing.filter;

import com.vti.testing.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentFilter {
    private String search;
    private Type type;
    private String departmentId;

}
