package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dados.Avaliacao;
import dados.Livro;
import dados.Usuario;
import registro.RegistroAvaliacao;
import registro.RegistroLivro;

public class UserFrame {

    private RegistroLivro registroLivro;
    private RegistroAvaliacao registroAvaliacao;
    private Usuario usuario;

    public UserFrame(Usuario usuario, RegistroLivro registroLivro, RegistroAvaliacao registroAvaliacao) {
        this.usuario = usuario;
        this.registroLivro = registroLivro;
        this.registroAvaliacao = registroAvaliacao;

        JFrame frame = new JFrame("User Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton cadastrarLivroButton = new JButton("Cadastrar Livro");
        JButton avaliarLivroButton = new JButton("Avaliar Livro");
        JButton visualizarLivrosButton = new JButton("Visualizar Livros");

        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        avaliarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avaliarLivro();
            }
        });

        visualizarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarLivros();
            }
        });

        panel.add(cadastrarLivroButton);
        panel.add(avaliarLivroButton);
        panel.add(visualizarLivrosButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void cadastrarLivro() {
        
    }

    private void avaliarLivro() {
        
    }

    private void visualizarLivros() {
        List<Livro> livros = registroLivro.getLivros();

        livros.sort((livro1, livro2) -> Double.compare(calcularMediaNotas(livro2), calcularMediaNotas(livro1)));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (Livro livro : livros) {
            textArea.append("Título: " + livro.getTitulo() + "\n");
            textArea.append("Autor: " + livro.getAutor() + "\n");
            textArea.append("Média das Notas: " + calcularMediaNotas(livro) + "\n\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame visualizarLivrosFrame = new JFrame("Livros Cadastrados");
        visualizarLivrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        visualizarLivrosFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        visualizarLivrosFrame.setSize(400, 300);
        visualizarLivrosFrame.setLocationRelativeTo(null);
        visualizarLivrosFrame.setVisible(true);
    }

    private double calcularMediaNotas(Livro livro) {
        List<Avaliacao> avaliacoes = registroAvaliacao.getAvaliacoes();
        int somaNotas = 0;
        int quantidadeAvaliacoes = 0;

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getLivro().equals(livro)) {
                somaNotas += avaliacao.getNota();
                quantidadeAvaliacoes++;
            }
        }

        return quantidadeAvaliacoes > 0 ? (double) somaNotas / quantidadeAvaliacoes : 0;
    }
}
