import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Produto> produtos;

    public CarrinhoDeCompras() {
        this.produtos = new ArrayList<>();
    }

    public void adiciona(Produto produto) {
        this.produtos.add(produto);
    }

    public List <Produto> getProdutos() {
        return this.produtos;
    }
}
