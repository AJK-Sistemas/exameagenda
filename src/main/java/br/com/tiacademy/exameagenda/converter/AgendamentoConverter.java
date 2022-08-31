package br.com.tiacademy.exameagenda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.repository.AplicadorRepository;
import br.com.tiacademy.exameagenda.repository.ExameRepository;
import br.com.tiacademy.exameagenda.repository.PacienteRepository;
import br.com.tiacademy.exameagenda.service.AplicadorService;
import br.com.tiacademy.exameagenda.service.ExameService;
import br.com.tiacademy.exameagenda.service.PacienteService;

@Component
public class AgendamentoConverter implements CrudConverter<Agendamento, AgendamentoDTO> {
    // @Autowired
    // public AplicadorService aplicadorService;

    // @Autowired
    // public PacienteService pacienteService;

    // @Autowired
    // public ExameService exameService;

    // @Autowired
    // private ModelMapper modelMapper;

    // @Override
    // public AgendamentoDTO entidadeParaDto(Agendamento entidade) {

    //     return modelMapper.map(entidade, AgendamentoDTO.class);

    // }

    // @Override
    // public Agendamento dtoParaEntidade(AgendamentoDTO dto) {
        
    //     return modelMapper.map(dto, Agendamento.class);
    //     // Paciente paciente = pacienteService.porId(dto.getPaciente_id());
    //     // Aplicador aplicador = aplicadorService.porId(dto.getAplicador_id());
    //     // Exame exame = exameService.porId(dto.getExame_id());

    //     // return new Agendamento(
    //     //         dto.getId(),
    //     //         dto.getDataExame(),
    //     //         dto.getDataRetirada(),
    //     //         dto.getStatus(),
    //     //         paciente,
    //     //         exame,
    //     //         aplicador);
    // }

    @Autowired
    private PacienteConverter pacienteConverter;
    
    @Autowired
    private ExameConverter exameConverter;
    
    @Autowired
    private AplicadorConverter aplicadorConverter;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ExameRepository exameRepository;
    
    @Autowired
    private AplicadorRepository aplicadorRepository;

    @Override
    public AgendamentoDTO entidadeParaDto(Agendamento entidade) {

        var dto = new AgendamentoDTO();
        dto.setId(entidade.getId());
        dto.setDataExame(entidade.getDataExame());
        dto.setHoraExame(entidade.getHoraExame());
        dto.setDataRetirada(entidade.getDataRetirada());
        dto.setStatus(entidade.getStatus());
        
        dto.setPaciente(pacienteConverter.entidadeParaDto(entidade.getPaciente()));
        dto.setExame(exameConverter.entidadeParaDto(entidade.getExame()));
        dto.setAplicador(aplicadorConverter.entidadeParaDto(entidade.getAplicador()));

        return dto;
    }

    @Override
    public Agendamento dtoParaEntidade(AgendamentoDTO dto) {

        var agendamento = new Agendamento();
        agendamento.setId(dto.getId());
        agendamento.setDataExame(dto.getDataExame());
        agendamento.setHoraExame(dto.getHoraExame());
        agendamento.setDataRetirada(dto.getDataRetirada());
        agendamento.setStatus(dto.getStatus());

        agendamento.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));
        agendamento.setExame(exameRepository.findById(dto.getExameId()).orElse(null));
        agendamento.setAplicador(aplicadorRepository.findById(dto.getAplicadorId()).orElse(null));

        return agendamento;
    }

}
