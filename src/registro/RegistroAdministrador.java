package registro;

import java.util.ArrayList;
import java.util.List;

import dados.Administrador;

public class RegistroAdministrador {

    private List<Administrador> administradores;

    public RegistroAdministrador() {
        administradores = new ArrayList<>();
    }

    public void adicionarAdministrador(Administrador administrador, String senha) {
        administrador.definirSenha(senha);
        administradores.add(administrador);
        System.out.println("Administrador adicionado com sucesso!");
    }

    public Administrador getAdministrador(int indice) {
        if (indice >= 0 && indice < administradores.size()) {
            return administradores.get(indice);
        } else {
            System.out.println("Índice inválido. Não foi possível obter o administrador.");
            return null;
        }
    }

    public Administrador getAdministradorPorNome(String nome) {
        return administradores.stream()
                .filter(admin -> admin.getNome().equals(nome))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Administrador com o nome " + nome + " não encontrado.");
                    return null;
                });
    }

    public boolean removerAdministrador(Administrador administrador) {
        if (administradores.remove(administrador)) {
            System.out.println("Administrador removido com sucesso!");
            return true;
        } else {
            System.out.println("Administrador não encontrado. Não foi possível remover.");
            return false;
        }
    }

    public void removerAdministrador(int indice) {
        if (indice >= 0 && indice < administradores.size()) {
            administradores.remove(indice);
            System.out.println("Administrador removido com sucesso!");
        } else {
            System.out.println("Índice inválido. Não foi possível remover o administrador.");
        }
    }

    public int size() {
        return administradores.size();
    }

    public void editarAdministrador(int indice, Administrador novoAdministrador) {
        if (indice >= 0 && indice < administradores.size()) {
            administradores.set(indice, novoAdministrador);
            System.out.println("Administrador editado com sucesso!");
        } else {
            System.out.println("Índice inválido. Não foi possível editar o administrador.");
        }
    }

    public List<Administrador> getAdministradores() {
        return new ArrayList<>(administradores);
    }
}
