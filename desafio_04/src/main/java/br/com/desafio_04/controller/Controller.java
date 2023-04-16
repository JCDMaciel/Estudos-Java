package br.com.desafio_04.controller;

import br.com.desafio_04.dao.ContatoDAO;
import br.com.desafio_04.dao.DAO;
import br.com.desafio_04.dao.PessoaDAO;
import br.com.desafio_04.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller{

    Scanner in = new Scanner(System.in);
    PessoaDAO pessoaDAO = new PessoaDAO();
    ContatoDAO contatoDAO = new ContatoDAO();
    String nome;
    String cpf;
    String email;
    String endereco;
    LocalDate dataDeNascimento;
    String nomeDoContato;
    String telefoneDoContato;
    String emailDoContato;
    int menu = 0;

    public void menu(){
        System.out.println("******* Cadastros *******");
        System.out.
                println("" +
                        "1- Cadastrar pessoas: \n" +
                        "2- Imprimir pessoas: \n" +
                        "3- Editar pessoas: \n" +
                        "4- Excluir pessoas: \n" +
                        "5- Voltar ao menu principal: ");
                        menu = in.nextInt();

        switch (menu){
            case 1:
                cadastrarPessoas();
                System.out.println("Alteração realizada conforme solicitado");
                System.out.println();
                break;
            case 2:
                System.out.println("******* Mostrando pessoas *******");
                System.out.println();
                imprimirPessoas();
                System.out.println("******* Fim das pessoas *******");
                System.out.println();
                break;
            case 3:
                editarPessoas();
                System.out.println("Alteração realizada conforme solicitado");
                System.out.println();
                break;
            case 4:
                excluirPessoas();
                System.out.println("Alteração realizada conforme solicitado");
                System.out.println();
                break;
            case 5:
                break;
            default:
                System.out.println("Nenhuma opção foi selecionada\n Voltando ao menu principal");
                System.out.println();
                break;
        }
    }

    public void cadastrarPessoas(){
        Pessoa pessoa = new Pessoa(recebeNome(),
                Validacao.imprimeCPF(recebeCpf()),
                recebeEmail(),
                recebeEndereco(),
                recebeDataDeNascimento());

        List<Contato> contatos = addContatos(pessoa);
        pessoa.setContatos(contatos);

        pessoaDAO.save(pessoa);

        for (Contato contato : contatos){
            contatoDAO.em.merge(contato);
        }
    }

    private String recebeNome(){
        System.out.println("Me informe o nome da pessoa: ");
        nome = in.next();
        return nome;
    }

    private String recebeCpf(){
        String testeCpf;

        System.out.println("Me informe o cpf da pessoa: ");
        testeCpf = in.next();

        if (Validacao.isCpf(testeCpf)) cpf = testeCpf;
        else {
            System.out.println("******* CPF inválido *******");
            System.out.println();
            recebeCpf();
        }
        return cpf;
    }

    private String recebeEmail(){
        String testeEmail;

        System.out.println("Me informe o email da pessoa: ");
        testeEmail = in.next();
        if (Validacao.isEmail(testeEmail)) email = testeEmail;
        else {
            System.out.println("******* Email inválido *******");
            System.out.println();
            recebeEmail();
        }

        return email;
    }

    private String recebeEndereco(){
        System.out.println("Me informe o endereço da pessoa: ");
        endereco = in.next();

        return endereco;
    }

    private LocalDate recebeDataDeNascimento(){
        System.out.println("Me informe a data de nascimento da pessoa: \nUse o formato DD/MM/AAAA");
        String input = in.next();

        LocalDate testaDataDeNascimento = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (Validacao.validaDataDeNascimento(testaDataDeNascimento)){
            int pergunta;

            System.out.println("A data de nascimento inserida foi: " + Validacao.imprimeDataDeNascimento(testaDataDeNascimento));
            System.out.println("Essa é a data de nascimento correta?\n 1 para sim, outro valor para não");
            pergunta = in.nextInt();

            if (pergunta == 1) dataDeNascimento = testaDataDeNascimento;
            else recebeDataDeNascimento();
        }
        else {
            System.out.println("******* Valor inválido *******");
            System.out.println("Insira DD/MM/AAAA");
            recebeDataDeNascimento();
        }

        return dataDeNascimento;
    }

    private List<Contato> addContatos(Pessoa pessoa) {
        List<Contato> contatos = new ArrayList<>();

        System.out.println("É necessário adicionar no mínimo dois contatos: ");

        Contato contato1 = new Contato(recebeNomeDoContato(), recebeTelefoneDoContato(), recebeEmailDoContato());
        contato1.setPessoa(pessoa);
        contatos.add(contato1);

        Contato contato2 = new Contato(recebeNomeDoContato(), recebeTelefoneDoContato(), recebeEmailDoContato());
        contato2.setPessoa(pessoa);
        contatos.add(contato2);

        int novoContato;

        System.out.println("Deseja cadastrar um novo contato? \n" +
                "1 para sim \n" +
                "Outro valor para não");
        novoContato = in.nextInt();

        while (novoContato == 1){
            cadastrarContatos();
            Contato contato = new Contato(nomeDoContato, telefoneDoContato, emailDoContato);
            contato.setPessoa(pessoa);
            contatos.add(contato);

            System.out.println(
                    "Deseja cadastrar um novo contato? \n" +
                            "1 para sim \n" +
                            "Outro valor para não");
            novoContato = in.nextInt();
        }

        return contatos;
    }

    private void cadastrarContatos(){
        recebeNomeDoContato();
        recebeTelefoneDoContato();
        recebeEmailDoContato();
    }

    private String recebeNomeDoContato(){
        System.out.println("Insira o nome do contato: ");
        nomeDoContato = in.next();
        return nomeDoContato;
    }

    private String recebeTelefoneDoContato(){
        System.out.println("Insira o telefone do contato: ");
        telefoneDoContato = in.next();
        return telefoneDoContato;
    }

    private String recebeEmailDoContato(){
        String testeEmailDoContato;

        System.out.println("Me informe o email do contato: ");
        testeEmailDoContato = in.next();
        if (Validacao.isEmail(testeEmailDoContato)) emailDoContato = testeEmailDoContato;
        else {
            System.out.println("******* Email inválido *******");
            System.out.println();
            recebeEmailDoContato();
        }
        return emailDoContato;
    }

    public void imprimirPessoas(){
        List<Pessoa> pessoas;

        System.out.println(
                "1- Imprimir todas as pessoas: \n" +
                "2- Pesquisar por ID: \n");
        int menu = in.nextInt();

        switch (menu){
            case 1:
                pessoas = pessoaDAO.findAllPessoas();

                for (Object pessoa : pessoas) {
                    montarImpressaoPessoas((Pessoa) pessoa);

                    List<Contato> contatos = contatoDAO.findAllContatos(((Pessoa) pessoa).getId());

                    for (Object contato : contatos){
                        montarImpressaoContatos((Contato) contato);
                    }
                }
                break;
            case 2:
                System.out.println("Me informe o ID da pessoa");
                Long id = in.nextLong();

                Pessoa pessoa = pessoaDAO.findPessoaById(id);

                if (pessoa != null) {
                    montarImpressaoPessoas(pessoa);
                    List<Contato> contatos = contatoDAO.findAllContatos(id);

                    for (Object contato : contatos){
                        montarImpressaoContatos((Contato) contato);
                    }
                } else {
                    System.out.println("ID não encontrado");
                }
                break;
            default:
                System.out.println("******* Opção inválida *******");
                imprimirPessoas();
                break;
        }
    }

    public void editarPessoas(){
        System.out.println("Me informe o ID da pessoa que deseja editar");
        Long id = in.nextLong();

        Pessoa pessoa = pessoaDAO.findPessoaById(id);

        if (pessoa != null){
            pessoa.setNome(recebeNome());
            pessoa.setCpf(Validacao.imprimeCPF(recebeCpf()));
            pessoa.setEmail(recebeEmail());
            pessoa.setEndereco(recebeEndereco());
            pessoa.setDataDeNascimento(recebeDataDeNascimento());
            pessoaDAO.update(pessoa, id);
            System.out.println("Alteração realizada conforme solicitado");
            System.out.println();
        } else {
            System.out.println("ID não encontrado");
        }
    }

    public void excluirPessoas(){
        System.out.println("Me informe o ID da pessoa que deseja excluir");
        Long id = Long.valueOf(in.next());

        Pessoa pessoa = pessoaDAO.findPessoaById(id);

        if (pessoa != null){
            pessoaDAO.delete(pessoa, id);
            System.out.println("Alteração realizada conforme solicitado");
            System.out.println();
        } else {
            System.out.println("ID não encontrado");
        }
    }

    private void montarImpressaoPessoas(Pessoa pessoa){
        System.out.println("******* Pessoa *******");
        System.out.println("ID: " + pessoa.getId());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Cpf: " + pessoa.getCpf());
        System.out.println("Email: " + pessoa.getEmail());
        System.out.println("Endereço: " + pessoa.getEndereco());
        System.out.println("Data de nascimento: "
                + pessoa.getDataDeNascimento()
                .format(DateTimeFormatter
                        .ofPattern("dd/MM/yyyy")));
        System.out.println();
    }

    private void montarImpressaoContatos(Contato contato){
        System.out.println("******* Contato *******");
        System.out.println("id do contato: " + contato.getIdDoContato());
        System.out.println("nome do contato: " + contato.getNomeDoContato());
        System.out.println("telefone do contato: " + contato.getTelefoneDoContato());
        System.out.println("email do contato: " + contato.getEmailDoContato());
        System.out.println();
    }
}
