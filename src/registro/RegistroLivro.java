package registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dados.Livro;
import dados.Usuario;
import dados.Enum.GeneroLivro;
import util.SessaoUsuario;

public class RegistroLivro {

    private List<Livro> livros;

    public RegistroLivro() {
        livros = new ArrayList<>();
    }

    
    public List<Livro> getLivrosOrdenadosPorMedia() {
        List<Livro> livrosOrdenados = getLivrosDoBanco();
    
        Collections.sort(livrosOrdenados, Comparator
                .comparingDouble((Livro livro) -> calcularMediaNotas(livro))
                .thenComparingInt((Livro livro) -> getNumeroAvaliacoes(livro))
                .reversed()
                .thenComparing(Livro::getTitulo));
    
        return livrosOrdenados;
    }

    public double calcularMediaNotas(Livro livro) {
        int quantidadeAvaliacoes = getNumeroAvaliacoes(livro);   
        return quantidadeAvaliacoes > 0 ? (double) livro.getNota() / quantidadeAvaliacoes : 0;
    }
    

    public int getNumeroAvaliacoes(Livro livro) {
        return livro.getNota() > 0 ? 1 : 0;
    }    
    

    public boolean cadastrarLivro(Livro livro) {
        if (livro != null) {
            Usuario usuarioLogado = getIdUsuarioAvaliador();
            if (usuarioLogado != null) {
                livro.setUsuarioAvaliadorID(usuarioLogado.getId());
                livros.add(livro);
                System.out.println("Livro cadastrado com sucesso!");
                return true;
            }
        }
        System.out.println("Falha ao cadastrar o livro. Livro não válido ou usuário não encontrado.");
        return false;
    }
    
    public void salvarLivroNoBanco(Livro livro) {
        String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
        String usuario = "root";
        String senhaDB = "user";
    
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

    private Usuario getIdUsuarioAvaliador() {
        Usuario usuarioLogado = SessaoUsuario.getUsuarioLogado();
        
        if (usuarioLogado != null) {
            String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
            String usuarioDB = "root";
            String senhaDB = "user";
    
            try (Connection connection = DriverManager.getConnection(url, usuarioDB, senhaDB);
                 PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM usuarios WHERE email = ?")) {
    
                pstmt.setString(1, usuarioLogado.getEmail());
    
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nome = resultSet.getString("nome");
                        String email = resultSet.getString("email");
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
        }
    
        return null;
    }
    
    public List<Livro> getLivrosDoBanco() {
        List<Livro> livrosDoBanco = new ArrayList<>();
    
        String url = "jdbc:mysql://localhost:3306/Sistema_de_Livros";
        String usuario = "root";
        String senhaDB = "user";
    
        try (Connection connection = DriverManager.getConnection(url, usuario, senhaDB);
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM livros")) {
    
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String titulo = resultSet.getString("titulo");
                    String autor = resultSet.getString("autor");
                    String genero = resultSet.getString("generoLivro");
                    double valor = resultSet.getDouble("valor");
                    int nota = resultSet.getInt("nota");
                    int usuarioAvaliadorID = resultSet.getInt("UsuarioAvaliadoId");
    
                    Livro livro = new Livro(titulo, autor, GeneroLivro.valueOf(genero), valor, nota, usuarioAvaliadorID);
                    livro.setId(id);
    
                    livrosDoBanco.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return livrosDoBanco;
    }
    

}
