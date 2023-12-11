package util;

import models.Usuario;

public class SessaoUsuario {
    private static Usuario usuarioLogado;

    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static int getIdUsuario() {
        if (usuarioLogado != null) {
            return usuarioLogado.getId();
        }
        return -1;
    }
}
