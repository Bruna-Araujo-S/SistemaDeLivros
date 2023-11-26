package registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Administrador;
import dados.Enum.NivelAcesso;

public class RegistroAdministrador {

    private List<Administrador> administradores;

    public RegistroAdministrador() {
        administradores = new ArrayList<>();
    }

    public boolean adicionarAdministrador(Administrador administrador, String senha) {
        administrador.definirSenha(senha);
        administrador.setNivelAcesso(NivelAcesso.ADMIN);
        
        if (administradores.add(administrador)) {
            System.out.println("Administrador adicionado com sucesso!");
            return true;
        } else {
            System.out.println("Falha ao adicionar administrador. Verifique os dados fornecidos.");
            return false;
        }
    }
    
    public Administrador getAdministradorByEmail(String email) {
    String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
    String usuario = "root";
    String senhaDB = "user";

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
