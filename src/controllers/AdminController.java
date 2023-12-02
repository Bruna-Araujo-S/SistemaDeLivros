package controllers;

import org.mindrot.jbcrypt.BCrypt;

import interfaces.gerenciamento.IAdminDAO;
import models.Administrador;

public class AdminController {

    private IAdminDAO adminDAO;

    public AdminController(IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public boolean cadastrarAdministrador(String nome, String email, int idade, char[] senhaChars, String nivelAcesso) {
        String senha = new String(senhaChars);

        String hashedSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
        Administrador administrador = new Administrador(nome, "", idade, "", email, hashedSenha, nivelAcesso);

        return adminDAO.salvarNoBancoDeDados(administrador, hashedSenha);
    }

}
