package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.domain.Paciente;
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
    private Paciente paciente;
    private Exame exame;
    private Aplicador aplicador;
}
