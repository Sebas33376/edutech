package BLL;

public class Usuario {

	private int id;
	private String userName;
	private String nombre;
	private String apellido;
	private String contrasena;
	private boolean activo;
	
	public Usuario(int id, String userName, String nombre, String apellido, String contrasena, boolean activo) {
		
		this.id = id;
		this.userName = userName;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.activo = activo;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", userName=" + userName + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", contrasena=" + contrasena + ", activo=" + activo + "]";
	}
	
	
	
}
