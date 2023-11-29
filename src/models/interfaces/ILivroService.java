package models.interfaces;

import java.util.List;

import models.Livro;

public interface ILivroService {

    List<Livro> getLivrosOrdenadosPorMedia();

    double calcularMediaNotas(Livro livro);

    int getNumeroAvaliacoes(Livro livro);

    boolean cadastrarLivro(Livro livro);

}
