package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions;

public class EmprestimoBusinessException extends Exception {
    public EmprestimoBusinessException(String errorMessage) {
        super(errorMessage);
    }
}
