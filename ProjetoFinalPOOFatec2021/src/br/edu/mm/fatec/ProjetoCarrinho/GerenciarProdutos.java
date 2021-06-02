package br.edu.mm.fatec.ProjetoCarrinho;

import br.edu.mm.fatec.ProjetoCarrinho.Cesta.Cesta;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Contato;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Fornecedor;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Produto;
import br.edu.mm.fatec.ProjetoCarrinho.View.CestaGUI;

import javax.swing.*;


public class GerenciarProdutos {



    public static void main(String[] args) {




        Contato contato = new Contato("Antonio","19 99999-9999", "antoniomogifornece@yahoo.com.br");
        Cesta ListaNova = new Cesta();
        Fornecedor mogiMarcas = new Fornecedor(121,"Fornecedor Mogiano", "Avenida Bandeirantes, 234", contato);

        Produto ervilhaSeca = new Produto(19983,"Ervilha Seca 100g ",8.00,0, mogiMarcas);
        Produto sacoFeijao = new Produto(28872,"Feijão 1kg",20.00,0, mogiMarcas);
        Produto sacoArroz = new Produto(37761,"Arroz 1kg",16.00,0, mogiMarcas);

        ListaNova.adicionarItem(ervilhaSeca);
        ListaNova.adicionarItem(sacoFeijao);
        ListaNova.adicionarItem(sacoArroz);
        ListaNova.exibirLista();



        JFrame cesta = new JFrame();
        cesta.setContentPane(new CestaGUI(ListaNova).getCestaTela());
        cesta.setSize(500, 450);
        cesta.setTitle("Cesta - Adicionar Item");
        cesta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cesta.setVisible(true);

    }
}
