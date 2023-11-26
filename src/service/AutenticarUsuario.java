
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dados.Usuario;
import util.SessaoUsuario;

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

    public boolean autenticarUsuario(String email) {
    String query = "SELECT * FROM usuarios WHERE email = ? ";
    try (PreparedStatement statement = conexao.prepareStatement(query)) {
        statement.setString(1, email);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Usuario usuarioAutenticado = obterUsuarioPorEmail(email);
                SessaoUsuario.setUsuarioLogado(usuarioAutenticado);
                return true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return false;
}

private Usuario obterUsuarioPorEmail(String email) {
    String query = "SELECT * FROM usuarios WHERE email = ?";
    
    try (PreparedStatement statement = conexao.prepareStatement(query)) {
        statement.setString(1, email);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                int idade = resultSet.getInt("idade");
                String sexo = resultSet.getString("sexo");

                return new Usuario(nome, email, senha, telefone, idade, sexo, id);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

}
