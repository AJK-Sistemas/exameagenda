package br.com.tiacademy.exameagenda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiacademy.exameagenda.core.crud.CrudController;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.dto.ExameDTO;

@RestController
@RequestMapping("/exame")
public class ExameController extends CrudController<Exame, ExameDTO, Long>{
    
}