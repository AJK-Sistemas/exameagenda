package br.com.tiacademy.exameagenda.converter;

import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Paciente;
import br.com.tiacademy.exameagenda.dto.PacienteDTO;

@Component
public class PacienteConverter implements CrudConverter<Paciente, PacienteDTO> {
    @Override
    public PacienteDTO entidadeParaDto(Paciente entidade) {
        return new PacienteDTO(
                entidade.getId(),
                entidade.getNome(),
                entidade.getCpf(),
                entidade.getTelefone(),
                entidade.getEmail());
    }

    @Override
    public Paciente dtoParaEntidade(PacienteDTO dto) {
        return new Paciente(
                dto.getId(),
                dto.getNome(),
                dto.getCpf(),
                dto.getTelefone(),
                dto.getEmail());
    }
    
}
