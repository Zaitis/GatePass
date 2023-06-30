package com.sk.GatePass.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


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
