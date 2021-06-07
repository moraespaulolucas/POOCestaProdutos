package br.edu.mm.fatec.cestaprodutos.Cesta;

import br.edu.mm.fatec.cestaprodutos.Models.Produto;

import java.util.ArrayList;
import java.util.List;

public class Cesta {
    List<Produto> itens = new ArrayList<>();
    double SomatorioTotal = 0;

    public void adicionarItem(Produto p) {
        itens.add(p);
    }

    private double calcularTotal(){
        for (Produto ProdutoAdcionavel : itens) {
            SomatorioTotal = SomatorioTotal + (ProdutoAdcionavel.getPreco() * ProdutoAdcionavel.getQuantidadeTotal());
        }
        return SomatorioTotal;
    }

    public double chamarTotal(){
        SomatorioTotal = 0;
        return calcularTotal();
    }

    public List<Produto> mandarLista() {
        return itens;
    }

    public int acharQuantidade(String procura) {
        for (Produto ProdutoAdcionavel : itens) {
            if (procura.equals(ProdutoAdcionavel.getDescricao())) {
                return ProdutoAdcionavel.getQuantidadeTotal();
            }
        }
        return 0;
    }

    //metodo 0 = mais || metodo 1 = menos
    public void mudarQuantidade(String procura, int metodo) {
        for (Produto ProdutoAdcionavel : itens) {
            if (procura.equals(ProdutoAdcionavel.getDescricao())) {
                int ValorAtual = ProdutoAdcionavel.getQuantidadeTotal();
                if (metodo == 0) ProdutoAdcionavel.setQuantidadeTotal(ValorAtual + 1);
                if (metodo == 1) ProdutoAdcionavel.setQuantidadeTotal(ValorAtual - 1);
            }
        }
    }
}
