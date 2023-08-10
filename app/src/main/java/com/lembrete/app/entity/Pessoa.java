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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cl_lembretes")
    private List<Lembrete> lembreteList;

}
