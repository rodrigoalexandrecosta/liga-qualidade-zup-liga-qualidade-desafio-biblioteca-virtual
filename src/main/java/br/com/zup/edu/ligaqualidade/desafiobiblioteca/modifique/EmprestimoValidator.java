package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.EmprestimoBusinessException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.EmprestimoValidationException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.InvalidBookTypeForUserException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.InvalidPeriodForEmprestimoException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;

import java.util.Set;

public class EmprestimoValidator {
    DadosEmprestimo emprestimo;
    DadosUsuario usuario;
    Set<DadosExemplar> exemplares;

    public EmprestimoValidator(DadosEmprestimo dadosEmprestimo, DadosUsuario dadosUsuario, Set<DadosExemplar> exemplares) {
        this.emprestimo = dadosEmprestimo;
        this.usuario = dadosUsuario;
        this.exemplares = exemplares;
    }

    public void validate() throws EmprestimoValidationException, EmprestimoBusinessException {
        validateEmprestimoTypeForUser();
        validateExemplarDisponivel();
        validateDataDevolucao();
    }

    private void validateEmprestimoTypeForUser() throws InvalidBookTypeForUserException {
        if (TipoExemplar.RESTRITO.equals(emprestimo.tipoExemplar) && TipoUsuario.PADRAO.equals(usuario.padrao)) {
            throw new InvalidBookTypeForUserException("Usuário do tipo PADRÃO não pode emprestar livros do tipo RESTRITO");
        }
    }

    private void validateExemplarDisponivel() throws EmprestimoBusinessException {
        if (exemplares.stream().noneMatch(exemplar -> exemplar.idLivro == emprestimo.idLivro)) {
            throw new EmprestimoBusinessException("Livro não disponível para empréstimo");
        }
    }

    private void validateDataDevolucao() throws InvalidPeriodForEmprestimoException {
        if (emprestimo.tempo > 60) {
            throw new InvalidPeriodForEmprestimoException("Tempo definido para empréstimo excede 60 dias");
        }
    }

}
