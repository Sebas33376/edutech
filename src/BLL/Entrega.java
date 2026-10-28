package BLL;

import java.time.LocalDate;

import DLL.ControllerEntrega;

public class Entrega {

	private int id;
	private int alumnoId;
	private String alumno;
	private int tareaId;
	// private Estado estado;
	private String estado;
	private LocalDate fecha;
	private String correccion;

	public Entrega(int id, int alumnoId, int tareaId, String estado) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setFecha(LocalDate.now());

	}

	public Entrega(int id, int alumnoId, int tareaId, String estado, LocalDate date, String correccion) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setFecha(date);
		setCorreccion(correccion);

	}

	public Entrega(int id, int alumnoId, int tareaId, String estado, String alumno, LocalDate date) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setAlumno(alumno);
		setFecha(date);

	}

	public Entrega(int id, int alumnoId, int tareaId, String estado, String alumno, LocalDate date, String correccion) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setAlumno(alumno);
		setFecha(date);
		setCorreccion(correccion);

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

	public String getEstado() {
		return estado;
	}

	public String getAlumno() {
		return alumno;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getCorreccion() {
		return correccion;
	}

	private void setCorreccion(String correccion) {
		this.correccion = correccion;
	}

	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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

	private void setEstado(String estado) {
		this.estado = estado;
	}

	private void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", alumnoId=" + alumnoId + ", tareaId=" + tareaId + ", estado=" + estado + "]";
	}

	public static boolean editarEntrega(Entrega entrega) {
		return entrega.getCorreccion().isEmpty() ? false : ControllerEntrega.editEntrega(entrega);
	}

}
