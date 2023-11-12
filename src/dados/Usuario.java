package dados;

import java.util.HashSet;
import java.util.Set;

public class Usuario {

    private String nome, telefone, sexo;
    private int idade;
    private static int proximoIdUsuario = 1;
    private final int idUsuario;
    private Set<String> tiposLivrosPreferidos;

    public Usuario(String nome, String telefone, int idade, String sexo) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.idUsuario = proximoIdUsuario++;
        this.tiposLivrosPreferidos = new HashSet<>();
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

    public int getIdUsuario() {
        return idUsuario;
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
                ", idUsuario=" + idUsuario +
                ", tiposLivrosPreferidos=" + tiposLivrosPreferidos +
                '}';
    }
}
