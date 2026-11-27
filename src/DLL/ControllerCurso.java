package DLL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.sql.PreparedStatement;

import BLL.Clase;
import BLL.Curso;
import BLL.Tarea;

public class ControllerCurso {
	private static Connection conexion = Conexion.getInstance().getConnection();

	public static boolean crearCurso(Curso curso, List<Clase> clases, List<Tarea> tareas) {
		boolean autoCommitOriginal = true;
		try {
			autoCommitOriginal = conexion.getAutoCommit();
			conexion.setAutoCommit(false);

			PreparedStatement stmtCurso = conexion.prepareStatement(
					"INSERT INTO cursos (titulo, descripcion, precio, categoria_fk, profesor_fk, activo) VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmtCurso.setString(1, curso.getTitulo());
			stmtCurso.setString(2, curso.getDescripcion());
			stmtCurso.setDouble(3, curso.getPrecio());
			stmtCurso.setInt(4, curso.getCategoriaId());
			stmtCurso.setInt(5, curso.getProfesorId());
			stmtCurso.setBoolean(6, false);
			stmtCurso.executeUpdate();

			ResultSet keysCurso = stmtCurso.getGeneratedKeys();
			if (!keysCurso.next()) {
				throw new SQLException("No se obtuvo el id del curso");
			}
			int cursoId = keysCurso.getInt(1);

			for (int i = 0; i < clases.size(); i++) {
				Clase clase = clases.get(i);
				PreparedStatement stmtClase = conexion.prepareStatement(
						"INSERT INTO clases (titulo, url, descripcion, curso_fk) VALUES (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				stmtClase.setString(1, clase.getTitulo());
				stmtClase.setString(2, clase.getUrl());
				stmtClase.setString(3, clase.getDescripcion());
				stmtClase.setInt(4, cursoId);
				stmtClase.executeUpdate();

				ResultSet keysClase = stmtClase.getGeneratedKeys();
				if (!keysClase.next()) {
					throw new SQLException("No se obtuvo el id de la clase " + (i + 1));
				}
				int claseId = keysClase.getInt(1);

				Tarea plantilla = tareas.get(i);
				if (plantilla != null) {
					Tarea tarea = new Tarea(plantilla.getTitulo(), plantilla.getDescripcion(), plantilla.getTipo(),
							plantilla.getUrl(), claseId);
					if (!ControllerTarea.setTarea(tarea)) {
						throw new SQLException("No se pudo guardar la tarea de la clase " + (i + 1));
					}
				}
			}

			conexion.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		} finally {
			try {
				conexion.setAutoCommit(autoCommitOriginal);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

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

	public static LinkedList<Curso> mostrarCursosByCategoria(int categoriaId) {
		LinkedList<Curso> cursos = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT c.id, c.titulo, c.descripcion, c.precio, ca.nombre , concat(u.nombre, ' ' , u.apellido) as 'profesor', c.profesor_fk, c.categoria_fk, c.activo, ca.nombre as 'categoria' FROM cursos c inner join categorias ca on c.categoria_fk = ca.id inner join usuarios u on u.id = c.profesor_fk WHERE c.categoria_fk = ?");
			stmt.setInt(1, categoriaId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				String categoria = rs.getString("categoria");
				String profesor = rs.getString("profesor");
				boolean activo = rs.getBoolean("activo");
				int profesorId = rs.getInt("profesor_fk");

				cursos.add(new Curso(id, titulo, descripcion, precio, activo, categoriaId, profesorId, categoria,
						profesor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursos;
	}

	public static boolean activarCurso(int cursoId, double precio) {
		try {
			PreparedStatement stmt = conexion.prepareStatement("UPDATE cursos SET activo = TRUE, precio = ? WHERE id = ?");
			stmt.setDouble(1, precio);
			stmt.setInt(2, cursoId);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean desactivarCurso(int cursoId) {
		try {
			PreparedStatement stmt = conexion.prepareStatement("UPDATE cursos SET activo = FALSE WHERE id = ?");
			stmt.setInt(1, cursoId);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
