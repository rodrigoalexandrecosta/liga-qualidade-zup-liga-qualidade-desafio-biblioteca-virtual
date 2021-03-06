package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions;

public class ExemplarNotFoundException extends EmprestimoBusinessException {
    public ExemplarNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
