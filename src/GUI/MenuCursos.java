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
					Usuario usuario = new Profesor(3, "prof_juan", "Juan", "Pérez",
							"$2a$12$T86dOi0GsX5GONhfSDnHUe79ki4wVMFk9yYdHw7yGPCEpcb5gCgqy", true,
							"juan.perez@davinci.edu.ar", "LEG-101", "Ingeniero en Sistemas", 1);
					MenuCursos frame = new MenuCursos(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuCursos(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Cursos");
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 10, 178, 443);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);

		cursos = ControllerCurso.mostrarCursos();
		inscripciones = ControllerInscripcion.getInscripcionesByAlumnoId(usuario.getId());

		model = new DefaultTableModel(new Object[] { "Título", "Descripción", "Categoría", "Profesor", "Precio" },
				cursos.size());
		table = new JTable(model);

		table.getSelectionModel().addListSelectionListener(e -> {

			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					Curso cursoSeleccionado = cursos.get(row);
					DetalleCurso detalle = new DetalleCurso(cursoSeleccionado, usuario);
					detalle.setVisible(true);
					dispose();
				}
			}
		});
		if (usuario instanceof Alumno) {
		
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

			getCursosImpartidos(cursos, model, usuario);

		} else if (usuario instanceof Administrador) {
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(198, 10, 675, 443);
		getContentPane().add(scrollPane);

		JButton btnMiPerfil = new JButton("Mi perfil");
		btnMiPerfil.setMargin(new Insets(10, 14, 10, 14));
		btnMiPerfil.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMiPerfil.addActionListener(e -> {
			PerfilUsuario perfilAlumno = new PerfilUsuario(usuario);
			perfilAlumno.setVisible(true);
			dispose();
		});
		toolBar.add(btnMiPerfil);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal inicio = new PantallaPrincipal();
				inicio.setVisible(true);
				dispose();
			}
		});
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

	private void getCursosImpartidos(LinkedList<Curso> cursos, DefaultTableModel model, Usuario usuario) {
		model.setRowCount(0);
		List<Curso> cursosByProfesor = cursos.stream().filter(curso -> curso.getProfesorId() == usuario.getId())
				.collect(Collectors.toList());
		System.out.println(cursosByProfesor);
		for (Curso curso : cursosByProfesor) {
			if (curso.isActivo()) {
				String precio = String.format("$%.2f", curso.getPrecio());
				model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
						curso.getProfesor(), precio });
			}
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
