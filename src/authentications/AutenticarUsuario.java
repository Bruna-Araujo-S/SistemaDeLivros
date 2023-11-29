
package authentications;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

import enums.NivelAcesso;
import models.Administrador;
import models.Usuario;
import util.SessaoUsuario;

public class AutenticarUsuario {
    private Connection conexao;

    public AutenticarUsuario(Connection conexao) {
        this.conexao = conexao;
    }

    public AutenticarUsuario() {
    }

    public void estabelecerConexao(String url, String usuario, String senhaDB) {
        try {
            this.conexao = DriverManager.getConnection(url, usuario, senhaDB);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
        } catch (SQLException e) {
            Logger.getLogger(AutenticarUsuario.class.getName()).log(Level.SEVERE,
                    "Erro ao estabelecer conexão com o banco de dados", e);
            e.printStackTrace();
        }
    }

    public boolean autenticarAdministrador(String email, String senha) {
        String query = "SELECT * FROM administrador WHERE email = ?";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setString(1, email.toLowerCase().trim());
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedSenha = resultSet.getString("senha");
                    if (BCrypt.checkpw(senha, hashedSenha)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro SQL: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Código do erro: " + e.getErrorCode());
            e.printStackTrace();
        }
    
        return false;
    }
    

    
    
    public boolean autenticarUsuario(String email, String senha) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        try (PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setString(1, email.toLowerCase().trim());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDB = resultSet.getString("senha");
                    if (BCrypt.checkpw(senha, hashedPasswordFromDB)) {
                        Usuario usuarioAutenticado = obterUsuarioPorEmail(email);
                        SessaoUsuario.setUsuarioLogado(usuarioAutenticado);
                        return true;
                    }
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

    private Administrador obterAdminPorEmail(String email) {
    String query = "SELECT * FROM administrador WHERE email = ?";

    try (PreparedStatement statement = conexao.prepareStatement(query)) {
        statement.setString(1, email);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                String senha = resultSet.getString("senha");
                String nivelAcessoStr = resultSet.getString("nivel_acesso");

                NivelAcesso nivelAcesso = NivelAcesso.valueOf(nivelAcessoStr);

                Administrador administrador = new Administrador(nome, "", idade, "", email, senha, nivelAcesso);
                administrador.setId(id);

                return administrador;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}
