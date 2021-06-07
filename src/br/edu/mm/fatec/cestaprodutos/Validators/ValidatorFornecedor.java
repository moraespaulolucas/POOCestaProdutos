package br.edu.mm.fatec.cestaprodutos.Validators;

import br.edu.mm.fatec.cestaprodutos.Validators.Messages.ValidatorMessage;

public class ValidatorFornecedor extends Validator {
    public static boolean validateStateField(String stateValue) {
        boolean isValidField = true;

        try {
            if(stateValue.length() != 2) {
                throw ValidatorException("O parâmetro estado deve ter apenas 2 caractéres");
            }
            if(!stateValue.equals(stateValue.toUpperCase())) {
                throw ValidatorException("O parâmetro estado deve ser composto apenas de letras maiúsculas");
            }
        }
        catch (ValidatorException error) {
            System.out.println(ValidatorMessage.makeValidatorMessage(error.getMessage(), "Estado"));
            isValidField = false;
        }
        catch (Exception error) {
            System.out.println(ValidatorMessage.makeValidatorMessage("Ocorreu uma exceção não tratada: " + error.getMessage()));
            isValidField = false;
        }
        return isValidField;
    }
}
