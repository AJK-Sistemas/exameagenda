package br.com.tiacademy.exameagenda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.dto.ExameDTO;
import br.com.tiacademy.exameagenda.service.ExameService;

@RestController
@RequestMapping("/exame")
public class ExameController extends CrudController<Exame, ExameDTO, Long> {

    @Autowired
    public ExameService exameService;

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ExameDTO>> getExames(@PathVariable("tipo") String tipo) {

        var exames = exameService.getTipoExame(tipo).stream().map(converter::entidadeParaDto).collect(Collectors.toList());

        return ResponseEntity.ok(exames);
    }
}