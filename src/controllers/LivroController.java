package controllers;

import java.util.List;

import enums.GeneroLivro;
import interfaces.gerenciamento.ILivroDAO;
import models.Livro;
import models.Usuario;
import util.SessaoUsuario;
import validations.ValidarCadastroLivro;

public class LivroController {

    private ILivroDAO livroDAO;

    public LivroController(ILivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

      public boolean cadastrarLivro(String titulo, String autor, GeneroLivro genero, double valor, int nota, int idUsuario) {
        ValidarCadastroLivro validador = new ValidarCadastroLivro();

        if (validador.validarTitulo(titulo) &&
            validador.validarAutor(autor) &&
            validador.validarValor(valor) &&
            validador.validarNota(nota)) {

            Livro novoLivro = new Livro(titulo, autor, genero, valor, nota, idUsuario);
            livroDAO.salvarLivroNoBanco(novoLivro);
            return true;
        } else {
            return false;
        }
    }


    public List<Livro> visualizarLivrosOrdenadosComMedia() {
        List<Livro> livrosOrdenados = livroDAO.getLivrosDoBanco();
    
        for (Livro livro : livrosOrdenados) {
            double mediaAvaliacoes = livroDAO.calcularMediaAvaliacoes(livro);
            livro.setMediaAvaliacoes(mediaAvaliacoes);
        }
    
        return livrosOrdenados;
    }
    

    public void avaliarLivro(Livro livro, int novaNota) {
        Usuario usuarioAvaliador = SessaoUsuario.getUsuarioLogado();

        if (usuarioAvaliador != null) {
            if (!livro.jaFoiAvaliado(usuarioAvaliador.getId())) {
                if (livro.getUsuarioAvaliadorID() == usuarioAvaliador.getId()) {
                    exibirAviso("Você não pode avaliar um livro que você mesmo cadastrou.");
                } else {
                    livro.atribuirNota(novaNota);
                    livroDAO.atualizarNotaLivroNoBanco(livro);
                    System.out.println("Livro avaliado com sucesso!");
                }
            } else {
                exibirAviso("Você já avaliou este livro.");
            }
        } else {
            exibirAviso("Erro ao obter o usuário avaliador.");
        }
    }

    private void exibirAviso(String mensagem) {
        System.out.println(mensagem);
    }

}

