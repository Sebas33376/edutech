package BLL;

public class Clase {

	private int id;
	private String titulo;
	private String url;
	private String descripcion;
	private int cursoId;
	
	public Clase(int id, String titulo, String url, String descripcion, int cursoId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.descripcion = descripcion;
		this.cursoId = cursoId;
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

	@Override
	public String toString() {
		return "Clase [id=" + id + ", titulo=" + titulo + ", url=" + url + ", descripcion=" + descripcion + ", curso="
				+ cursoId + "]";
	}
	
	
	
}
