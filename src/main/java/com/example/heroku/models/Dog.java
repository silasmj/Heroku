package com.example.heroku.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="dogs")
@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String race;

    @Column
    private int weight;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Gender gender;
}
