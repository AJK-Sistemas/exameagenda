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
    private Long pacienteId;
    private Long exameId;
    private Long aplicadorId;
    private LocalDateTime dataExame;
    private LocalDateTime dataRetirada;
    private String status;
}
