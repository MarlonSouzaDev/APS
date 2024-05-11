import java.util.ArrayList;
import java.util.List;

public class Produto2 {
    private int id;
    private static int proximoId = 1; // Variável estática para gerar o próximo ID automaticamente
    private String nome;
    private double preco;
    private int quantidade;
    private static List<Produto2> estoque = new ArrayList<>();

    public Produto2(String nome, double preco, int quantidade) {
        this.id = proximoId++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        estoque.add(id, this);
    }

    public static void adicionarProduto(String nome, double preco, int quantidade){
        new Produto2(nome, preco, quantidade);
        System.out.println("Produto adicionado ao estoque: " + nome + " Quantidade: " + quantidade);
    }

    @SuppressWarnings("unlikely-arg-type")
	public static void removerProduto(int id){
        if (estoque.contains(id)){
            Produto2 produto = estoque.get(id);
            estoque.remove(id);
            System.out.println("Produto removido do estoque: " + produto.getNome());
        } else {
            System.out.println("Produto não encontrado no estoque");
        }
    }

    public static void exibirListaProdutos() {
        System.out.println("Lista de produtos no estoque:");
        for (Produto2 produto : estoque) {
            System.out.println("- ID: " + produto.getId() + ", Nome: " + produto.getNome() +
                    ", Preço: " + produto.getPreco() + ", Quantidade: " + produto.getQuantidade());
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static List<Produto2> getEstoque() {
        return estoque;
    }
}
