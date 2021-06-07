package br.edu.mm.fatec.cestaprodutos.Models;

public class Produto {
    private int codigo;
    private String descricao;
    private double preco;
    private int quantidade;
    private Fornecedor fornecedor;
    private int quantidadeTotal;

    public Produto (int codigo, String descricao, double preco, int quantidade, Fornecedor fornecedor) {
        setCodigo(codigo);
        setDescricao(descricao);
        setPreco(preco);
        setQuantidade(quantidade);
        setFornecedor(fornecedor);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() { return fornecedor; }

    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor;}

    public int getQuantidadeTotal() { return quantidadeTotal; }

    public void setQuantidadeTotal(int quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    @Override
    public String toString() {
        return "Produto: " + "\n" +
                "Codigo: " + codigo + "\n" +
                "Descrição: " + descricao + "\n" +
                "Preço: " +"R$" + String.format("%.2f",preco) + "\n" +
                "Quantidade: " + quantidade + "\n" + fornecedor.toString();

    }
}
