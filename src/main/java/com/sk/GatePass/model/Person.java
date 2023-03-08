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
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="mail")
    private String mail;

    @Column(name="password")
    private String password;

}
