package com.sk.GatePass.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="plate")
    private String plate;



}
