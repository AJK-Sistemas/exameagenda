package br.com.tiacademy.exameagenda.controller;

import java.sql.Date;
import java.util.List;

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
    private ExameService exameService;

    @GetMapping("/{status}/{data}")
    public ResponseEntity<List<Exame>> list(@PathVariable("status") String status, @PathVariable("data") Date data) {

        var lista = exameService.getExames(status, data);

        return ResponseEntity.ok(lista);
    }

}