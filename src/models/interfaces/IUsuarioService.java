package models.interfaces;

import models.Usuario;

public interface IUsuarioService {

    Usuario[] getTodosUsuarios();

    boolean cadastrarUsuario(Usuario novoUsuario);

    boolean editarUsuario(int id, String novoNome, String novoTelefone, int novaIdade, String novoSexo);

    boolean removerUsuario(int id);
}
