package BLL;

public class Profesor extends Usuario {

	private String legajo;
	private String profesion;
	private int categoriaId;
	
    

	public Profesor(int id, String userName, String nombre, String apellido, String contrasena, boolean activo,
			String legajo, String profesion, int categoriaId) {
		super(id, userName, nombre, apellido, contrasena, activo);
		this.legajo = legajo;
		this.profesion = profesion;
		this.categoriaId = categoriaId;
	}

	public String getLegajo() {
		return legajo;
	}

	public String getProfesion() {
		return profesion;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	private void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	private void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	private void setCategoria(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	@Override
	public String toString() {
		return "Profesor [legajo=" + legajo + ", profesion=" + profesion + ", categoria=" + categoriaId + "]";
	}
	
	
	
}
