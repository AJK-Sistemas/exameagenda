package br.com.tiacademy.exameagenda.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.enums.Status;
import br.com.tiacademy.exameagenda.service.AgendamentoService;
import br.com.tiacademy.exameagenda.service.AplicadorService;
import br.com.tiacademy.exameagenda.service.ExameService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController extends CrudController<Agendamento, AgendamentoDTO, Long> {

    @Autowired
    public AgendamentoService agService;
    
    @Autowired
    public AplicadorService apService;

    @Autowired
    public ExameService exameService;

    // @PostMapping("/objeto")
    // public ResponseEntity<Agendamento> create(@RequestBody Agendamento entidade) {

    //     var salvo = service.criar(entidade);

    //     ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

    //     var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

    //     return ResponseEntity.created(uri).body(salvo);
    // }

    @GetMapping("/paginada")
    public ResponseEntity<Page<Agendamento>> pagina(Pageable pageable) {

        var listaPaginada = service.pagination(pageable);

        return ResponseEntity.ok(listaPaginada);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Agendamento>> listar() {

        var lista = agService.getAgendamento();

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Agendamento> create(@RequestBody AgendamentoDTO dto) {

        var especialidade = exameService.tipoExame(dto.getExameId());
        
        var hora = dto.getHoraExame();

        var data = dto.getDataExame();

        var aplicador = apService.apliDisponiveis(hora, data, especialidade);

        try{
        dto.setAplicadorId(aplicador.get(0).getId());
        }
        catch(IndexOutOfBoundsException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem aplicador disponivel", e);
        }

        dto.setStatus(Status.AFAZER.getStatus());

        var entidade = converter.dtoParaEntidade(dto);

        var salvo = service.criar(entidade);

        ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

        var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping("/horaslivres/{id}/{data}")
    public ResponseEntity<List<Time>> espelhoHoras(@PathVariable("id") Long idExame,
            @PathVariable("data") Date data) {

        List<Time> horas = agService.geraHoras(idExame, data);

        return ResponseEntity.ok(horas);
    }

    @GetMapping("/dataexame/{data}")
    public ResponseEntity<List<Agendamento>> dataExame(@PathVariable("data") Date data) {

        var entidade = agService.getByDataExame(data);

        return ResponseEntity.ok(entidade);
    }

    // @GetMapping("/agendadata/{data}")
    // public ResponseEntity<List<Agendamento>> datasDeExame(@PathVariable("data") String data) {

    //     List<Agendamento> agendamentos = agService.agendamentosData(data);

    //     return ResponseEntity.ok(agendamentos);
    // }

    @GetMapping("/{status}/{data}")
    public ResponseEntity<List<Agendamento>> listagemDiaria(@PathVariable("status") String status,
            @PathVariable("data") Date data) {

        var agendamentos = agService.getListagemDiaria(status, data);

        return ResponseEntity.ok(agendamentos);
    }
}
