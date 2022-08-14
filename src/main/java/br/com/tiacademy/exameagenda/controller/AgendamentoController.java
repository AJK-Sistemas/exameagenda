package br.com.tiacademy.exameagenda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController extends CrudController<Agendamento, AgendamentoDTO, Long>{
    
}
