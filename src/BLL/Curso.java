package BLL;

public class Curso {

	private int id;
	private String titulo;
	private String descripcion;
	private Double precio;
	private int categoriaId;
	private int profesorId;
	
	public Curso(int id, String titulo, String descripcion, Double precio ,int categoriaId, int profesorId) {
	
		setId(id);
		setTitulo(titulo);
		setDescripcion(descripcion);
		setPrecio(precio);
		setCategoriaId(categoriaId);
		setProfesorId(profesorId);
		
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
	
	public Double getPrecio() {
		return precio;
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
	
	private void setPrecio(Double precio) {
		this.precio = precio;
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
