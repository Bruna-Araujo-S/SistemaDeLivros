package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

public class ValidacaoCadastro {


    private static final String MSG_EMAIL_INVALIDO = "O email fornecido é inválido deve ser: exemplo@provedor.com ou exemplo@provedor.com.br.";
    private static final String MSG_NOME_USUARIO_INVALIDO = "O nome de usuário deve conter pelo menos nome e sobrenome.";
    private static final String MSG_IDADE_INVALIDA = "A idade deve ser maior ou igual a 13.";
    private static final String MSG_SEXO_INVALIDO = "O sexo fornecido é inválido Favor selecionar entre Feminino, Masculino ou Outros.";
    private static final String MSG_SENHA_INVALIDA = "A senha não atende aos requisitos mínimos, deve no minimo ter 8 caracteres dentre eles um caracter especial, um numero e uma letra maiuscula.";
    private static final String MSG_TELEFONE_INVALIDO = "O telefone informado não segue o padrão (XX)9XXXX-XXXX ou (XX)XXXX-XXXX";

    public static boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean validarNomeUsuario(String nomeUsuario) {
        String[] tokens = nomeUsuario.split("\\s+");
        return tokens.length >= 2;
    }

    public static boolean validarSexo(String sexo) {
        String sexoLowerCase = sexo.trim().toLowerCase();
        return sexoLowerCase.equals("masculino") || sexoLowerCase.equals("feminino")
            || sexoLowerCase.equals("outros");
    }

    public static boolean validarIdade(int idade) {
        return idade >= 13;
    }

    public static boolean validarTelefone(String telefone) {
    String regex = "^(\\d{2})\\d{4,5}-?\\d{4}$|^(\\d{2})9\\d{4,5}-?\\d{4}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(telefone);
    return matcher.matches();
    }
    

     public static boolean validarSenha(String senha) {
        if (senha.length() < 8) {
            return false;
        }
        if (!senha.matches(".*[A-Z].*")) {
            return false;
        }
        if (!senha.matches(".*[a-z].*")) {
            return false;
        }
        if (!senha.matches(".*\\d.*")) {
            return false;
        }
        if (!senha.matches(".*[!@#$%^&*()-+=].*")) {
            return false;
        }
        return true;
    }

    public static String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static String obterMensagemEmailInvalido() {
        return MSG_EMAIL_INVALIDO;
    }

    public static String obterMensagemNomeUsuarioInvalido() {
        return MSG_NOME_USUARIO_INVALIDO;
    }

    public static String obterMensagemIdadeInvalida() {
        return MSG_IDADE_INVALIDA;
    }

    public static String obterMensagemSexoInvalido() {
        return MSG_SEXO_INVALIDO;
    }

    public static String obterMensagemSenhaInvalida() {
        return MSG_SENHA_INVALIDA;
    }
    public static String obterMensagemTelefoneInvalido() {
        return MSG_TELEFONE_INVALIDO;
    }
}

