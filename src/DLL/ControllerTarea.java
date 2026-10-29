package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.log.Log;

import BLL.Tarea;

public class ControllerTarea {
	private static Connection conexion = Conexion.getInstance().getConnection();
	
	public static boolean setTarea(Tarea tarea) {
		try {
			PreparedStatement stmt = conexion.prepareStatement("INSERT INTO tareas (tipo, url, clase_fk, titulo, descripcion) VALUES (?,?,?,?,?)");
			stmt.setString(1, tarea.getTipo());
			stmt.setString(2, tarea.getUrl());
			stmt.setInt(3, tarea.getClaseId());
			stmt.setString(4, tarea.getTitulo());
			stmt.setString(5, tarea.getDescripcion());
			
			int filas = stmt.executeUpdate();
			return filas > 0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public static Tarea getTarea(int claseId) {
		Tarea tarea = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM tareas WHERE clase_fk = ?");
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
		System.out.println(tarea);
		return tarea;
	}
	
	public static boolean editTarea(Tarea tarea) {
		try {
			PreparedStatement stmt = conexion.prepareStatement("UPDATE tareas SET tipo = ?, url = ?, titulo = ?, descripcion = ? WHERE id = ?");
			stmt.setString(1, tarea.getTipo());
			stmt.setString(2, tarea.getUrl());
			stmt.setString(3, tarea.getTitulo());
			stmt.setString(0, tarea.getDescripcion());
			stmt.setInt(0, tarea.getId());
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
