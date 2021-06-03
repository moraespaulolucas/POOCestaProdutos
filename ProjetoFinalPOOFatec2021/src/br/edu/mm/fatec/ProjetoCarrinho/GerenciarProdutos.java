package br.edu.mm.fatec.ProjetoCarrinho;

import br.edu.mm.fatec.ProjetoCarrinho.Cesta.Cesta;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Contato;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Fornecedor;
import br.edu.mm.fatec.ProjetoCarrinho.Models.Produto;
import br.edu.mm.fatec.ProjetoCarrinho.Utils.Util;
import br.edu.mm.fatec.ProjetoCarrinho.View.CestaGUI;

import java.security.Provider;
import java.util.*;

import javax.swing.*;


public class GerenciarProdutos {

    static Map<Integer, Contato> mapIdContact = new HashMap<Integer, Contato>();
    static Map<Integer, Fornecedor> mapIdProvider = new HashMap<Integer, Fornecedor>();
    static Map<Integer, Produto> mapIdProduct = new HashMap<Integer, Produto>();

    public static void main(String[] args) {
        Boolean isProgramRunning = true;
        Cesta cesta = new Cesta();

        /* Contato contato = new Contato("Antonio","19 99999-9999", "antoniomogifornece@yahoo.com.br");
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
        cesta.setVisible(true); */

        showWelcomeMessage();
        while(isProgramRunning) {
            showMenu();
            isProgramRunning = flowManager(cesta);
        }
    }

    private static void showMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Digite 1 - para cadastrar um contato").append('\n');
        sb.append("Digite 2 - para cadastrar um fornecedor").append('\n');
        sb.append("Digite 3 - para cadastrar um produto").append('\n');
        sb.append("Digite 4 - para acessar a cesta de compras").append('\n');
        sb.append("Digite 5 - para listar os fornecedores").append('\n');
        sb.append("Digite 6 - para listar os contatos").append('\n');
        sb.append("Digite 7 - para listar os produtos").append('\n');
        sb.append("Digite 8 - para acessar encerrar o programa").append('\n');

        System.out.println(sb.toString());
    }

    private static void showWelcomeMessage() {
        System.out.println("Bem-vindo(a) à aplicação E-Cesta");
    }

    private static void contactFlowManager() {
        String nome = Util.scanString("Digite o nome do contato");
        String telefone = Util.scanString("Digite o telefone do contato");
        String email = Util.scanString("Digite o email do contato");

        Contato contact = new Contato(mapIdContact.values().size() + 1, nome, telefone, email);
        mapIdContact.put(contact.getCodigo(), contact);
        System.out.println("Contato foi criado!");
    }

    private static void providerFlowManager() {
        try {
            String razaoSocial = Util.scanString("Digte a razão social do fornecedor");
            String cidade = Util.scanString("Digite a cidade do fornecedor");
            String endereco = Util.scanString("Digite o endereço do fornecedor");
            String estado = Util.scanString("Digite o estado do fornecedor");
            Integer codigoContato = Util.scanInteger("Digite o código do contato para adicionar ao fornecedor");

            try {
                Contato contato = findContact(codigoContato);
                Fornecedor provider = new Fornecedor(mapIdProvider.values().size() + 1, razaoSocial, cidade, endereco, estado, contato);
                mapIdProvider.put(provider.getCodigo(), provider);
                System.out.println("Fornecedor foi criado!");
            }
            catch(NotFoundException error) {
                System.out.println(error.getMessage());
            }
        }
        catch (InputMismatchException error) {
            System.out.println("Erro: tipo de campo inválido digitado!");;
        }

    }

    private static void productFlowManager() {

        try {
            String descricao = Util.scanString("Digite a descrição do produto");
            Double preco = Util.scanDouble("Digite o preço do produto");
            Integer quantidade = Util.scanInteger("Digite a quantidade do produto");
            Integer codigoFornecedor = Util.scanInteger("Digite o código do fornecedor para adicionar ao produto");
            try {
                Fornecedor fornecedor = findProvider(codigoFornecedor);
                Produto product = new Produto(mapIdProduct.values().size() + 1, descricao, preco, quantidade, fornecedor);
                mapIdProduct.put(product.getCodigo(), product);
                System.out.println("Produto foi criado!");
            }
            catch (NotFoundException error) {
                System.out.println(error.getMessage());
            }
        }
        catch(InputMismatchException error) {
            System.out.println("Erro: tipo de campo inválido digitado!");;
        }
    }

    private static Contato findContact(Integer codigoContato) throws NotFoundException {
        if(mapIdContact.get(codigoContato) != null) {
            return mapIdContact.get(codigoContato);
        }
        else {
            throw new NotFoundException("Contato não foi encontrado!");
        }
    }

    private static Fornecedor findProvider(Integer codigoFornecedor) throws NotFoundException {
        if(mapIdProvider.get(codigoFornecedor) != null) {
            return mapIdProvider.get(codigoFornecedor);
        }
        else {
            throw new NotFoundException("Fornecedor não foi encontrado!");
        }
    }

    private static void shoppingBasketFlowManager(Cesta listaCesta) {
        JFrame cesta = new JFrame();
        cesta.setContentPane(new CestaGUI(listaCesta).getCestaTela());
        cesta.setSize(500, 450);
        cesta.setTitle("Cesta - Adicionar Item");
        cesta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cesta.setVisible(true);
    }

    private static void showProviders() {
        for(Fornecedor fornecedor: mapIdProvider.values()) {
            System.out.println(fornecedor.toString());
        }
    }

    private static void showContacts() {
        for(Contato contato: mapIdContact.values()) {
            System.out.println(contato.toString());
        }
    }

    private static void showProducts() {
        for(Produto produto: mapIdProduct.values()) {
            System.out.println(produto.toString());
        }
    }

    private static Boolean flowManager(Cesta cesta) {

        switch (requestOption()) {
            case 1:
                contactFlowManager();
                break;
            case 2:
                providerFlowManager();
                break;
            case 3:
                productFlowManager();
                break;
            case 4:
                shoppingBasketFlowManager(cesta);
                break;
            case 5:
                showProviders();
                break;
            case 6:
                showContacts();
                break;
            case 7:
                showProducts();
                break;
            case 8:
                System.out.println("Obrigado por utilizar a E-Cesta");
                return false;
            default:
                System.out.println("Opção inválida. Digite novamente");
                break;
        }

        return true;
    }

    private static Integer requestOption() {
        try {
            return Util.scanInteger("Digite a opção");
        }
        catch(Exception error) {
            return -1;
        }
    }

    public static class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
