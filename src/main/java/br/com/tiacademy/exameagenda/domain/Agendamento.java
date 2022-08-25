package br.com.tiacademy.exameagenda.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private LocalDate dataRetirada;
    private String status;
    
    @ManyToOne
    @JoinColumn(name="paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="exame_id", referencedColumnName = "id")
    private Exame exame;

    @ManyToOne
    @JoinColumn(name="aplicador_id", referencedColumnName = "id")
    private Aplicador aplicador;

}
