package dao.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.gerenciamento.IUsuarioDAO;
import jdbc.ConnectionSQL;
import models.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

    String url = "jdbc:mysql://localhost:3306/Sistema_de_Livro";
    String usuario = "root";
    String senhaDB = "root";

    @Override
    public Usuario getUsuarioByEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ? ";
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    int idade = resultSet.getInt("idade");
                    String sexo = resultSet.getString("sexo");
                    String senha = resultSet.getString("senha");
                    String telefone = resultSet.getString("telefone");

                    Usuario useUsuario = new Usuario(nome, email, senha, telefone, idade, sexo);
                    useUsuario.setId(id);
                    return useUsuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario getUsuarioById(int id) {
        try {
            ConnectionSQL connectionSQL = new ConnectionSQL();
            connectionSQL.OpenDatabase();

            String query = "SELECT * FROM usuarios WHERE id = " + id;
            ResultSet resultSet = connectionSQL.ExecutaQuery(query);

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                int idade = resultSet.getInt("idade");
                String sexo = resultSet.getString("sexo");
                String senha = resultSet.getString("senha");

                Usuario usuario = new Usuario(nome, null, senha, telefone, idade, sexo);
                usuario.setId(id);

                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void adicionarUsuarioNoBanco(Usuario usuario) {
        ConnectionSQL connectionSQL = new ConnectionSQL();
        connectionSQL.OpenDatabase();

        String query = String.format(
                "INSERT INTO usuarios (nome, telefone, idade, sexo, email, senha) VALUES ('%s', '%s', %d, '%s', '%s', '%s')",
                usuario.getNome(), usuario.getTelefone(), usuario.getIdade(), usuario.getSexo(), usuario.getEmail(),
                usuario.getSenha());

        try {
            connectionSQL.ExecutaUpdate(query);
            System.out.println("Usuário adicionado ao banco de dados com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar usuário ao banco de dados: " + e.getMessage());
        } finally {
            connectionSQL.CloseDatabase();
        }
    }

    @Override
    public void editarUsuarioNoBanco(int id, String novoNome, String novoTelefone, int novaIdade, String novoSexo) {
        ConnectionSQL connectionSQL = new ConnectionSQL();
        connectionSQL.OpenDatabase();

        String query = String.format(
                "UPDATE usuarios SET nome='%s', telefone='%s', idade=%d, sexo='%s' WHERE id=%d",
                novoNome, novoTelefone, novaIdade, novoSexo, id);

        try {
            connectionSQL.ExecutaUpdate(query);
            System.out.println("Usuário atualizado no banco de dados com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar usuário no banco de dados: " + e.getMessage());
        } finally {
            connectionSQL.CloseDatabase();
        }
    }

    @Override
    public void removerUsuarioNoBanco(int id) {
        ConnectionSQL connectionSQL = new ConnectionSQL();
        connectionSQL.OpenDatabase();

        String query = "DELETE FROM usuarios WHERE id=" + id;
        try {
            connectionSQL.ExecutaUpdate(query);
            System.out.println("Usuário removido do banco de dados com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao remover usuário do banco de dados: " + e.getMessage());
        } finally {
            connectionSQL.CloseDatabase();
        }
    }
}
