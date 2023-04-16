package br.com.desafio_05.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desafio_05.dao.ContatoDAO;
import br.com.desafio_05.dao.PessoaDAO;
import br.com.desafio_05.model.Contato;
import br.com.desafio_05.model.Pessoa;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/adicionaPessoa")
public class AdicionaPessoaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String linha;

        while((linha = reader.readLine()) != null){
            buffer.append(linha);
            buffer.append(System.lineSeparator());
        }

        response.setContentType("application/json; charset=UTF-8");
        String data = buffer.toString();

        JSONObject jsonPessoa = new JSONObject(data);
        JSONArray jsonContatoString = jsonPessoa.getJSONArray("contatos");

        Pessoa pessoa = new Pessoa();
        pessoa.fromJson(jsonPessoa);

        PessoaDAO pessoaDao = new PessoaDAO();
        pessoaDao.save(pessoa);

        for (int i = 0; i < jsonContatoString.length(); i++){
            JSONObject json = jsonContatoString.getJSONObject(i);

            ContatoDAO contatoDAO = new ContatoDAO();

            Contato contato = new Contato();
            contato.fromJson(json);

            contato.setPessoa(pessoa); // Associa o contato à pessoa
            contatoDAO.save(contato);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
