package com.lembrete.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoas", schema = "public")
public class Pessoa extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_nome", length = 50)
    private String nome;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_pessoa_lembrete", // Nome da tabela de junção
            joinColumns =  @JoinColumn(name = "pessoa_id"), // Coluna que referencia Pessoa
            inverseJoinColumns = @JoinColumn(name = "lembrete_id") // Coluna que referencia Lembrete
    )
    private List<Lembrete> lembreteList = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }
}
