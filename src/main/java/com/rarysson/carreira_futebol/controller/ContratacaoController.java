package com.rarysson.carreira_futebol.controller;

import com.rarysson.carreira_futebol.entity.*;
import com.rarysson.carreira_futebol.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/contratacoes")
@CrossOrigin(origins = "http://localhost:5173")
public class ContratacaoController {

    @Autowired private JogadorRepository jogadorRepository;
    @Autowired private TimeRepository timeRepository;
    @Autowired private PropostaRepository propostaRepository;
    @Autowired private ContratoRepository contratoRepository;

    @PostMapping("/propostas/{jogadorId}")
    public ResponseEntity<List<Proposta>> gerarPropostas(@PathVariable Long jogadorId) {
        Jogador jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        List<Time> times = timeRepository.findAll();
        if (times.size() < 2) {
            throw new RuntimeException("Cadastre pelo menos 2 times no banco");
        }

        Random random = new Random();
        Time time1 = times.get(random.nextInt(times.size()));
        Time time2 = times.get(random.nextInt(times.size()));
        while (time2.equals(time1)) {
            time2 = times.get(random.nextInt(times.size()));
        }

        List<Proposta> propostas = new ArrayList<>();

        Proposta p1 = new Proposta();
        p1.setJogador(jogador);
        p1.setTime(time1);
        p1.setSalarioAnual(5 + random.nextDouble() * 15);
        p1.setDuracaoAnos(2 + random.nextInt(4));
        propostaRepository.save(p1);
        propostas.add(p1);

        Proposta p2 = new Proposta();
        p2.setJogador(jogador);
        p2.setTime(time2);
        p2.setSalarioAnual(5 + random.nextDouble() * 15);
        p2.setDuracaoAnos(2 + random.nextInt(4));
        propostaRepository.save(p2);
        propostas.add(p2);

        return ResponseEntity.ok(propostas);
    }

    @PostMapping("/aceitar/{propostaId}")
    public ResponseEntity<Contrato> aceitarProposta(@PathVariable Long propostaId) {
        Proposta proposta = propostaRepository.findById(propostaId)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        proposta.setAceita(true);
        propostaRepository.save(proposta);

        Contrato contrato = new Contrato();
        contrato.setJogador(proposta.getJogador());
        contrato.setTime(proposta.getTime());
        contrato.setSalarioAnual(proposta.getSalarioAnual());
        contrato.setDuracaoAnos(proposta.getDuracaoAnos());
        contrato.setDataInicio(LocalDate.now());

        contratoRepository.save(contrato);

        proposta.getJogador().setContratoAtual(contrato);
        jogadorRepository.save(proposta.getJogador());

        return ResponseEntity.ok(contrato);
    }
}