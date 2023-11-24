package registro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dados.Avaliacao;
import dados.Livro;
import dados.Usuario;
import jdbc.ConnectionSQL;

public class RegistroAvaliacao {

    private List<Avaliacao> avaliacoes;

    public RegistroAvaliacao() {
        avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(int nota, Usuario usuario, Livro livro, ConnectionSQL conexao) {
        Avaliacao avaliacao = new Avaliacao(nota, usuario, livro);
        avaliacoes.add(avaliacao);
        System.out.println("Avaliação adicionada com sucesso!");
    }

    public void editarAvaliacao(int novaNota, Usuario usuario, Livro livro, ConnectionSQL conexao) {
        avaliacoes.stream()
                .filter(avaliacao -> avaliacao.getLivro().equals(livro) && avaliacao.getUsuario().equals(usuario))
                .findFirst()
                .ifPresent(avaliacao -> {
                    System.out.println("Avaliação editada com sucesso!");
                });
    }

    public void excluirAvaliacao(Usuario usuario, Livro livro, ConnectionSQL conexao) {
        Iterator<Avaliacao> iterator = avaliacoes.iterator();
        while (iterator.hasNext()) {
            Avaliacao avaliacao = iterator.next();
            if (avaliacao.getLivro().equals(livro) && avaliacao.getUsuario().equals(usuario)) {
                iterator.remove();
                System.out.println("Avaliação excluída com sucesso!");
                return;
            }
        }
        System.out.println("Avaliação não encontrada.");
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    
}
