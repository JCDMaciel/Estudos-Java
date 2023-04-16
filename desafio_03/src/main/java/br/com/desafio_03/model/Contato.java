package br.com.desafio_03.model;

public class Contato {
    private Long idDaPessoa;
    private Long idDoContato;
    private String nomeDoContato;
    private String telefoneDoContato;
    private String emailDoContato;

    public Contato(Long idDaPessoa,Long idDoContato, String nomeDoContato, String telefoneDoContato, String emailDoContato) {
        this.idDaPessoa = idDaPessoa;
        this.idDoContato = idDoContato;
        this.nomeDoContato = nomeDoContato;
        this.telefoneDoContato = telefoneDoContato;
        this.emailDoContato = emailDoContato;
    }

    public Contato(Long idDoContato, String nomeDoContato, String telefoneDoContato, String emailDoContato) {
        this.idDoContato = idDoContato;
        this.nomeDoContato = nomeDoContato;
        this.telefoneDoContato = telefoneDoContato;
        this.emailDoContato = emailDoContato;
    }
    public Contato(String nomeDoContato, String telefoneDoContato, String emailDoContato) {
        this.nomeDoContato = nomeDoContato;
        this.telefoneDoContato = telefoneDoContato;
        this.emailDoContato = emailDoContato;
    }

    public String getNomeDoContato() {
        return nomeDoContato;
    }

    public String getTelefoneDoContato() {
        return telefoneDoContato;
    }

    public String getEmailDoContato() {
        return emailDoContato;
    }

    public Long getIdDoContato() {
        return idDoContato;
    }

    public void setIdDoContato(Long idDoContato) {
        this.idDoContato = idDoContato;
    }

    public void setIdDaPessoa(Long idDaPessoa) {
        this.idDaPessoa = idDaPessoa;
    }

    public void setNomeDoContato(String nomeDoContato) {
        this.nomeDoContato = nomeDoContato;
    }

    public void setTelefoneDoContato(String telefoneDoContato) {
        this.telefoneDoContato = telefoneDoContato;
    }

    public void setEmailDoContato(String emailDoContato) {
        this.emailDoContato = emailDoContato;
    }
}
