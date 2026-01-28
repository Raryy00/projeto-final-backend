package com.rarysson.carreira_futebol.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ano;
    private String liga;

    @ManyToOne
    @JoinColumn(name = "time_campeao_id")
    private Time timeCampeao;

    private int pontosCampeao;

    @ManyToMany
    @JoinTable(
        name = "temporada_times_participantes",
        joinColumns = @JoinColumn(name = "temporada_id"),
        inverseJoinColumns = @JoinColumn(name = "time_id")
    )
    private List<Time> timesParticipantes = new ArrayList<>();
}