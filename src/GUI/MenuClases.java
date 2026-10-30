package GUI;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Administrador;
import BLL.Alumno;
import BLL.Clase;
import BLL.Curso;
import BLL.Profesor;
import BLL.Usuario;
import DLL.ControllerClase;
import DLL.ControllerInscripcion;
import javax.swing.JLabel;

public class MenuClases extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private LinkedList<Clase> clases;
	private Clase claseSeleccionada;

	public MenuClases(Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Clases");
		contentPane.setLayout(null);

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}
		if (usuario instanceof Alumno) {
			clases = ControllerClase.getClasesByCurso(curso.getId());
			model = new DefaultTableModel(
					new Object[] { "Título", "Clase", "Descripción", "Curso", "Tarea", "Entregado" }, 0);
			model.setRowCount(0);
			for (Clase clase : clases) {
				model.addRow(new Object[] { clase.getTitulo(), clase.getUrl(), clase.getDescripcion(), clase.getCurso(),
						clase.isHasTarea() ? "Tiene" : "No tiene",
						clase.getEntregado()});
			}
		} else if (usuario instanceof Profesor) {
			clases = ControllerClase.getClasesByCursoAndProfesor(curso.getId(), usuario.getId());
			model = new DefaultTableModel(
					new Object[] { "Título", "Clase", "Descripción", "Curso", "Tarea", "Entregas" }, 0);
			model.setRowCount(0);
			for (Clase clase : clases) {
				model.addRow(new Object[] { clase.getTitulo(), clase.getUrl(), clase.getDescripcion(), clase.getCurso(),
						clase.isHasTarea() ? "Tiene" : "No tiene",
						clase.getCantEntregas() == 0 ? "Sin entregas" : clase.getCantEntregas() });
			}
		} else if (usuario instanceof Administrador) {

		}

		table = new JTable(model);
		JPanel panel = new JPanel();
		panel.setBounds(198, 384, 896, 69);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnTareas = new JButton("Ver tarea");
		btnTareas.setBounds(70, 11, 130, 47);
		btnTareas.setEnabled(false);
		btnTareas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(claseSeleccionada);
				MenuTareas menu = new MenuTareas(claseSeleccionada, curso);
				menu.setVisible(true);
				dispose();
			}
		});
		

		JButton btnAgregarTarea = new JButton("Agregar tarea");
		btnAgregarTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarTarea agregar = new AgregarTarea(claseSeleccionada, curso);
				agregar.setVisible(true);
				dispose();
			}
		});
		btnAgregarTarea.setEnabled(false);
		btnAgregarTarea.setBounds(270, 11, 130, 47);
		
		
		JButton btnEditar = new JButton("Editar Clase");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarClase editMenu = new EditarClase(claseSeleccionada, curso);
				editMenu.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBounds(470, 11, 130, 47);
		
		JButton btnComprar = new JButton("Comprar curso");
		btnComprar.setBounds(670, 11, 150, 47);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPago pantallaPago = new PantallaPago(curso);
				pantallaPago.setVisible(true);
				dispose();
			}
		});
		if (usuario instanceof Alumno) {
			boolean estaInscripto = ControllerInscripcion.getInscripcionesByAlumnoId(usuario.getId()).stream()
					.anyMatch(inscripcion -> inscripcion.getCursoId() == curso.getId());
			btnComprar.setVisible(!estaInscripto);
			btnTareas.setVisible(!estaInscripto);
		} else {
			btnComprar.setVisible(false);
		}
		panel.add(btnTareas);
		panel.add(btnComprar);

		if (usuario instanceof Profesor) {
			panel.add(btnAgregarTarea);
			panel.add(btnEditar);
		}
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					claseSeleccionada = clases.get(row);
					btnEditar.setEnabled(true);
					btnAgregarTarea.setEnabled(!claseSeleccionada.isHasTarea());
					btnTareas.setEnabled(claseSeleccionada.isHasTarea());
				}
			}
		});

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

		JButton btnCursos = new JButton("Cursos");
		btnCursos.setMargin(new Insets(10, 14, 10, 14));
		btnCursos.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCursos.addActionListener(e -> {
			MenuCursos menu = new MenuCursos();
			menu.setVisible(true);
			dispose();
		});
		toolBar.add(btnCursos);

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
		scrollPane.setBounds(196, 104, 898, 269);
		getContentPane().add(scrollPane);
		btnCerrarSesion.setMargin(new Insets(10, 14, 10, 14));
		btnCerrarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));

		toolBar.add(btnCerrarSesion);

		String html = "<html><H1>" + curso.getTitulo() + "</H1><span>" + curso.getCategoria() + "</span><p>"
				+ curso.getDescripcion() + "</p></html>";

		JLabel lblCurso = new JLabel(html);
		lblCurso.setBounds(196, 10, 675, 85);
		contentPane.add(lblCurso);
		setVisible(true);

	}
}