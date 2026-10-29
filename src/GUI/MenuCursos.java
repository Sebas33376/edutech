package GUI;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import BLL.Alumno;
import BLL.Curso;
import BLL.Inscripcion;
import BLL.Profesor;
import BLL.Administrador;
import BLL.Usuario;
import DLL.ControllerCurso;
import DLL.ControllerInscripcion;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.EventQueue;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private LinkedList<Inscripcion> inscripciones;
	private LinkedList<Curso> cursos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario.logueado = new Profesor(3, "prof_juan", "Juan", "Pérez",
							"$2a$10$vI8aWBnW3fID.ZQ4/zo1G.q1lRps.9cGLcZEiGDMVr5yUP1KUOYTa", true,
							"juan.perez@davinci.edu.ar", "LEG-101", "Ingeniero en Sistemas", 1);
					MenuCursos frame = new MenuCursos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuCursos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Cursos");
		contentPane.setLayout(null);
		
		Usuario usuario = Usuario.logueado;
		
		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 10, 178, 443);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);

		JButton btnMiPerfil = new JButton("Mi perfil");
		btnMiPerfil.setMargin(new Insets(10, 14, 10, 14));
		btnMiPerfil.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMiPerfil.addActionListener(e -> {
			PerfilUsuario perfilAlumno = new PerfilUsuario();
			perfilAlumno.setVisible(true);
			dispose();
		});
		toolBar.add(btnMiPerfil);
		if (usuario instanceof Alumno) {
			cursos = ControllerCurso.mostrarCursos();
			inscripciones = ControllerInscripcion.getInscripcionesByAlumnoId(usuario.getId());
			model = new DefaultTableModel(new Object[] { "Título", "Descripción", "Categoría", "Profesor", "Precio" },
					0);
			table = new JTable(model);

			table.getSelectionModel().addListSelectionListener(e -> {

				if (!e.getValueIsAdjusting()) {
					int row = table.getSelectedRow();
					if (row != -1) {
						Curso cursoSeleccionado = cursos.get(row);
						DetalleCurso detalle = new DetalleCurso(cursoSeleccionado);
						detalle.setVisible(true);
						dispose();
					}
				}
			});
			getCursos(cursos, model);
			JButton btnCursos = new JButton("Mis cursos");
			btnCursos.setMargin(new Insets(10, 14, 10, 14));
			btnCursos.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnCursos.addActionListener(e -> {
				if (btnCursos.getText().equals("Mis cursos")) {
					btnCursos.setText("Cursos");
					getInscripciones(inscripciones, model);
				} else if (btnCursos.getText().equals("Cursos")) {
					btnCursos.setText("Mis cursos");
					getCursos(cursos, model);

				}
			});
			toolBar.add(btnCursos);
		} else if (usuario instanceof Profesor) {
			cursos = ControllerCurso.mostrarCursosByProfesor(usuario.getId());
			model = new DefaultTableModel(new Object[] { "Título", "Descripción", "Categoría", "Activo" }, 0);
			table = new JTable(model);
			table.getSelectionModel().addListSelectionListener(e -> {

				if (!e.getValueIsAdjusting()) {
					int row = table.getSelectedRow();
					if (row != -1) {
						Curso cursoSeleccionado = cursos.get(row);
						MenuClases detalle = new MenuClases(cursoSeleccionado.getId());
						detalle.setVisible(true);
						dispose();
					}
				}
			});
			getCursosImpartidos(cursos, model);
			JButton btnCursos = new JButton("Cursos");
			btnCursos.setMargin(new Insets(10, 14, 10, 14));
			btnCursos.setBorder(new EmptyBorder(0, 0, 0, 0));
			toolBar.add(btnCursos);

		} else if (usuario instanceof Administrador) {
		}

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario.logueado = null;
				PantallaPrincipal inicio = new PantallaPrincipal();
				inicio.setVisible(true);
				dispose();
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(198, 10, 675, 443);
		getContentPane().add(scrollPane);
		btnCerrarSesion.setMargin(new Insets(10, 14, 10, 14));
		btnCerrarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));

		toolBar.add(btnCerrarSesion);
		setVisible(true);
	}

	private void getCursos(LinkedList<Curso> cursos, DefaultTableModel model) {
		model.setRowCount(0);
		for (Curso curso : cursos) {
			if (curso.isActivo()) {
				String precio = String.format("$%.2f", curso.getPrecio());
				model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
						curso.getProfesor(), precio });
			}
		}

	};

	private void getCursosImpartidos(LinkedList<Curso> cursos, DefaultTableModel model) {
		model.setRowCount(0);
		List<Curso> cursosByProfesor = cursos.stream().filter(curso -> curso.getProfesorId() == Usuario.logueado.getId())
				.collect(Collectors.toList());
		for (Curso curso : cursosByProfesor) {
			model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
					curso.isActivo() ? "Activo" : "Inactivo" });

		}

	};

	private void getInscripciones(LinkedList<Inscripcion> inscripciones, DefaultTableModel model) {
		model.setRowCount(0);
		for (Inscripcion inscripcion : inscripciones) {
			Curso curso = ControllerCurso.getCursoById(inscripcion.getCursoId());
			String precio = String.format("$%.2f", curso.getPrecio());
			model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
					curso.getProfesor(), precio });
		}
	}
}
