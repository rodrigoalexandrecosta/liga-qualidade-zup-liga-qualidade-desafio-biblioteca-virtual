package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions;

public class UserNotFoundException extends EmprestimoBusinessException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
