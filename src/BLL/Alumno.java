package BLL;

import java.time.LocalDate;

public class Alumno extends Usuario {

	private LocalDate fechaIngreso;
	
	public Alumno(int id, String userName, String nombre, String apellido, String contrasena, boolean activo) {
		super(id, userName, nombre, apellido, contrasena, activo);
		// TODO Auto-generated constructor stub
		
		setFechaIngreso(LocalDate.now());
		
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	private void setFechaIngreso(LocalDate fecha_ingreso) {
		this.fechaIngreso = fecha_ingreso;
	}

	@Override
	public String toString() {
		return "Alumno [fecha_ingreso=" + fechaIngreso + "]";
	}
	
	

}
