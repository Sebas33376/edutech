package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import BLL.Clase;

public class ControllerClase {
	private static Connection conexion = Conexion.getInstance().getConnection();

	public static LinkedList<Clase> getClasesByCurso(int cursoId, int profesorId) {
		LinkedList<Clase> clases = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT c.id, c.titulo, c.url, c.descripcion, cu.titulo as 'curso' FROM clases c inner join cursos cu on cu.id = c.curso_fk WHERE cu.profesor_fk = ? AND cu.id = ?");
			stmt.setInt(1, profesorId);
			stmt.setInt(2, cursoId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String url = rs.getString("url");
				String descripcion = rs.getString("descripcion");
				String curso = rs.getString("curso");
				clases.add(new Clase(id, titulo, url, descripcion, cursoId,curso));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clases;
	}
	
	public static boolean editClase(Clase clase) {
		try {
			PreparedStatement stmt = conexion.prepareStatement("UPDATE clases SET titulo = ?, url = ?, descripcion = ? WHERE id = ?");
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
