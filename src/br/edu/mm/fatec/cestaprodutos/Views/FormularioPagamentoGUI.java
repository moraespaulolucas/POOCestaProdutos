package br.edu.mm.fatec.cestaprodutos.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioPagamentoGUI {
    private JPanel PainelFormulario;
    private JRadioButton creditoRadioButton;
    private JRadioButton boletoRadioButton;
    private JRadioButton debitoRadioButton;
    private JTextField TotalDaCompra;
    private JButton finalizarPagamentoButton;
    private JButton cancelarButton;

    public FormularioPagamentoGUI(double total){
        TotalDaCompra.setText(String.format("Total da compra: R$ %.2f", total));

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==creditoRadioButton){
                    boletoRadioButton.setSelected(false);
                    debitoRadioButton.setSelected(false);
                }

                if(e.getSource()==boletoRadioButton){
                    creditoRadioButton.setSelected(false);
                    debitoRadioButton.setSelected(false);
                }

                if(e.getSource()==debitoRadioButton){
                    boletoRadioButton.setSelected(false);
                    creditoRadioButton.setSelected(false);
                }
            }
        };

        creditoRadioButton.addActionListener(listener);
        boletoRadioButton.addActionListener(listener);
        debitoRadioButton.addActionListener(listener);

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==cancelarButton){
                System.exit(0);
                }
                if(e.getSource()==finalizarPagamentoButton){
                    JOptionPane.showMessageDialog(null, "Abrirá uma aba no navegador para efetuar o pagamento", "Obrigado por usar o E-Cesta", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        };

        finalizarPagamentoButton.addActionListener(listener1);
        cancelarButton.addActionListener(listener1);
    }
    public JPanel getPainelFormulario() {
        return PainelFormulario;
    }
}
