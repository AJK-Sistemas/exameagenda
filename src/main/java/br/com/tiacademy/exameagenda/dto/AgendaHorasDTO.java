package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgendaHorasDTO implements Serializable {
    @Temporal(TemporalType.TIME)
    private Date hora;
    private Long conta;
}