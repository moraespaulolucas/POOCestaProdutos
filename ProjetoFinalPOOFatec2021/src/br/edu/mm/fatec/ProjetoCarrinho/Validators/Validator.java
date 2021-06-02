package br.edu.mm.fatec.ProjetoCarrinho.Validators;

import br.edu.mm.fatec.ProjetoCarrinho.Validators.Messages.ValidatorMessage;

public class Validator {

    public static ValidatorException ValidatorException(String message) {
        return new ValidatorException(message);
    }

    public static boolean validateIntegerNotNegative(Object value, String fieldName) {

        boolean isValidField = true;

        try {
            if((Double) value < 0) {
                throw new ValidatorException("Valor não pode ser negativo");
            }
        }
        catch (ValidatorException error) {
            System.out.println(ValidatorMessage.makeValidatorMessage(error.getMessage(), fieldName));
            isValidField = false;
        }
        catch (ClassCastException error) {
            System.out.println(ValidatorMessage.makeValidatorMessage("O parâmetro específicado não é um valor válido para o campo", fieldName));
            isValidField = false;
        }
        catch (Exception error) {
            System.out.println(ValidatorMessage.makeValidatorMessage("Ocorreu uma exceção não tratada: " + error.getMessage()));
            isValidField = false;
        }

        return isValidField;
    }

    static class ValidatorException extends Exception {
        ValidatorException() {}

        ValidatorException(String message) {
            super(message);
        }
    }
}
