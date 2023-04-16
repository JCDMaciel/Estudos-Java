package br.com.desafio_03.dao;

import br.com.desafio_03.infra.ConnectionFactory;
import br.com.desafio_03.model.Pessoa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaDAO implements InterfacePessoaDAO {

    @Override
    public Pessoa save(Pessoa pessoa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO pessoas (nome, cpf, email, endereco, data_de_nascimento) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());
            preparedStatement.setString(3, pessoa.getEmail());
            preparedStatement.setString(4, pessoa.getEndereco());
            preparedStatement.setDate(5, Date.valueOf(pessoa.getDataDeNascimento()));

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            pessoa.setId(generatedId);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public Pessoa update(Pessoa pessoa, Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE pessoas SET nome = ?, cpf = ?, email = ?, endereco = ?, data_de_nascimento = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());
            preparedStatement.setString(3, pessoa.getEmail());
            preparedStatement.setString(4, pessoa.getEndereco());
            preparedStatement.setDate(5, Date.valueOf(pessoa.getDataDeNascimento()));
            preparedStatement.setInt(6, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public Pessoa updateNome(Pessoa pessoa, Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE pessoas SET nome = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setInt(2, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public Pessoa updateCpf(Pessoa pessoa, Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE pessoas SET cpf = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoa.getCpf());
            preparedStatement.setInt(2, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public Pessoa updateEmail(Pessoa pessoa, Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE pessoas SET email = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoa.getEmail());
            preparedStatement.setInt(2, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public Pessoa updateEndereco(Pessoa pessoa, Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE pessoas SET endereco = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pessoa.getEndereco());
            preparedStatement.setInt(2, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public Pessoa updateDataDeNascimento(Pessoa pessoa, Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE pessoas SET data_de_nascimento = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(pessoa.getDataDeNascimento()));
            preparedStatement.setInt(2, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM pessoas WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Math.toIntExact(id));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pessoa> findAll() {
        String sql = "SELECT id, nome, cpf, email, endereco, data_de_nascimento FROM pessoas";

        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idNoBanco = resultSet.getLong("id");
                String nomeNoBanco = resultSet.getString("nome");
                String cpfNoBanco = resultSet.getString("cpf");
                String emailNoBanco = resultSet.getString("email");
                String enderecoNoBanco = resultSet.getString("endereco");
                LocalDate dataDeNascimentoNoBanco = resultSet.getDate("data_de_nascimento").toLocalDate();

                Pessoa pessoa = new Pessoa(idNoBanco, nomeNoBanco, cpfNoBanco, emailNoBanco, enderecoNoBanco, dataDeNascimentoNoBanco);
                pessoas.add(pessoa);

            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoas;
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        String sql = "SELECT id, nome, cpf, email, endereco, data_de_nascimento FROM pessoas WHERE id = ?";

        Pessoa pessoa = null;

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idNoBanco = resultSet.getLong("id");
                String nomeNoBanco = resultSet.getString("nome");
                String cpfNoBanco = resultSet.getString("cpf");
                String emailNoBanco = resultSet.getString("email");
                String enderecoNoBanco = resultSet.getString("endereco");
                LocalDate dataDeNascimentoNoBanco = resultSet.getDate("data_de_nascimento").toLocalDate();

                pessoa = new Pessoa(idNoBanco, nomeNoBanco, cpfNoBanco, emailNoBanco, enderecoNoBanco, dataDeNascimentoNoBanco);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(pessoa);
    }
    @Override
    public List<Pessoa> findByNome(String nome) {
        String sql = "SELECT id, nome, cpf, email, endereco, data_de_nascimento FROM pessoas WHERE nome = ?";

        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idNoBanco = resultSet.getLong("id");
                String nomeNoBanco = resultSet.getString("nome");
                String cpfNoBanco = resultSet.getString("cpf");
                String emailNoBanco = resultSet.getString("email");
                String enderecoNoBanco = resultSet.getString("endereco");
                LocalDate dataDeNascimentoNoBanco = resultSet.getDate("data_de_nascimento").toLocalDate();

                Pessoa pessoa = new Pessoa(idNoBanco, nomeNoBanco, cpfNoBanco, emailNoBanco, enderecoNoBanco, dataDeNascimentoNoBanco);
                pessoas.add(pessoa);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoas;
    }
}
