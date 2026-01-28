package com.rarysson.carreira_futebol.repository;

import com.rarysson.carreira_futebol.entity.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporadaRepository extends JpaRepository<Temporada, Long> {
    Optional<Temporada> findByAnoAndLiga(int ano, String liga);
}