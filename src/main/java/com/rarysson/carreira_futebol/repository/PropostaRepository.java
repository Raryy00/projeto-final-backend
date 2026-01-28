package com.rarysson.carreira_futebol.repository;

import com.rarysson.carreira_futebol.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByJogadorId(Long jogadorId);
}