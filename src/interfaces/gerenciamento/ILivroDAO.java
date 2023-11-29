package interfaces.gerenciamento;

import java.util.List;

import models.Livro;
import models.Usuario;

public interface ILivroDAO {

    void salvarLivroNoBanco(Livro livro);

    void atualizarNotaLivroNoBanco(Livro livro);

    List<Livro> getLivrosDoBanco();

    Usuario getIdUsuarioAvaliador(String email);

    double calcularMediaAvaliacoes(Livro livro);

}
