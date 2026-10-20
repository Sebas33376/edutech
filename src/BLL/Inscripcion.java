package BLL;

import java.time.LocalDate;

public class Inscripcion {

	private int usuarioId;
	private int cursoId;
	private LocalDate fechaInscripicion;
	private int medioDePagoId;
	private MedioDePago medioDePago;
	
	public Inscripcion(int usuarioId, int cursoId, LocalDate fechaInscripicion, int medioDePagoId) {
		setUsuario(usuarioId);
		setCurso(cursoId);
		setFechaInscripicion(LocalDate.now());
		setMedioDePagoId(medioDePagoId);
	}

	public int getUsuarioId() {
		return usuarioId;
	}
	public int getCursoId() {
		return cursoId;
	}
	public LocalDate getFechaInscripicion() {
		return fechaInscripicion;
	}
	
	public int getMedioDePagoId() {
		return medioDePagoId;
	}
	
	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	
	private void setUsuario(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	private void setCurso(int cursoId) {
		this.cursoId = cursoId;
	}
	private void setFechaInscripicion(LocalDate fechaInscripicion) {
		this.fechaInscripicion = fechaInscripicion;
	}
	
	private void setMedioDePagoId(int medioDePagoId) {
		this.medioDePagoId = medioDePagoId;
	}
	
	private void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}
	
	@Override
	public String toString() {
		return "Inscripcion [usuario=" + usuarioId + ", curso=" + cursoId + ", fechaInscripicion=" + fechaInscripicion
				+ "]";
	}
	
	
	
}
