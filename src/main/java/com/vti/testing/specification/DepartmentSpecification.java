package com.vti.testing.specification;

import com.vti.testing.entity.Department;
import com.vti.testing.filter.DepartmentFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class DepartmentSpecification {
    private static final String SEARCH = "search";
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String TYPE = "type";

    public static Specification<Department> buildWhere(DepartmentFilter form) {
        if (form == null) {
            return null;
        }
        DepartmentSpecification.CustomSpecification whereByName = new DepartmentSpecification.CustomSpecification(SEARCH, form.getSearch());
        DepartmentSpecification.CustomSpecification whereByDepartmentName = new DepartmentSpecification.CustomSpecification(DEPARTMENT_ID, form.getDepartmentId());
        DepartmentSpecification.CustomSpecification whereByType = new DepartmentSpecification.CustomSpecification(TYPE, form.getType());

        return Specification.where(whereByName).and(whereByDepartmentName).and(whereByType);
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<Department> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {
                case SEARCH:
                    // WHERE username LIKE "%value%"
                    Join join = root.join("accounts",JoinType.LEFT);
                    return criteriaBuilder.like(join.get("userName"), "%" + value.toString().trim() + "%");
                case DEPARTMENT_ID:
                    return criteriaBuilder.equal(root.get("department").get("id"), value.toString().trim());
                case TYPE:
                    return criteriaBuilder.equal(root.get("type"), value);
                default:
                    return null;
            }
        }
    }
}
