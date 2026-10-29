package BLL;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import DLL.ControllerInscripcion;

public class Inscripcion {

	private int usuarioId;
	private int cursoId;
	private Curso curso;
	private Usuario usuario;
	private LocalDate fechaInscripicion;
	private int medioDePagoId;
	private MedioDePago medioDePago;
	
	public Inscripcion(int usuarioId, int cursoId, LocalDate fechaInscripicion, int medioDePagoId) {
		setUsuario(usuarioId);
		setCurso(cursoId);
		setFechaInscripicion(LocalDate.now());
		setMedioDePagoId(medioDePagoId);
	}
	
	public Inscripcion(int usuarioId, int cursoId, Curso curso, Usuario usuario, LocalDate fechaInscripicion, int medioDePagoId, MedioDePago medioDePago) {
		setUsuario(usuarioId);
		setCurso(cursoId);
		setCurso(curso);
		setUsuario(usuario);
		setFechaInscripicion(fechaInscripicion);
		setMedioDePagoId(medioDePagoId);
		setMedioDePago(medioDePago);
	}

	public int getUsuarioId() {
		return usuarioId;
	}
	public int getCursoId() {
		return cursoId;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public Usuario getUsuario() {
		return usuario;
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
	
	private void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	public static boolean Inscribirce(int alumnoId, int cursoId, int medioDePagoId) {
		
			return alumnoId == 0 && cursoId == 0 && medioDePagoId == 0 ? false : ControllerInscripcion.inscribirAlumno(alumnoId, cursoId, medioDePagoId);
		}

	
	@Override
	public String toString() {
		return "Inscripcion [usuario=" + usuarioId + ", curso=" + cursoId + ", fechaInscripicion=" + fechaInscripicion
				+ "]";
	}
	
	
	
}
