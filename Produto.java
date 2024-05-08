import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String nome;
    private int codigo;
    private int quantidadeEmEstoque;

    public Produto(String nome, int codigo, int quantidadeEmEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", codigo=" + codigo +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                '}';
    }
}

class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public void modificarProduto(Produto produto, String novoNome, int novoCodigo, int novaQuantidade) {
        produto.setNome(novoNome);
        produto.setCodigo(novoCodigo);
        produto.setQuantidadeEmEstoque(novaQuantidade);
    }

    public boolean verificarProduto(Produto produto) {
        return produtos.contains(produto);
    }

    public boolean verificarQuantidade(Produto produto, int quantidade) {
        return produto.getQuantidadeEmEstoque() >= quantidade;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "produtos=" + produtos +
                '}';
    }
}
