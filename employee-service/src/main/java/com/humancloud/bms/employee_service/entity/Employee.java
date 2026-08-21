package com.humancloud.bms.employee_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.humancloud.bms.employee_service.enums.Status;
import com.humancloud.bms.employee_service.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_number", nullable = false, unique = true, length = 20)
    private String employeeNumber;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role position;

    @Column(name = "manager_id")
    private Long managerId;

    //  Owned by branch-service. different database.
    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
