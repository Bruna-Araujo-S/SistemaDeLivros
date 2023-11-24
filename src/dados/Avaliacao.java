package dados;

public class Avaliacao {

    private int nota;
    private Usuario usuario;
    private Livro livro;

    public Avaliacao(int nota, Usuario usuario, Livro livro) {
        setNota(nota);
        this.usuario = usuario;
        this.livro = livro;
    }

    public int getNota() {
        return nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setNota(int novaNota) {
        if (isValidNota(novaNota)) {
            this.nota = novaNota;
        } else {
            throw new IllegalArgumentException("A nota deve estar no intervalo de 0 a 10.");
        }
    }

    private boolean isValidNota(int novaNota) {
        return novaNota >= 0 && novaNota <= 10;
    }

    @Override
    public String toString() {
        return String.format("Avaliação: Livro='%s', Usuário='%s', Nota='%d'", livro.getTitulo(), usuario.getNome(), nota);
    }

}
