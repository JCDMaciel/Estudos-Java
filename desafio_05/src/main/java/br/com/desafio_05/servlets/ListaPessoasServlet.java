package br.com.desafio_05.servlets;

import br.com.desafio_05.dao.PessoaDAO;
import br.com.desafio_05.model.Pessoa;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listaPessoas")
public class ListaPessoasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        JSONArray pessoas = new JSONArray();

        for (Pessoa pessoa : pessoaDAO.findAllPessoas()) {
            pessoas.put(pessoa.toJson());
        }
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(pessoas);
    }
}
