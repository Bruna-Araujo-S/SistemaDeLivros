import javax.swing.*;


public class SistemaDeLivros {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new LoginInterface();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
