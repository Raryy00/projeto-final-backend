package com.rarysson.carreira_futebol.controller;

import com.rarysson.carreira_futebol.entity.Jogador;
import com.rarysson.carreira_futebol.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
@CrossOrigin(origins = "http://localhost:5173")
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @GetMapping
    public List<Jogador> listarTodos() {
        return jogadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable Long id) {
        return jogadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jogador criar(@RequestBody Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @RequestBody Jogador jogadorAtualizado) {
        return jogadorRepository.findById(id)
                .map(j -> {
                    j.setNome(jogadorAtualizado.getNome());
                    j.setIdade(jogadorAtualizado.getIdade());
                    j.setPosicao(jogadorAtualizado.getPosicao());
                    j.setAltura(jogadorAtualizado.getAltura());
                    j.setPeso(jogadorAtualizado.getPeso());
                    j.setPeDominante(jogadorAtualizado.getPeDominante());
                    j.setValorMercado(jogadorAtualizado.getValorMercado());
                    return ResponseEntity.ok(jogadorRepository.save(j));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}