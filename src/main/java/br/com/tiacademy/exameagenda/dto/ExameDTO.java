package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.sql.Time;

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
    private Time duracao;
    private Time horaInicio;
    private Time horaFim;
    private Long disponibilidade;
}
