package br.com.desafio_04.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
