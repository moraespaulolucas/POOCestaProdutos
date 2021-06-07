package br.edu.mm.fatec.cestaprodutos.Models;

public class Fornecedor {
    private int codigo;
    private String razaoSocial;
    private String endereco;
    private String cidade;
    private String estado;
    private Contato contato;

    public Fornecedor(int codigo, String razaoSocial, String endereco, String cidade, String estado, Contato contato) {
        setCodigo(codigo);
        setRazaoSocial(razaoSocial);
        setEndereco(endereco);
        setCidade(cidade);
        setEstado(estado);
        setContato(contato);
    }

    public Fornecedor(int codigo, String razaoSocial, String endereco, Contato contato) {
        setCodigo(codigo);
        setRazaoSocial(razaoSocial);
        setEndereco(endereco);
        setCidade("Mogi Mirim");
        setEstado("SP");
        setContato(contato);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setContato(Contato contato) { this.contato = contato; }

    @Override
    public String toString() {
        return "----------------------------" + "\n" +
                    "Fornecedor: " + "\n" +
                    "Codigo: " + codigo + "\n" +
                    "Razão Social: " + razaoSocial + "\n" +
                    "Endereço: " + endereco + "\n" +
                    "Cidade: " + cidade + "\n" +
                    "Estado: " + estado + "\n" +
                    "Contato: ---------------------- \n" + contato.toString() +
                    "---------------------- \n" +
                    "============================" + "\n \n";
    }
}
