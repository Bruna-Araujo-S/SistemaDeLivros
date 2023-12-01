package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controllers.LivroController;
import dao.services.AdminDAO;
import dao.services.LivroDAO;
import dao.services.UsuarioDAO;
import enums.GeneroLivro;
import interfaces.gerenciamento.ILivroDAO;
import models.Livro;
import models.Usuario;
import services.AdminService;
import util.SessaoUsuario;

public class UserFrame extends JFrame {

    private ILivroDAO livroDAO;
    private Usuario usuario;
    private LivroController livroController;

    public UserFrame(Usuario usuario, ILivroDAO livroDAO) {
        this.usuario = usuario;
        this.livroDAO = livroDAO;
        this.livroController = new LivroController(livroDAO);

        setTitle("User Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton cadastrarLivroButton = new JButton("Cadastrar Livro");
        JButton visualizarLivrosOrdenadosButton = new JButton("Visualizar Livros");
        JButton avaliarLivrosButton = new JButton("Avaliar Livros");

        Dimension buttonSize = new Dimension(200, 50);
        cadastrarLivroButton.setPreferredSize(buttonSize);
        visualizarLivrosOrdenadosButton.setPreferredSize(buttonSize);
        avaliarLivrosButton.setPreferredSize(buttonSize);

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

        avaliarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avaliarLivros();
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaLogin();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(cadastrarLivroButton, gbc);

        gbc.gridy = 1;
        add(avaliarLivrosButton, gbc);

        gbc.gridy = 2;
        add(visualizarLivrosOrdenadosButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 5, 5, 5);
        add(logoutButton, gbc);

        int totalWidth = buttonSize.width + 30;
        int totalHeight = buttonSize.height * 5 + 30;

        setSize(totalWidth, totalHeight);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarLivro() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15, 3));

        JLabel titleLabel = new JLabel("Título:");
        JTextField titleField = new JTextField();

        JLabel authorLabel = new JLabel("Autor:");
        JTextField authorField = new JTextField();

        JLabel genreLabel = new JLabel("Gênero:");
        JComboBox<GeneroLivro> genreComboBox = new JComboBox<>(GeneroLivro.values());

        JLabel valueLabel = new JLabel("Valor:");
        JTextField valueField = new JTextField();

        JLabel notaLabel = new JLabel("Nota de 0 a 10:");
        JTextField notaField = new JTextField();

        JButton voltarButton = new JButton("Voltar");
        JButton cadastrarButton = new JButton("Cadastrar");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivroAction(panel, titleField.getText(), authorField.getText(),
                        (GeneroLivro) genreComboBox.getSelectedItem(), valueField.getText(),
                        notaField.getText());
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
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
        panel.add(voltarButton);

