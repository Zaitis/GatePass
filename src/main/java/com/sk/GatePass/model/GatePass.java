package com.sk.GatePass.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "gate_pass")
public class GatePass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_card")
    private String idCard;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="phone")
    private String phone;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "car_id", unique=true, referencedColumnName = "id")
    private Car cars;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_id",  referencedColumnName = "id")
    private Company company;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "person_id",  referencedColumnName = "id")
    private Person person;

    @Column(name="accepted_date")
    private Date acceptedDate;




}
