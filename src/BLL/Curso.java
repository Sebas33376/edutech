package BLL;

public class Curso {

	private int id;
	private String titulo;
	private String descripcion;
	private Double precio;
	private boolean activo;
	private int categoriaId;
	private String categoria;
	private int profesorId;
	private String profesor;
	
	public Curso(int id, String titulo, String descripcion, Double precio ,int categoriaId, int profesorId) {
	
		setId(id);
		setTitulo(titulo);
		setDescripcion(descripcion);
		setPrecio(precio);
		setCategoriaId(categoriaId);
		setProfesorId(profesorId);
		
	}

	public Curso( int id, String titulo, String descripcion, double precio, boolean activo ,int categoriaId, int profesorId,String categoria, String profesor) {
		
		setId(id);
		setTitulo(titulo);
		setDescripcion(descripcion);
		setPrecio(precio);
		setCategoria(categoria);
		setProfesor(profesor);
		setActivo(activo);
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

	public boolean isActivo() {
		return activo;
	}

	public int getCategoriaId() {
		return categoriaId;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public int getProfesorId() {
		return profesorId;
	}
	
	public String getProfesor() {
		return profesor;
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
	
	private void setActivo(boolean activo) {
		this.activo = activo;
	}

	private void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	private void setProfesorId(int profesorId) {
		this.profesorId = profesorId;
	}
	
	private void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", activo=" + activo + ", categoriaId=" + categoriaId + ", categoria=" + categoria + ", profesorId="
				+ profesorId + ", profesor=" + profesor + "]";
	}


	
	
}
