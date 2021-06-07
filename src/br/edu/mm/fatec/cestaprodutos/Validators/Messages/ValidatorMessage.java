package br.edu.mm.fatec.cestaprodutos.Validators.Messages;

public class ValidatorMessage {
    public static String makeValidatorMessage(String message, String field) {
        StringBuilder st = new StringBuilder();
        st.append("O parâmetro ")
                .append(field)
                .append(" ")
                .append("não pode ser preenchido.")
                .append("Erro: ")
                .append(message);

        return  st.toString();
    }

    public static String makeValidatorMessage(String message) {
        StringBuilder st = new StringBuilder();
        st.append("O seguinte erro ocorreu:  ").append(message);
        return  st.toString();
    }
}
