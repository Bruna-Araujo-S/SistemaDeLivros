package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.services.AdminDAO;
import enums.NivelAcesso;
import interfaces.gerenciamento.IAdminDAO;
import models.Administrador;

public class CadastroAdmin extends JFrame {

    private JTextField nomeField, emailField, idadeField;
    private JPasswordField senhaField;
    private IAdminDAO adminDAO;

    public CadastroAdmin() {
        this.adminDAO = new AdminDAO();
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

        Administrador administrador = new Administrador(nome, "", idade, "", email, senha, NivelAcesso.ADMIN);

        if (adminDAO.salvarNoBancoDeDados(administrador, senha)) {
            JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar administrador.");
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
