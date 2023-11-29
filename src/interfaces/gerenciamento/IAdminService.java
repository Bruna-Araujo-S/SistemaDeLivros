package interfaces.gerenciamento;

import models.Administrador;

public interface IAdminService {

      boolean adicionarAdministrador(Administrador administrador, String senha);

      Administrador getAdministradorByEmail(String email);
    
}
