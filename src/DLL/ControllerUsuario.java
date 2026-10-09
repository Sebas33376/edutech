package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Administrador;
import BLL.Alumno;
import BLL.Profesor;
import BLL.Usuario;
import repository.Hashing;

public class ControllerUsuario<T extends Usuario> {

	private static Connection conexion = Conexion.getInstance().getConnection();

	public <T> T login(String email, String password) {

		T usuario = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT *, CASE WHEN p.id IS NOT NULL THEN 'PROFESOR' WHEN a.id IS NOT NULL THEN 'ALUMNO' ELSE 'ADMIN' END AS rol FROM usuarios u LEFT JOIN profesores p ON u.id = p.id LEFT JOIN alumnos a ON u.id = a.id WHERE u.email = ? AND u.activo = TRUE;");
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String contrasena = rs.getString("contrasena");
				boolean activo = rs.getBoolean("activo");
				String rol = rs.getString("rol");

				if (Hashing.verificar(password, contrasena)) {
					switch (rol) {
					case "PROFESOR":
						String legajo = rs.getString("legajo");
						String profecion = rs.getString("profesion");
						int categoriaId = rs.getInt("categoria_fk");
						usuario = (T) new Profesor(id, userName, nombre, apellido, contrasena, activo, email, legajo,
								profecion, categoriaId);
						break;

					case "ALUMNO":
						usuario = (T) new Alumno(id, userName, nombre, apellido, contrasena, activo, email);
						break;

					case "ADMIN":

						break;

					default:
						System.out.println("Tipo de usuario desconocido: " + rol);
						break;
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	};

	public void agregarUsuario(Usuario usuario) {
		try {
			PreparedStatement statement = conexion.prepareStatement(
					"INSERT INTO usuarios (id, userName, nombre, apellido, contrasena, activo, email) VALUES  VALUES (?,?,?,?,?,?,?)");
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getUserName());
			statement.setString(3, usuario.getNombre());
			statement.setString(4, usuario.getApellido());
			statement.setString(5, Hashing.hash(usuario.getContrasena()));
			statement.setBoolean(6, usuario.isActivo());
			statement.setString(7, usuario.getEmail());

			if (usuario instanceof Alumno) {
				Alumno alumno = (Alumno) usuario;
				PreparedStatement statementAlu = conexion
						.prepareStatement("INSERT INTO alumnos (id, fecha_ingreso) VALUES (?,?)");
				statementAlu.setInt(1, alumno.getId());
				statementAlu.setDate(2, java.sql.Date.valueOf(alumno.getFechaIngreso()));
			} else if (usuario instanceof Profesor) {
				Profesor profesor = (Profesor) usuario;
				PreparedStatement statementProf = conexion.prepareStatement(
						"INSERT INTO profesores (id, legajo, profesion, categoria_fk) VALUES (?,?,?,?)");
				statementProf.setInt(1, profesor.getId());
				statementProf.setString(2, profesor.getLegajo());
				statementProf.setString(3, profesor.getProfesion());
				statementProf.setInt(4, profesor.getCategoriaId());
			} else if (usuario instanceof Administrador) {
				Administrador admin = (Administrador) usuario;
				PreparedStatement statementAdmin = conexion
						.prepareStatement("INSERT INTO administradores (id, categoria_fk) VALUES (?,?)");
				statementAdmin.setInt(1, admin.getId());
				statementAdmin.setInt(4, admin.getCategoriaId());
			} else {
				System.out.println("Tipo de usuario desconocido");
			}
			;

			int filas = statement.executeUpdate();
			if (filas > 0) {
				System.out.println("Usuario agregado correctamente.");
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "No se puede crear usuario con mail existente");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedList<Usuario> mostrarUsuarios() {
		LinkedList<Usuario> usuarios = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT \r\n" + "   u.id,\r\n" + "    u.userName,\r\n"
					+ "    u.nombre,\r\n" + "    u.apellido,\r\n" + "    u.email,\r\n" + "    u.activo,\r\n"
					+ "    p.legajo,\r\n" + "    p.profesion,\r\n" + "    a.fecha_ingreso,\r\n"
					+ "   COALESCE(p.categoria_fk, adm.categoria_fk) AS categoria_fk,\r\n" + "    CASE \r\n"
					+ "        WHEN p.id IS NOT NULL THEN 'PROFESOR'\r\n"
					+ "        WHEN a.id IS NOT NULL THEN 'ALUMNO'\r\n" + "        ELSE 'ADMIN'\r\n"
					+ "    END AS rol\r\n" + "FROM usuarios u\r\n" + "LEFT JOIN profesores p ON u.id = p.id\r\n"
					+ "LEFT JOIN alumnos a ON u.id = a.id\r\n" + "LEFT JOIN administradores adm ON u.id = adm.id\r\n"
					+ "WHERE u.activo = TRUE;");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				boolean activo = rs.getBoolean("activo");
				String rol = rs.getString("rol");
				String email = rs.getString("email");
				String legajo;
				String profecion;
				int categoriaId;
				switch (rol) {
				case "PROFESOR":
					legajo = rs.getString("legajo");
					profecion = rs.getString("profesion");
					categoriaId = rs.getInt("categoria_fk");
					usuarios.add((T) new Profesor(id, userName, nombre, apellido, activo, email, legajo, profecion,
							categoriaId));
					break;

				case "ALUMNO":
					LocalDate fechaIngreso = rs.getDate("fecha_ingreso").toLocalDate();
					usuarios.add((T) new Alumno(id, userName, nombre, apellido, activo, email, fechaIngreso));
					break;

				case "ADMIN":
					categoriaId = rs.getInt("categoria_fk");
					usuarios.add((T) new Administrador(id, userName, nombre, apellido, activo, email, categoriaId));
					break;

				default:
					System.out.println("Tipo de usuario desconocido: " + rol);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public LinkedList<Usuario> mostrarAlumnos() {
		LinkedList<Usuario> usuarios = new LinkedList<>();
		try {
			PreparedStatement stmt = conexion.prepareStatement(
					"SELECT u.id, u.userName, u.nombre, u.apellido, u.activo, u.email, a.fecha_ingreso FROM usuarios u INNER JOIN alumnos a ON a.id = u.id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				boolean activo = rs.getBoolean("activo");
				String email = rs.getString("email");
				LocalDate fechaIngreso = rs.getDate("fecha_ingreso").toLocalDate();

				usuarios.add((T) new Alumno(id, userName, nombre, apellido, activo, email, fechaIngreso));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
}
