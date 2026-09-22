package BLL;

public class Curso {

	private int id;
	private String titulo;
	private String descripcion;
	private int categoriaId;
	private int profesorId;
	
	public Curso(int id, String titulo, String descripcion, int categoriaId, int profesorId) {
	
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.categoriaId = categoriaId;
		this.profesorId = profesorId;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public int getProfesorId() {
		return profesorId;
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	private void setProfesorId(int profesorId) {
		this.profesorId = profesorId;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", categoria=" + categoriaId	+ ", profesor=" + profesorId + "]";
	}
	
	
	
}
