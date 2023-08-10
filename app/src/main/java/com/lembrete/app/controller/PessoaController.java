package com.lembrete.app.controller;

import com.lembrete.app.entity.Lembrete;
import com.lembrete.app.entity.Pessoa;
import com.lembrete.app.repository.PessoaRepository;
import com.lembrete.app.service.PessoaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/pessoa")
public class PessoaController {

    private PessoaRepository pessoaRepository;

    private PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService){
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok().body(pessoa.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<Pessoa>> findByAtivo(@PathVariable boolean ativo) {
        List<Pessoa> pessoasAtivos = pessoaRepository.findByAtivo(ativo);
        if (!pessoasAtivos.isEmpty()) {
            return ResponseEntity.ok(pessoasAtivos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if (pessoas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(pessoas);
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa pessoa) {
        try {
            pessoaService.cadastrar(pessoa);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable @NotNull Long id, @RequestBody Pessoa pessoa) {
        try {
            pessoaService.atualizar(id, pessoa);
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
            pessoaService.excluir(id);
            return ResponseEntity.ok().body("Lembrete excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o lembrete.");
        }
    }
}
