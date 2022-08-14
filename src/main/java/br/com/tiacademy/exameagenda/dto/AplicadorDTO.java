package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AplicadorDTO implements Serializable{
    private Long id;
	private String nome;
}
