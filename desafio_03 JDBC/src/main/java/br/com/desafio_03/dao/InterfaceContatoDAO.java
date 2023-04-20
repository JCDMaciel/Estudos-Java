package br.com.desafio_03.dao;

import br.com.desafio_03.model.Contato;

import java.util.List;

public interface InterfaceContatoDAO {

    Contato save(Contato contato, Long idDaPessoa);
    Contato update(Contato contato, Long id);
    List<Contato> findById(Long idDaPessoa);
}
