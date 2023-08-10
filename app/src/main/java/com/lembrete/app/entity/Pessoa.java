package com.lembrete.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_pessoas", schema = "public")
public class Pessoa extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_nome", length = 50)
    private String nome;

    @Getter @Setter
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cl_lembrete_id")
    private List<Lembrete> lembreteList;

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Lembrete> getLembreteList(){
        return lembreteList;
    }

    public void setLembreteList(List<Lembrete> lembreteList) {
        this.lembreteList = lembreteList;
    }
}
