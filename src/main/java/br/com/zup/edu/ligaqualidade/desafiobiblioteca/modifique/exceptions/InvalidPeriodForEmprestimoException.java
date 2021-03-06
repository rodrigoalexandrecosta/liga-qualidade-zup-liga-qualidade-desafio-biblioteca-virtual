package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions;

public class InvalidPeriodForEmprestimoException extends EmprestimoValidationException {
    public InvalidPeriodForEmprestimoException(String errorMessage) {
        super(errorMessage);
    }
}
