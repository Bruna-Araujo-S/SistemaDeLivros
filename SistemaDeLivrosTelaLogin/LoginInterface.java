import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class LoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginInterface() {
        setTitle("Login - Sistema de Livros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("background.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Nome de Usuario:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        usernameField.setBorder(createTextFieldBorder());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        passwordField.setBorder(createTextFieldBorder());
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(passwordField, gbc);

        // Painel para caixas de login e senha
        JPanel loginFieldsPanel = new JPanel(new GridBagLayout());
        loginFieldsPanel.setOpaque(false); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginFieldsPanel.add(contentPanel, gbc);

        loginButton = new JButton("Entrar");
        loginButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginFieldsPanel.add(loginButton, gbc);

        JLabel siteNameLabel = new JLabel("Sistema de Livros");
        siteNameLabel.setHorizontalAlignment(JLabel.CENTER);
        siteNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        siteNameLabel.setForeground(Color.WHITE);

        contentPanel.setOpaque(false);

        backgroundPanel.add(loginFieldsPanel, BorderLayout.CENTER);
        backgroundPanel.add(siteNameLabel, BorderLayout.SOUTH);

        setContentPane(backgroundPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
            }
        });
    }

    private Border createTextFieldBorder() {
        return new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
                g2d.dispose();
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginInterface loginInterface = new LoginInterface();
            loginInterface.setVisible(true);
        });
    }
}
