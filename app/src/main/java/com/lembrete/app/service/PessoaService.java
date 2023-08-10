package com.lembrete.app.service;

import com.lembrete.app.entity.Pessoa;
import com.lembrete.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findByName(String nome){
        return pessoaRepository.findByName(nome);
    }

    Optional<Pessoa> findById(Long id){
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> findByAtivo(boolean ativo){
        return pessoaRepository.findByAtivo(ativo);
    }

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public void validarPessoa(final Pessoa pessoa){
        if (!pessoa.getNome().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nome do fornecedor inválido");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Pessoa pessoa) {
        validarPessoa(pessoa);
        pessoaRepository.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Pessoa pessoa) {
        validarPessoa(pessoa);

        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);

        if (pessoaExistente.isPresent()) {
            Pessoa pessoaAtualizado = pessoaExistente.get();

            if (pessoa.getNome() != null) {
                pessoaAtualizado.setNome(pessoa.getNome());
            }

            if (pessoa.getLembreteList() != null){
                pessoaAtualizado.setLembreteList(pessoa.getLembreteList());
            }

            pessoaRepository.save(pessoaAtualizado);
        } else {
            throw new IllegalArgumentException("Id inválido!");
        }
    }
}
