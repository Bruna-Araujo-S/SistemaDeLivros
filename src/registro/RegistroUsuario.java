package registro;

import java.util.ArrayList;
import java.util.List;

import dados.Usuario;

public class RegistroUsuario {

    private List<Usuario> usuarios;

    public RegistroUsuario() {
        usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    public Usuario getUsuario(int indice) {
        return (indice >= 0 && indice < usuarios.size()) ? usuarios.get(indice) : null;
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void removerUsuario(int indice) {
        if (indice >= 0 && indice < usuarios.size()) {
            usuarios.remove(indice);
        }
    }

    public int size() {
        return usuarios.size();
    }

    public boolean editarUsuario(int indice, String novoNome, String novoTelefone, int novaIdade, String novoSexo) {
        if (indice >= 0 && indice < usuarios.size()) {
            Usuario usuario = usuarios.get(indice);
            if (usuario != null) {
                usuario.setNome(novoNome);
                usuario.setTelefone(novoTelefone);
                usuario.setIdade(novaIdade);
                usuario.setSexo(novoSexo);
                return true;
            }
        }
        return false;
    }

    public void excluirUsuario(int indice) {
        if (indice >= 0 && indice < usuarios.size()) {
            usuarios.remove(indice);
        }
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}
