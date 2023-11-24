package programa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dados.Administrador;
import dados.Avaliacao;
import dados.Livro;
import dados.Usuario;
import dados.Enum.GeneroLivro;
import jdbc.ConnectionSQL;
import registro.RegistroLivro;
import registro.RegistroUsuario;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static RegistroLivro rL;
    private static RegistroUsuario rU;
    private static ConnectionSQL conexao = new ConnectionSQL();


    
//#region Usuario
	private static void adicionaUsuario() {
		String nome, telefone, sexo;
		int idade;
		Usuario u;
		System.out.println("Adicionar usuário:\n");
		System.out.print("Nome: ");
		nome = sc.nextLine();
		System.out.print("Telefone: ");
		telefone = sc.nextLine();
		System.out.print("Idade: ");
		idade = sc.nextInt();
		sc.nextLine();
		System.out.print("Sexo: ");
		sexo = sc.nextLine();

		u = new Usuario(nome, telefone, idade, sexo);
		rU.adicionarUsuario(u);

        String sql = "INSERT INTO Usuarios (Nome, Telefone, Idade, Sexo) VALUES ('" + nome + "', '" + telefone + "', " + idade + ", '" + sexo + "')";
        conexao.ExecutaQuery(sql);
        System.out.println("Usuário adicionado com sucesso!");
	}
	
    private static void listarTodosUsuarios() {
        System.out.println("=== Lista de Usuários ===");
        System.out.println("Cod\t Nome\t\t\t Telefone");
        for (int i = 0; i < ((List<Livro>) rU).size(); i++) {
            Usuario u = rU.getUsuario(i);
            System.out.printf("%s\t %s\t\t  %s\n",
                    u.getId(), u.getNome(), u.getTelefone());
        }
    }
//#endregion Usuario

//#region Administrador
private static void adicionarAdministrador() {
    String nome, email, senha;
    int idade;
    Administrador admin;

    System.out.println("Adicionar Administrador:\n");
    System.out.print("Nome: ");
    nome = sc.nextLine();
    System.out.print("Idade: ");
    idade = sc.nextInt();
    sc.nextLine();
    System.out.print("Email: ");
    email = sc.nextLine();
    System.out.print("Senha: ");
    senha = sc.nextLine();

    String sql = "INSERT INTO Administrador (Nome, Idade, Email, Senha) VALUES ('" + nome + "', " + idade + ", '" + email + "', '" + senha + "')";

    try {
        conexao.ExecutaUpdate(sql);
        System.out.println("Administrador adicionado com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao adicionar o administrador: " + e.getMessage());
    }
}
//#endregion Administrador
    
    
//#region Livro
private static void adicionaLivro() {
    String titulo, autor;
    double valor;
    GeneroLivro genero;
    Livro l;
    
    System.out.println("Adicionar Livro:\n");
    System.out.print("Titulo: ");
    titulo = sc.nextLine();
    System.out.print("Autor: ");
    autor = sc.nextLine();

    System.out.println("Escolha o gênero do livro:");
    for (GeneroLivro opcao : GeneroLivro.values()) {
        System.out.println(opcao.ordinal() + 1 + ". " + opcao.name());
    }

    int escolha = sc.nextInt();
    sc.nextLine();

    if (escolha > 0 && escolha <= GeneroLivro.values().length) {
        genero = GeneroLivro.values()[escolha - 1];
    } else {
        System.out.println("Opção inválida. Usando 'OUTRO' como padrão.");
        genero = GeneroLivro.OUTRO;
    }

    System.out.print("Valor: ");
    valor = sc.nextDouble();

    l = new Livro(titulo, autor, genero, valor);
    rL.addLivro(l);

    String sql = "INSERT INTO Livros (Titulo, Autor, GeneroLivro, Valor) VALUES ('" + titulo + "', '" + autor + "', '" + genero.name() + "', " + valor + ")";
    conexao.ExecutaQuery(sql);

    System.out.print("Digite a nota (de 0 a 10) que você dá para este livro: ");
    int nota = sc.nextInt();
    sc.nextLine();

    String sqlAvaliacao = "INSERT INTO Avaliacao (Nota, LivroId) VALUES (" + nota + ", " + l.getCodigoDoLivro() + ")";
    conexao.ExecutaQuery(sqlAvaliacao);

    System.out.println("\nLivro adicionado com sucesso!");
    System.out.println("Informações do Livro:");
    System.out.printf("Código: %s\nTitulo: %s\nAutor: %s\nGenero: %s\nValor: %s\n",
            l.getCodigoDoLivro(), l.getTitulo(), l.getAutor(), l.getGenero(), l.getValor());
    exibirInformacoesLivro(l);
}


    private static void exibirInformacoesLivro(Livro livro) {
        System.out.println("Informações do Livro:");
        System.out.printf("Código: %s\nTitulo: %s\nAutor: %s\nTipo: %s\nValor: %s\n",
                livro.getCodigoDoLivro(), livro.getTitulo(), livro.getAutor(), livro.getGenero(), livro.getValor());
    }

    private static void listarLivrosLivres() {
        System.out.println("=== Lista de livros disponíveis ===");
        System.out.println("Nr Registro\t Titulo\t Autor\t Valor");
        for (Livro livro : rL.getLivros()) {
            if (livro.getDataEmprestimo() == null);
            
        }
    }

    private static void listarTodosLivros() {
        System.out.println("=== Lista de livros ===");
        System.out.println("Cod\t Titulo\t\t\t Autor\t\t Valor\t\t Situacao\t\t Usuario");
        for (Livro livro : rL.getLivros()) {
            if (livro.getDataEmprestimo() == null) {
                System.out.printf("%s\t %s\t\t  %s\t\t\t %s\t\t Livro Disponível\n",
                        livro.getCodigoDoLivro(), livro.getTitulo(), livro.getAutor(), livro.getValor());
            } else {
                System.out.printf("%s\t %s\t\t  %s\t\t\t %s\t\t %s\n",
                        livro.getCodigoDoLivro(), livro.getTitulo(), livro.getAutor(), livro.getValor(), livro.getUsuarioComLivro());
            }
        }
    }

	private static void emprestarLivro() {
        System.out.print("Digite o código do livro a ser emprestado: ");
        int codLivro = sc.nextInt();
        sc.nextLine();
    
        listarTodosUsuarios();
        System.out.print("Digite o código do usuário a emprestar o livro: ");
        int idUsuario = sc.nextInt();
        sc.nextLine();
    
        boolean usuarioEncontrado = false;
    
        for (Usuario usuario : rU.getTodosUsuarios()) {
            if (usuario.getId() == idUsuario) {
                usuarioEncontrado = true;
    
                for (Livro livro : rL.getLivros()) {
                    if (livro.getCodigoDoLivro() == codLivro && livro.getDataEmprestimo() == null) {
                        emprestarLivroParaUsuario(livro, usuario);
                        break;
                    }
                }
                break;
            }
        }
    
        if (!usuarioEncontrado) {
            System.out.println("Usuário não encontrado.");
        }
    }
    
    private static void emprestarLivroParaUsuario(Livro livro, Usuario usuario) {
        System.out.print("Digite a nota (de 0 a 10) que você dá para este livro: ");
        int nota = sc.nextInt();
        sc.nextLine();
    
        Avaliacao avaliacao = new Avaliacao(nota, usuario, livro);
        livro.adicionarAvaliacao(avaliacao);
    
        rL.emprestarLivro(livro, usuario);
    
        System.out.println("Livro código " + livro.getCodigoDoLivro() +
                " emprestado com sucesso para o usuário: " + usuario.getNome() + ".");
        System.out.println("Data de empréstimo: " + livro.getDataEmprestimo());
    }
    
    private static void devolverLivro() {
        boolean livroEncontrado = false;
        System.out.print("Digite o código do livro a ser devolvido: ");
        int codLivro = sc.nextInt();
        sc.nextLine();
        
        for (Livro livro : rL.getLivros()) {
            if (livro.getCodigoDoLivro() == codLivro) {
                livro.setDataEmprestimo(null);
                livroEncontrado = true;
                System.out.println("Livro código " + livro.getCodigoDoLivro() + " devolvido com sucesso.");
                break;
            }
        }
        if (!livroEncontrado) {
            System.out.println("Livro não encontrado.");
        }
    }
    

    private static void listarLivrosAZ() {
        rL.ordenarPorTituloAZ();
        listarTodosLivros();
    }
