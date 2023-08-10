package com.lembrete.app.repository;

import com.lembrete.app.entity.Lembrete;
import com.lembrete.app.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa , Long> {

    @Query("From Pessoa where nome = :nome")
    public List<Pessoa> findByName(@Param("nome")final String nome);

    @Query("From Lembrete where ativo = :ativo")
    public List<Lembrete> findByAtivo(@Param("Ativo")final boolean ativo);

}
