package com.rarysson.carreira_futebol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Jogador extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int idade;
    private String posicao;
    private double altura; // cm
    private double peso;   // kg
    private String peDominante;
    private double valorMercado; // em milhões de €

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    private List<Proposta> propostas = new ArrayList<>();

    @OneToOne(mappedBy = "jogador")
    private Contrato contratoAtual;
}