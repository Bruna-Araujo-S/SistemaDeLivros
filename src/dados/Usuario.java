package dados;

import java.util.HashSet;
import java.util.Set;


public class Usuario {

    private String nome, telefone, sexo;
    private int idade;
    private Set<String> tiposLivrosPreferidos;
    private String senha;
    private String email;
    private static int proximoId = 1;
    private int id;

    public Usuario(String nome, String email, String senha, String telefone, int idade, String sexo) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.tiposLivrosPreferidos = new HashSet<>();
        this.senha = senha;
        this.email = email;
        this.id = proximoId++;
    }

    public Usuario(String nome, String email, String senha, String telefone, int idade, String sexo, int id) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.tiposLivrosPreferidos = new HashSet<>();
        this.senha = senha;
        this.email = email;
        this.id = id;
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

    public void setSenha(String senha){
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
 
    
    public void adicionarTipoLivroPreferido(String tipoLivro) {
        this.tiposLivrosPreferidos.add(tipoLivro);
    }

    public void removerTipoLivroPreferido(String tipoLivro) {
        this.tiposLivrosPreferidos.remove(tipoLivro);
    }

    public Set<String> getTiposLivrosPreferidos() {
        return tiposLivrosPreferidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo='" + sexo + '\'' +
                ", idade=" + idade +
                ", tiposLivrosPreferidos=" + tiposLivrosPreferidos +
                '}';
    }

}
