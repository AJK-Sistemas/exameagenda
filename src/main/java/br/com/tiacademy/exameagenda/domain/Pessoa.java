package br.com.tiacademy.exameagenda.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.tiacademy.exameagenda.core.crud.CrudDomain;

public abstract class Pessoa implements CrudDomain<Long>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String email;
    
}
