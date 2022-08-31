package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendamentoDTO implements Serializable {

    private Long id;
    private Long pacienteId;
    private Long exameId;
    private Long aplicadorId;
    private Date dataExame;
    private Time horaExame;
    private Date dataRetirada;
    private String status;
    private PacienteDTO paciente;
    private ExameDTO exame;
    private AplicadorDTO aplicador;

}
