package br.edu.mm.fatec.cestaprodutos.Views;

import br.edu.mm.fatec.cestaprodutos.Cesta.Cesta;

import br.edu.mm.fatec.cestaprodutos.Models.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CestaGUI {
    private JPanel CestaTela;
    private int ValorAtual;
    private JButton BtnAdicionar;
    private JButton BtnRemover;
    private JTextField textField1;
    private JButton BtnTerminar;
    private JComboBox ComboProdutos;
    private JTable TabelaProdutos;
    private JTextField TotalFinalPreco;
    private int NumeroAtual;
    private final String Mensagem = "Não há o que remover";
    private String MensagemFinal;

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
                    createTable(atual);
                    TotalFinalPreco.setText(String.format("Total: R$ %.2f", atual.chamarTotal()));
                }
                if(e.getSource()==BtnRemover){
                    if(Integer.parseInt(textField1.getText()) > 0) {
                        NumeroAtual = Integer.parseInt(textField1.getText()) - 1;
                        textField1.setText(Integer.toString(NumeroAtual));
                        atual.mudarQuantidade(ComboProdutos.getSelectedItem().toString(), 1);
                    }else  JOptionPane.showMessageDialog (null, Mensagem);
                    createTable(atual);
                    TotalFinalPreco.setText(String.format("Total: R$ %.2f", atual.chamarTotal()));
                }
                if(e.getSource()==BtnTerminar){
                    JFrame formularioGUI = new JFrame();
                    formularioGUI.setContentPane(new FormularioPagamentoGUI(atual.chamarTotal()).getPainelFormulario());
                    formularioGUI.setSize(400,400);
                    formularioGUI.setTitle("Finalizar - compra");
                    formularioGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    formularioGUI.setVisible(true);
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
        DefaultTableModel model = new DefaultTableModel(
                null,
                new String[]{"Codigo", "Descrição", "Preço", "Quantidade",});
            TabelaProdutos.setModel(model);
            TabelaProdutos.getTableHeader().setReorderingAllowed(false);
        for (Produto ProdutoAdcionavel : cestaAtual.mandarLista()) {
            if(ProdutoAdcionavel.getQuantidadeTotal() > 0) {
                String preco = String.format("%.2f",ProdutoAdcionavel.getPreco());
                Object[] rowAtual = {ProdutoAdcionavel.getCodigo(), ProdutoAdcionavel.getDescricao(),  preco, ProdutoAdcionavel.getQuantidadeTotal()};
                model.addRow(rowAtual);
            }
        }
    }

    public JPanel getCestaTela() {
        return CestaTela;
    }
}
