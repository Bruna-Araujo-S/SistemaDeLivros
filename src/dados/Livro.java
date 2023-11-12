package dados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dados.Enum.GeneroLivro;

public class Livro implements Comparable<Livro> {

    private String titulo, autor;
    private GeneroLivro genero;  
    private LocalDateTime dataEmprestimo;
    private double valor;
    private static int codLv = 1;
    private int codigoDoLivro;
    private Usuario usuarioComLivro;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, GeneroLivro genero, double valor) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.valor = valor;
        dataEmprestimo = null;
        usuarioComLivro = null;
        this.codigoDoLivro = codLv;
        codLv++;
        this.avaliacoes = new ArrayList<>();
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

    public int getCodigoDoLivro() {
        return codigoDoLivro;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Usuario getUsuarioComLivro() {
        return usuarioComLivro;
    }

    public void setUsuarioComLivro(Usuario usuarioComLivro) {
        this.usuarioComLivro = usuarioComLivro;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        double somaNotas = 0.0;
        for (Avaliacao avaliacao : avaliacoes) {
            somaNotas += avaliacao.getNota();
        }

        return somaNotas / avaliacoes.size();
    }

    public int compareTo(Livro l) {
        double mediaThis = this.calcularMediaAvaliacoes();
        double mediaL = l.calcularMediaAvaliacoes();

        if (mediaThis != mediaL) {
            return Double.compare(mediaL, mediaThis); 
        } else {
            if (avaliacoes.size() != l.avaliacoes.size()) {
                return Integer.compare(l.avaliacoes.size(), avaliacoes.size()); 
            } else {
                return this.titulo.compareToIgnoreCase(l.getTitulo());
            }
        }
    }

	public List<Avaliacao> getAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }

	   public void removerAvaliacao(Avaliacao avaliacao) {
        avaliacoes.remove(avaliacao);
    }
}
