package br.com.tiacademy.exameagenda.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface AgendaHorasDTO {
    LocalTime getHora();
    Long getConta();
}