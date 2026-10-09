package BLL;

public class Administrador extends Usuario {

	private int categoriaId;
	
	public Administrador(int id, String userName, String nombre, String apellido, String contrasena, boolean activo, String email, int categoriaId) {
		super(id, userName, nombre, apellido, contrasena, activo, email);
		// TODO Auto-generated constructor stub
		this.setCategoriaId(categoriaId);
	}
	
	public Administrador(int id, String userName, String nombre, String apellido,  boolean activo, String email, int categoriaId) {
		super(id, userName, nombre, apellido, activo, email);
		// TODO Auto-generated constructor stub
		this.setCategoriaId(categoriaId);
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	private void setCategoriaId(int id) {
		this.categoriaId = id;
	}

	@Override
	public String toString() {
		return "Administrador [" + super.toString() + " categoria=" + categoriaId + "]";
	}

	@Override
	public void Menu() {
		// TODO Auto-generated method stub
		
	}
	
	
}
