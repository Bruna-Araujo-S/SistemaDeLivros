package registro;

import java.util.ArrayList;
import java.util.List;

import dados.Administrador;
import util.ValidacaoCadastro;

public class RegistroAdministrador {

    private List<Administrador> administradores;

    public RegistroAdministrador() {
        administradores = new ArrayList<>();
    }

     public void adicionarAdministrador(Administrador administrador, String senha) {
        if (!ValidacaoCadastro.validarEmail(administrador.getEmail())) {
            System.out.println(ValidacaoCadastro.obterMensagemEmailInvalido());
            return;
        }
        if (!ValidacaoCadastro.validarNomeUsuario(administrador.getNome())) {
            System.out.println(ValidacaoCadastro.obterMensagemNomeUsuarioInvalido());
            return;
        }
        if (!ValidacaoCadastro.validarIdade(administrador.getIdade())) {
            System.out.println(ValidacaoCadastro.obterMensagemIdadeInvalida());
            return;
        }
        if (!ValidacaoCadastro.validarSexo(administrador.getSexo())) {
            System.out.println(ValidacaoCadastro.obterMensagemSexoInvalido());
            return;
        }
        if (!ValidacaoCadastro.validarSenha(administrador.getSenha())) {
            System.out.println(ValidacaoCadastro.obterMensagemSenhaInvalida());
            return;
        }
        administrador.definirSenha(senha);
        administradores.add(administrador);
        System.out.println("Administrador adicionado com sucesso!");
    }


    public void editarAdministrador(int indice, Administrador novoAdministrador, String novaSenha) {
        if (indice >= 0 && indice < administradores.size()) {
            if (!ValidacaoCadastro.validarEmail(novoAdministrador.getEmail())) {
                System.out.println(ValidacaoCadastro.obterMensagemEmailInvalido());
                return;
            }
            if (!ValidacaoCadastro.validarNomeUsuario(novoAdministrador.getNome())) {
                System.out.println(ValidacaoCadastro.obterMensagemNomeUsuarioInvalido());
                return;
            }
            if (!ValidacaoCadastro.validarIdade(novoAdministrador.getIdade())) {
                System.out.println(ValidacaoCadastro.obterMensagemIdadeInvalida());
                return;
            }
            if (!ValidacaoCadastro.validarSexo(novoAdministrador.getSexo())) {
                System.out.println(ValidacaoCadastro.obterMensagemSexoInvalido());
                return;
            }
                if (novaSenha != null && !ValidacaoCadastro.validarSenha(novaSenha)) {
                System.out.println(ValidacaoCadastro.obterMensagemSenhaInvalida());
                return;
            }
                administradores.set(indice, novoAdministrador);
            if (novaSenha != null) {
                novoAdministrador.definirSenha(novaSenha);
            }
            System.out.println("Administrador editado com sucesso!");
        } else {
            System.out.println("Índice inválido. Não foi possível editar o administrador.");
        }
    }
    
    public Administrador getAdministradorByEmail(String email) {
        for (Administrador admin : administradores) {
            System.out.println("Lista de Administradores: " + administradores);
            if (admin.getEmail().equals(email)) {
                return admin;
            }

        }
        return null;
    }

    public boolean removerAdministradorPorId(int id) {
        for (Administrador admin : administradores) {
            if (admin.getId() == id) {
                administradores.remove(admin);
                System.out.println("Administrador removido com sucesso!");
                return true;
            }
        }
        System.out.println("Administrador com ID " + id + " não encontrado. Não foi possível remover.");
        return false;
    }
    
}
