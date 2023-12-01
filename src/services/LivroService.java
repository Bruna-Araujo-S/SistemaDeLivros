package services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.gerenciamento.ILivroDAO;
import models.Livro;
import models.Usuario;
import models.interfaces.ILivroService;
import util.SessaoUsuario;

public class LivroService implements ILivroService {

    private ILivroDAO livroDAO;

    public LivroService(ILivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    @Override
    public List<Livro> getLivrosOrdenadosPorMedia() {
        List<Livro> livrosOrdenados = livroDAO.getLivrosDoBanco();

        Collections.sort(livrosOrdenados, Comparator
                .comparingDouble((Livro livro) -> calcularMediaNotas(livro))
                .thenComparingInt((Livro livro) -> getNumeroAvaliacoes(livro))
                .reversed()
                .thenComparing(Livro::getTitulo));

        return livrosOrdenados;
    }

    @Override
    public double calcularMediaNotas(Livro livro) {
        List<Livro> livrosDoBanco = livroDAO.getLivrosDoBanco();

        List<Livro> livrosDoMesmoID = livrosDoBanco.stream()
                .filter(l -> l.getId() == livro.getId())
                .sorted(Comparator.comparingInt(Livro::getNumeroAvaliacoes).reversed()
                        .thenComparing(Livro::getTitulo))
                .collect(Collectors.toList());

        int somaNotas = 0;
        int quantidadeAvaliacoes = 0;

        for (Livro livroOrdenado : livrosDoMesmoID) {
            if (livroOrdenado.getUsuarioAvaliadorID() == livro.getUsuarioAvaliadorID()) {
                somaNotas += livroOrdenado.getNota();
                quantidadeAvaliacoes += livroOrdenado.getNumeroAvaliacoes();
            }
        }

        return quantidadeAvaliacoes > 0 ? (double) somaNotas / quantidadeAvaliacoes : 0;
    }

    @Override
    public int getNumeroAvaliacoes(Livro livro) {
        int numeroAvaliacoes = livro.getNota() > 0 ? 1 : 0;

        List<Livro> livrosDoBanco = livroDAO.getLivrosDoBanco();
        for (Livro livroBanco : livrosDoBanco) {
            if (livroBanco.getId() == livro.getId()) {
                numeroAvaliacoes += livroBanco.getNota() > 0 ? 1 : 0;
            }
        }

        return numeroAvaliacoes;
    }

    @Override
    public boolean cadastrarLivro(Livro livro) {
        if (livro != null) {
            Usuario usuarioLogado = getIdUsuarioAvaliador();
            if (usuarioLogado != null) {
                livro.setUsuarioAvaliadorID(usuarioLogado.getId());
                livroDAO.salvarLivroNoBanco(livro);
                System.out.println("Livro cadastrado com sucesso!");
                return true;
            }
        }
        System.out.println("Falha ao cadastrar o livro. Livro não válido ou usuário não encontrado.");
        return false;
    }

    private Usuario getIdUsuarioAvaliador() {
        return SessaoUsuario.getUsuarioLogado();
    }

}
