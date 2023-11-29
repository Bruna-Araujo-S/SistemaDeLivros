package validations;

import javax.swing.JOptionPane;

import models.Livro;

public class ValidarCadastroLivro {

    public boolean validarTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            exibirMensagemErro("Livro inválido: título não pode ser vazio.");
            return false;
        }
        return true;
    }

    public boolean validarAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            exibirMensagemErro("Livro inválido: autor não pode ser vazio.");
            return false;
        }
        return true;
    }

    public boolean validarValor(double valor) {
        if (valor <= 0) {
            exibirMensagemErro("Livro inválido: valor deve ser maior que zero.");
            return false;
        }
        return true;
    }

    public boolean validarNota(int nota) {
        if (nota < 0 || nota > 10) {
            exibirMensagemErro("Livro inválido: nota deve estar entre 0 e 10.");
            return false;
        }
        return true;
    }

    public boolean validarLivro(Livro livro) {
        if (livro == null) {
            exibirMensagemErro("Livro inválido: o objeto é nulo.");
            return false;
        }

        return validarTitulo(livro.getTitulo()) &&
               validarAutor(livro.getAutor()) &&
               validarValor(livro.getValor()) &&
               validarNota(livro.getNota());
    }

    private void exibirMensagemErro(String mensagem) {
        System.out.println(mensagem);
    JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);

    }
}
