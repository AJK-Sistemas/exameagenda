package br.com.tiacademy.exameagenda.controller;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import br.com.tiacademy.exameagenda.service.AgendamentoService;
import br.com.tiacademy.exameagenda.service.AplicadorService;
import br.com.tiacademy.exameagenda.service.ExameService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/agendamento")
@Slf4j
public class AgendamentoController extends CrudController<Agendamento, AgendamentoDTO, Long> {

    @Autowired
    public AgendamentoService agendamentoService;

    @Autowired
    public AplicadorService aplicadorService;

    @Autowired
    public ExameService exameService;

    @PostMapping("/inserir")
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody AgendamentoDTO dto) {

        var especialidade = exameService.tipoExame(dto.getExameId());

        var hora = dto.getHoraExame();

        var data = dto.getDataExame();

        var aplicador = aplicadorService.apliDisponiveis(hora, data, especialidade);

        try {
            dto.setAplicadorId(aplicador.get(0).getId());
        } catch (IndexOutOfBoundsException e) {

            throw new RuntimeException("Sem aplicador disponivel");
        }

        dto.setStatus(Status.AFAZER.getStatus());

        var entidade = converter.dtoParaEntidade(dto);
        log.info(entidade.toString());
        var salvo = service.criar(entidade);

        var salvoDTO = converter.entidadeParaDto(salvo);

        ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

        var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvoDTO);
    }

    @GetMapping("/horaslivres/{id}/{data}")
    public ResponseEntity<List<Time>> espelhoHoras(@PathVariable("id") Long idExame,
            @PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<Time> horas = agendamentoService.geraHoras(idExame, data);

        return ResponseEntity.ok(horas);
    }

    @GetMapping("/afazer/{data}")
    public ResponseEntity<List<AgendamentoDTO>> aFazer(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        var agendamentos = agendamentoService.getAfazer(data).stream().map(converter::entidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/aretirar/{data}")
    public ResponseEntity<List<AgendamentoDTO>> aRetirar(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        var agendamentos = agendamentoService.getAretirar(data).stream().map(converter::entidadeParaDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(agendamentos);
    }
}
