
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutenticarUsuario {
    private Connection conexao;

    public AutenticarUsuario(Connection conexao) {
        this.conexao = conexao;
    }

    public AutenticarUsuario() {
    }

      public void estabelecerConexao(String url, String usuario, String senha) {
        try {
            this.conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
        } catch (SQLException e) {
            Logger.getLogger(AutenticarUsuario.class.getName()).log(Level.SEVERE, "Erro ao estabelecer conexão com o banco de dados", e);
            e.printStackTrace();
        }
    }


    public boolean autenticarAdministrador(String email, String senha) {
        String query = "SELECT * FROM administrador WHERE LOWER(email) = LOWER(?) AND senha = ?";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setString(1, email.toLowerCase());
            statement.setString(2, senha);
    
            System.out.println("Query: " + query);
            System.out.println("Email: " + email);
            System.out.println("Senha: " + senha);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao autenticar administrador: " + e.getMessage());
        }
        return false;
    }
    

    
    

    public boolean autenticarUsuario(String email, String senha) {
        String query = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, senha);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
}
