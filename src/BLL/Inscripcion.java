package BLL;

import java.time.LocalDate;

public class Inscripcion {

	private int usuarioId;
	private int cursoId;
	private LocalDate fechaInscripicion;
	
	public Inscripcion(int usuarioId, int cursoId, LocalDate fechaInscripicion) {
		super();
		this.usuarioId = usuarioId;
		this.cursoId = cursoId;
		this.fechaInscripicion = LocalDate.now();
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
	private void setUsuario(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	private void setCurso(int cursoId) {
		this.cursoId = cursoId;
	}
	private void setFechaInscripicion(LocalDate fechaInscripicion) {
		this.fechaInscripicion = fechaInscripicion;
	}
	@Override
	public String toString() {
		return "Inscripcion [usuario=" + usuarioId + ", curso=" + cursoId + ", fechaInscripicion=" + fechaInscripicion
				+ "]";
	}
	
	
	
}
