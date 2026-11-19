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
	private byte[] archivo;
	
	public Entrega(int alumnoId, int tareaId) {

		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setFecha(LocalDate.now());
		

	}

	public Entrega(int alumnoId, int tareaId, byte[] archivo) {

		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setFecha(LocalDate.now());
		setArchivo(archivo);
		

	}
	
	public Entrega(int id, int alumnoId, int tareaId, String estado,byte[] archivo) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setFecha(LocalDate.now());
		setArchivo(archivo);
		

	}
	
	public Entrega(int id, int alumnoId, int tareaId, String estado, LocalDate date, String correccion) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setFecha(date);
		setCorreccion(correccion);

	}

	public Entrega(int id, int alumnoId, int tareaId, String estado, LocalDate date, String correccion, byte[] archivo) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setFecha(date);
		setCorreccion(correccion);
		setArchivo(archivo);

	}

	public Entrega(int id, int alumnoId, int tareaId, String estado, String alumno, LocalDate date, byte[] archivo) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setAlumno(alumno);
		setFecha(date);
		setArchivo(archivo);

	}
	
	public Entrega(int id, int alumnoId, int tareaId, String estado, String alumno, LocalDate date, String correccion) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setAlumno(alumno);
		setFecha(date);
		setCorreccion(correccion);
		setArchivo(archivo);

	}

	public Entrega(int id, int alumnoId, int tareaId, String estado, String alumno, LocalDate date, String correccion, byte[] archivo) {

		setId(id);
		setAlumnoId(alumnoId);
		setTareaId(tareaId);
		setEstado(estado);
		setAlumno(alumno);
		setFecha(date);
		setCorreccion(correccion);
		setArchivo(archivo);

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

	public byte[] getArchivo() {
		return archivo;
	}

	private void setArchivo(byte[] archivo) {
		this.archivo = archivo;
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
	
	public static boolean crearEntrega(Entrega entrega) {
		return ControllerEntrega.setEntrega(entrega);
	}

}
