package com.sk.GatePass.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;




@Entity
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

    public Company(String companyName, String phone, String mail){
        this.companyName=companyName;
        this.phone=phone;
        this.mail=mail;
    }

}
