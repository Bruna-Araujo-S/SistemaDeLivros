package dados;

import dados.Enum.GeneroLivro;

public class Livro implements Comparable<Livro> {

    private String titulo, autor;
    private GeneroLivro genero;
    private double valor;
    private int usuarioAvaliadorID;
    private int nota;
    private int id;
    private int numeroAvaliacoes;


   
    public Livro(String titulo, String autor, GeneroLivro genero, double valor, int nota, int idUsuario) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.valor = valor;
        this.nota = nota;
        this.usuarioAvaliadorID = idUsuario;
    }
    
    
    public int getNumeroAvaliacoes() {
        return this.numeroAvaliacoes;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public GeneroLivro getGenero() {
        return genero;
    }

    public void setGenero(GeneroLivro genero) {
        this.genero = genero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNota() {
        return nota;
    }

    public void atribuirNota(int novaNota) {
        if (isValidNota(novaNota)) {
            this.nota = novaNota;
        } else {
            throw new IllegalArgumentException("A nota deve estar no intervalo de 0 a 10.");
        }
    }

    private boolean isValidNota(int novaNota) {
        return novaNota >= 0 && novaNota <= 10;
    }

       public void setUsuarioAvaliadorID(int usuarioAvaliadorID) {
        this.usuarioAvaliadorID = usuarioAvaliadorID;
    }

    public int getUsuarioAvaliadorID() {
        return this.usuarioAvaliadorID;
    }


    @Override
    public int compareTo(Livro o) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
    
