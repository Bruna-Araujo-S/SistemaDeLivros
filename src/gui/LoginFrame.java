package gui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dados.Administrador;
import dados.Usuario;
import registro.RegistroAdministrador;
import registro.RegistroLivro;
import registro.RegistroUsuario;
import service.AutenticarUsuario;

public class LoginFrame extends JFrame {

    private RegistroAdministrador registroAdministrador;
    private RegistroUsuario registroUsuario;
    private RegistroLivro registroLivro;
    private AutenticarUsuario autenticarUsuario;


    public LoginFrame(RegistroAdministrador registroAdministrador, RegistroUsuario registroUsuario, RegistroLivro registroLivro) {
        this.registroAdministrador = registroAdministrador;
        this.registroUsuario = registroUsuario;
        this.registroLivro = registroLivro;
        this.autenticarUsuario = new AutenticarUsuario();
        this.autenticarUsuario.estabelecerConexao("jdbc:mysql://localhost:3306/Sistema_de_Livros", "root", "user");
        
        


        setTitle("Acessar Sistema de Livros");
        setSize(450, 190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon("src/Imagens/Biblioteca.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BorderLayout());

        JTextField emailField = new JTextField(15);
        JPasswordField senhaField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JComboBox<String> userTypeComboBox = new JComboBox<>(new String[]{"Administrador", "Comum"});

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();

                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Email e senha são obrigatórios.");
                    return;
                }

                if ("Administrador".equals(userType)) {
                    if (autenticarUsuario.autenticarAdministrador(email, senha)) {
                        Administrador administrador = registroAdministrador.getAdministradorByEmail(email);
                        if (administrador != null && administrador.verificarSenha(senha)) {
                            new AdminFrame(administrador.getRegistroUsuario());
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(LoginFrame.this, "Credenciais inválidas para Administrador. Tente novamente.");
                        }
                    }
                } else if ("Comum".equals(userType)) {
                    if (autenticarUsuario.autenticarUsuario(email)) {
                        Usuario usuario = registroUsuario.getUsuarioByEmail(email);
                        if (usuario != null && usuario.verificarSenha(senha)) {
                            new UserFrame(usuario, registroLivro);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(LoginFrame.this, "Credenciais inválidas para Usuário. Tente novamente.");
                        }
                    }
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Senha:"));
        inputPanel.add(senhaField);

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

    public static void main(String[] args) {
        RegistroAdministrador registroAdmin = new RegistroAdministrador();
        RegistroUsuario registroUsuario = new RegistroUsuario();
        RegistroLivro registroLivro = new RegistroLivro();

        new LoginFrame(registroAdmin, registroUsuario, registroLivro);
    }
}
