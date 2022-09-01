package br.com.tiacademy.exameagenda.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.dto.AplicadorDTO;
import br.com.tiacademy.exameagenda.service.AplicadorService;

@RestController
@RequestMapping("/aplicador")
public class AplicadorController extends CrudController<Aplicador, AplicadorDTO, Long> {
    @Autowired
    public AplicadorService apliService;

    @GetMapping("/disponiveis/{hora}/{data}/{especialidade}")
    public ResponseEntity<List<AplicadorDTO>> apliDiponiveis(@PathVariable("hora") Time hora,
            @PathVariable("data") Date data,
            @PathVariable("especialidade") String especialidade) {
        List<AplicadorDTO> agendas = apliService.apliDisponiveis(hora, data, especialidade).stream().map(converter::entidadeParaDto).collect(Collectors.toList());

        return ResponseEntity.ok(agendas);
    }
}