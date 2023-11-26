package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dados.Administrador;
import dados.Enum.NivelAcesso;

public class CadastroAdmin extends JFrame {

    private JTextField nomeField, emailField, idadeField;
    private JPasswordField senhaField;

    public CadastroAdmin() {
        setTitle("Cadastro de Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nomeField = new JTextField(20);
        nomeField.setBounds(100, 20, 165, 25);
        panel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        JLabel idadeLabel = new JLabel("Idade:");
        idadeLabel.setBounds(10, 80, 80, 25);
        panel.add(idadeLabel);

        idadeField = new JTextField(20);
        idadeField.setBounds(100, 80, 165, 25);
        panel.add(idadeField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 110, 80, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(100, 110, 165, 25);
        panel.add(senhaField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(10, 160, 120, 25);
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAdministrador();
            }
        });
    }

    private void cadastrarAdministrador() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        int idade = Integer.parseInt(idadeField.getText());
        char[] senhaChars = senhaField.getPassword();
        String senha = new String(senhaChars);


        Administrador administrador = new Administrador(nome, "", idade, "", email, "", NivelAcesso.ADMIN);

        if (salvarNoBancoDeDados(administrador, senha)) {
            JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar administrador.");
        }
    }

    private boolean salvarNoBancoDeDados(Administrador administrador, String senha) {
        String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
        String usuario = "root";
        String senhaDB = "user";

        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB)) {
            String query = "INSERT INTO administrador (nome, email, idade, senha) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, administrador.getNome());
                preparedStatement.setString(2, administrador.getEmail());
                preparedStatement.setInt(3, administrador.getIdade());
                preparedStatement.setString(4, senha);
                
                int rowsAffected = preparedStatement.executeUpdate();
                

if (rowsAffected > 0) {
    System.out.println("Administrador inserido com sucesso!");
    return true;
} else {
    System.out.println("Nenhuma linha afetada. Falha ao inserir o administrador.");
    return false;
}

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroAdmin();
            }
        });
    }
}
