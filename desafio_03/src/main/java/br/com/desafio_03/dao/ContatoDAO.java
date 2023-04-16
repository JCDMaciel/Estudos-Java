package br.com.desafio_03.dao;

import br.com.desafio_03.infra.ConnectionFactory;
import br.com.desafio_03.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements InterfaceContatoDAO {

    @Override
    public Contato save(Contato contato, Long idDaPessoa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO contatos (id_pessoa, nome, telefone, email) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, idDaPessoa);
            preparedStatement.setString(2, contato.getNomeDoContato());
            preparedStatement.setString(3, contato.getTelefoneDoContato());
            preparedStatement.setString(4, contato.getEmailDoContato());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id_contato");
            contato.setIdDoContato(generatedId);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contato;
    }

    @Override
    public Contato update(Contato contato, Long idDaPessoa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id_pessoa = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contato.getNomeDoContato());
            preparedStatement.setString(2, contato.getTelefoneDoContato());
            preparedStatement.setString(3, contato.getEmailDoContato());
            preparedStatement.setLong(4, idDaPessoa);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contato;
    }

    @Override
    public List<Contato> findById(Long idDaPessoa) {
        String sql = "SELECT id_contato, nome, telefone, email FROM contatos WHERE id_pessoa = ?";

        List<Contato> contatos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, idDaPessoa);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idNoBanco = resultSet.getLong("id_contato");
                String nomeNoBanco = resultSet.getString("nome");
                String telefoneNoBanco = resultSet.getString("telefone");
                String emailNoBanco = resultSet.getString("email");

                Contato contato = new Contato(idNoBanco, nomeNoBanco, telefoneNoBanco, emailNoBanco);
                contatos.add(contato);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contatos;
    }
}
