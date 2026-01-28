package com.rarysson.carreira_futebol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    private double salarioAnual; // milhões de €
    private int duracaoAnos;
    private boolean aceita = false;
}