package br.com.tiacademy.exameagenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Aplicador> apliDiponiveis(@PathVariable("hora") String hora, @PathVariable("data") String data,
            @PathVariable("especialidade") String especialidade) {

        List<Aplicador> agendas = apliService.apliDisponiveis(hora, data, especialidade);

        return agendas;
    }
}