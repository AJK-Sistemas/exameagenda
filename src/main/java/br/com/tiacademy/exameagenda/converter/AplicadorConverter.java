package br.com.tiacademy.exameagenda.converter;

import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.dto.AplicadorDTO;

@Component
public class AplicadorConverter implements CrudConverter<Aplicador, AplicadorDTO> {
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
