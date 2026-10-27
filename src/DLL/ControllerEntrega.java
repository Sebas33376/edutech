package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import BLL.Entrega;
import BLL.Estado;

public class ControllerEntrega {
	private static Connection conexion = Conexion.getInstance().getConnection();
	
	public static LinkedList<Entrega> getEntregasByTp(int tareaId){
		LinkedList<Entrega> entregas = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT e.id, e.alumno_fk , u.userName, e.fecha, e.estado FROM entregas e INNER JOIN usuarios u ON e.alumno_fk = u.id WHERE e.tarea_fk = ?");
			stmt.setInt(1, tareaId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int alumnoId = rs.getInt("alumno_fk");
				String estado = rs.getString("estado");
				String alumno = rs.getString("userName");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				entregas.add(new Entrega(id, alumnoId, tareaId, estado, alumno, fecha));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entregas;
	}
}
