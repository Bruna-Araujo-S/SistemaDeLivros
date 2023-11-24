package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dados.Usuario;
import registro.RegistroUsuario;

public class AdminFrame extends JFrame {

    private RegistroUsuario registroUsuario;

    public AdminFrame(RegistroUsuario registroUsuario) {
        this.registroUsuario = registroUsuario;

        setTitle("Admin Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addUserButton = new JButton("Adicionar Usuário");
        JButton editUserButton = new JButton("Editar Usuário");
        JButton removeUserButton = new JButton("Remover Usuário");

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
                excluirUsuario();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(addUserButton);
        add(editUserButton);
        add(removeUserButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarUsuario() {
        String nome = JOptionPane.showInputDialog("Nome do usuário:");
        String telefone = JOptionPane.showInputDialog("Telefone do usuário:");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade do usuário:"));
        String sexo = JOptionPane.showInputDialog("Sexo do usuário:");

        Usuario novoUsuario = new Usuario(nome, telefone, idade, sexo);
        registroUsuario.adicionarUsuario(novoUsuario);
        JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!");
    }

    private void editarUsuario() {
        int codigoUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do usuário:"));

        Usuario usuario = registroUsuario.getUsuario(codigoUsuario);
        if (usuario != null) {
            String novoNome = JOptionPane.showInputDialog("Novo nome do usuário:");
            String novoTelefone = JOptionPane.showInputDialog("Novo telefone do usuário:");
            int novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Nova idade do usuário:"));
            String novoSexo = JOptionPane.showInputDialog("Novo sexo do usuário:");

            usuario.setNome(novoNome);
            usuario.setTelefone(novoTelefone);
            usuario.setIdade(novaIdade);
            usuario.setSexo(novoSexo);

            JOptionPane.showMessageDialog(this, "Usuário editado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
        }
    }

    private void excluirUsuario() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do usuário:"));

        Usuario usuario = registroUsuario.getUsuario(id);
        if (usuario != null) {
            registroUsuario.removerUsuarioPorId(id);
            JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
        }
    }
}
