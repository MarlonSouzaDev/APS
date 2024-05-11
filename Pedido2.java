import java.util.ArrayList;
import java.util.List;

public class Pedido2 {
    private List<String> produtosSelecionados;
    private List<Integer> quantidades;

    public Pedido2() {
        this.produtosSelecionados = new ArrayList<>();
        this.quantidades = new ArrayList<>();
    }

    // Método para adicionar um produto ao pedido
    public void adicionarProduto(String produto, int quantidade) {
        int index = produtosSelecionados.indexOf(produto);
        if (index != -1) {
            quantidade += quantidades.get(index); // Adiciona a nova quantidade à quantidade existente
            quantidades.set(index, quantidade); // Atualiza a quantidade do produto no pedido
        } else {
            produtosSelecionados.add(produto);
            quantidades.add(quantidade);
        }
        System.out.println("Produto adicionado ao pedido: " + produto + " (Quantidade: " + quantidade + ")");
    }

    // Método para excluir um produto do pedido
    public void excluirProduto(String produto) {
        int index = produtosSelecionados.indexOf(produto);
        if (index != -1) {
            produtosSelecionados.remove(index);
            quantidades.remove(index);
            System.out.println("Produto removido do pedido: " + produto);
        } else {
            System.out.println("Produto não encontrado no pedido: " + produto);
        }
    }

    // Método para exibir a lista de produtos no pedido
    public void exibirListaProdutos() {
        System.out.println("Lista de produtos no pedido:");
        for (int i = 0; i < produtosSelecionados.size(); i++) {
            String produto = produtosSelecionados.get(i);
            int quantidade = quantidades.get(i);
            System.out.println("- " + produto + " (Quantidade: " + quantidade + ")");
        }
    }

    // Método para atualizar o estoque após finalizar o pedido
    public void atualizarEstoque(List<Produto2> estoque) {
        for (int i = 0; i < produtosSelecionados.size(); i++) {
            String produtoNome = produtosSelecionados.get(i);
            int quantidadePedido = quantidades.get(i);
            for (Produto2 produto : estoque) {
                if (produtoNome.equals(produto.getNome())) {
                    if (quantidadePedido <= produto.getQuantidade()) {
                        produto.setQuantidade(produto.getQuantidade() - quantidadePedido);
                    } else {
                        System.out.println("Quantidade insuficiente em estoque para o produto: " + produto.getNome());
                    }
                    break;
                }
            }
        }
    }

    public List<String> getProdutosSelecionados() {
        return produtosSelecionados;
    }

    public List<Integer> getQuantidades() {
        return quantidades;
    }

    public int getQuantidade(String produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuantidade'");
    }
}

