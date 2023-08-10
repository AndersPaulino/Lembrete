package com.lembrete.app.service;

import com.lembrete.app.entity.Lembrete;
import com.lembrete.app.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository){
        this.lembreteRepository = lembreteRepository;
    }
    Optional<Lembrete> findById(Long id){
        return lembreteRepository.findById(id);
    }

    public List<Lembrete> findByAtivo(boolean ativo){
        return lembreteRepository.findByAtivo(ativo);
    }

    public List<Lembrete> findAll(){
        return lembreteRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Lembrete lembrete) {
        lembreteRepository.save(lembrete);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Lembrete lembrete) {

        Optional<Lembrete> lembreteExistente = lembreteRepository.findById(id);

        if (lembreteExistente.isPresent()) {
            Lembrete lembreteAtualizado = lembreteExistente.get();

            lembreteRepository.save(lembreteAtualizado);
        } else {
            throw new IllegalArgumentException("Id inválido!");
        }
    }
}
