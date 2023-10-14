package dados;

public class Usuario {

	private String nome, telefone;
	private static int codUsr=1;
	private int codigoDoUsuario;
	
	public Usuario(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.codigoDoUsuario = codUsr;
		codUsr++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCodigoDoUsuario() {
		return codigoDoUsuario;
	}	

}
