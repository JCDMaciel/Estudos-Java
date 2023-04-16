package br.com.desafio_05.servlets;

import br.com.desafio_05.dao.PessoaDAO;
import br.com.desafio_05.model.Pessoa;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/excluiPessoa")
public class ExcluiPessoaServlet extends HttpServlet {
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

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.findPessoaById(jsonPessoa.getLong("id"));

        pessoaDAO.delete(pessoa,  jsonPessoa.getLong("id"));
    }
}
