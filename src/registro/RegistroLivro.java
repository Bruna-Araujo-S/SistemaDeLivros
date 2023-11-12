package registro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public Livro getLivro(int indice) {
        return (indice >= 0 && indice < livros.size()) ? livros.get(indice) : null;
    }

  public List<Livro> getLivros() {
        return new ArrayList<>(livros);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void removerLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            livros.remove(indice);
        } else {
            System.out.println("Índice inválido. Não foi possível excluir o livro.");
        }
    }

    public int quantidadeLivros() {
        return livros.size();
    }

    public void emprestarLivro(Livro livro, Usuario usuario) {
        LocalDateTime now = LocalDateTime.now();
        livro.setDataEmprestimo(now);
        livro.setUsuarioComLivro(usuario);
    }

    public void ordenarPorTituloAZ() {
        Collections.sort(livros);
    }

    public Livro getLivroPorCodigo(int codigo) {
        return livros.stream()
                .filter(livro -> livro.getCodigoDoLivro() == codigo)
                .findFirst()
                .orElse(null);
    }

    public void ordenarPorAvaliacoes() {
        livros.sort(Comparator.comparingDouble(Livro::calcularMediaAvaliacoes).reversed());
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
  
}
