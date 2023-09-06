package com.lembrete.app.service;

import com.lembrete.app.entity.Pessoa;
import com.lembrete.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findByName(String nome) {
        return pessoaRepository.findByName(nome);
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> findByAtivo(boolean ativo) {
        return pessoaRepository.findByAtivo(ativo);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Pessoa pessoa) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido!"));

        // Atualize apenas o nome
        pessoaExistente.setNome(pessoa.getNome());

        pessoaRepository.save(pessoaExistente);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da pessoa é nulo.");
        }
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o ID: " + id));

        if (!pessoa.getLembreteList().isEmpty()) {
            throw new IllegalStateException("A pessoa não pode ser excluída porque está vinculada a lembretes.");
        }

        pessoaRepository.deleteById(id);
    }
}
