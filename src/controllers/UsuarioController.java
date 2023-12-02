package controllers;

import org.mindrot.jbcrypt.BCrypt;

import interfaces.gerenciamento.IUsuarioDAO;
import models.Usuario;
import services.UsuarioService;
import validations.ValidarCadastroUsuario;

public class UsuarioController {

    private UsuarioService usuarioService;
    private IUsuarioDAO usuarioDAO;

    public UsuarioController(UsuarioService usuarioService, IUsuarioDAO usuarioDAO) {
        this.usuarioService = usuarioService;
        this.usuarioDAO = usuarioDAO;
    }

    public boolean cadastrarUsuario(String nome, String telefone, int idade, String sexo, String email, String senha,
            String nivelAcesso) {
        ValidarCadastroUsuario validador = new ValidarCadastroUsuario();

        if (validador.validarNome(nome) && validador.validarTelefone(telefone) &&
                validador.validarEmail(email) && validador.validarSenha(senha)) {

            String hashedSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
            Usuario novoUsuario = new Usuario(nome, email, hashedSenha, telefone, idade, sexo, nivelAcesso);

            if (usuarioService.cadastrarUsuario(novoUsuario)) {
                return true;
            }
        }
        return false;
    }

    public boolean editarUsuario(int id, String novoNome, String novoTelefone, int novaIdade, String novoSexo) {
        ValidarCadastroUsuario validador = new ValidarCadastroUsuario();

        if (validador.validarNome(novoNome) && validador.validarTelefone(novoTelefone) &&
                validador.validarIdade(novaIdade)) {
            return usuarioService.editarUsuario(id, novoNome, novoTelefone, novaIdade, novoSexo);
        }
        return false;
    }

    public boolean removerUsuario(int id) {
        return usuarioService.removerUsuario(id);
    }
}
