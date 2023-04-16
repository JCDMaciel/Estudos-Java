package br.com.desafio_03;

import br.com.desafio_03.dao.ContatoDAO;
import br.com.desafio_03.dao.InterfaceContatoDAO;
import br.com.desafio_03.dao.InterfacePessoaDAO;
import br.com.desafio_03.dao.PessoaDAO;
import br.com.desafio_03.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Controller{

    Scanner in = new Scanner(System.in);
    InterfacePessoaDAO pessoaDao = new PessoaDAO();
    InterfaceContatoDAO contatoDAO = new ContatoDAO();
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
                System.out.println("Alteração realizada conforma solicitado");
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
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 4:
                excluirPessoas();
                System.out.println("Alteração realizada conforma solicitado");
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
                ValidaCpf.imprimeCPF(recebeCpf()),
                recebeEmail(),
                recebeEndereco(),
                recebeDataDeNascimento());
        pessoaDao.save(pessoa);
        addContatos(pessoa.getId());
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

        if (ValidaCpf.isCpf(testeCpf)) cpf = testeCpf;
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
        if (ValidaEmail.isEmail(testeEmail)) email = testeEmail;
        else {
            while (!ValidaEmail.isEmail(testeEmail)){
                System.out.println("******* Email inválido *******");
                System.out.println();
                recebeEmail();
            }
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

        if (ValidaData.validaDataDeNascimento(testaDataDeNascimento)){
            int pergunta;

            System.out.println("A data de nascimento inserida foi: " + ValidaData.imprimeDataDeNascimento(testaDataDeNascimento));
            System.out.println("Essa é a data de nascimento correta?\n 1 para sim, outro valor para não");
            pergunta = in.nextInt();

            if (pergunta == 1) dataDeNascimento = testaDataDeNascimento;
            else recebeDataDeNascimento();
        }
        else {
            System.out.println("******* Valor invalido *******");
            System.out.println("Insira DD/MM/AAAA");
            recebeDataDeNascimento();
        }

        return dataDeNascimento;
    }

    private List<Contato> addContatos(Long idDaPessoa){
        List<Contato> contatos  = new ArrayList<>();

        System.out.println("É necessário adicionar no minimo dois contatos: ");

        cadastrarContatos();
        contatoDAO.save(new Contato(nomeDoContato, telefoneDoContato, emailDoContato), idDaPessoa);
        cadastrarContatos();
        contatoDAO.save(new Contato(nomeDoContato, telefoneDoContato, emailDoContato), idDaPessoa);

        int novoContato;

        System.out.println("Deseja cadastrar um novo contato? \n" +
                "1 para sim \n" +
                "Outro valor para não");
        novoContato = in.nextInt();

        while (novoContato == 1){
            cadastrarContatos();
            contatos.add(new Contato(nomeDoContato, telefoneDoContato, emailDoContato));

            System.out.println("Deseja cadastrar um novo contato? \n" +
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
        if (ValidaEmail.isEmail(testeEmailDoContato)) emailDoContato = testeEmailDoContato;
        else {
            System.out.println("******* Email inválido *******");
            System.out.println();
            recebeEmailDoContato();
        }
        return emailDoContato;
    }

    public void imprimirPessoas(){
        List<Pessoa> pessoas;

        System.out.println("1- Imprimir todas as pessoas: \n" +
                "2- Pesquisar por ID: \n" +
                "3- Pesquisar por Nome: ");
        int menu = in.nextInt();

        switch (menu){
            case 1:
                pessoas = pessoaDao.findAll();

                for (Pessoa pessoa : pessoas) {
                    montarImpressaoPessoas(pessoa);

                    List<Contato> contatos = contatoDAO.findById(pessoa.getId());
                    for (Contato contato : contatos) {
                        montarImpressaoContatos(contato);
                    }
                }
                break;
            case 2:
                System.out.println("Me informe o ID da pessoa");
                Long id = in.nextLong();

                Optional<Pessoa> pessoaOptional = pessoaDao.findById(id);

                pessoaOptional.ifPresent(this::montarImpressaoPessoas);

                List<Contato> contatos = contatoDAO.findById(pessoaOptional.get().getId());

                for (Contato contato : contatos) {
                    montarImpressaoContatos(contato);
                }
                break;
            case 3:
                System.out.println("Me informe o nome da pessoa");
                String nome = in.next();

                pessoas = pessoaDao.findByNome(nome);

                for (Pessoa pessoa : pessoas) {
                    montarImpressaoPessoas(pessoa);
                    contatos = contatoDAO.findById(pessoa.getId());

                    for (Contato contato : contatos) {
                        montarImpressaoContatos(contato);
                    }
                }
                break;
            default:
                System.out.println("******* Opção inválida *******");
                imprimirPessoas();
                break;
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

    public void editarPessoas(){
        System.out.println("Me informe o ID da pessoa que deseja editar");
        Long id = in.nextLong();

        Optional<Pessoa> pessoas = pessoaDao.findById(id);
        List<Contato> contatos = contatoDAO.findById(id);

        Pessoa pessoa = pessoas.get();

        System.out.println("1- Para editar todos os campos: \n" +
                "2- Para editar somente o nome: \n" +
                "3- Para editar somente o cpf: \n" +
                "4- Para editar somente o email: \n" +
                "5- Para editar somente o endereço: \n" +
                "6- Para editar somente a data de nascimento: \n" +
                "7- Para editar somente os contatos: \n");
        int menu = in.nextInt();

        switch (menu){
            case 1:
                pessoa.setNome(recebeNome());
                pessoa.setCpf(ValidaCpf.imprimeCPF(recebeCpf()));
                pessoa.setEmail(recebeEmail());
                pessoa.setEndereco(recebeEndereco());
                pessoa.setDataDeNascimento(recebeDataDeNascimento());
                pessoaDao.update(pessoa, id);
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 2:
                pessoa.setNome(recebeNome());
                pessoaDao.updateNome(pessoa, id);
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 3:
                pessoa.setCpf(ValidaCpf.imprimeCPF(recebeCpf()));
                pessoaDao.updateCpf(pessoa, id);
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 4:
                pessoa.setEmail(recebeEmail());
                pessoaDao.updateEmail(pessoa, id);
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 5:
                pessoa.setEndereco(recebeEndereco());
                pessoaDao.updateEndereco(pessoa, id);
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 6:
                pessoa.setDataDeNascimento(recebeDataDeNascimento());
                pessoaDao.updateDataDeNascimento(pessoa, id);
                System.out.println("Alteração realizada conforma solicitado");
                System.out.println();
                break;
            case 7:
                for (Contato contato : contatos){
                    contato.setNomeDoContato(recebeNomeDoContato());
                    contato.setTelefoneDoContato(recebeTelefoneDoContato());
                    contato.setEmailDoContato(recebeEmailDoContato());
                    contatoDAO.update(contato, id);
                    System.out.println("Alteração realizada conforma solicitado");
                    System.out.println();
                }
                break;
            default:
                System.out.println("******* Opção inválida *******");
                editarPessoas();
                break;
        }
    }

    public void excluirPessoas(){
        System.out.println("Me informe o ID da pessoa que deseja excluir");
        Long id = Long.valueOf(in.next());

        pessoaDao.delete(id);
    }
}
