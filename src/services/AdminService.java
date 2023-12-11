package services;

import java.util.ArrayList;
import java.util.List;

import interfaces.gerenciamento.IAdminDAO;
import interfaces.gerenciamento.IAdminService;
import models.Administrador;

public class AdminService implements IAdminService {

    private IAdminDAO adminDAO;

    private List<Administrador> administradores;

    public AdminService(IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
        administradores = new ArrayList<>();
    }

    public boolean adicionarAdministrador(Administrador administrador, String senha) {
        administrador.definirSenha(senha);
        administrador.setNivelAcesso(senha);

        return adminDAO.salvarNoBancoDeDados(administrador, senha);
    }

    public Administrador getAdministradorByEmail(String email) {
        return adminDAO.getAdministradorByEmail(email);
    }

}
