import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private Map<Produto, Integer> itens;
    private Estoque estoque;

    public Pedido(Estoque estoque) {
        this.itens = new HashMap<>();
        this.estoque = estoque;
    }

    // Adiciona um produto a lista de produtos
    public void adicionarItem(Produto produto, int quantidade) {
        // Verifica se o produto existe no estoque e se há quantidade suficiente
        if (estoque.verificarProduto(produto) && estoque.verificarQuantidade(produto, quantidade)) {
            itens.put(produto, quantidade);
        } else {
            System.out.println("Produto não disponível em quantidade suficiente.");
        }
    }

    // Remove um produto da lista de produtos
    public void removerItem(Produto produto) {
        itens.remove(produto);
    }

    // Modifica a quantidade de um produto na lista
    public void modificarQuantidade(Produto produto, int novaQuantidade) {
        // Verifica se o produto está na lista
        if (itens.containsKey(produto)) {
            // Verifica se há quantidade suficiente no estoque
            if (estoque.verificarQuantidade(produto, novaQuantidade)) {
                itens.put(produto, novaQuantidade);
            } else {
                System.out.println("Quantidade desejada não disponível em estoque.");
            }
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }
    // Mostra os itens presentes no carrinho
    public void mostrarItens() {
        System.out.println("Itens no carrinho:");
        for (Map.Entry<Produto, Integer> item : itens.entrySet()) {
            Produto produto = item.getKey();
            int quantidade = item.getValue();
            System.out.println(produto.getNome() + " - Quantidade: " + quantidade);
        }
    }
    // Finaliza a compra, subtraindo a quantidade dos itens do estoque
    public void finalizarCompra() {
        for (Map.Entry<Produto, Integer> item : itens.entrySet()) {
            Produto produto = item.getKey();
            int quantidade = item.getValue();
            estoque.modificarProduto(produto, null, quantidade, quantidade);
        }
        itens.clear(); // Limpa o carrinho após finalizar a compra
    }
}
