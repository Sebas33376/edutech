package BLL;

import java.time.LocalDate;

public class MedioDePago {
	private int id;
	private int userId;
	private String titular;
	private String numero;
	private LocalDate fechaVencimiento;
	private String codigoSeguridad;
	//private TipoDePago tipo;
	private String tipo;
	private String nombre;

	public MedioDePago(int id, int userId, String titular, String numero, LocalDate fechaVencimiento,
			String codigoSeguridad, String tipo, String nombre) {
		setId(id);
		setUserId(userId);
		setTitular(titular);
		setNumero(numero);
		setFechaVencimiento(fechaVencimiento);
		setCodigoSeguridad(codigoSeguridad);
		setTipo(tipo);
		setNombre(nombre);
	}
	
	
	public MedioDePago(int userId, String titular, String numero, LocalDate fechaVencimiento,
			String codigoSeguridad, String tipo, String nombre) {
		setUserId(userId);
		setTitular(titular);
		setNumero(numero);
		setFechaVencimiento(fechaVencimiento);
		setCodigoSeguridad(codigoSeguridad);
		setTipo(tipo);
		setNombre(nombre);
	}
	
	public int getId() {
		return id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	private void setUserId(int userId) {
		this.userId = userId;
	}
	
	private void setTitular(String titular) {
		this.titular = titular;
	}
	
	private void setNumero(String numero) {
		this.numero = numero;
	}
	
	private void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	private void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	
	private void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "MedioDePago [id=" + id + ", userId=" + userId + ", titular=" + titular + ", numero=" + numero
				+ ", fechaVencimiento=" + fechaVencimiento + ", codigoSeguridad=" + codigoSeguridad + ", tipo=" + tipo
				+ ", nombre=" + nombre + "]";
	}
}
