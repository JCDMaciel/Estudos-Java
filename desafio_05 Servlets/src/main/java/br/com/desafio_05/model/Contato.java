package br.com.desafio_05.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato")
    private Long idDoContato;
    @Column(name = "nome")
    private String nomeDoContato;
    @Column(name = "telefone")
    private String telefoneDoContato;
    @Column(name = "email")
    private String emailDoContato;
    @ManyToOne
    @JoinColumn(
            name = "id_pessoa",
            nullable = false
    )
    private Pessoa pessoa;

    public Contato(String nomeDoContato, String telefoneDoContato, String emailDoContato) {
        this.nomeDoContato = nomeDoContato;
        this.telefoneDoContato = telefoneDoContato;
        this.emailDoContato = emailDoContato;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("idDoContato", getIdDoContato());
        json.put("nomeDoContato", getNomeDoContato());
        json.put("telefoneDoContato", getTelefoneDoContato());
        json.put("emailDoContato", getEmailDoContato());
        json.put("pessoaDoContato", getPessoa());

        return json;
    }

    public Contato fromJson(JSONObject json) {
        Contato contato = new Contato();

        this.nomeDoContato = json.getString("nomeDoContato");
        this.telefoneDoContato = json.getString("telefoneDoContato");
        this.emailDoContato = json.getString("emailDoContato");

        return contato;
    }
}
