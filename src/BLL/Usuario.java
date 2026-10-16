package BLL;

import javax.swing.JOptionPane;

import DLL.ControllerUsuario;

public abstract class Usuario {

	private int id;
	private String userName;
	private String nombre;
	private String apellido;
	private String contrasena;
	private boolean activo;
	private String email;
	private static ControllerUsuario controller = new ControllerUsuario();

	public Usuario(int id, String userName, String nombre, String apellido, String contrasena, boolean activo,
			String email) {

		this.id = id;
		this.userName = userName;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.activo = activo;
		this.email = email;
	}

	public Usuario(int id, String userName, String nombre, String apellido, boolean activo, String email) {

		this.id = id;
		this.userName = userName;
		this.nombre = nombre;
		this.apellido = apellido;
		this.activo = activo;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getEmail() {
		return email;
	}
	
	public static ControllerUsuario getController() {
		return controller;
	}
	
	private void setId(int id) {
		this.id = id;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setApellido(String apellido) {
		this.apellido = apellido;
	}

	private void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	private void setActivo(boolean activo) {
		this.activo = activo;
	}

	private void setEmail(String email) {
		this.email = email;
	}
	
	public static void setController(ControllerUsuario controller) {
		Usuario.controller = controller;
	}

	public abstract void Menu();

	public static Usuario Login(String nombre, String contrasenia) {
		if (nombre.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
			return null;
		} else {

			return controller.login(nombre, contrasenia);
		}

	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", userName=" + userName + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", contrasena=" + contrasena + ", activo=" + activo + "]";
	}

}
