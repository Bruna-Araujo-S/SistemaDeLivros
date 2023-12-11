package validations;

import javax.swing.JOptionPane;

public class ValidarCadastroUsuario {

    public boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (email.matches(regex)) {
            return true;
        } else {
            exibirMensagemErro("Email inválido. Deve conter @ nome do provedor .com ou .br");
            return false;
        }
    }

    public boolean validarNome(String nome) {
        if (nome != null && nome.trim().contains(" ")) {
            return true;
        } else {
            exibirMensagemErro("Nome inválido. Deve conter pelo menos nome e sobrenome.");
            return false;
        }
    }

    public boolean validarTelefone(String telefone) {
        String regex = "^[0-9]{2}[-]?[0-9]{9}$";

        if (telefone.matches(regex)) {
            return true;
        } else {
            exibirMensagemErro("Telefone: 11 dígitos (DDD+número) ou com traço.");
            return false;
        }
    }

    public boolean validarIdade(int idade) {
        if (idade > 13) {
            return true;
        } else {
            exibirMensagemErro("Idade inválida. Deve ser maior que 13 anos.");
            return false;
        }
    }

    public boolean validarSenha(String senha) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";

        if (senha.matches(regex)) {
            return true;
        } else {
            exibirMensagemErro("Senha: 6+ letras, números e símbolos. ");
            return false;
        }
    }

    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
