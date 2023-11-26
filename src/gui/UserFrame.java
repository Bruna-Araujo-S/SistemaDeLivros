package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dados.Livro;
import dados.Usuario;
import dados.Enum.GeneroLivro;
import registro.RegistroLivro;
import util.SessaoUsuario;

public class UserFrame {

    private RegistroLivro registroLivro;
    private Usuario usuario;

    public UserFrame(Usuario usuario, RegistroLivro registroLivro) {
        this.usuario = usuario;
        this.registroLivro = registroLivro;

        JFrame frame = new JFrame("User Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton cadastrarLivroButton = new JButton("Cadastrar Livro");
        JButton visualizarLivrosOrdenadosButton = new JButton("Visualizar Livros");

        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        visualizarLivrosOrdenadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarLivrosOrdenados();
            }
        });

        panel.add(cadastrarLivroButton);
        panel.add(visualizarLivrosOrdenadosButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

     private void cadastrarLivro() {
        JFrame cadastrarLivroFrame = new JFrame("Cadastrar Livro");
        cadastrarLivroFrame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel titleLabel = new JLabel("Título:");
        JTextField titleField = new JTextField();

        JLabel authorLabel = new JLabel("Autor:");
        JTextField authorField = new JTextField();

        JLabel genreLabel = new JLabel("Gênero:");
        JComboBox<GeneroLivro> genreComboBox = new JComboBox<>(GeneroLivro.values());

        JLabel valueLabel = new JLabel("Valor:");
        JTextField valueField = new JTextField();

        JLabel notaLabel = new JLabel("Nota:");
        JTextField notaField = new JTextField();

        JButton cadastrarButton = new JButton("Cadastrar");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = titleField.getText();
                String autor = authorField.getText();
                GeneroLivro genero = (GeneroLivro) genreComboBox.getSelectedItem();
                double valor = Double.parseDouble(valueField.getText());
                int nota = Integer.parseInt(notaField.getText());
                int idUsuario = SessaoUsuario.getIdUsuario();
                
                Livro novoLivro = new Livro(titulo, autor, genero, valor, nota, idUsuario);
                
                registroLivro.cadastrarLivro(novoLivro);
                registroLivro.salvarLivroNoBanco(novoLivro);
                cadastrarLivroFrame.dispose();
            }
        });
        
        

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(genreLabel);
        panel.add(genreComboBox);
        panel.add(valueLabel);
        panel.add(valueField);
         panel.add(notaLabel);
        panel.add(notaField);
        panel.add(new JLabel());
        panel.add(cadastrarButton);

        cadastrarLivroFrame.add(panel);
        cadastrarLivroFrame.setLocationRelativeTo(null);
        cadastrarLivroFrame.setVisible(true);
    }

    private void visualizarLivrosOrdenados() {
        JFrame visualizarLivrosFrame = new JFrame("Livros Ordenados");
        visualizarLivrosFrame.setSize(600, 400);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
    
        JTextArea textArea = new JTextArea();
    
        List<Livro> livrosOrdenados = registroLivro.getLivrosOrdenadosPorMedia();
    
        for (Livro livro : livrosOrdenados) {
            textArea.append("Título: " + livro.getTitulo() + "\n");
            textArea.append("Autor: " + livro.getAutor() + "\n");
            textArea.append("Gênero: " + livro.getGenero() + "\n");
            textArea.append("Média de Avaliações: " + registroLivro.calcularMediaNotas(livro) + "\n");
            textArea.append("Número de Avaliações: " + registroLivro.getNumeroAvaliacoes(livro) + "\n");
            textArea.append("\n------------------------\n\n");
        }
    
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
    
        visualizarLivrosFrame.add(panel);
        visualizarLivrosFrame.setLocationRelativeTo(null);
        visualizarLivrosFrame.setVisible(true);
    }
    
    
}


