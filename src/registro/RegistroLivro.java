package registro;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import dados.*;

public class RegistroLivro {
	
	private ArrayList<Livro> rL;

	public RegistroLivro() {
		rL = new ArrayList<Livro>();
	}
	
	public void addLivro (Livro livro) {
		rL.add(livro);
	}

	public Livro getLivro (int indice) {
		return rL.get(indice);
	}
	
	public void removeLivro(Livro livro) {
		rL.remove(livro);
	}
	
	public void removeLivro (int indice) {
		rL.remove(indice);
	}
	
	public int size() {
		return rL.size();
	}
	
	public void emprestaLivro (Livro livro, Usuario usuario){
		LocalDateTime now = LocalDateTime.now();
		livro.setDataEmprestimo(now);
		livro.setUsuarioComLivro(usuario);
	}
	
	public void ordenaTituloAZ(){
		Collections.sort(rL);
	}

}