package com.sk.GatePass.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="company_name")
    private String companyName;

    @Column(name="phone")
    private String phone;

    @Column(name="mail")
    private String mail;


}
