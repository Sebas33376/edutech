package BLL;

import java.time.LocalDate;

public class Alumno extends Usuario {

	private LocalDate fechaIngreso;
	
	public Alumno(int id, String userName, String nombre, String apellido, String contrasena, boolean activo, String email) {
		super(id, userName, nombre, apellido, contrasena, activo, email);
		// TODO Auto-generated constructor stub
		
		setFechaIngreso(LocalDate.now());
		
	}
	
	public Alumno(int id, String userName, String nombre, String apellido, boolean activo, String email, LocalDate fechaIngreso) {
		super(id, userName, nombre, apellido,activo, email);
		// TODO Auto-generated constructor stub
		this.setFechaIngreso(fechaIngreso);
		
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	private void setFechaIngreso(LocalDate fecha_ingreso) {
		this.fechaIngreso = fecha_ingreso;
	}



	@Override
	public String toString() {
		return "Alumno [" + super.toString() + " fechaIngreso=" + fechaIngreso + "]";
	}

	@Override
	public void Menu() {
		// TODO Auto-generated method stub
		
	}
	
	

}
