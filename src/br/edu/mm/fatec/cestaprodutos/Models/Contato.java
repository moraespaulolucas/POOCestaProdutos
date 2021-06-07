package br.edu.mm.fatec.cestaprodutos.Models;

public class Contato {
    private Integer codigo;
    private String nome;
    private String telefone;
    private String email;

    public Contato(Integer codigo, String nome,String telefone,String email) {
        setCodigo(codigo);
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\n" +
                "Contato: " + nome + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n";
    }
}
