package com.lembrete.app.controller;

import com.lembrete.app.entity.Lembrete;
import com.lembrete.app.repository.LembreteRepository;
import com.lembrete.app.service.LembreteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/lembrete")
public class LembreteController {

    private LembreteRepository lembreteRepository;

    private LembreteService lembreteService;

    @Autowired
    public LembreteController(LembreteRepository lembreteRepository, LembreteService lembreteService){
        this.lembreteRepository = lembreteRepository;
        this.lembreteService = lembreteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lembrete> findById(@PathVariable Long id) {
        Optional<Lembrete> lembrete = lembreteRepository.findById(id);
        if (lembrete.isPresent()) {
            return ResponseEntity.ok().body(lembrete.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<Lembrete>> findByAtivo(@PathVariable boolean ativo) {
        List<Lembrete> lembretesAtivos = lembreteRepository.findByAtivo(ativo);
        if (!lembretesAtivos.isEmpty()) {
            return ResponseEntity.ok(lembretesAtivos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Lembrete> lembreteList = lembreteRepository.findAll();
        if (lembreteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(lembreteList);
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Lembrete lembrete) {
        try {
            lembreteService.cadastrar(lembrete);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable @NotNull Long id, @RequestBody Lembrete lembrete) {
        try {
            lembreteService.atualizar(id, lembrete);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o registro.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirLembrete(@PathVariable @NotNull Long id) {
        try {
            lembreteService.excluir(id);
            return ResponseEntity.ok().body("Lembrete excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o lembrete.");
        }
    }

}
