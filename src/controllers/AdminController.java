package controllers;

import org.mindrot.jbcrypt.BCrypt;

import enums.NivelAcesso;
import interfaces.gerenciamento.IAdminDAO;
import models.Administrador;

public class AdminController {

    private IAdminDAO adminDAO;

    public AdminController(IAdminDAO adminDAO) {
            this.adminDAO = adminDAO;
    }
    
    public boolean cadastrarAdministrador(String nome, String email, int idade, char[] senhaChars) {
        String senha = new String(senhaChars);
    
        String hashedSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
        NivelAcesso nivelAcesso = NivelAcesso.ADMIN;   
        Administrador administrador = new Administrador(nome, "", idade, "", email, hashedSenha, nivelAcesso);
    
        return adminDAO.salvarNoBancoDeDados(administrador, hashedSenha);
    }
    
}
