package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendamentoDTO implements Serializable {
    private Long id;
    private LocalDateTime dataExame;
    private LocalDateTime dataRetirada;
    private String status;
    private Long paciente_id;
    private Long exame_id;
    private Long aplicador_id;
}
