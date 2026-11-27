package BLL;

import java.util.LinkedList;

import DLL.ControllerCurso;

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
	
	
	public static boolean crear(Curso curso, LinkedList<Clase> clases, LinkedList<Tarea> tareas) {
		if (curso.getTitulo().isEmpty() || clases.isEmpty() || clases.size() != tareas.size()) {
			return false;
		}
		return ControllerCurso.crearCurso(curso, clases, tareas);
	}


	public static boolean darDeAlta(Curso curso, double precio) {
		if (!puedeAdministrar(curso) || precio <= 0) {
			return false;
		}
		return ControllerCurso.activarCurso(curso.getId(), precio);
	}

	public static boolean darDeBaja(Curso curso) {
		if (!puedeAdministrar(curso)) {
			return false;
		}
		return ControllerCurso.desactivarCurso(curso.getId());
	}

	private static boolean puedeAdministrar(Curso curso) {
		return Usuario.logueado instanceof Administrador
				&& ((Administrador) Usuario.logueado).getCategoriaId() == curso.getCategoriaId();
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", activo=" + activo + ", categoriaId=" + categoriaId + ", categoria=" + categoria + ", profesorId="
				+ profesorId + ", profesor=" + profesor + "]";
	}


	
	
}
