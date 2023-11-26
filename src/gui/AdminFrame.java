package gui;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.mindrot.jbcrypt.BCrypt;

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
                removerUsuario();
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
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 3));
    
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
    
        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();
    
        JLabel idadeLabel = new JLabel("Idade:");
        JTextField idadeField = new JTextField();
    
        JLabel sexoLabel = new JLabel("Sexo:");
        JTextField sexoField = new JTextField();
    
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
    
        JLabel senhaLabel = new JLabel("Senha:");
        JTextField senhaField = new JPasswordField();
        JCheckBox mostrarSenhaCheckBox = new JCheckBox("Mostrar Senha");
    
        JButton cadastrarButton = new JButton("Cadastrar");

        mostrarSenhaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JPasswordField) senhaField).setEchoChar(mostrarSenhaCheckBox.isSelected() ? '\0' : '*');
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                int idade = Integer.parseInt(idadeField.getText());
                String sexo = sexoField.getText();
                String email = emailField.getText();
                  String senha = senhaField.getText();
                String hashedSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
    
                Usuario novoUsuario = new Usuario(nome, email, hashedSenha, telefone, idade, sexo);
                if (registroUsuario.adicionarUsuario(novoUsuario)) {
                    registroUsuario.adicionarUsuarioNoBanco(novoUsuario);
                    JOptionPane.showMessageDialog(null, "Usuário adicionado e salvo no banco de dados com sucesso!");
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
        panel.add(sexoField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(mostrarSenhaCheckBox);
        panel.add(new JLabel());
        panel.add(cadastrarButton);
    
        JOptionPane.showOptionDialog(
            null,
            panel,
            "Cadastro de Usuário",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            new Object[]{},
            null
        );
    }
    
    private void editarUsuario() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
    
        int userId = Integer.parseInt(JOptionPane.showInputDialog("ID do usuário a ser editado:"));
        Usuario usuario = registroUsuario.getUsuarioById(userId);
    
        if (usuario != null) {
            JLabel novoNomeLabel = new JLabel("Novo nome:");
            JTextField novoNomeField = new JTextField(usuario.getNome());
    
            JLabel novoTelefoneLabel = new JLabel("Novo telefone:");
            JTextField novoTelefoneField = new JTextField(usuario.getTelefone());
    
            JLabel novaIdadeLabel = new JLabel("Nova idade:");
            JTextField novaIdadeField = new JTextField(String.valueOf(usuario.getIdade()));
    
            JLabel novoSexoLabel = new JLabel("Novo sexo:");
            JTextField novoSexoField = new JTextField(usuario.getSexo());
    
            JLabel novaSenhaLabel = new JLabel("Nova senha:");
            JTextField novaSenhaField = new JTextField(usuario.getSenha());
    
            JButton editarButton = new JButton("Editar");
    
            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String novoNome = novoNomeField.getText();
                    String novoTelefone = novoTelefoneField.getText();
                    int novaIdade = Integer.parseInt(novaIdadeField.getText());
                    String novoSexo = novoSexoField.getText();
                    String novaSenha = novaSenhaField.getText();
                    String hashedNovaSenha = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
    
                    if (registroUsuario.editarUsuario(usuario.getId(), novoNome, novoTelefone, novaIdade, novoSexo, hashedNovaSenha)) {
                        registroUsuario.editarUsuarioNoBanco(usuario.getId(), novoNome, novoTelefone, novaIdade, novoSexo, hashedNovaSenha);
                        JOptionPane.showMessageDialog(null, "Usuário editado e atualizado no banco de dados com sucesso!");
                        ((Window) SwingUtilities.getWindowAncestor(panel)).dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao editar usuário. Verifique os dados fornecidos.");
                    }
                }
            });
    
            panel.add(novoNomeLabel);
            panel.add(novoNomeField);
            panel.add(novoTelefoneLabel);
            panel.add(novoTelefoneField);
            panel.add(novaIdadeLabel);
            panel.add(novaIdadeField);
            panel.add(novoSexoLabel);
            panel.add(novoSexoField);
            panel.add(novaSenhaLabel);
            panel.add(novaSenhaField);
            panel.add(new JLabel());
            panel.add(editarButton);
    
            JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Edição de Usuário",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{},
                    null
            );
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    
private void removerUsuario() {
    int userId = Integer.parseInt(JOptionPane.showInputDialog("ID do usuário a ser removido:"));
    
    if (registroUsuario.removerUsuarioPorId(userId)) {
        System.out.println("Usuário removido com sucesso!");
    } else {
        System.out.println("Falha ao remover usuário. Verifique o ID fornecido.");
    }
}

}


