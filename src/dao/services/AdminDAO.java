package dao.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import enums.NivelAcesso;
import interfaces.gerenciamento.IAdminDAO;
import models.Administrador;

public class AdminDAO implements IAdminDAO {

    private String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
    private String usuario = "root";
    private String senhaDB = "user";
    
    @Override
public boolean salvarNoBancoDeDados(Administrador administrador, String senha) {

    String query = "INSERT INTO administrador (nome, email, idade, senha) VALUES (?, ?, ?, ?)";
    try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB)) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, administrador.getNome());
            preparedStatement.setString(2, administrador.getEmail());
            preparedStatement.setInt(3, administrador.getIdade());

            String hashedSenha = BCrypt.hashpw(administrador.getSenha(), BCrypt.gensalt());
            preparedStatement.setString(4, hashedSenha);

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

    @Override
public Administrador getAdministradorByEmail(String email) {

        String query = "SELECT * FROM administrador WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    int idade = resultSet.getInt("idade");
                    String senha = resultSet.getString("senha");
                    NivelAcesso nivelAcesso = NivelAcesso.valueOf(resultSet.getString("nivel_acesso"));

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
