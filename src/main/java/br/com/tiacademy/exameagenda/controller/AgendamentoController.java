package br.com.tiacademy.exameagenda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController extends CrudController<Agendamento, AgendamentoDTO, Long>{
    @PostMapping("/objeto")
    public ResponseEntity<Agendamento> create(@RequestBody Agendamento entidade) {

        var salvo = service.criar(entidade);

        ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

        var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);
    }
}
