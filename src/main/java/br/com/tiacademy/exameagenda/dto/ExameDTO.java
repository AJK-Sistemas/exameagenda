package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExameDTO implements Serializable{
    private Long id;
    private String tipo;
    private String descricao;
    private int duracao;
    private int disponibilidade;
    private double valor;
}
