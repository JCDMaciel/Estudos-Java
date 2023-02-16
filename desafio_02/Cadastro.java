import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cadastro {

    Scanner in = new Scanner(System.in);
    List<Pessoa> pessoas = new ArrayList<>();
    String nome;
    String cpf;
    String email;
    String endereco;
    String dataDeNascimento;
    String nomeDoContato;
    String telefoneDoContato;
    String emailDoContato;
    int menu = 0;

    public void menu(){
        System.out.println("******* Cadastros *******");
        System.out.println("1- Cadastrar pessoas: \n2- Imprimir pessoas: \n3- Voltar ao menu principal: ");
        menu = in.nextInt();
        switch (menu){
            case 1:
                cadastrarPessoas();
                break;
            case 2:
                if (pessoas.size() != 0) imprimirPessoas();
                else System.out.println("******* Não há pessoas cadastradas *******\n");
                break;
            case 3:
                break;
            default:
                System.out.println("Nenhuma opção foi selecionada\n*******************\nVoltando ao menu principal\n*******************");
                System.out.println();
                break;
        }
    }

    public void cadastrarPessoas(){
        recebeNome();
        recebeCpf();
        recebeEmail();
        recebeEndereco();
        recebeDataDeNascimento();
        pessoas.add(new Pessoa(nome, ValidaCpf.imprimeCPF(cpf), email, endereco, ValidaData.imprimeDataDeNascimento(dataDeNascimento), addContatos()));
    }

    public void recebeNome(){
        System.out.println("Me informe o nome da pessoa: ");
        nome = in.next();
    }

    public void recebeCpf(){
        String testeCpf;

        System.out.println("Me informe o cpf da pessoa: ");
        testeCpf = in.next();
        if (ValidaCpf.isCpf(testeCpf)) cpf = testeCpf;
        else {
            while (!ValidaCpf.isCpf(testeCpf)){
                System.out.println("******* CPF inválido *******");
                System.out.println();
                recebeCpf();
            }
        }
    }

    public void recebeEmail(){
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
    }

    public void recebeEndereco(){
        System.out.println("Me informe o endereço da pessoa: ");
        endereco = in.next();
    }

    public void recebeDataDeNascimento(){
        String testaDataDeNascimento;

        System.out.println("Me informe a data de nascimento da pessoa: \nUsar o formato DDMMAAAA");
        testaDataDeNascimento = in.next();

        if (testaDataDeNascimento.length() == 8 && ValidaData.validaDataDeNascimento(testaDataDeNascimento)){
            int pergunta;

            System.out.println("A data de nascimento inserida foi: " + ValidaData.imprimeDataDeNascimento(testaDataDeNascimento));
            System.out.println("Essa é a data de nascimento correta?\n 1 para sim, outro valor para não");
            pergunta = in.nextInt();

            if (pergunta == 1) dataDeNascimento = testaDataDeNascimento;
            else recebeDataDeNascimento();
        }
        else {
            System.out.println("******* Valor invalido *******");
            System.out.println("Insira DDMMAAAA");
            recebeDataDeNascimento();
        }
    }

    public List<Contato> addContatos(){
        List<Contato> contatos  = new ArrayList<>();

        System.out.println("É necessário adicionar no minimo dois contatos: ");

        cadastrarContatos();
        contatos.add(new Contato(nomeDoContato, telefoneDoContato, emailDoContato));
        cadastrarContatos();
        contatos.add(new Contato(nomeDoContato, telefoneDoContato, emailDoContato));

        int novoContato;

        System.out.println("Deseja cadastrar um novo contato?\n1 para sim\nOutro valor para não");
        novoContato = in.nextInt();

        while (novoContato == 1){
            cadastrarContatos();
            contatos.add(new Contato(nomeDoContato, telefoneDoContato, emailDoContato));

            System.out.println("Deseja cadastrar um novo contato?\n1 para sim\nOutro valor para não");
            novoContato = in.nextInt();
        }

        return contatos;
    }

    public void cadastrarContatos(){
        recebeNomeDoContato();
        recebeTelefoneDoContato();
        recebeEmailDoContato();
    }

    public void recebeNomeDoContato(){
        System.out.println("Insira o nome do contato: ");
        nomeDoContato = in.next();
    }

    public void recebeTelefoneDoContato(){
        System.out.println("Insira o telefone do contato: ");
        telefoneDoContato = in.next();
    }

    public void recebeEmailDoContato(){
        String testeEmailDoContato;

        System.out.println("Me informe o email do contato: ");
        testeEmailDoContato = in.next();
        if (ValidaEmail.isEmail(testeEmailDoContato)) emailDoContato = testeEmailDoContato;
        else {
            while (!ValidaEmail.isEmail(testeEmailDoContato)){
                System.out.println("******* Email inválido *******");
                System.out.println();
                recebeEmailDoContato();
            }
        }
    }

    public void imprimirPessoas(){
        for (Pessoa pessoaDaLista : pessoas){
            System.out.println("********* Pessoa *********");
            System.out.println("Nome da pessoa: " + pessoaDaLista.getNome());
            System.out.println("CPF da pessoa: " + pessoaDaLista.getCpf());
            System.out.println("E-mail da pessoa: " + pessoaDaLista.getEmail());
            System.out.println("Endereço da pessoa: " + pessoaDaLista.getEndereco());
            System.out.println("Data de nascimento da pessoa: " + pessoaDaLista.getDataDeNascimento());
            System.out.println();

            for (Contato contatosDasPessoas : pessoaDaLista.getContatos()){
                System.out.println("********* Contato *********");
                System.out.println("Nome do contato: " + contatosDasPessoas.getNomeDoContato());
                System.out.println("Telefone do contato: " + contatosDasPessoas.getTelefoneDoContato());
                System.out.println("E-mail do contato: " + contatosDasPessoas.getEmailDoContato());
                System.out.println();
            }
        }
    }
}
