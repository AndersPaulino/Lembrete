package com.lembrete.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_lembretes", schema = "public")
public class Lembrete extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_descricao", length = 255)
    private String descricao;
}
