public class Contato {
    private String nomeDoContato;
    private String telefoneDoContato;
    private String emailDoContato;

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
}
