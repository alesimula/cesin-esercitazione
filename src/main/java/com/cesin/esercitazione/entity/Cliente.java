package com.cesin.esercitazione.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private boolean isPublic;
}
