package br.com.tiacademy.exameagenda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Paciente;
import br.com.tiacademy.exameagenda.dto.PacienteDTO;

@Component
public class PacienteConverter implements CrudConverter<Paciente, PacienteDTO> {
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public PacienteDTO entidadeParaDto(Paciente entidade) {
        return modelMapper.map(entidade, PacienteDTO.class);
    }

    @Override
    public Paciente dtoParaEntidade(PacienteDTO dto) {
        return modelMapper.map(dto, Paciente.class);
    }
    
}
