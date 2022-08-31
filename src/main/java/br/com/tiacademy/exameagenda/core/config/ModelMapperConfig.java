package br.com.tiacademy.exameagenda.core.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tiacademy.exameagenda.converter.PacienteConverter;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.domain.Paciente;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.dto.PacienteDTO;
import br.com.tiacademy.exameagenda.service.AplicadorService;
import br.com.tiacademy.exameagenda.service.ExameService;
import br.com.tiacademy.exameagenda.service.PacienteService;

@Configuration
public class ModelMapperConfig {
    // @Autowired
    // public AplicadorService aplicadorService;

    // @Autowired
    // public PacienteService pacienteService;

    // @Autowired
    // public ExameService exameService;

    // @Autowired
    // public PacienteConverter paConverter;

    // @Bean
    // public ModelMapper modelMapper() {
    //     var modelMapper = new ModelMapper();

    //     Converter<Long, Paciente> pacienteLong = ctx -> pacienteService.porId(ctx.getSource());

    //     Converter<Long, Aplicador> aplicadorLong = ctx -> aplicadorService.porId(ctx.getSource());

    //     Converter<Long, Exame> exameLong = ctx -> exameService.porId(ctx.getSource());

    //     var model1 = modelMapper.createTypeMap(AgendamentoDTO.class, Agendamento.class);
    //     model1.addMappings(
    //             mapper -> mapper.using(pacienteLong).map(AgendamentoDTO::getPacienteId, Agendamento::setPaciente));
    //     model1.addMappings(
    //             mapper -> mapper.using(aplicadorLong).map(AgendamentoDTO::getAplicadorId, Agendamento::setAplicador));
    //     model1.addMappings(mapper -> mapper.using(exameLong).map(AgendamentoDTO::getExameId, Agendamento::setExame));
    //     // model1.<Paciente>addMapping(src ->
    //     // pacienteService.porId(src.getPaciente_id()),
    //     // (dest,value)->dest.setPaciente(value));
    //     // model1.<Exame>addMapping(src -> exameService.porId(src.getExame_id()),
    //     // (dest,value)->dest.setExame(value));
    //     // model1.<Aplicador>addMapping(src ->
    //     // aplicadorService.porId(src.getAplicador_id()),
    //     // (dest,value)->dest.setAplicador(value));

    //     //Converter<Paciente, PacienteDTO> pacienteLongDto = ctx -> paConverter(ctx.getSource());

    //     var model = modelMapper.createTypeMap(Agendamento.class, AgendamentoDTO.class);
    //     model.<Long>addMapping(src -> src.getPaciente().getId(), (dest, value) -> dest.setPacienteId(value));
    //     model.<Long>addMapping(src -> src.getExame().getId(), (dest, value) -> dest.setExameId(value));
    //     model.<Long>addMapping(src -> src.getAplicador().getId(), (dest, value) -> dest.setAplicadorId(value));

    //     return modelMapper;

    // }

}
