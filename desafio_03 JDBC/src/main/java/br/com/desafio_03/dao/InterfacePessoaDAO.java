package br.com.desafio_03.dao;

import br.com.desafio_03.model.Pessoa;
import java.util.List;
import java.util.Optional;

public interface InterfacePessoaDAO {
    Pessoa save(Pessoa pessoa);
    Pessoa update(Pessoa pessoa, Long id);
    Pessoa updateNome(Pessoa pessoa, Long id);
    Pessoa updateCpf(Pessoa pessoa, Long id);
    Pessoa updateEmail(Pessoa pessoa, Long id);
    Pessoa updateEndereco(Pessoa pessoa, Long id);
    Pessoa updateDataDeNascimento(Pessoa pessoa, Long id);

    void delete(Long id);
    List<Pessoa> findAll();
    Optional<Pessoa> findById(Long id);
    List<Pessoa> findByNome(String nome);
}
