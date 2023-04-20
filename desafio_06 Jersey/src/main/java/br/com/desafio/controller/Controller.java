package br.com.desafio.controller;

import br.com.desafio.dao.ContatoDAO;
import br.com.desafio.dao.PessoaDAO;
import br.com.desafio.model.Contato;
import br.com.desafio.model.Pessoa;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cadastro")
public class Controller {
    @GET
    public String obter() {
        return "Hello, Jersey!";
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listaPessoas/")
    public Response listaPessoas(){
        PessoaDAO pessoaDAO = new PessoaDAO();
        JSONArray pessoas = new JSONArray();

        for (Pessoa pessoa : pessoaDAO.findAllPessoas()) {
            pessoas.put(pessoa.toJson());
        }

        Response response = Response.status(Response.Status.OK).entity(pessoas.toString()).build();

        return response;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editaPessoa/{id}")
    public void editaPessoa(String data, @PathParam("id") long id){
        JSONObject jsonPessoa = new JSONObject(data);

        Pessoa pessoa = new Pessoa();
        pessoa.fromJson(jsonPessoa);

        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.update(pessoa, id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adicionaPessoa/")
    public void adicionaPessoa(String data){
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

            contato.setPessoa(pessoa);
            contatoDAO.save(contato);
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/excluiPessoa/{id}")
    public void excluiPessoa(@PathParam("id") long id){
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.findPessoaById(id);

        pessoaDAO.delete(pessoa, id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/buscaPessoa/{id}")
    public Response buscaPessoa(@PathParam("id") long id){
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.findPessoaById(id);
        JSONObject json = pessoa.toJson();

        Response response = Response.status(Response.Status.OK).entity(json.toString()).build();

        return response;
    }
}
