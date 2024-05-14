import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        estoque.add(this);
    }

    public static void adicionarProduto(String nome, double preco, int quantidade){
        new Produto2(nome, preco, quantidade);
        System.out.println("Produto adicionado ao estoque: " + nome + " Quantidade: " + quantidade);
        
    }

    // Método para remover um produto do estoque
    public static void removerProduto(int id) {
        Produto2 produto = estoque.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (produto != null) {
            estoque.remove(produto);
            System.out.println("Produto removido do estoque: " + produto.getNome());
        } else {
            System.out.println("Produto não encontrado no estoque");
        }
    }

    public static void exibirListaProdutos() {
            List<Produto2> estoque = Produto2.getEstoque();
        
            // Remove duplicatas de IDs mantendo a ordem de inserção
            List<Produto2> estoqueSemDuplicatas = new ArrayList<>();
            Set<Integer> ids = new HashSet<>();
            for (Produto2 produto : estoque) {
                if (ids.add(produto.getId())) {
                    estoqueSemDuplicatas.add(produto);
                }
            }
        
            // Exibe o estoque sem duplicatas de IDs
            System.out.println("Estoque:");
            for (Produto2 produto : estoqueSemDuplicatas) {
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
