package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controllers.UsuarioController;
import dao.services.AdminDAO;
import dao.services.LivroDAO;
import dao.services.UsuarioDAO;
import enums.Sexo;
import interfaces.gerenciamento.IUsuarioDAO;
import models.Usuario;
import services.AdminService;
import services.UsuarioService;

public class AdminFrame extends JFrame {

    private UsuarioService usuarioService;
    private IUsuarioDAO usuarioDAO;
    private UsuarioController usuarioController;

    public AdminFrame(UsuarioService usuarioService, IUsuarioDAO usuarioDAO) {
        this.usuarioService = usuarioService;
        this.usuarioDAO = usuarioDAO;
        this.usuarioController = new UsuarioController(usuarioService, usuarioDAO);

        setTitle("Admin Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addUserButton = new JButton("Adicionar Usuário");
        JButton editUserButton = new JButton("Editar Usuário");
        JButton removeUserButton = new JButton("Remover Usuário");

        Dimension buttonSize = new Dimension(200, 50);
        addUserButton.setPreferredSize(buttonSize);
        editUserButton.setPreferredSize(buttonSize);
        removeUserButton.setPreferredSize(buttonSize);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerUsuario();
            }
        });

        JButton logoutButton = new JButton("Logout");

        logoutButton = new JButton("Logout");
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
        add(addUserButton, gbc);

        gbc.gridy = 1;
        add(editUserButton, gbc);

        gbc.gridy = 2;
        add(removeUserButton, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 5, 5, 5);
        add(logoutButton, gbc);

        int totalWidth = buttonSize.width + 30;
        int totalHeight = buttonSize.height * 5 + 30;

        setSize(totalWidth, totalHeight);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarUsuario() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 3));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();

        JLabel idadeLabel = new JLabel("Idade:");
        JTextField idadeField = new JTextField();

        JLabel sexoLabel = new JLabel("Genero:");
        JComboBox<Sexo> sexoComboBox = new JComboBox<>(Sexo.values());

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        JTextField senhaField = new JPasswordField();
        JCheckBox mostrarSenhaCheckBox = new JCheckBox("Mostrar Senha");

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        mostrarSenhaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JPasswordField) senhaField).setEchoChar(mostrarSenhaCheckBox.isSelected() ? '\0' : '*');
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                int idade;
                try {
                    idade = Integer.parseInt(idadeField.getText());
                } catch (NumberFormatException ex) {
                    System.out.println("Idade inválida");
                    return;
                }
                Sexo sexo = (Sexo) sexoComboBox.getSelectedItem();

                String email = emailField.getText();
                String senha = senhaField.getText();

                if (usuarioController.cadastrarUsuario(nome, telefone, idade, sexo.name(), email, senha)) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                    ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao adicionar usuário. Verifique os dados fornecidos.");
                }
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(telefoneLabel);
        panel.add(telefoneField);
        panel.add(idadeLabel);
        panel.add(idadeField);
        panel.add(sexoLabel);
        panel.add(sexoComboBox);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(mostrarSenhaCheckBox);
        panel.add(new JLabel());
        panel.add(cadastrarButton);
        panel.add(voltarButton);
        JOptionPane.showOptionDialog(
                null,
                panel,
                "Cadastro de Usuário",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[] {},
                null);
    }

    private void editarUsuario() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 3));

        int userId = Integer.parseInt(JOptionPane.showInputDialog("ID do usuário a ser editado:"));
        Usuario usuario = usuarioDAO.getUsuarioById(userId);

        if (usuario != null) {
            JLabel novoNomeLabel = new JLabel("Novo nome:");
            JTextField novoNomeField = new JTextField(usuario.getNome());

            JLabel novoTelefoneLabel = new JLabel("Novo telefone:");
            JTextField novoTelefoneField = new JTextField(usuario.getTelefone());

            JLabel novaIdadeLabel = new JLabel("Nova idade:");
            JTextField novaIdadeField = new JTextField(String.valueOf(usuario.getIdade()));

            JLabel novoSexoLabel = new JLabel("Novo genero:");
            JComboBox<Sexo> novoSexoComboBox = new JComboBox<>(Sexo.values());

            JPanel botoesPanel = new JPanel(new GridLayout(1, 2));

            JButton editarButton = new JButton("Editar");
            JButton voltarButton = new JButton("Voltar");

            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String novoNome = novoNomeField.getText();
                    String novoTelefone = novoTelefoneField.getText();
                    int novaIdade = Integer.parseInt(novaIdadeField.getText());
                    Sexo novoSexo = (Sexo) novoSexoComboBox.getSelectedItem();

                    if (usuarioController.editarUsuario(usuario.getId(), novoNome, novoTelefone, novaIdade,
                            novoSexo.name())) {
                        JOptionPane.showMessageDialog(null, "Usuário editado e atualizado com sucesso!");
                        ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao editar usuário. Verifique os dados fornecidos.");
                    }
                }
            });

            voltarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
                }
            });
            botoesPanel.add(editarButton);
            botoesPanel.add(voltarButton);
            panel.add(novoNomeLabel);
            panel.add(novoNomeField);
            panel.add(novoTelefoneLabel);
            panel.add(novoTelefoneField);
            panel.add(novaIdadeLabel);
            panel.add(novaIdadeField);
            panel.add(novoSexoLabel);
            panel.add(novoSexoComboBox);
            panel.add(new JLabel());
            panel.add(botoesPanel);

            JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Edição de Usuário",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] {},
                    null);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado para o ID fornecido.");
            System.out.println("Usuário não encontrado.");
        }
    }

    private void removerUsuario() {
        int userId = Integer.parseInt(JOptionPane.showInputDialog("ID do usuário a ser removido:"));
        Usuario usuario = usuarioDAO.getUsuarioById(userId);

        if (usuario != null) {
            String mensagemConfirmacao = "Você está prestes a remover o seguinte usuário:\n\n";
            mensagemConfirmacao += "ID: " + usuario.getId() + "\n";
            mensagemConfirmacao += "Nome: " + usuario.getNome() + "\n";
            mensagemConfirmacao += "Telefone: " + usuario.getTelefone() + "\n";
            mensagemConfirmacao += "Idade: " + usuario.getIdade() + "\n";
            mensagemConfirmacao += "Sexo: " + usuario.getSexo() + "\n";
            mensagemConfirmacao += "Email: " + usuario.getEmail() + "\n";

            Locale.setDefault(new Locale("pt", "BR"));
            int opcao = JOptionPane.showConfirmDialog(null, mensagemConfirmacao, "Confirmação de Remoção",
                    JOptionPane.YES_NO_OPTION);

            if (opcao == JOptionPane.YES_OPTION) {
                if (usuarioController.removerUsuario(userId)) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    System.out.println("Usuário removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Falha ao remover usuário. Verifique o ID fornecido.");
                    System.out.println("Falha ao remover usuário. Verifique o ID fornecido.");
                }
            } else {
                System.out.println("Operação de remoção cancelada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado para o ID fornecido.");
            System.out.println("Usuário não encontrado.");
        }
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
