package com.sk.GatePass.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "gate_pass")
public class GatePass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", unique = true, referencedColumnName ="id" )
    private User user;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "car_id", unique = true, referencedColumnName = "id")
    private Car cars;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;


    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "accepted_date")
    private Date acceptedDate;


}
