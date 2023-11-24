package dados;

import dados.Enum.NivelAcesso;
import registro.RegistroUsuario;

public class Administrador extends Usuario {

    private int id;
    private String email;
    private String senha;
    private NivelAcesso nivelAcesso;
    private RegistroUsuario registroUsuario;

    public Administrador(String nome, String telefone, int idade, String sexo, String email, String senha, NivelAcesso nivelAcesso) {
        super(nome, telefone, idade, sexo);
        this.email = "";
        this.senha = "";
        this.nivelAcesso = nivelAcesso;
        this.registroUsuario = new RegistroUsuario();
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return this.email.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
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

    public void editarUsuario(int codigoUsuario, String novoNome, int novaIdade) {
        Usuario usuario = registroUsuario.getUsuario(codigoUsuario);
        if (usuario != null) {
            usuario.setNome(novoNome);
            usuario.setIdade(novaIdade);
            System.out.println("\nUsuário editado com sucesso!");
            System.out.println("Informações Atualizadas do Usuário:");
            System.out.printf("Código: %s\nNome: %s\nTelefone: %s\nIdade: %s\nSexo: %s\n",
                    usuario.getId(), usuario.getNome(), usuario.getTelefone(), usuario.getIdade(), usuario.getSexo());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void excluirUsuario(int Id, RegistroUsuario registroUsuario) {
        Usuario usuario = registroUsuario.getUsuario(Id);
    
        if (usuario != null) {
            registroUsuario.removerUsuarioPorId(usuario.getId());
            System.out.println("Usuário excluído com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
        System.out.println("Você não tem permissão para excluir usuários.");
    }
    
        public RegistroUsuario getRegistroUsuario() {
            return this.registroUsuario;
        }
        
    }
    

