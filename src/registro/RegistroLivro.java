package registro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dados.Livro;
import dados.Usuario;
import dados.Enum.GeneroLivro;

public class RegistroLivro {

    private List<Livro> livros;

    public RegistroLivro() {
        livros = new ArrayList<>();
    }

    public void addLivro(Livro livro) {
        livros.add(livro);
    }

public void editarLivro(int indice, String novoTitulo, String novoAutor, GeneroLivro novoGenero, double novoValor) {
    if (indice >= 0 && indice < livros.size()) {
        Livro livro = livros.get(indice);
        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);
        livro.setGenero(novoGenero);
        livro.setValor(novoValor);
        System.out.println("Livro editado com sucesso!");
    } else {
        System.out.println("Índice inválido. Não foi possível editar o livro.");
    }
}

     public void excluirLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            livros.remove(indice);
            System.out.println("Livro excluído com sucesso.");
        } else {
            System.out.println("Índice inválido. Não foi possível excluir o livro.");
        }
    }

    public void emprestarLivro(Livro livro, Usuario usuario) {
        LocalDateTime now = LocalDateTime.now();
        livro.setDataEmprestimo(now);
        livro.setUsuarioComLivro(usuario);
    }

    public void ordenarPorTituloAZ() {
        Collections.sort(livros);
    }
    
      public List<Livro> getLivros() {
        return new ArrayList<>(livros);
    }

}
