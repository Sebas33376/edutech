package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import BLL.Entrega;

public class ControllerEntrega {
	private static Connection conexion = Conexion.getInstance().getConnection();

	public static LinkedList<Entrega> getEntregasByTp(int tareaId) {
		LinkedList<Entrega> entregas = new LinkedList<>();

		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT e.id, e.alumno_fk , u.userName, e.fecha, e.estado, e.correccion FROM entregas e INNER JOIN usuarios u ON e.alumno_fk = u.id WHERE e.tarea_fk = ?");
			stmt.setInt(1, tareaId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int alumnoId = rs.getInt("alumno_fk");
				String estado = rs.getString("estado");
				String alumno = rs.getString("userName");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				String correccion = rs.getString("correccion");
				entregas.add(new Entrega(id, alumnoId, tareaId, estado, alumno, fecha, correccion));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entregas;
	}

	public static boolean editEntrega(Entrega entrega) {
		try {
			PreparedStatement stmt = conexion
					.prepareStatement("UPDATE entregas SET estado = ?, correccion = ? WHERE id = ?");
			stmt.setString(1, "Corregido");
			stmt.setString(2, entrega.getCorreccion());
			stmt.setInt(3, entrega.getId());
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean setEntrega(Entrega entrega) {
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"INSERT INTO entregas (alumno_fk, tarea_fk, estado, fecha, correccion, archivo) VALUES (?, ?, ?, ?, ?, ?)");

			stmt.setInt(1, entrega.getAlumnoId());
			stmt.setInt(2, entrega.getTareaId());
			stmt.setString(3, "Sin corregir");
			stmt.setDate(4, Date.valueOf(entrega.getFecha()));
			stmt.setString(5, null);
			if (entrega.getArchivo() != null) {
				stmt.setBytes(6, entrega.getArchivo());
			} else {
				stmt.setNull(6, java.sql.Types.BLOB);
			}
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
