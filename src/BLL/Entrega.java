package BLL;

public class Entrega {

	private int id;
	private int alumnoId;
	private int tareaId;
	private Estado estado;
	
	public Entrega(int id, int alumnoId, int tareaId, Estado estado) {
		super();
		this.id = id;
		this.alumnoId = alumnoId;
		this.tareaId = tareaId;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public int getAlumnoId() {
		return alumnoId;
	}

	public int getTareaId() {
		return tareaId;
	}

	public Estado getEstado() {
		return estado;
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setAlumnoId(int alumnoId) {
		this.alumnoId = alumnoId;
	}

	private void setTareaId(int tareaId) {
		this.tareaId = tareaId;
	}

	private void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", alumnoId=" + alumnoId + ", tareaId=" + tareaId + ", estado=" + estado + "]";
	}
	
	
	
}
