import java.util.ArrayList;
import java.util.List;

public class Produto {
    private int id;
    private static int proximoId = 1; // Variável estática para gerar o próximo ID automaticamente
    private String nome;
    private double preco;
    private int quantidade;
    private static List<Produto> estoque = new ArrayList<>();

    // Constructor do Produto
    public Produto(String nome, double preco, int quantidade) {
        this.id = proximoId++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        estoque.add(this);
    }

    // Método  para adicionar um novo produto ao estoque
    public static void adicionarProduto(String nome, double preco, int quantidade){
        new Produto(nome, preco, quantidade);
        System.out.println("Produto adicionado ao estoque: " + nome + " Quantidade: " + quantidade);
    }

    // Método para remover um produto do estoque, verificando se o mesmo existe no estoque
    public static void removerProduto(int id) {
        Produto produtoRemover = null;
        for (Produto produto : estoque) {
            if (produto.getId() == id) {
                produtoRemover = produto;
                break;
            }
        }

        // Se a variável for diferente de nula, o item é removido
        if (produtoRemover != null) {
            estoque.remove(produtoRemover);
            estoque.remove(produtoRemover);
            System.out.println("Produto removido do estoque: " + produtoRemover.getNome());
        } else {
            System.out.println("Produto não encontrado no estoque");
        }
    }

    // Método para exibir a lista de estoque, verificando se há duplicatas na lista e removendo-as
    public static void exibirListaProdutos() {
        List<Produto> estoque = Produto.getEstoque();
    
        // Remove duplicatas de IDs mantendo a ordem de inserção
        List<Produto> estoqueSemDuplicatas = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        for (Produto produto : estoque) {
            if (!ids.contains(produto.getId())) {
                ids.add(produto.getId());
                estoqueSemDuplicatas.add(produto);
            }
        }
    
        // Exibe o estoque sem duplicatas de IDs
        System.out.println("Estoque:");
        for (Produto produto : estoqueSemDuplicatas) {
            System.out.println("- ID: " + produto.getId() + ", Nome: " + produto.getNome() +
                ", Preço: " + produto.getPreco() + ", Quantidade: " + produto.getQuantidade());
        }
    }
    
    // getters & setters

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

    public static List<Produto> getEstoque() {
        return estoque;
    }
}