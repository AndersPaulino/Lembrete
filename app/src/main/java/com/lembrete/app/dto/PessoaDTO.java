package com.lembrete.app.dto;

import com.lembrete.app.entity.Lembrete;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PessoaDTO {
    private String nome;
    private List<Lembrete> lembreteList;
}
