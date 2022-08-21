package br.com.tiacademy.exameagenda.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.tiacademy.exameagenda.core.crud.CrudDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agendamento implements CrudDomain<Long>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataExame;
    private LocalDateTime dataRetirada;
    private String status;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Exame exame;

    @ManyToOne
    private Aplicador aplicador;

}
