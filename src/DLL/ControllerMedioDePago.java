package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import BLL.MedioDePago;

public class ControllerMedioDePago {

	private static Connection conexion = Conexion.getInstance().getConnection();
	
	public static MedioDePago getMedioDePagoById(int id) {
		MedioDePago medioDePago = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT * FROM medios_de_pago WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int userId = rs.getInt("user_fk");
				String titular = rs.getString("titular");
				String numero = rs.getString("numero");
				LocalDate fechaVencimiento = rs.getDate("fecha_vencimiento").toLocalDate();
				String codigoSeguridad = rs.getString("cod_seguridad");
				String nombre = rs.getString("nombre");
				String tipo = rs.getString("tipo");
				
				medioDePago = new MedioDePago(id, userId, titular, numero, fechaVencimiento, codigoSeguridad, tipo, nombre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medioDePago;
	}
	
	public static LinkedList<MedioDePago> getMediosDePagosByUsuario(int id) {
		LinkedList<MedioDePago> mediosDePago = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT * FROM medios_de_pago WHERE user_fk = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int userId = rs.getInt("user_fk");
				String titular = rs.getString("titular");
				String numero = rs.getString("numero");
				LocalDate fechaVencimiento = rs.getDate("fecha_vencimiento").toLocalDate();
				String codigoSeguridad = rs.getString("cod_seguridad");
				String nombre = rs.getString("nombre");
				String tipo = rs.getString("tipo").toUpperCase();
				
				mediosDePago.add(new MedioDePago(id, userId, titular, numero, fechaVencimiento, codigoSeguridad, tipo, nombre));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediosDePago;
	}
}
