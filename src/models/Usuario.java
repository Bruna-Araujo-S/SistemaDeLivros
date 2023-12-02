package models;

public class Usuario {

    private String nome, telefone, sexo;
    private int idade;
    private String senha;
    private String email;
    private static int proximoId = 1;
    private int id;
    private String nivelAcesso;

    public Usuario(String nome, String email, String senha, String telefone, int idade, String sexo,
            String nivelAcesso) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.senha = senha;
        this.email = email;
        this.id = proximoId++;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(String nivelAcesso, String nome, String email, String senha, String telefone, int idade, String sexo,
            int id) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.senha = senha;
        this.email = email;
        this.id = id;
        this.nivelAcesso = nivelAcesso;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

}
