package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO implements Serializable {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
}
