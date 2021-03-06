package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions;

public class InvalidBookTypeForUserException extends EmprestimoValidationException {
    public InvalidBookTypeForUserException(String errorMessage) {
        super(errorMessage);
    }
}
