package com.rarysson.carreira_futebol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String liga;       // "Europa" ou "Brasileirão"
    private int reputacao;     // 1 a 10
}