package com.lembrete.app.service;

import com.lembrete.app.entity.Lembrete;
import com.lembrete.app.repository.LembreteRepository;
import com.lembrete.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LembreteService {

    private final LembreteRepository lembreteRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository, PessoaRepository pessoaRepository) {
        this.lembreteRepository = lembreteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Lembrete> findById(Long id) {
        return lembreteRepository.findById(id);
    }

    public List<Lembrete> findByAtivo(boolean ativo) {
        return lembreteRepository.findByAtivo(ativo);
    }

    public List<Lembrete> findAll() {
        return lembreteRepository.findAll();
    }

    public void cadastrar(Lembrete lembrete) {
        lembreteRepository.save(lembrete);
    }

    public void atualizar(Long id, Lembrete lembrete) {
        Optional<Lembrete> lembreteExistente = lembreteRepository.findById(id);

        if (lembreteExistente.isPresent()) {
            Lembrete lembreteAtualizado = lembreteExistente.get();
            lembreteRepository.save(lembreteAtualizado);
        } else {
            throw new IllegalArgumentException("Id inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do lembrete é nulo.");
        }

        Lembrete lembrete = lembreteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lembrete não encontrado com o ID: " + id));

        lembreteRepository.delete(lembrete);
    }
}
