package dados;

import java.util.ArrayList;
import java.util.List;

import dados.Enum.GeneroLivro;
import dados.Enum.Permissao;
import registro.RegistroLivro;
import registro.RegistroUsuario;

public class Administrador extends Usuario {

    private RegistroUsuario registroUsuario;
    private RegistroLivro registroLivro;
    private List<Permissao> permissoes;
    private String senha;

    public Administrador(RegistroUsuario registroUsuario, RegistroLivro registroLivro, String nome, String telefone, int idade, String sexo) {
        super(nome, telefone, idade, sexo);
        this.registroUsuario = registroUsuario;
        this.registroLivro = registroLivro;
        this.permissoes = new ArrayList<>();
        this.senha = "";
    }

    public void adicionarPermissao(Permissao permissao) {
        permissoes.add(permissao);
    }

    public void removerPermissao(Permissao permissao) {
        permissoes.remove(permissao);
    }

    public boolean possuiPermissao(Permissao permissao) {
        return permissoes.contains(permissao);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public RegistroUsuario getRegistroUsuario() {
        return registroUsuario;
    }

    public RegistroLivro getRegistroLivro() {
        return registroLivro;
    }


    public void definirSenha(String senha) {
        this.senha = senha;
        System.out.println("Senha definida com sucesso!");
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }
    
    public void adicionarUsuario(String nome, String telefone, int idade, String sexo) {
        Usuario novoUsuario = new Usuario(nome, telefone, idade, sexo);
        registroUsuario.adicionarUsuario(novoUsuario);
        System.out.println("Novo usuário adicionado com sucesso!");
    }

    public void editarUsuario(int codigoUsuario, String novoNome, String novoTelefone, int novaIdade, String novoSexo) {
        Usuario usuario = registroUsuario.getUsuario(codigoUsuario);
        if (usuario != null) {
            usuario.setNome(novoNome);
            usuario.setTelefone(novoTelefone);
            usuario.setIdade(novaIdade);
            usuario.setSexo(novoSexo);
            System.out.println("\nUsuário editado com sucesso!");
            System.out.println("Informações Atualizadas do Usuário:");
            System.out.printf("Código: %s\nNome: %s\nTelefone: %s\nIdade: %s\nSexo: %s\n",
                    usuario.getIdUsuario(), usuario.getNome(), usuario.getTelefone(), usuario.getIdade(), usuario.getSexo());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void excluirUsuario(int codigoUsuario, Administrador administrador) {
        if (administrador != null && administrador.possuiPermissao(Permissao.EXCLUIR_USUARIO)) {
            Usuario usuario = registroUsuario.getUsuario(codigoUsuario);
            
            if (usuario != null) {
                registroUsuario.removerUsuario(usuario);
                System.out.println("Usuário excluído com sucesso.");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } else {
            System.out.println("Você não tem permissão para excluir usuários.");
        }
    }
    
    public void adicionarLivro(String titulo, String autor, GeneroLivro genero, double valor) {
        Livro novoLivro = new Livro(titulo, autor, genero, valor);
        registroLivro.addLivro(novoLivro);
        System.out.println("Novo livro adicionado com sucesso!");
    }

    public void editarLivro(int codigoLivro, String novoTitulo, String novoAutor, GeneroLivro novoGenero, double novoValor) {
    Livro livro = registroLivro.getLivro(codigoLivro);
    if (livro != null) {
        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);
        livro.setGenero(novoGenero);
        livro.setValor(novoValor);

        System.out.println("\nLivro editado com sucesso!");
        System.out.println("Informações Atualizadas do Livro:");
        System.out.printf("Código: %s\nTitulo: %s\nAutor: %s\nGenero: %s\nValor: %s\n",
                livro.getCodigoDoLivro(), livro.getTitulo(), livro.getAutor(), livro.getGenero(), livro.getValor());
    } else {
        System.out.println("Livro não encontrado.");
    }
}


    public void excluirLivro(int codigoLivro) {
        Livro livro = registroLivro.getLivro(codigoLivro);
        if (livro != null) {
            registroLivro.removerLivro(livro);
            System.out.println("Livro excluído com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    public void adicionarAvaliacao(Livro livro, Usuario usuario, int nota) {
        if (livro != null && usuario != null) {
            Avaliacao novaAvaliacao = new Avaliacao(nota, usuario, livro);
            livro.adicionarAvaliacao(novaAvaliacao);
            System.out.println("\nAvaliação adicionada com sucesso!");
        } else {
            System.out.println("Livro ou usuário não encontrado.");
        }
    }

    public void editarAvaliacao(Avaliacao avaliacao, int novaNota) {
        if (avaliacao != null) {
            avaliacao.setNota(novaNota);
            System.out.println("\nAvaliação editada com sucesso!");
            System.out.println("Informações Atualizadas da Avaliação:");
            System.out.printf("Livro: %s\nUsuário: %s\nNova Nota: %s\n",
                    avaliacao.getLivro().getTitulo(), avaliacao.getUsuario().getNome(), avaliacao.getNota());
        } else {
            System.out.println("Avaliação não encontrada.");
        }
    }

    public void excluirAvaliacao(Avaliacao avaliacao) {
        if (avaliacao != null) {
            avaliacao.getLivro().removerAvaliacao(avaliacao);
            System.out.println("Avaliação excluída com sucesso.");
        } else {
            System.out.println("Avaliação não encontrada.");
        }
    }

    public void listarTodosUsuarios() {
        List<Usuario> usuarios = registroUsuario.getUsuarios();
        if (!usuarios.isEmpty()) {
            System.out.println("\nLista de Todos os Usuários:");
            for (Usuario usuario : usuarios) {
                System.out.printf("Código: %s | Nome: %s | Idade: %s\n", usuario.getIdUsuario(), usuario.getNome(), usuario.getIdade());
            }
        } else {
            System.out.println("\nNenhum usuário cadastrado.");
        }
    }
    
    public void listarTodosLivros() {
        List<Livro> livros = registroLivro.getLivros();
        if (!livros.isEmpty()) {
            System.out.println("\nLista de Todos os Livros:");
            for (Livro livro : livros) {
                System.out.printf("Código: %s | Título: %s | Autor: %s\n", livro.getCodigoDoLivro(), livro.getTitulo(), livro.getAutor());
            }
        } else {
            System.out.println("\nNenhum livro cadastrado.");
        }
    }

    public void listarTodasAvaliacoes() {
        List<Livro> livros = registroLivro.getLivros();
        if (!livros.isEmpty()) {
            System.out.println("\nLista de Todas as Avaliações:");
            for (Livro livro : livros) {
                List<Avaliacao> avaliacoes = livro.getAvaliacoes();
                if (!avaliacoes.isEmpty()) {
                    for (Avaliacao avaliacao : avaliacoes) {
                        System.out.printf("Livro: %s | Usuário: %s | Nota: %s\n",
                                livro.getTitulo(), avaliacao.getUsuario().getNome(), avaliacao.getNota());
                    }
                } else {
                    System.out.printf("Livro: %s | Nenhuma avaliação cadastrada.\n", livro.getTitulo());
                }
            }
        } else {
            System.out.println("\nNenhum livro cadastrado.");
        }
    }
    public void exibirEstatisticasAvaliacoes(Livro livro) {
        if (livro != null) {
            System.out.println("\nEstatísticas de Avaliações para o Livro " + livro.getTitulo() + ":");
            System.out.println("Número total de avaliações: " + livro.getAvaliacoes().size());
            System.out.println("Média de notas: " + livro.calcularMediaAvaliacoes());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
}
