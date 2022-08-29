package br.com.tiacademy.exameagenda.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.tiacademy.exameagenda.enums.Status;
import br.com.tiacademy.exameagenda.repository.AgendamentoRepository;
import br.com.tiacademy.exameagenda.service.AgendamentoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController extends CrudController<Agendamento, AgendamentoDTO, Long> {

    // @PostMapping("/objeto")
    // public ResponseEntity<Agendamento> create(@RequestBody Agendamento entidade)
    // {

    // var salvo = service.criar(entidade);

    // ServletUriComponentsBuilder buider =
    // ServletUriComponentsBuilder.fromCurrentRequest();

    // var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

    // return ResponseEntity.created(uri).body(salvo);
    // }

    // public AgendamentoService getService() {
    // return (AgendamentoService) service;
    // }

    @Autowired
    private AgendamentoService service;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/paginada")
    public ResponseEntity<Page<Agendamento>> pagina(Pageable pageable) {

        var listaPaginada = service.pagination(pageable);

        return ResponseEntity.ok(listaPaginada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Agendamento>> listar() {

        var lista = service.getAgendamento();

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Agendamento> create(@RequestBody AgendamentoDTO dto) {

        var id = dto.getExameId();

        var tipo = agendamentoRepository.tipoExame(id);
        var aplicadorId = agendamentoRepository.idAplicador(tipo);

        dto.setAplicadorId(aplicadorId);

        var entidade = converter.dtoParaEntidade(dto);
        entidade.setStatus(Status.AFAZER.getStatus());

        var salvo = service.criar(entidade);

        ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

        var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<Agendamento>> dataExame(@PathVariable("data") Date data) {

        var entidade = service.getByDataExame(data);

        return ResponseEntity.ok(entidade);
    }

    @GetMapping("/hora/{hora}")
    public ResponseEntity<List<Agendamento>> horaExame(@PathVariable("hora") Time hora) {

        var entidade = service.getByHoraExame(hora);

        return ResponseEntity.ok(entidade);
    }


    @GetMapping("/{status}/{data}")
    public ResponseEntity<List<Agendamento>> listagemDiaria(@PathVariable("status") String status,
            @PathVariable("data") Date data) {

        var agendamentos = service.getListagemDiaria(status, data);

        return ResponseEntity.ok(agendamentos);
    }

}
