package registro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bin.BancoDeDados.ConnectionSQL;
import dados.Avaliacao;
import dados.Livro;
import dados.Usuario;

public class RegistroAvaliacao {

    private List<Avaliacao> avaliacoes;

    public RegistroAvaliacao() {
        avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(int nota, Usuario usuario, Livro livro, ConnectionSQL conexao) {
        Avaliacao avaliacao = new Avaliacao(nota, usuario, livro);
        avaliacao.salvarNoBancoDeDados(conexao);
        avaliacoes.add(avaliacao);
        System.out.println("Avaliação adicionada com sucesso!");
    }

    public void editarAvaliacao(int novaNota, Usuario usuario, Livro livro, ConnectionSQL conexao) {
        avaliacoes.stream()
                .filter(avaliacao -> avaliacao.getLivro().equals(livro) && avaliacao.getUsuario().equals(usuario))
                .findFirst()
                .ifPresent(avaliacao -> {
                    avaliacao.atualizarNoBancoDeDados(conexao, novaNota);
                    System.out.println("Avaliação editada com sucesso!");
                });
    }

    public void excluirAvaliacao(Usuario usuario, Livro livro, ConnectionSQL conexao) {
        Iterator<Avaliacao> iterator = avaliacoes.iterator();
        while (iterator.hasNext()) {
            Avaliacao avaliacao = iterator.next();
            if (avaliacao.getLivro().equals(livro) && avaliacao.getUsuario().equals(usuario)) {
                avaliacao.excluirDoBancoDeDados(conexao);
                iterator.remove();
                System.out.println("Avaliação excluída com sucesso!");
                return;
            }
        }
        System.out.println("Avaliação não encontrada.");
    }

    public void exibirAvaliacoesDoLivro(Livro livro) {
        System.out.println("=== Avaliações do Livro ===");
        System.out.println("Nota\t Usuário");

        avaliacoes.stream()
                .filter(avaliacao -> avaliacao.getLivro().equals(livro))
                .forEach(avaliacao -> System.out.printf("%d\t %s\n", avaliacao.getNota(), avaliacao.getUsuario().getNome()));

        double media = calcularMediaAvaliacoesDoLivro(livro);
        System.out.printf("Média de Avaliações: %.2f\n", media);
    }

    public double calcularMediaAvaliacoesDoLivro(Livro livro) {
        return avaliacoes.stream()
                .filter(avaliacao -> avaliacao.getLivro().equals(livro))
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0);
    }
}
