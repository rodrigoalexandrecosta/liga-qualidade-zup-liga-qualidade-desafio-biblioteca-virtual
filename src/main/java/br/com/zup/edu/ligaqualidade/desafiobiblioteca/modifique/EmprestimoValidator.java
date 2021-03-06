package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
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

    public void validate() throws Exception {
        ValidateEmprestimoTypeForUser();
        ValidateDataDevolucao();
    }

    private void ValidateEmprestimoTypeForUser() throws Exception {
        if(emprestimo.tipoExemplar == TipoExemplar.RESTRITO && usuario.padrao == TipoUsuario.PADRAO) {
            throw new Exception("User PADRAO cannot access RESTRITO");
        }
    }

    private void ValidateDataDevolucao() throws Exception {
        if(emprestimo.tempo > 60) {
            throw new Exception("Time defined for emprestimo exceeds limit of 60");
        }
    }

}
