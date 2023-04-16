package br.com.desafio_05.servlets;

import br.com.desafio_05.dao.PessoaDAO;
import br.com.desafio_05.model.Pessoa;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/editaPessoa")
public class EditaPessoaServlet extends HttpServlet {
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

        Pessoa pessoa = new Pessoa();
        pessoa.fromJson(jsonPessoa);

        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.update(pessoa,  jsonPessoa.getLong("id"));
    }
}
