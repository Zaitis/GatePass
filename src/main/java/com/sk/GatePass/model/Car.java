package com.sk.GatePass.model;


import lombok.*;

import javax.persistence.*;


@Entity
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

    public Car(String brand, String model, String plate){
        this.brand=brand;
        this.model=model;
        this.plate=plate;
    }


}
