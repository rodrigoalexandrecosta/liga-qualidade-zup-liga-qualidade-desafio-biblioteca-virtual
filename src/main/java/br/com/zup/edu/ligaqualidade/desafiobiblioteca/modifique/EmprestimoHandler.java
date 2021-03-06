package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EmprestimoHandler {
    Set<DadosEmprestimo> emprestimos;
    Set<DadosUsuario> usuarios;
    Set<DadosExemplar> exemplares;

    public EmprestimoHandler(Set<DadosUsuario> usuarios, Set<DadosEmprestimo> emprestimos, Set<DadosExemplar> exemplares) {
        this.usuarios = usuarios;
        this.emprestimos = emprestimos;
        this.exemplares = exemplares;
    }

    public Set<EmprestimoConcedido> concedeEmprestimos(){
        Set<EmprestimoConcedido> emprestimosConcedidos = new HashSet<EmprestimoConcedido>();

        emprestimos.forEach((DadosEmprestimo emprestimo) -> {
            try {
                DadosUsuario usuario = findUser(usuarios, emprestimo.idUsuario);
                DadosExemplar exemplar = findExemplar(exemplares, emprestimo.idLivro, emprestimo.tipoExemplar);

                EmprestimoValidator emprestimoValidator = new EmprestimoValidator(emprestimo, usuario);
                emprestimoValidator.validate();

                LocalDate hoje = LocalDate.now();
                LocalDate dataPrevistaDevolucao = hoje.plusDays(emprestimo.tempo);

                emprestimosConcedidos.add(new EmprestimoConcedido(usuario.idUsuario, exemplar.idExemplar, dataPrevistaDevolucao));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        return emprestimosConcedidos;
    }

    private DadosUsuario findUser(Set<DadosUsuario> usuarios, Integer idUsuario) throws Exception {
        for(DadosUsuario usuario: usuarios) {
            if(usuario.idUsuario == idUsuario) {
                return usuario;
            }
        }
        throw new Exception("User not found");
    }

    private DadosExemplar findExemplar(Set<DadosExemplar> exemplares, Integer idLivro, TipoExemplar tipoExemplar) throws Exception {
        for(DadosExemplar exemplar: exemplares) {
            if(exemplar.idLivro == idLivro && exemplar.tipo == tipoExemplar) {
                return exemplar;
            }
        }
        throw new Exception("Exemplar not found");
    }
}
