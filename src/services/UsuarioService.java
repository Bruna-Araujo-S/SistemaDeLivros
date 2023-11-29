package services;

import java.util.ArrayList;
import java.util.List;

import interfaces.gerenciamento.IUsuarioDAO;
import models.Usuario;
import models.interfaces.IUsuarioService;

public class UsuarioService implements IUsuarioService {

    private List<Usuario> usuarios;
    private IUsuarioDAO usuarioDAO;

    public UsuarioService(IUsuarioDAO usuarioDAO) {
        usuarios = new ArrayList<>();
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario[] getTodosUsuarios() {
        return usuarios.toArray(new Usuario[0]);
    }

    public boolean cadastrarUsuario(Usuario novoUsuario) {
        if (usuarioDAO != null) {
            usuarios.add(novoUsuario);
            usuarioDAO.adicionarUsuarioNoBanco(novoUsuario);
            System.out.println("Usuário adicionado com sucesso!");
            return true;
        } else {
            System.out.println("Erro: IUsuarioDAO não inicializado corretamente.");
            return false;
        }
    }

    public boolean editarUsuario(int id, String novoNome, String novoTelefone, int novaIdade, String novoSexo) {
        Usuario usuario = usuarioDAO.getUsuarioById(id);
        if (usuario != null) {
            usuario.setNome(novoNome);
            usuario.setTelefone(novoTelefone);
            usuario.setIdade(novaIdade);
            usuario.setSexo(novoSexo);

            usuarioDAO.editarUsuarioNoBanco(id, novoNome, novoTelefone, novaIdade, novoSexo);

            return true;
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }

    public boolean removerUsuario(int id) {
        Usuario usuario = usuarioDAO.getUsuarioById(id);

        if (usuario != null) {
            usuarios.remove(usuario);

            usuarioDAO.removerUsuarioNoBanco(id);

            return true;
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }

}
