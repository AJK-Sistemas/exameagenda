package br.com.tiacademy.exameagenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.service.AgendamentoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController extends CrudController<Agendamento, AgendamentoDTO, Long>{

    @Autowired
    public AgendamentoService agservice;

    @PostMapping("/objeto")
    public ResponseEntity<Agendamento> create(@RequestBody Agendamento entidade) {

        var salvo = service.criar(entidade);

        ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

        var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping("/horas/{id}/{data}")
    public List<String> espelhoHoras(@PathVariable("id") Long idExame, @PathVariable("data") String data) {

        List<String> horas =agservice.geraHoras(idExame,data);

        return horas;
    }
    
}
