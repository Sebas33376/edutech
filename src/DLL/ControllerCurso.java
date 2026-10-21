package DLL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.sql.PreparedStatement;

import BLL.Curso;

public class ControllerCurso {
	private static Connection conexion = Conexion.getInstance().getConnection();

	public static Curso getCursoById(int id) {
		Curso curso = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT c.titulo as 'Titulo', c.descripcion as 'Descripción', c.precio as 'Precio', ca.nombre as 'Categoría', concat(u.nombre, ' ' , u.apellido) as 'Profesor' FROM cursos c inner join categorias ca on c.categoria_fk = ca.id inner join usuarios u on u.id = c.profesor_fk where c.id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				String titulo = rs.getString("Titulo");
				String descripcion = rs.getString("Descripción");
				double precio = rs.getDouble("Precio");
				String categoria = rs.getString("Categoría");
				String profesor = rs.getString("Profesor");

				curso = new Curso(titulo, descripcion, precio, categoria, profesor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curso;
	}
	
	public static LinkedList<Curso> mostrarCursos() {
		LinkedList<Curso> cursos = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT c.titulo as 'Titulo', c.descripcion as 'Descripción', c.precio as 'Precio', ca.nombre as 'Categoría', concat(u.nombre, ' ' , u.apellido) as 'Profesor' FROM cursos c inner join categorias ca on c.categoria_fk = ca.id inner join usuarios u on u.id = c.profesor_fk");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				String titulo = rs.getString("Titulo");
				String descripcion = rs.getString("Descripción");
				double precio = rs.getDouble("Precio");
				String categoria = rs.getString("Categoría");
				String profesor = rs.getString("Profesor");

				cursos.add(new Curso(titulo, descripcion, precio, categoria, profesor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursos;
	}
}
