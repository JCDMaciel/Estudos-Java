import java.util.List;

public class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String dataDeNascimento;
    private List<Contato> contatos;

    public Pessoa(String nome, String cpf, String email, String endereco, String dataDeNascimento, List<Contato> contatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.dataDeNascimento = dataDeNascimento;
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public List<Contato> getContatos() {
        return contatos;
    }
}
