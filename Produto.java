import java.io.*;
import java.util.*;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void adicionarProduto(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public static void main(String[] args) {
        List<Produto> estoque = new ArrayList<>();
        estoque.add(new Produto("Whey", 10, 120.0));
        estoque.add(new Produto("Creatina", 15, 100.0));

        escreverEstoqueCSV(estoque);
        exibirEstoqueCSV("estoque.csv");
    }

    public static void escreverEstoqueCSV(List<Produto> estoque) {
        try (PrintWriter writer = new PrintWriter(new File("estoque.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nome,Quantidade,Preco\n");
            for (Produto produto : estoque) {
                sb.append(produto.getNome());
                sb.append(",");
                sb.append(produto.getQuantidade());
                sb.append(",");
                sb.append(produto.getPreco());
                sb.append("\n");
            }
            writer.write(sb.toString());
            System.out.println("Estoque gravado com sucesso em 'estoque.csv'");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void exibirEstoqueCSV(String arquivoCSV) {
        try (Scanner scanner = new Scanner(new File(arquivoCSV))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
                System.out.println("Nome: " + attributes[0] +
                        ", Quantidade: " + attributes[1] +
                        ", Preco: " + attributes[2]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}