//#endregion Livro


//#region Avaliação
private static void listarAvaliacoesLivro() {
    System.out.print("Digite o código do livro para ver as avaliações: ");
    int codLivro = sc.nextInt();
    sc.nextLine();

    String sql = "SELECT * FROM Avaliacao WHERE LivroId = " + codLivro;
    ResultSet result = conexao.ExecutaQuery(sql);

    if (result != null) {
        try {
            while (result.next()) {
                int nota = result.getInt("Nota");
                System.out.printf("Nota: %d\n", nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Erro ao executar a consulta.");
    }
    // Livro livro = rL.getLivroPorCodigo(codLivro);
    // if (livro != null) {
    //     List<Avaliacao> avaliacoes = livro.getAvaliacoes();
    //
    //     if (avaliacoes.isEmpty()) {
    //         System.out.println("Este livro ainda não possui avaliações.");
    //     } else {
    //         System.out.println("=== Avaliações do Livro ===");
    //         System.out.println("Nota\t Usuário");
    //
    //         for (Avaliacao avaliacao : avaliacoes) {
    //             System.out.printf("%d\t %s\n", avaliacao.getNota(), avaliacao.getUsuario().getNome());
    //         }
    //
    //         double media = livro.calcularMediaAvaliacoes();
    //         System.out.printf("Média de Avaliações: %.2f\n", media);
    //     }
    // } else {
    //     System.out.println("Livro não encontrado.");
    // }
}
//#endregion Avaliação


    public static void main(String[] args) throws SQLException {
        rL = new RegistroLivro();
        rU = new RegistroUsuario();
        conexao.OpenDatabase();

        int op;
        do {
            System.out.println("\n==> Menu:\n");
            System.out.println("   0 - sair");
            System.out.println("   1 - adicionar livro");
            System.out.println("   2 - adicionar usuário");
            System.out.println("   3 - emprestar livro");
            System.out.println("   4 - devolver livro");
            System.out.println("   5 - listar livros por ordem alfabética");
            System.out.println("   6 - listar avaliações de um livro");
            System.out.println("   7 - adicionar administrador");
            System.out.print("\n   Opcao: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    adicionaLivro();
                    break;
                case 2:
                    adicionaUsuario();
                    break;
                case 3:
                    listarLivrosLivres();
                    emprestarLivro();
                    break;
                case 4:
                    devolverLivro();
                    break;
                case 5:
                    listarLivrosAZ();
                    listarTodosLivros();
                    break;
                case 6:
                    listarAvaliacoesLivro();
                    break;
                case 7:
                    adicionarAdministrador();
                    break;
                default:
                    System.out.println("Opcao invalida!\n\n");
                    break;
            }
        } while (op != 0);
                
        conexao.CloseDatabase();
    }
}
