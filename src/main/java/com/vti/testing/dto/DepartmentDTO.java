package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.testing.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    @NonNull
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @NonNull
    private String name;
    @NonNull
    private List<AccountDTO> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountDTO extends RepresentationModel<DepartmentDTO>{
        private int id;
        private String userName;
        private String firstName;
        private String lastName;
        private int departmentId;
        private String departmentName;
    }
}
