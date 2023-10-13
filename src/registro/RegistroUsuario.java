package registro;


import java.util.ArrayList;

import dados.Usuario;

public class RegistroUsuario {
	
	private ArrayList<Usuario> rU;

	public RegistroUsuario() {
		rU = new ArrayList<Usuario>();
	}
	
	public void addUsuario (Usuario usuario) {
		rU.add(usuario);
	}

	public Usuario getUsuario (int indice) {
		return rU.get(indice);
	}
	
	public void removeContato (Usuario usuario) {
		rU.remove(usuario);
	}
	
	public void removeContato (int indice) {
		rU.remove(indice);
	}
	
	public int size() {
		return rU.size();
	}

}