        JOptionPane.showOptionDialog(null, panel, "Cadastro de Livro", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);
    }

    private void cadastrarLivroAction(JPanel panel, String title, String author, GeneroLivro genre, String value,
            String nota) throws NumberFormatException {
        try {
            double valor = ConverNum(value);
            int notaInt = Integer.parseInt(nota);
            int idUsuario = SessaoUsuario.getIdUsuario();

            Livro novoLivro = new Livro(title, author, genre, valor, notaInt, idUsuario);

            if (livroController.cadastrarLivro(title, author, genre, valor, notaInt, idUsuario)) {
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void visualizarLivrosOrdenados() {
        JFrame visualizarLivrosFrame = new JFrame("Livros Ordenados");
        visualizarLivrosFrame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        JTextArea textArea = new JTextArea();

        new Thread(() -> {
            System.out.println("Antes de obter livros ordenados com média.");
            List<Livro> livrosOrdenados = livroController.visualizarLivrosOrdenadosComMedia();
            System.out.println("Depois de obter livros ordenados com média.");

            SwingUtilities.invokeLater(() -> {
                textArea.setText("");

                for (Livro livro : livrosOrdenados) {
                    textArea.append("Título: " + livro.getTitulo() + "\n");
                    textArea.append("Autor: " + livro.getAutor() + "\n");
                    textArea.append("Gênero: " + livro.getGenero() + "\n");
                    textArea.append("Média de Avaliações: " + livro.getMediaAvaliacoes() + "\n");
                    textArea.append("Número de Avaliações: " + livro.getNumeroAvaliacoes() + "\n");
                    textArea.append("\n------------------------\n\n");
                }

                panel.add(new JScrollPane(textArea));

                visualizarLivrosFrame.add(panel);

                visualizarLivrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                visualizarLivrosFrame.setLocationRelativeTo(null);
                visualizarLivrosFrame.setVisible(true);
            });

        }).start();
    }

    private void avaliarLivros() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 20));

        JLabel livroLabel = new JLabel("Escolha um livro para avaliar:");
        JComboBox<Livro> livroComboBox = new JComboBox<>(
                livroController.visualizarLivrosOrdenadosComMedia().toArray(new Livro[0]));
        livroComboBox.setRenderer(getLivroListCellRenderer());

        JButton verInfoButton = new JButton("Ver Informações");
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);

        JLabel notaLabel = new JLabel("Atribuir Nota (0 a 10):");
        JTextField notaField = new JTextField();

        JButton avaliarButton = new JButton("Avaliar");
        JButton voltarButton = new JButton("Voltar");

        avaliarButton.addActionListener(e -> {
            Livro livroSelecionado = (Livro) livroComboBox.getSelectedItem();
            int novaNota = Integer.parseInt(notaField.getText());
            Usuario usuarioAvaliador = SessaoUsuario.getUsuarioLogado();

            if (usuarioAvaliador != null) {
                if (avaliacaoValida(livroSelecionado, usuarioAvaliador, novaNota)) {
                    realizarAvaliacao(livroSelecionado, novaNota, infoTextArea);
                    JOptionPane.showMessageDialog(null, "Livro avaliado com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
                }
            } else {
                exibirAviso("Erro ao obter o usuário avaliador.");
            }
        });

        verInfoButton.addActionListener(e -> {
            Livro livroSelecionado = (Livro) livroComboBox.getSelectedItem();
            exibirInformacoesLivroPopUp(livroSelecionado);
        });

        voltarButton.addActionListener(e -> ((Window) SwingUtilities.getWindowAncestor(panel)).dispose());

        livroComboBox.addActionListener(e -> {
            Livro livroSelecionado = (Livro) livroComboBox.getSelectedItem();
            exibirInformacoesLivro(infoTextArea, livroSelecionado);
        });

        panel.add(livroLabel);
        panel.add(livroComboBox);
        panel.add(verInfoButton);
        panel.add(notaLabel);
        panel.add(notaField);
        panel.add(new JLabel());
        panel.add(avaliarButton);
        panel.add(voltarButton);

        JOptionPane.showOptionDialog(null, panel, "Avaliar Livro", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] {}, null);
    }

    private void exibirInformacoesLivroPopUp(Livro livro) {
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        exibirInformacoesLivro(infoTextArea, livro);

        JPanel panel = new JPanel();
        panel.add(infoTextArea);

        int result = JOptionPane.showOptionDialog(null, panel, "Informações do Livro", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, new Object[] { "OK" }, null);

        if (result == 0) {
        }
    }

    private DefaultListCellRenderer getLivroListCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Livro) {
                    Livro livro = (Livro) value;
                    setText(livro.getTitulo());
                }
                return this;
            }
        };
    }

    private boolean avaliacaoValida(Livro livro, Usuario usuario, int novaNota) {
        if (!livro.jaFoiAvaliado(usuario.getId())) {
            if (livro.getUsuarioAvaliadorID() == usuario.getId()) {
                exibirAviso("Você não pode avaliar um livro que você mesmo cadastrou.");
                return false;
            } else {
                return true;
            }
        } else {
            exibirAviso("Você já avaliou este livro.");
            return false;
        }
    }

    private void realizarAvaliacao(Livro livro, int novaNota, JTextArea infoTextArea) {
        livro.atribuirNota(novaNota);
        livroController.avaliarLivro(livro, novaNota);

        exibirInformacoesLivro(infoTextArea, livro);
        System.out.println("Livro avaliado com sucesso!");
    }

    private void exibirInformacoesLivro(JTextArea infoTextArea, Livro livro) {
        infoTextArea.setText("Título: " + livro.getTitulo() + "\n" + "Autor: " + livro.getAutor() + "\n"
                + "Gênero: " + livro.getGenero() + "\n" + "Média de Avaliações: " + livro.getMediaAvaliacoes() + "\n"
                + "Número de Avaliações: " + livro.getNumeroAvaliacoes() + "\n");
    }

    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private double ConverNum(String str) throws NumberFormatException {
        str = str.replace(",", ".");
        return Double.parseDouble(str);
    }

    private void voltarParaLogin() {
        AdminDAO adminDAO = new AdminDAO();
        AdminService registroAdmin = new AdminService(adminDAO);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();
        new LoginFrame(registroAdmin, livroDAO, usuarioDAO, adminDAO);

        dispose();
    }
}
