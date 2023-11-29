package dao.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.GeneroLivro;
import interfaces.gerenciamento.ILivroDAO;
import models.Livro;
import models.Usuario;

public class LivroDAO implements ILivroDAO {

    private String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
    private String usuario = "root";
    private String senhaDB = "user";
    

    @Override
    public void salvarLivroNoBanco(Livro livro) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO livros (titulo, autor, generoLivro, valor, nota, UsuarioAvaliadoId, numeroAvaliacoes) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getGenero().toString());
            pstmt.setDouble(4, livro.getValor());
            pstmt.setInt(5, livro.getNota());
            pstmt.setInt(6, livro.getUsuarioAvaliadorID());
            pstmt.setInt(7, 0);

            pstmt.executeUpdate();
            System.out.println("Livro salvo no banco com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calcularMediaAvaliacoes(Livro livro) {
        int somaNotas = 0;
        int numeroAvaliacoes = 0;
    
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
             PreparedStatement pstmt = connection.prepareStatement("SELECT nota FROM livros WHERE UsuarioAvaliadoId = ?")) {
    
            pstmt.setInt(1, livro.getUsuarioAvaliadorID());
    
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    somaNotas += resultSet.getInt("nota");
                    numeroAvaliacoes++;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return numeroAvaliacoes > 0 ? (double) somaNotas / numeroAvaliacoes : 0.0;
    }
    

    @Override
    public void atualizarNotaLivroNoBanco(Livro livro) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
            PreparedStatement pstmt = connection.prepareStatement("UPDATE livros SET nota = ?, numeroAvaliacoes = ?, mediaAvaliacoes = ? WHERE id = ?")) {
    
            pstmt.setInt(1, livro.getNota());
            pstmt.setInt(2, livro.getNumeroAvaliacoes());
            
            double novaMedia = calcularMediaAvaliacoes(livro);
            pstmt.setDouble(3, novaMedia);
    
            pstmt.setInt(4, livro.getId());
    
            pstmt.executeUpdate();
            System.out.println("Nota, número de avaliações e média de avaliações do livro atualizados no banco com sucesso!");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Livro> getLivrosDoBanco() {
        List<Livro> livrosDoBanco = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM livros");
        ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String genero = resultSet.getString("generoLivro");
                double valor = resultSet.getDouble("valor");
                int nota = resultSet.getInt("nota");
                int numeroAvaliacoes = resultSet.getInt("numeroAvaliacoes");
                double mediaAvaliacoes = resultSet.getDouble("mediaAvaliacoes");
                int usuarioAvaliadorID = resultSet.getInt("UsuarioAvaliadoId");

                Livro livro = new Livro(titulo, autor, GeneroLivro.valueOf(genero), valor, nota, usuarioAvaliadorID);
                livro.setId(id);
                livro.setNumeroAvaliacoes(numeroAvaliacoes);
                livro.setMediaAvaliacoes(mediaAvaliacoes);

                livrosDoBanco.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livrosDoBanco;
    }

    @Override
    public Usuario getIdUsuarioAvaliador(String email) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM usuarios WHERE email = ?")) {
    
            pstmt.setString(1, email);
    
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String emailUsuario = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    String telefone = resultSet.getString("telefone");
                    int idade = resultSet.getInt("idade");
                    String sexo = resultSet.getString("sexo");
    
                    return new Usuario(nome, emailUsuario, senha, telefone, idade, sexo, id);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }

}
