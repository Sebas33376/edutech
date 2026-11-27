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

	public boolean puedeAdministrar(Usuario objetivo) {
		if (objetivo instanceof Alumno) {
			return true;
		}
		if (objetivo instanceof Profesor) {
			return ((Profesor) objetivo).getCategoriaId() == categoriaId;
		}
		return false;
	}

	public String siguienteLegajo() {
		return getController().siguienteLegajo();
	}


	public Profesor crearProfesor(String userName, String nombre, String apellido, String email, String contrasena,
			String profesion) {
		Profesor profesor = new Profesor(0, userName, nombre, apellido, contrasena, true, email, siguienteLegajo(),
				profesion, categoriaId);
		return Usuario.registrar(profesor) ? profesor : null;
	}

	public boolean darDeBaja(Usuario objetivo) {
		return puedeAdministrar(objetivo) && getController().bajaUsuario(objetivo.getId());
	}

	@Override
	public String toString() {
		return "Administrador [" + super.toString() + " categoria=" + categoriaId + "]";
	}

	
	
}

