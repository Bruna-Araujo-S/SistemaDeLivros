import javax.swing.*;
import java.awt.*;


//Implementado
public class SistemaDeLivrosSite extends JFrame {
   public SistemaDeLivrosSite() {
 setTitle("Sistema de Livros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setPreferredSize(new Dimension(800, 50));
        JLabel titleLabel = new JLabel("Sistema de Livros");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        JLabel contentLabel = new JLabel("Bem-vindo(a)!");
        contentPanel.add(contentLabel);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.LIGHT_GRAY);
        footerPanel.setPreferredSize(new Dimension(800, 50));
        JLabel footerLabel = new JLabel("© 2023 - Todos os direitos reservados");
        footerPanel.add(footerLabel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaDeLivrosSite ui = new SistemaDeLivrosSite();
            ui.setVisible(true);
        });
    
   }
    
    
}
