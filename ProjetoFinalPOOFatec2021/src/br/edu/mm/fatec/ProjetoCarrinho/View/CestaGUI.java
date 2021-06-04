package br.edu.mm.fatec.ProjetoCarrinho.View;

import br.edu.mm.fatec.ProjetoCarrinho.Cesta.Cesta;
import br.edu.mm.fatec.ProjetoCarrinho.GerenciarProdutos;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class CestaGUI {
    private JPanel CestaTela;

    public CestaGUI(Cesta atual) {
        createTable(atual);

        for (Produto produtoAtual: atual.mandarLista()){
            ComboProdutos.addItem(produtoAtual.getDescricao());
        }


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==BtnAdicionar){
                    NumeroAtual =  Integer.parseInt(textField1.getText()) + 1;
                    textField1.setText(Integer.toString(NumeroAtual));
                    atual.mudarQuantidade(ComboProdutos.getSelectedItem().toString(), 0);
                }
                if(e.getSource()==BtnRemover){
                    if(Integer.parseInt(textField1.getText()) > 0) {
                        NumeroAtual = Integer.parseInt(textField1.getText()) - 1;
                        textField1.setText(Integer.toString(NumeroAtual));
                        atual.mudarQuantidade(ComboProdutos.getSelectedItem().toString(), 1);
                    }else  JOptionPane.showMessageDialog (null, Mensagem);
                }
                if(e.getSource()==BtnTerminar){

                }

            }
        };
        BtnAdicionar.addActionListener(listener);
        BtnRemover.addActionListener(listener);
        BtnTerminar.addActionListener(listener);


        ComboProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValorAtual = atual.acharQuantidade(ComboProdutos.getSelectedItem().toString());
                textField1.setText(Integer.toString(ValorAtual));
            }
        });


    }

    private void createTable(Cesta cestaAtual){

        TabelaProdutos.setModel(new DefaultTableModel(
                null,
                new String[]{"Codigo", "Descrição", "Preço", "Quantidade",}
        ));
    }


    public JPanel getCestaTela() {
        return CestaTela;
    }
    private int ValorAtual;
    private JButton BtnAdicionar;
    private JButton BtnRemover;
    private JTextField textField1;
    private JButton BtnTerminar;
    private JComboBox ComboProdutos;
    private JTable TabelaProdutos;
    private int NumeroAtual;
    private String Mensagem = "Não há o que remover";





}