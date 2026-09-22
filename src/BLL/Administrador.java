package BLL;

public class Administrador extends Usuario {

	private int categoriaId;
	
	public Administrador(int id, String userName, String nombre, String apellido, String contrasena, boolean activo) {
		super(id, userName, nombre, apellido, contrasena, activo);
		// TODO Auto-generated constructor stub
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	private void setCategoriaId(int id) {
		this.categoriaId = id;
	}

	@Override
	public String toString() {
		return "Administrador [categoria=" + categoriaId + "]";
	}
	
	
}
