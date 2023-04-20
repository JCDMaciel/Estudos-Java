import java.util.List;

public class MaiorEMenor {
    private Produto menor;
    private Produto maior;

    public void encontra(List<Produto> carrinho) {
        for (Produto produto : carrinho) {
            if ((menor == null) || (produto.getValor() < menor.getValor())) {
                menor = produto;
            }
            if ((maior == null) || (produto.getValor() > maior.getValor())) {
                maior = produto;
            }
        }
    }

    public Produto getMenor() {
        return menor;
    }

    public Produto getMaior() {
        return maior;
    }
}