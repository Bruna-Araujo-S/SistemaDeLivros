package interfaces.gerenciamento;


import models.Usuario;

public interface IUsuarioDAO {

    void adicionarUsuarioNoBanco(Usuario usuario);

    Usuario getUsuarioByEmail(String email);

    Usuario getUsuarioById(int id);

    void editarUsuarioNoBanco(int id, String novoNome, String novoTelefone, int novaIdade, String novoSexo);

    void removerUsuarioNoBanco(int id);
}
