    import java.util.ArrayList;
    import java.util.List;

    public class Pedido {
        private List<String> produtosSelecionados;
        private List<Integer> quantidades;

        public Pedido() {
            this.produtosSelecionados = new ArrayList<>();
            this.quantidades = new ArrayList<>();
        }

        // Método para adicionar um produto ao pedido
        public void adicionarAoPedido(String produto, int quantidade) {
            // Verifica se o produto existe no estoque e obtém a quantidade disponível
            Produto produtoNoEstoque = Produto.getEstoque().stream()
                .filter(p -> p.getNome().equalsIgnoreCase(produto)) // ignora maiúscula e minúscula
                .findFirst() // retorna o primeiro encontrado
                .orElse(null); //caso não encontre, retorna nulo

            if (produtoNoEstoque == null) {
                System.out.println("O produto " + produto + " não existe no estoque.");
                return;
            }

            // Verifica se a quantidade desejada está disponível no estoque
            if (produtoNoEstoque.getQuantidade() < quantidade) {
                System.out.println("Quantidade insuficiente no estoque para o produto: " + produto + ". Este produto possui " + produtoNoEstoque.getQuantidade() + "unidade(s).");
                return;
            }

            // Verifica se o produto selecionado já existe na lista de pedido
            int index = produtosSelecionados.indexOf(produto);
            if (index != -1) {
                int novaQuantidade = quantidades.get(index) + quantidade; // Adiciona a nova quantidade à quantidade existente
                if (produtoNoEstoque.getQuantidade() < novaQuantidade) {
                    System.out.println("Quantidade insuficiente no estoque para o produto: " + produto + ". Este produto possui " + produtoNoEstoque.getQuantidade() + "unidade(s).");
                    return; // Sai do método se a nova quantidade exceder a quantidade disponível
                }
                quantidades.set(index, novaQuantidade); // Atualiza a quantidade do produto no pedido
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
        public void atualizarEstoque(List<Produto> estoque) {
            for (int i = 0; i < produtosSelecionados.size(); i++) {
                String produtoNome = produtosSelecionados.get(i);
                int quantidadePedido = quantidades.get(i);
                for (Produto produto : estoque) {
                    if (produtoNome.equals(produto.getNome())) {
                        if (quantidadePedido <= produto.getQuantidade()) {
                            produto.setQuantidade(produto.getQuantidade() - quantidadePedido);
                            produtosSelecionados.clear();
                        } else {
                            System.out.println("Quantidade insuficiente em estoque para o produto: " + produto.getNome());
                        }
                        break;
                    }
                }
            }
        }

        // getters
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