package com.sk.GatePass.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "gate_pass")
public class GatePass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_id", unique = true, referencedColumnName = "id")
    private Car cars;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "accepted_date")
    private LocalDateTime acceptedDate;





}
