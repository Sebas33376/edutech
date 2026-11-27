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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import repository.Validaciones;

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
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class MenuCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Curso cursoSeleccionado;
	private LinkedList<Inscripcion> inscripciones;
	private LinkedList<Curso> cursos;
	private LinkedList<Curso> cursosEnTabla;

	public MenuCursos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 500);
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
		toolBar.setBounds(10, 10, 178, 440);
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

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(198, 386, 896, 64);
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(null);

		JButton btnVerCurso = new JButton("Ver curso");
		btnVerCurso.setBounds(10, 11, 155, 42);
		btnVerCurso.setEnabled(false);
		btnVerCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalleCurso detalle = new DetalleCurso(cursoSeleccionado);
				detalle.setVisible(true);
				dispose();
			}
		});
		buttonsPanel.add(btnVerCurso);

		JButton btnCrearCurso = new JButton("Crear curso");
		btnCrearCurso.setBounds(175, 11, 155, 42);
		btnCrearCurso.addActionListener(e -> {
			CrearCurso crearForm = new CrearCurso();
			crearForm.setVisible(true);
			dispose();
		});
		btnCrearCurso.setVisible(usuario instanceof Profesor);
		buttonsPanel.add(btnCrearCurso);

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
						cursoSeleccionado = cursosEnTabla.get(row);
						btnVerCurso.setEnabled(true);
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
						btnVerCurso.setEnabled(true);
						cursoSeleccionado = cursosEnTabla.get(row);

					}
				}
			});
			getCursosImpartidos(cursos, model);
			JButton btnCursos = new JButton("Cursos");
			btnCursos.setMargin(new Insets(10, 14, 10, 14));
			btnCursos.setBorder(new EmptyBorder(0, 0, 0, 0));
			toolBar.add(btnCursos);

		} else if (usuario instanceof Administrador) {
			Administrador admin = (Administrador) usuario;
			cursos = ControllerCurso.mostrarCursosByCategoria(admin.getCategoriaId());
			model = new DefaultTableModel(new Object[] { "Título", "Descripción", "Profesor", "Precio", "Estado" }, 0);
			table = new JTable(model);

			JButton btnAlta = new JButton("Dar de alta");
			btnAlta.setBounds(175, 11, 155, 42);
			btnAlta.setEnabled(false);
			buttonsPanel.add(btnAlta);

			JButton btnBaja = new JButton("Dar de baja");
			btnBaja.setBounds(340, 11, 155, 42);
			btnBaja.setEnabled(false);
			buttonsPanel.add(btnBaja);

			JLabel lblPrecio = new JLabel("Precio");
			lblPrecio.setBounds(510, 8, 100, 14);
			buttonsPanel.add(lblPrecio);

			JTextField tfPrecio = new JTextField();
			tfPrecio.setBounds(510, 26, 120, 26);
			tfPrecio.setEnabled(false);
			buttonsPanel.add(tfPrecio);

			table.getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int row = table.getSelectedRow();
					if (row != -1) {
						cursoSeleccionado = cursosEnTabla.get(row);
						boolean activo = cursoSeleccionado.isActivo();
						btnVerCurso.setEnabled(true);
						btnAlta.setEnabled(!activo);
						btnBaja.setEnabled(activo);
						tfPrecio.setEnabled(!activo);
						tfPrecio.setText(activo || cursoSeleccionado.getPrecio() == 0 ? ""
								: String.valueOf(cursoSeleccionado.getPrecio()));
					} else {
						btnVerCurso.setEnabled(false);
						btnAlta.setEnabled(false);
						btnBaja.setEnabled(false);
						tfPrecio.setEnabled(false);
						tfPrecio.setText("");
					}
				}
			});

			btnAlta.addActionListener(e -> {
				if (!Validaciones.esPrecioValido(tfPrecio.getText())) {
					JOptionPane.showMessageDialog(null, "Ingresá un precio válido, mayor a 0 (por ejemplo 15000 o 15000,50)");
					return;
				}
				double precio = Validaciones.convertirPrecio(tfPrecio.getText());
				if (Curso.darDeAlta(cursoSeleccionado, precio)) {
					JOptionPane.showMessageDialog(null, "Curso dado de alta");
					cursos = ControllerCurso.mostrarCursosByCategoria(admin.getCategoriaId());
					getCursosAdministrador(cursos, model);
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo dar de alta el curso");
				}
			});

			btnBaja.addActionListener(e -> {
				int respuesta = JOptionPane.showConfirmDialog(null,
						"¿Dar de baja el curso \"" + cursoSeleccionado.getTitulo() + "\"?");
				if (respuesta != JOptionPane.YES_OPTION) {
					return;
				}
				if (Curso.darDeBaja(cursoSeleccionado)) {
					JOptionPane.showMessageDialog(null, "Curso dado de baja");
					cursos = ControllerCurso.mostrarCursosByCategoria(admin.getCategoriaId());
					getCursosAdministrador(cursos, model);
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo dar de baja el curso");
				}
			});

			getCursosAdministrador(cursos, model);

			JButton btnCursos = new JButton("Cursos");
			btnCursos.setMargin(new Insets(10, 14, 10, 14));
			btnCursos.setBorder(new EmptyBorder(0, 0, 0, 0));
			toolBar.add(btnCursos);

			JButton btnUsuarios = new JButton("Usuarios");
			btnUsuarios.setMargin(new Insets(10, 14, 10, 14));
			btnUsuarios.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnUsuarios.addActionListener(e -> {
				MenuUsuarios menuUsuarios = new MenuUsuarios();
				menuUsuarios.setVisible(true);
				dispose();
			});
			toolBar.add(btnUsuarios);
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
		scrollPane.setBounds(198, 10, 896, 365);
		getContentPane().add(scrollPane);

		btnCerrarSesion.setMargin(new Insets(10, 14, 10, 14));
		btnCerrarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));
		toolBar.add(btnCerrarSesion);
		setVisible(true);
	}

	private void getCursos(LinkedList<Curso> cursos, DefaultTableModel model) {
		model.setRowCount(0);
		cursosEnTabla = new LinkedList<>();
		for (Curso curso : cursos) {
			if (curso.isActivo()) {
				String precio = String.format("$%.2f", curso.getPrecio());
				model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
						curso.getProfesor(), precio });
				cursosEnTabla.add(curso);
			}
		}

	};

	private void getCursosImpartidos(LinkedList<Curso> cursos, DefaultTableModel model) {
		model.setRowCount(0);
		cursosEnTabla = new LinkedList<>();
		List<Curso> cursosByProfesor = cursos.stream()
				.filter(curso -> curso.getProfesorId() == Usuario.logueado.getId()).collect(Collectors.toList());
		for (Curso curso : cursosByProfesor) {
			model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
					curso.isActivo() ? "Activo" : "Inactivo" });
			cursosEnTabla.add(curso);
		}

	};

	private void getCursosAdministrador(LinkedList<Curso> cursos, DefaultTableModel model) {
		model.setRowCount(0);
		cursosEnTabla = new LinkedList<>();
		for (Curso curso : cursos) {
			String precio = String.format("$%.2f", curso.getPrecio());
			model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getProfesor(), precio,
					curso.isActivo() ? "Activo" : "Inactivo" });
			cursosEnTabla.add(curso);
		}
	};

	private void getInscripciones(LinkedList<Inscripcion> inscripciones, DefaultTableModel model) {
		model.setRowCount(0);
		cursosEnTabla = new LinkedList<>();
		for (Inscripcion inscripcion : inscripciones) {
			Curso curso = ControllerCurso.getCursoById(inscripcion.getCursoId());
			String precio = String.format("$%.2f", curso.getPrecio());
			model.addRow(new Object[] { curso.getTitulo(), curso.getDescripcion(), curso.getCategoria(),
					curso.getProfesor(), precio });
			cursosEnTabla.add(curso);
		}
	}
}
