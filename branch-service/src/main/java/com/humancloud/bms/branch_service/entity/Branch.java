package com.humancloud.bms.branch_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "branch_code", nullable = false, unique = true, length = 20)
    private String branchCode;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false, length = 80)
    private String city;

    @Column(nullable = false,length = 80)
    private String state;

    @Column(nullable = false, length = 20)
    private String status;
}
