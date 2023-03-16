package com.vti.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
@Entity
@Table(name = "`Account`")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username",length = 50,nullable = false,unique = true)
    private String userName;
    @Column(name = "`password`",length = 800,nullable = false)
    private String password;
    @Column(name = "first_name",length = 50,nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 50,nullable = false)
    private String lastName;
    @Formula("concat(first_name,' ',last_name)")
    private String fullName;
    @Column(name ="`role`")
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @PrePersist
    public void prePersist(){
        if (role == null){
            role = Role.EMPLOYEE;
        }
        // Mã Hóa Password
        password = new BCryptPasswordEncoder().encode(password);
    }
}
