package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.EmprestimoValidationException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.InvalidBookTypeForUserException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.InvalidPeriodForEmprestimoException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;

public class EmprestimoValidator {
    DadosEmprestimo emprestimo;
    DadosUsuario usuario;

    public EmprestimoValidator(DadosEmprestimo dadosEmprestimo, DadosUsuario dadosUsuario){
        this.emprestimo = dadosEmprestimo;
        this.usuario = dadosUsuario;
    }

    public void validate() throws EmprestimoValidationException {
        ValidateEmprestimoTypeForUser();
        ValidateDataDevolucao();
    }

    private void ValidateEmprestimoTypeForUser() throws InvalidBookTypeForUserException {
        if(TipoExemplar.RESTRITO.equals(emprestimo.tipoExemplar) && TipoUsuario.PADRAO.equals(usuario.padrao)) {
            throw new InvalidBookTypeForUserException("User PADRAO cannot access RESTRITO");
        }
    }

    private void ValidateDataDevolucao() throws InvalidPeriodForEmprestimoException {
        if(emprestimo.tempo > 60) {
            throw new InvalidPeriodForEmprestimoException("Time defined for emprestimo exceeds limit of 60");
        }
    }

}
