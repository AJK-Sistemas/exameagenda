package br.com.tiacademy.exameagenda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.dto.AplicadorDTO;

@Component
public class AplicadorConverter implements CrudConverter<Aplicador, AplicadorDTO> {
    // @Autowired
    // private ModelMapper modelMapper;

    // @Override
    // public AplicadorDTO entidadeParaDto(Aplicador entidade) {
    //     return modelMapper.map(entidade, AplicadorDTO.class);
    // }

    // @Override
    // public Aplicador dtoParaEntidade(AplicadorDTO dto) {
    //     return modelMapper.map(dto, Aplicador.class);
    // }

    @Override
    public AplicadorDTO entidadeParaDto(Aplicador entidade) {
        return new AplicadorDTO(
                entidade.getId(),
                entidade.getNome(),
                entidade.getCpf(),
                entidade.getTelefone(),
                entidade.getEmail(),
                entidade.getEspecialidade());
    }

    @Override
    public Aplicador dtoParaEntidade(AplicadorDTO dto) {
        return new Aplicador(
                dto.getId(),
                dto.getNome(),
                dto.getCpf(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getEspecialidade());
    }
}
