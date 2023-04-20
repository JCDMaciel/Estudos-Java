package br.com.desafio.model;

import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    @Column(name = "data_de_nascimento")
    private Date dataDeNascimento;
    @OneToMany(
            mappedBy = "pessoa",
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Contato> contatos;

    public Pessoa(String nome, String cpf, String email, String endereco, Date dataDeNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.dataDeNascimento = dataDeNascimento;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id", getId());
        json.put("nome", getNome());
        json.put("cpf", getCpf());
        json.put("email", getEmail());
        json.put("endereco", getEndereco());
        json.put("dataDeNascimento", String.valueOf(getDataDeNascimento()));

        return json;
    }

    public Pessoa fromJson(JSONObject json){
        Pessoa pessoa = new Pessoa();

        this.nome = json.getString("nome");
        this.cpf = json.getString("cpf");
        this.email = json.getString("email");
        this.endereco = json.getString("endereco");
        this.dataDeNascimento = parseDate(json.getString("dataDeNascimento"));

        return pessoa;
    }

    public static Date parseDate(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            return sdf.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
