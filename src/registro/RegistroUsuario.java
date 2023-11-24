package registro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dados.Usuario;
import util.ValidacaoCadastro;

public class RegistroUsuario {

    private List<Usuario> usuarios;

    public RegistroUsuario() {
        usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        if (ValidacaoCadastro.validarEmail(usuario.getEmail()) &&
            ValidacaoCadastro.validarNomeUsuario(usuario.getNome()) &&
            ValidacaoCadastro.validarIdade(usuario.getIdade()) &&
            ValidacaoCadastro.validarTelefone(usuario.getTelefone()) &&
            ValidacaoCadastro.validarSexo(usuario.getSexo())) {
            usuarios.add(usuario);
            System.out.println("Usuário adicionado com sucesso!");
        } else {
            if (!ValidacaoCadastro.validarEmail(usuario.getEmail())) {
                System.out.println(ValidacaoCadastro.obterMensagemEmailInvalido());
            }
            if (!ValidacaoCadastro.validarNomeUsuario(usuario.getNome())) {
                System.out.println(ValidacaoCadastro.obterMensagemNomeUsuarioInvalido());
            }
            if (!ValidacaoCadastro.validarIdade(usuario.getIdade())) {
                System.out.println(ValidacaoCadastro.obterMensagemIdadeInvalida());
            }
            if (!ValidacaoCadastro.validarSexo(usuario.getSexo())) {
                System.out.println(ValidacaoCadastro.obterMensagemSexoInvalido());
            }
            if (!ValidacaoCadastro.validarTelefone(usuario.getTelefone())) {
                System.out.println(ValidacaoCadastro.obterMensagemTelefoneInvalido());
                }
            System.out.println("Falha ao adicionar usuário. Verifique os dados fornecidos.");
        }
    }
    
    public boolean editarUsuario(int indice, String novoNome, String novoTelefone, int novaIdade, String novoSexo) {
        if (indice >= 0 && indice < usuarios.size()) {
            Usuario usuario = usuarios.get(indice);
            if (ValidacaoCadastro.validarNomeUsuario(novoNome) &&
                ValidacaoCadastro.validarIdade(novaIdade) &&
                ValidacaoCadastro.validarSexo(novoSexo)) {
                usuario.setNome(novoNome);
                usuario.setTelefone(novoTelefone);
                usuario.setIdade(novaIdade);
                usuario.setSexo(novoSexo);
                return true;
            } else {
                if (!ValidacaoCadastro.validarNomeUsuario(novoNome)) {
                    System.out.println(ValidacaoCadastro.obterMensagemNomeUsuarioInvalido());
                }
                if (!ValidacaoCadastro.validarIdade(novaIdade)) {
                    System.out.println(ValidacaoCadastro.obterMensagemIdadeInvalida());
                }
                if (!ValidacaoCadastro.validarSexo(novoSexo)) {
                    System.out.println(ValidacaoCadastro.obterMensagemSexoInvalido());
                }
                if (!ValidacaoCadastro.validarTelefone(novoTelefone)) {
                    System.out.println(ValidacaoCadastro.obterMensagemTelefoneInvalido());
                }
                return false;
            }
        }
        return false;
    }

    public void removerUsuarioPorId(int id) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getId() == id) {
                iterator.remove();
                System.out.println("Usuário removido com sucesso!");
                return;
            }
        }
        System.out.println("Usuário com ID " + id + " não encontrado. Não foi possível remover.");
    }
  
    public Usuario getUsuarioByEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario getUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario[] getTodosUsuarios() {
        return usuarios.toArray(new Usuario[0]);
    }    

}
