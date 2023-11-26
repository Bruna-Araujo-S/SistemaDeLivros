package dados;

public class Avaliacao {
    private Livro livro;
    private int nota;

    public Avaliacao(Livro livro, int nota) {
        this.livro = livro;
        this.nota = nota;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getNota() {
        return nota;
    }
}
