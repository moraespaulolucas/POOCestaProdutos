package br.edu.mm.fatec.cestaprodutos;

import br.edu.mm.fatec.cestaprodutos.Cesta.Cesta;
import br.edu.mm.fatec.cestaprodutos.Models.Contato;
import br.edu.mm.fatec.cestaprodutos.Models.Fornecedor;
import br.edu.mm.fatec.cestaprodutos.Models.Produto;
import br.edu.mm.fatec.cestaprodutos.Utils.Util;
import br.edu.mm.fatec.cestaprodutos.Validators.Validator;
import br.edu.mm.fatec.cestaprodutos.Validators.ValidatorFornecedor;
import br.edu.mm.fatec.cestaprodutos.Validators.ValidatorProduto;
import br.edu.mm.fatec.cestaprodutos.Views.CestaGUI;

import java.util.*;

import javax.swing.*;

public class GerenciarProdutos {
    static Map<Integer, Contato> mapIdContact = new HashMap<>();
    static Map<Integer, Fornecedor> mapIdProvider = new HashMap<>();
    static Map<Integer, Produto> mapIdProduct = new HashMap<>();

    public static void main(String[] args) {
        boolean isProgramRunning = true;
        Cesta cesta = new Cesta();

        showWelcomeMessage();
        while(isProgramRunning) {
            showMenu();
            isProgramRunning = flowManager(cesta);
        }
    }

    private static void showWelcomeMessage() {
        System.out.println("Bem-vindo(a) à aplicação E-Cesta");
    }

    private static void showMenu() {
        String sb = "Digite 1 - para cadastrar um contato" + '\n' +
                    "Digite 2 - para cadastrar um fornecedor" + '\n' +
                    "Digite 3 - para cadastrar um produto" + '\n' +
                    "Digite 4 - para acessar a cesta de compras" + '\n' +
                    "Digite 5 - para listar os fornecedores" + '\n' +
                    "Digite 6 - para listar os contatos" + '\n' +
                    "Digite 7 - para listar os produtos" + '\n' +
                    "Digite 8 - para acessar encerrar o programa" + '\n';
        System.out.println(sb);
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
                productFlowManager(cesta);
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
            if(!ValidatorFornecedor.validateStateField(estado)){
                do {
                    estado = Util.scanString();
                }while(!ValidatorFornecedor.validateStateField(estado));
                }

            Integer codigoContato = Util.scanInteger("Digite o código do contato para adicionar ao fornecedor");
            if(!Validator.validateIntegerNotNegative(codigoContato, "codigo")){
                do {
                    codigoContato = Util.scanInteger();
                }while(!Validator.validateIntegerNotNegative(codigoContato, "codigo"));
            }
            try {
                Contato contato = findContact(codigoContato);
                Fornecedor provider = new Fornecedor(mapIdProvider.values().size() + 1, razaoSocial, cidade, endereco, estado, contato);
                mapIdProvider.put(provider.getCodigo(), provider);
                System.out.println("Fornecedor foi criado!");
            }
            catch(NotFoundException error) {
                System.out.println("Não foi possível criar o fornecedor");
                System.out.println(error.getMessage());
            }
        }
        catch (InputMismatchException error) {
            System.out.println("Erro: tipo de campo inválido digitado!");
        }
    }

    private static void productFlowManager(Cesta cesta) {
        try {
            String descricao = Util.scanString("Digite a descrição do produto");
            Double preco = Util.scanDouble("Digite o preço do produto");
            if(!ValidatorProduto.validateIntegerNotNegativeDouble(preco, "preço")){
                do {
                    preco = Util.scanDouble();
                }while(!Validator.validateIntegerNotNegativeDouble(preco, "preço"));
            }
            Integer quantidade = Util.scanInteger("Digite a quantidade do produto");
            if(!ValidatorProduto.validateIntegerNotNegative(quantidade, "quantidade")){
                do {
                    quantidade = Util.scanInteger();
                }while(!ValidatorProduto.validateIntegerNotNegative(quantidade, "quantidade"));
            }
            Integer codigoFornecedor = Util.scanInteger("Digite o código do fornecedor para adicionar ao produto");
            if(!ValidatorProduto.validateIntegerNotNegative(codigoFornecedor, "codigo")){
                do {
                    codigoFornecedor = Util.scanInteger();
                }while(!ValidatorProduto.validateIntegerNotNegative(codigoFornecedor, "codigo"));
            }
            try {
                Fornecedor fornecedor = findProvider(codigoFornecedor);
                Produto product = new Produto(mapIdProduct.values().size() + 1, descricao, preco, quantidade, fornecedor);
                mapIdProduct.put(product.getCodigo(), product);
                System.out.println("Produto foi criado!");
                product.setQuantidadeTotal(0);
                cesta.adicionarItem(product);
            }
            catch (NotFoundException error) {
                System.out.println("Não foi possível criar o produto!");
                System.out.println(error.getMessage());
            }
        }
        catch(InputMismatchException error) {
            System.out.println("Erro: tipo de campo inválido digitado!");
        }
    }

    private static void shoppingBasketFlowManager(Cesta listaCesta) {
        JFrame cestaGUIatual = new JFrame();

        cestaGUIatual.setContentPane(new CestaGUI(listaCesta).getCestaTela());
        cestaGUIatual.setSize(1280, 720);
        cestaGUIatual.setTitle("Cesta - Adicionar Item");
        cestaGUIatual.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cestaGUIatual.setVisible(true);
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

    public static class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
