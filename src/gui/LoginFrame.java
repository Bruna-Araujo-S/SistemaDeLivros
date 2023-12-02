package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import authentications.AutenticarUsuario;
import dao.services.AdminDAO;
import dao.services.LivroDAO;
import dao.services.UsuarioDAO;
import interfaces.gerenciamento.IAdminDAO;
import interfaces.gerenciamento.ILivroDAO;
import interfaces.gerenciamento.IUsuarioDAO;
import models.Administrador;
import models.Usuario;
import services.AdminService;

public class LoginFrame extends JFrame {

    private AdminService registroAdministrador;
    private AutenticarUsuario autenticarUsuario;
    private JPasswordField senhaField;
    private ILivroDAO livroDAO;
    private Usuario usuario;
    private IUsuarioDAO usuarioDAO;
    private IAdminDAO adminDAO;

    public LoginFrame(AdminService registroAdministrador, ILivroDAO livroDAO, IUsuarioDAO usuarioDAO,
            IAdminDAO adminDAO) {
        this.registroAdministrador = registroAdministrador;
        this.livroDAO = livroDAO;
        this.usuarioDAO = usuarioDAO;
        this.adminDAO = adminDAO;
        this.autenticarUsuario = new AutenticarUsuario();
        this.autenticarUsuario.estabelecerConexao("jdbc:mysql://localhost:3306/Sistema_de_Livro", "root", "root");

        setTitle("Acessar Book Library");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon("src/Imagens/Biblioteca.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BorderLayout());

        JCheckBox showPasswordCheckBox = new JCheckBox("Mostrar Senha");
        showPasswordCheckBox.setOpaque(false);

        senhaField = new JPasswordField(15);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                senhaField.setEchoChar(checkBox.isSelected() ? '\0' : '*');
            }
        });

        JTextField emailField = new JTextField(15);
        JButton loginButton = new JButton("Login");
        JComboBox<String> userTypeComboBox = new JComboBox<>(new String[] { "Administrador", "Comum" });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                ;
                String senha = new String(senhaField.getPassword()).trim();
                ;
                String userType = (String) userTypeComboBox.getSelectedItem();

                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Email e senha são obrigatórios.");
                    return;
                }

                boolean autenticado = false;

                if ("Administrador".equals(userType)) {
                    autenticado = autenticarUsuario.autenticarAdministrador(email, senha);
                } else if ("Comum".equals(userType)) {
                    autenticado = autenticarUsuario.autenticarUsuario(email, senha);
                }

                if (autenticado) {
                    processarLogin(email, userType);
                } else {
                    exibirMensagemErro("Email ou senha inválidos. Tente novamente.");
                }
            }
        });
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridLayout(4, 8, 6, 6));

        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Senha:"));
        inputPanel.add(senhaField);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        inputPanel.add(emptyPanel);

        inputPanel.add(showPasswordCheckBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);

        inputPanel.add(new JLabel("Tipo de Usuário:"));
        inputPanel.add(userTypeComboBox);

        formPanel.add(inputPanel, BorderLayout.WEST);
        formPanel.add(loginButton, BorderLayout.SOUTH);

        backgroundLabel.add(formPanel, BorderLayout.CENTER);

        Font font = emailField.getFont();
        emailField.setFont(new Font(font.getName(), font.getStyle(), 16));

        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void processarLogin(String email, String userType) {
        if ("Administrador".equals(userType)) {
            Administrador administrador = adminDAO.getAdministradorByEmail(email);
            if (administrador != null) {
                new AdminFrame(administrador.getRegistroUsuario(), usuarioDAO);
                dispose();
            } else {
                exibirMensagemErro("Erro ao obter informações do administrador. Tente novamente.");
            }
        } else if ("Comum".equals(userType)) {
            Usuario usuario = usuarioDAO.getUsuarioByEmail(email);
            if (usuario != null) {
                new UserFrame(usuario, livroDAO);
                dispose();
            } else {
                exibirMensagemErro("Erro ao obter informações do usuário. Tente novamente.");
            }
        }
    }

    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(LoginFrame.this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        IAdminDAO adminDAO = new AdminDAO();
        AdminService adminService = new AdminService(adminDAO);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LivroDAO livroDAO = new LivroDAO();

        new LoginFrame(adminService, livroDAO, usuarioDAO, adminDAO);
    }

}
