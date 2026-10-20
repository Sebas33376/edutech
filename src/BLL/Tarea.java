package BLL;

public class Tarea {
	
	private int id;
	private TipoDeEntrega tipo;
	private String url;
	private int claseId;
	
	public Tarea(int id, TipoDeEntrega tipo, String url, int claseId) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.url = url;
		this.claseId = claseId;
	}

	public int getId() {
		return id;
	}

	public TipoDeEntrega getTipo() {
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

	private void setTipo(TipoDeEntrega tipo) {
		this.tipo = tipo;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setClaseId(int claseId) {
		this.claseId = claseId;
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", tipo=" + tipo + ", url=" + url + ", clase=" + claseId + "]";
	}
	
	

}
