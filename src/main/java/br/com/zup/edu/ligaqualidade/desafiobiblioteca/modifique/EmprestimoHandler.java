package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.EmprestimoBusinessException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.EmprestimoValidationException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.ExemplarNotFoundException;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.exceptions.UserNotFoundException;
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

                LocalDate dataPrevistaDevolucao = calculateDataPrevistaDevolucao(emprestimo);

                emprestimosConcedidos.add(new EmprestimoConcedido(usuario.idUsuario, exemplar.idExemplar, dataPrevistaDevolucao));
            } catch (EmprestimoValidationException e) {
                System.out.println(e.getMessage());
            } catch (EmprestimoBusinessException e) {
                System.out.println(e.getMessage());
            }
        });

        return emprestimosConcedidos;
    }

    private DadosUsuario findUser(Set<DadosUsuario> usuarios, Integer idUsuario) throws UserNotFoundException {
        for(DadosUsuario usuario: usuarios) {
            if(idUsuario.equals(usuario.idUsuario)) {
                return usuario;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    private DadosExemplar findExemplar(Set<DadosExemplar> exemplares, Integer idLivro, TipoExemplar tipoExemplar) throws ExemplarNotFoundException {
        for(DadosExemplar exemplar: exemplares) {
            if(idLivro.equals(exemplar.idLivro) && tipoExemplar.equals(exemplar.tipo)) {
                return exemplar;
            }
        }
        throw new ExemplarNotFoundException("Exemplar not found");
    }

    private LocalDate calculateDataPrevistaDevolucao(DadosEmprestimo emprestimo) {
        LocalDate hoje = LocalDate.now();
        return hoje.plusDays(emprestimo.tempo);
    }
}
