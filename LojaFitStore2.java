import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LojaFitStore2 {
    private static Pedido2 pedido = new Pedido2();
    public static void main(String[] args) {

        carregarListaPedidoCSV();
        carregarEstoqueCSV();

        Scanner scanner = new Scanner(System.in);
        int opcao;
        int opcaoEstoque;
        int opcaoPedido;
        do {
            System.out.println("----- Menu -----");
            System.out.println("1. Estoque");
            System.out.println("2. Lista de Pedidos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

            switch (opcao) {
                case 1:
                    do {
                        System.out.println("\n----- Estoque -----");
                        System.out.println("1. Adicionar Produto");
                        System.out.println("2. Remover Produto");
                        System.out.println("3. Exibir Lista de Produtos");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcaoEstoque = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

                        switch (opcaoEstoque) {
                            case 1:
                                adicionarProdutoEstoque(scanner);
                                break;
                            case 2:
                                removerProdutoEstoque(scanner);
                                break;
                            case 3:
                                Produto2.exibirListaProdutos();
                                break;
                        }
                    } while (opcaoEstoque != 0);
                    break;
                case 2:
                    do {
                        System.out.println("\n----- Lista de Pedidos -----");
                        System.out.println("1. Adicionar Produto ao Pedido");
                        System.out.println("2. Remover Item do Pedido");
                        System.out.println("3. Exibir Lista de Pedidos");
                        System.out.println("4. Finalizar Baixa do Estoque");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha uma opção: ");
                        opcaoPedido = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

                        switch (opcaoPedido) {
                            case 1:
                                adicionarProdutoPedido(scanner);
                                break;
                            case 2:
                                removerItemPedido(scanner);
                                break;
                            case 3:
                                pedido.exibirListaProdutos();
                                break;
                            case 4:
                                finalizarPedido();
                                break;
                        }
                    } while (opcaoPedido != 0);
                    break;
            }
        } while (opcao != 0);

        // Salvar dados do estoque em CSV
        salvarEstoqueCSV();

        // Salvar dados da lista de pedidos em CSV
        salvarPedidoCSV();

        scanner.close();

        System.out.println("Programa encerrado.");
    }

    private static void adicionarProdutoEstoque(Scanner scanner) {
        // Solicita e lê o nome do produto
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        
        final String nomeFinal = nome;
        
        // Verifica se o produto já está no estoque
        boolean produtoJaExiste = Produto2.getEstoque().stream()
        .anyMatch(produto -> produto.getNome().equalsIgnoreCase(nomeFinal));

        if (produtoJaExiste) {
        System.out.println("Este produto já está no estoque.");
        return; // Retorna para o menu principal sem adicionar o produto
}
        // Loop para garantir que o nome do produto não esteja em branco
        while (nome.isEmpty()) {
            System.out.println("Nome do produto não pode estar em branco.");
            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine();
        }
    
        double preco = 0;
        boolean precoValido = false;
        // Loop para garantir que o preço seja um número válido
        while (!precoValido) {
            try {
                System.out.print("Digite o preço do produto (use ',' como separador decimal): ");
                preco = scanner.nextDouble();
                precoValido = true;
            } catch (InputMismatchException e) {
                // Se o usuário digitar um valor inválido, exibe uma mensagem de erro e limpa o buffer do scanner
                System.out.println("Formato inválido para o preço. Por favor, use ',' como separador decimal.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    
        int quantidade = 0;
        boolean quantidadeValida = false;
        // Loop para garantir que a quantidade seja um número inteiro válido
        while (!quantidadeValida) {
            try {
                System.out.print("Digite a quantidade do produto: ");
                quantidade = scanner.nextInt();
                if (quantidade < 0) {
                    System.out.println("A quantidade não pode ser negativa. Por favor, digite um valor válido.");
                } else {
                    quantidadeValida = true;
                }
            } catch (InputMismatchException e) {
                // Se o usuário digitar um valor inválido, exibe uma mensagem de erro e limpa o buffer do scanner
                System.out.println("Quantidade inválida. Por favor, digite um número inteiro.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    
        // Adiciona o produto ao estoque
        Produto2.adicionarProduto(nome, preco, quantidade);
        salvarEstoqueCSV();
    }
    

    private static void removerProdutoEstoque(Scanner scanner) {
        System.out.print("Digite o ID do produto a ser removido: ");
        int id = scanner.nextInt();

        Produto2.removerProduto(id);
        salvarEstoqueCSV();
    }

    private static void adicionarProdutoPedido(Scanner scanner) {
        System.out.print("Digite o nome do produto a ser adicionado ao pedido: ");
        String produto = scanner.nextLine();

        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scanner.nextInt();

        pedido.adicionarProduto(produto,quantidade);
        salvarPedidoCSV();
    }

    private static void removerItemPedido(Scanner scanner) {
        System.out.print("Digite o nome do produto a ser removido da lista de pedido: ");
        String produto = scanner.nextLine();

        pedido.excluirProduto(produto);
        salvarPedidoCSV();
    }

    private static void salvarEstoqueCSV() {
        try (FileWriter writer = new FileWriter("estoque.csv")) {
            for (Produto2 produto : Produto2.getEstoque()) {
                writer.write(produto.getId() + "," + produto.getNome() + "," + produto.getPreco() + "," + produto.getQuantidade() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque em CSV: " + e.getMessage());
        }
    }

    private static void salvarPedidoCSV() {
        try (FileWriter writer = new FileWriter("pedido.csv")) {
            for (String produto : pedido.getProdutosSelecionados()) {
                int quantidade = pedido.getQuantidade(produto);
                writer.write(produto + "," + quantidade + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o pedido em CSV: " + e.getMessage());
        }
    }

    private static void finalizarPedido() {
    List<Produto2> estoqueAtualizado = new ArrayList<>(Produto2.getEstoque());
    pedido.atualizarEstoque(estoqueAtualizado);
    salvarEstoqueCSV();
}


    private static void carregarEstoqueCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("estoque.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String nome = parts[1];
                double preco = Double.parseDouble(parts[2]);
                int quantidade = Integer.parseInt(parts[3]);

                // Verifica se o produto já existe no estoque
                boolean produtoJaExiste = Produto2.getEstoque().stream()
                        .anyMatch(produto -> produto.getNome().equalsIgnoreCase(nome));

                if (!produtoJaExiste) {
                    Produto2 produto = new Produto2(nome, preco, quantidade);
                    Produto2.getEstoque().add(produto);
                }
            }
        removerDuplicatasEstoqueCSV();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estoque do CSV: " + e.getMessage());
        }
    }


    private static void carregarListaPedidoCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("pedido.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String nome = parts[0];
                int quantidade = Integer.parseInt(parts[1]);
                pedido.adicionarProduto(nome, quantidade); // Adiciona o produto diretamente ao pedido
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Erro ao carregar o carrinho do CSV: " + e.getMessage());
        }
        
    }
    
    private static void removerDuplicatasEstoqueCSV() {
    try (BufferedReader br = new BufferedReader(new FileReader("estoque.csv"))) {
        List<String> linhas = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            if (!ids.contains(id)) {
                linhas.add(line);
                ids.add(id);
            }
        }

        try (FileWriter writer = new FileWriter("estoque.csv")) {
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
        }

    } catch (FileNotFoundException e) {
        // Arquivo não encontrado, não há itens no estoque
    } catch (IOException e) {
        System.out.println("Erro ao carregar o estoque do CSV: " + e.getMessage());
    }

    
}


}
