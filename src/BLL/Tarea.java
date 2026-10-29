package BLL;

import DLL.ControllerTarea;

public class Tarea {

	private int id;
	private String titulo;
	private String descripcion;
	// private TipoDeEntrega tipo;
	private String tipo;
	private String url;
	private int claseId;
	
	public Tarea( String titulo, String descripcion, String tipo, String url, int claseId) {
		setTitulo(titulo);
		setDescripcion(descripcion);
		setTipo(tipo);
		setUrl(url);
		setClaseId(claseId);
	}

	public Tarea(int id, String titulo, String descripcion, String tipo, String url, int claseId) {
		setId(id);
		setTitulo(titulo);
		setDescripcion(descripcion);
		setTipo(tipo);
		setUrl(url);
		setClaseId(claseId);
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

	public String getTipo() {
		return tipo;
	}

	public String getUrl() {
		return url;
	}

	public int getClaseId() {
		return claseId;
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

	private void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setClaseId(int claseId) {
		this.claseId = claseId;
	}

	public static boolean AgregarTarea(Tarea tarea) {
		return tarea.getTitulo().isEmpty() || tarea.getUrl().isEmpty() || tarea.getDescripcion().isEmpty() ? false
				: ControllerTarea.setTarea(tarea);
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", tipo=" + tipo + ", url=" + url + ", clase=" + claseId + "]";
	}

}
