package BLL;

import javax.swing.JOptionPane;

import DLL.ControllerClase;

public class Clase {

	private int id;
	private String titulo;
	private String url;
	private String descripcion;
	private int cursoId;
	private String curso;
	
	public Clase(int id, String titulo, String url, String descripcion, int cursoId) {
		setId(id);
		setTitulo(titulo);
		setUrl(url);
		setDescripcion(descripcion);
		setCursoId(cursoId);
	}
	
	public Clase(int id, String titulo, String url, String descripcion, int cursoId, String curso) {
		setId(id);
		setTitulo(titulo);
		setUrl(url);
		setDescripcion(descripcion);
		setCursoId(cursoId);
		setCurso(curso);
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrl() {
		return url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCursoId() {
		return cursoId;
	}

	public String getCurso() {
		return curso;
	}

	private void setCurso(String curso) {
		this.curso = curso;
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private void setCursoId(int cursoId) {
		this.cursoId = cursoId;
	}
	
	public static boolean editarClase(Clase clase) {
		boolean exito = false;
		if (clase.getTitulo().isEmpty() || clase.getUrl().isEmpty() || clase.getDescripcion().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
		} else {
			exito = ControllerClase.editClase(clase);
		}
		return exito;
	}

	@Override
	public String toString() {
		return "Clase [id=" + id + ", titulo=" + titulo + ", url=" + url + ", descripcion=" + descripcion + ", curso="
				+ cursoId + "]";
	}
	
	
	
}
