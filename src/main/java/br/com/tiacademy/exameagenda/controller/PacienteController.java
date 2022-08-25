package br.com.tiacademy.exameagenda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Paciente;
import br.com.tiacademy.exameagenda.dto.PacienteDTO;

@RestController
@RequestMapping("/paciente")
public class PacienteController extends CrudController<Paciente, PacienteDTO, Long> {

}
