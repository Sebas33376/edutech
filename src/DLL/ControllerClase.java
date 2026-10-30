package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import BLL.Clase;
import BLL.Usuario;

public class ControllerClase {
	private static Connection conexion = Conexion.getInstance().getConnection();

	public static LinkedList<Clase> getClasesByCurso(int cursoId) {
		LinkedList<Clase> clases = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT \n"
					+ "    c.id, \n"
					+ "    c.titulo, \n"
					+ "    c.url, \n"
					+ "    c.descripcion, \n"
					+ "    cu.titulo AS 'curso', \n"
					+ "    COUNT(t.id) > 0 AS tiene_tarea,\n"
					+ "    CASE \n"
					+ "        WHEN MAX(e.id) IS NOT NULL THEN 'Entregado' \n"
					+ "        ELSE 'No entregado' \n"
					+ "    END AS estado_entrega\n"
					+ "FROM clases c \n"
					+ "INNER JOIN cursos cu ON cu.id = c.curso_fk \n"
					+ "LEFT JOIN tareas t ON t.clase_fk = c.id \n"
					+ "LEFT JOIN entregas e ON e.tarea_fk = t.id AND e.alumno_fk = ?\n"
					+ "WHERE cu.id = ? \n"
					+ "GROUP BY c.id, c.titulo, c.url, c.descripcion, cu.titulo");
			stmt.setInt(1, Usuario.logueado.getId());
			stmt.setInt(2, cursoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String url = rs.getString("url");
				String descripcion = rs.getString("descripcion");
				String curso = rs.getString("curso");
				String entregado = rs.getString("estado_entrega");
				boolean hasTarea = rs.getBoolean("tiene_tarea");
				clases.add(new Clase(id, titulo, url, descripcion, cursoId, curso, entregado, hasTarea));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}

	public static LinkedList<Clase> getClasesByCursoAndProfesor(int cursoId, int profesorId) {
		LinkedList<Clase> clases = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT \r\n" + "    c.id, \r\n" + "    c.titulo, \r\n"
					+ "    c.url, \r\n" + "    c.descripcion, \r\n" + "    cu.titulo as 'curso',\r\n"
					+ "    COUNT(t.id) > 0 as tiene_tarea,\r\n" + "    COUNT(e.id) as 'total_entregas'\r\n"
					+ "FROM clases c \r\n" + "INNER JOIN cursos cu ON cu.id = c.curso_fk \r\n"
					+ "LEFT JOIN tareas t ON t.clase_fk = c.id\r\n" + "LEFT JOIN entregas e ON e.tarea_fk = t.id\r\n"
					+ "WHERE cu.profesor_fk = ? AND cu.id = ?\r\n"
					+ "GROUP BY c.id, c.titulo, c.url, c.descripcion, cu.titulo");
			stmt.setInt(1, profesorId);
			stmt.setInt(2, cursoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String url = rs.getString("url");
				String descripcion = rs.getString("descripcion");
				String curso = rs.getString("curso");
				int cant = rs.getInt("total_entregas");
				boolean hasTarea = rs.getBoolean("tiene_tarea");
				clases.add(new Clase(id, titulo, url, descripcion, cursoId, curso, cant, hasTarea));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}

	public static boolean editClase(Clase clase) {
		try {
			PreparedStatement stmt = conexion
					.prepareStatement("UPDATE clases SET titulo = ?, url = ?, descripcion = ? WHERE id = ?");
			stmt.setString(1, clase.getTitulo());
			stmt.setString(2, clase.getUrl());
			stmt.setString(3, clase.getDescripcion());
			stmt.setInt(4, clase.getId());
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}