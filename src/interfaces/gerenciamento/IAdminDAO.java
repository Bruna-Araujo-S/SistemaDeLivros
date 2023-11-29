package interfaces.gerenciamento;

import models.Administrador;

public interface IAdminDAO {
    
     boolean salvarNoBancoDeDados(Administrador administrador, String senha);
     
     Administrador getAdministradorByEmail(String email);

}
