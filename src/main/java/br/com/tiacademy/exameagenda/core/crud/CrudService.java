package br.com.tiacademy.exameagenda.core.crud;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class CrudService<T, ID> {
    
    @Autowired
	protected CrudRepository<T, ID> repository;
	
	public List<T> listar() {
		return repository.findAll();
	}

    public T porId(ID id) {
		return repository.findById(id).orElse(null);
	}

    public T criar(T entidade) {

		return repository.save(entidade);
	}

    public T editar(ID id, T entidade) {
		T recuperado = porId(id);
		if (Objects.isNull(recuperado)) {
			throw new RuntimeException("não foi encontrado");
		}

		T entidadeSalvar = editarEntidade(recuperado,entidade);

		return repository.save(entidadeSalvar);
	}
    
    public void excluir(ID id) {
		repository.deleteById(id);
	}

    protected abstract T editarEntidade(T recuperado, T entidade);
}
