package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BLL.Tarea;
import BLL.TipoDeEntrega;

public class ControllerTarea {
	private static Connection conexion = Conexion.getInstance().getConnection();
	
	public static Tarea getTarea(int claseId) {
		Tarea tarea = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM tareas WHERE id = ?");
			stmt.setInt(1, claseId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");	
				String tipo = rs.getString("tipo");
				String url = rs.getString("url");
				
				tarea = new Tarea(id,titulo, descripcion , tipo , url , claseId);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tarea;
	}
	

}
