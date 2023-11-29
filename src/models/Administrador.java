package models;

import org.mindrot.jbcrypt.BCrypt;

import dao.services.UsuarioDAO;
import enums.NivelAcesso;
import interfaces.gerenciamento.IUsuarioDAO;
import services.UsuarioService;

public class Administrador extends Usuario {

    private int id;
    private String email;
    private String senha;
    private NivelAcesso nivelAcesso;
    private UsuarioService usuarioService;
    private IUsuarioDAO usuarioDAO;

    public Administrador(String nome, String telefone, int idade, String sexo, String email, String senha,
        NivelAcesso nivelAcesso) {
    super(nome, telefone, email, senha, idade, sexo);
    this.email = email;
    this.senha = senha;
    this.nivelAcesso = nivelAcesso;
    this.usuarioDAO = new UsuarioDAO();
    this.usuarioService = new UsuarioService(usuarioDAO);
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso ADMIN) {
        this.nivelAcesso = ADMIN;
    }

    public void definirSenha(String senha) {
        this.senha = senha;
        System.out.println("Senha definida com sucesso!");
    }

    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }

    public UsuarioService getRegistroUsuario() {
        return this.usuarioService;
    }

}
