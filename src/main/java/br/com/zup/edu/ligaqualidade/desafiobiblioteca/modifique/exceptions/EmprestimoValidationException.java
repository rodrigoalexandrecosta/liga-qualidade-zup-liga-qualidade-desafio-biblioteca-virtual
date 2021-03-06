package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions;

public class EmprestimoValidationException extends Exception {
    public EmprestimoValidationException(String errorMessage) {
        super(errorMessage);
    }
}
