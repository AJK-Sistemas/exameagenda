package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendamentoDTO implements Serializable {

    private Long id;
    private Long paciente_id;
    private Long aplicador_id;
    private Long exame_id;
    private LocalDateTime dataExame;
    private LocalDate dataRetirada;
    private String status;

}
