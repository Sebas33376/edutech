package GUI;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] menu = { "Login", "Salir" };

		int opcion;
		do {
			opcion = JOptionPane.showOptionDialog(null, "Bienvenido a EduTech", "EduTech", 0,
					JOptionPane.DEFAULT_OPTION, null, menu, menu[0]);
			if (opcion == 0) {
				String email = JOptionPane.showInputDialog("Ingrese mail");
				String contrasena = JOptionPane.showInputDialog("Ingrese contraseña");
				if (email.equals("admin@gmail.com") && contrasena.equals("1234")) {
					int opcionAdmin;
					String[] opAdmin = { "Crear Profesor", "Crear Categoria", "Ver Usuarios", "Cerrar Sesion" };
					do {
						opcionAdmin = JOptionPane.showOptionDialog(null,
								"Bienvenido Administrador (Aca se va a mostrar el nombre del admin)", "", 0, 0, null,
								opAdmin, opAdmin[0]);
						switch (opcionAdmin) {
						case 0:
							String nombreProf = JOptionPane.showInputDialog("Ingrese nombre del profesor");
							String legajoProf = JOptionPane.showInputDialog("Ingrese legajo");
							JOptionPane.showMessageDialog(null,
									"Profesor " + nombreProf + " (legajo " + legajoProf + ") creado con exito");
							break;
						case 1:
							String nombreCat = JOptionPane.showInputDialog("Ingrese nombre de la categoria");
							JOptionPane.showMessageDialog(null, "Categoria " + nombreCat + " creada con exito");
							break;
						case 2:
							JOptionPane.showMessageDialog(null,
									"Ver Usuarios\n -----------------------\nMuestra el listado de usuarios del sistema");
							break;
						default:
							break;
						}
					} while (opcionAdmin != 3);
				} else if (email.equals("profesor@gmail.com") && contrasena.equals("1234")) {
					int opcionProfesor;
					String[] opProfesor = { "Crear Curso", "Crear Clase", "Crear Tarea", "Evaluar Entrega",
							"Cerrar Sesion" };
					do {
						opcionProfesor = JOptionPane.showOptionDialog(null,
								"Bienvenido Profesor (Aca se va a mostrar el nombre del profesor)", "", 0, 0, null,
								opProfesor, opProfesor[0]);
						switch (opcionProfesor) {
						case 0:
							String tituloCurso = JOptionPane.showInputDialog("Ingrese titulo del curso");
							String descCurso = JOptionPane.showInputDialog("Ingrese descripcion");
							JOptionPane.showMessageDialog(null, "Curso " + tituloCurso + " creado con exito");
							break;
						case 1:
							String tituloClase = JOptionPane.showInputDialog("Ingrese titulo de la clase");
							JOptionPane.showMessageDialog(null,
									"Clase " + tituloClase + " creada (bloqueada por defecto)");
							break;
						case 2:
							String tituloTarea = JOptionPane.showInputDialog("Ingrese titulo de la tarea");
							String[] opTipoEntrega = { "Choice", "Entregable" };
							int tipoEntrega = JOptionPane.showOptionDialog(null, "Seleccione el tipo de entrega", "", 0,
									0, null, opTipoEntrega, opTipoEntrega[0]);
							JOptionPane.showMessageDialog(null,
									"Tarea " + tituloTarea + " creada con tipo " + opTipoEntrega[tipoEntrega]);
							break;
						case 3:
							String notaStr = JOptionPane.showInputDialog("Ingrese la nota (0 a 10)");
							JOptionPane.showMessageDialog(null, "Entrega evaluada con nota " + notaStr);
							break;
						default:
							break;
						}
					} while (opcionProfesor != 4);
				} else if (email.equals("alumno@gmail.com") && contrasena.equals("1234")) {
					int opcionAlumno;
					String[] opAlumno = { "Ver Cursos Disponibles", "Anotarse a Curso", "Entregar Tarea",
							"Ver Nota Final", "Cerrar Sesion" };
					do {
						opcionAlumno = JOptionPane.showOptionDialog(null,
								"Bienvenido Alumno (Aca se va a mostrar el nombre del alumno)", "", 0, 0, null,
								opAlumno, opAlumno[0]);
						switch (opcionAlumno) {
						case 0:
							JOptionPane.showMessageDialog(null,
									"Ver Cursos Disponibles\n -----------------------\nMuestra el listado de cursos abiertos");
							break;
						case 1:
							String cursoAnotarse = JOptionPane
									.showInputDialog("Ingrese el titulo del curso al que desea anotarse");
							JOptionPane.showMessageDialog(null, "Te anotaste correctamente al curso " + cursoAnotarse);
							break;
						case 2:
							String urlEntrega = JOptionPane.showInputDialog("Ingrese la URL o archivo de la entrega");
							JOptionPane.showMessageDialog(null, "Entrega realizada, queda en estado Pendiente");
							break;
						case 3:
							JOptionPane.showMessageDialog(null,
									"Ver Nota Final\n -----------------------\nMuestra el promedio de tus entregas evaluadas");
							break;
						default:
							break;
						}
					} while (opcionAlumno != 4);
				} else {
					JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos");
				}
			}
		} while (opcion != 1);
	}
}
