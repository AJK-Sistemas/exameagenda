package br.com.tiacademy.exameagenda.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.service.AplicadorService;
import br.com.tiacademy.exameagenda.service.ExameService;
import br.com.tiacademy.exameagenda.service.PacienteService;

@Configuration
public class ModelMapperConfig {
    @Autowired
    public AplicadorService aplicadorService;

    @Autowired
    public PacienteService pacienteService;

    @Autowired
    public ExameService exameService;

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();


        // var model1 = modelMapper.createTypeMap(AgendamentoDTO.class, Agendamento.class);
        // model1.<Paciente>addMapping(src -> pacienteService.porId(src.getPaciente_id()), (dest,value)->dest.setPaciente(value));
        // model1.<Exame>addMapping(src -> exameService.porId(src.getExame_id()), (dest,value)->dest.setExame(value));
        // model1.<Aplicador>addMapping(src -> aplicadorService.porId(src.getAplicador_id()), (dest,value)->dest.setAplicador(value));

        var model = modelMapper.createTypeMap(Agendamento.class, AgendamentoDTO.class);
        model.<Long>addMapping(src -> src.getPaciente().getId(), (dest,value)->dest.setPaciente_id(value));
        model.<Long>addMapping(src -> src.getExame().getId(), (dest,value)->dest.setExame_id(value));
        model.<Long>addMapping(src -> src.getAplicador().getId(), (dest,value)->dest.setAplicador_id(value));
        
        return modelMapper;

    }

}
