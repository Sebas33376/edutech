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
					"SELECT c.id, c.titulo, c.descripcion, c.precio, ca.nombre , concat(u.nombre, ' ' , u.apellido) as 'profesor', c.profesor_fk, c.categoria_fk, c.activo, ca.nombre as 'categoria'FROM cursos c inner join categorias ca on c.categoria_fk = ca.id inner join usuarios u on u.id = c.profesor_fk where c.id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				String categoria = rs.getString("categoria");
				String profesor = rs.getString("profesor");
				boolean activo = rs.getBoolean("activo");
				int categoriaId = rs.getInt("categoria_fk");
				int profesorId = rs.getInt("profesor_fk");

				curso = new Curso(id, titulo, descripcion, precio, activo, categoriaId, profesorId, categoria,
						profesor);
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
					"SELECT c.id, c.titulo, c.descripcion, c.precio, ca.nombre , concat(u.nombre, ' ' , u.apellido) as 'profesor', c.profesor_fk, c.categoria_fk, c.activo, ca.nombre as 'categoria' FROM cursos c inner join categorias ca on c.categoria_fk = ca.id inner join usuarios u on u.id = c.profesor_fk WHERE c.id NOT IN (SELECT curso_id FROM inscripciones WHERE user_id = 6)");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				String categoria = rs.getString("categoria");
				String profesor = rs.getString("profesor");
				boolean activo = rs.getBoolean("activo");
				int categoriaId = rs.getInt("categoria_fk");
				int profesorId = rs.getInt("profesor_fk");

				cursos.add(new Curso(id, titulo, descripcion, precio, activo, categoriaId, profesorId, categoria,
						profesor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursos;
	}
	
	public static LinkedList<Curso> mostrarCursosByProfesor(int profesorId) {
		LinkedList<Curso> cursos = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT c.id, c.titulo, c.descripcion, c.precio, ca.nombre , concat(u.nombre, ' ' , u.apellido) as 'profesor', c.profesor_fk, c.categoria_fk, c.activo, ca.nombre as 'categoria' FROM cursos c inner join categorias ca on c.categoria_fk = ca.id inner join usuarios u on u.id = c.profesor_fk WHERE c.profesor_fk = ?");
			stmt.setInt(1, profesorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				String categoria = rs.getString("categoria");
				String profesor = rs.getString("profesor");
				boolean activo = rs.getBoolean("activo");
				int categoriaId = rs.getInt("categoria_fk");

				cursos.add(new Curso(id, titulo, descripcion, precio, activo, categoriaId, profesorId, categoria,
						profesor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursos;
	}
}
