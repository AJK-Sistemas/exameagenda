package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExameDTO implements Serializable {
    private Long id;
    private String tipo;
    private String descricao;
    private String duracao;
    private String hora_inicio;
    private String hora_fim;
    private Long disponibilidade;
    private double valor;
}
