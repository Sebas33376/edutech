package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import BLL.Curso;
import BLL.Inscripcion;
import BLL.MedioDePago;
import BLL.Usuario;

public class ControllerInscripcion {
	private static Connection conexion = Conexion.getInstance().getConnection();
	
	public static boolean inscribirAlumno(int alumnoId, int cursoId, int medioDePagoId) {
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"INSERT INTO inscripciones (user_id, curso_id, fecha_inscripcion, medio_de_pago_fk) VALUES ('?,?,?, ?)");
			stmt.setInt(1, alumnoId);
			stmt.setInt(2, cursoId);
			stmt.setDate(3, Date.valueOf(LocalDate.now()));
			stmt.setInt(4, medioDePagoId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static LinkedList<Inscripcion> getInscripcionesByAlumnoId(int alumnoId) {
		LinkedList<Inscripcion> inscripciones = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT * FROM inscripciones WHERE user_id = ? inner join cursos c on c.id = inscripciones.curso_id inner join medios_de_pago m on m.id = inscripciones.medio_de_pago_fk");
			stmt.setInt(1, alumnoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int cursoId = rs.getInt("curso_id");
				LocalDate fechaInscripcion = rs.getDate("fecha_inscripcion").toLocalDate();
				int medioDePagoId = rs.getInt("medio_de_pago_fk");
				Usuario usuario = ControllerUsuario.getUsuarioById(alumnoId);
				Curso curso = ControllerCurso.getCursoById(cursoId);
				MedioDePago medioDePago = ControllerMedioDePago.getMedioDePagoById(medioDePagoId);
				inscripciones.add(new Inscripcion(alumnoId, cursoId, curso, usuario, fechaInscripcion, medioDePagoId, medioDePago));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inscripciones;
	}
}
