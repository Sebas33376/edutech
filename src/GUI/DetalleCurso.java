package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class DetalleCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private LinkedList<Clase> clases;
	private Clase claseSeleccionada;
	private JTable table;

	public DetalleCurso(Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Curso");
		contentPane.setLayout(null);

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		JPanel panelCurso = new JPanel();
		panelCurso.setBounds(10, 48, 486, 324);
		contentPane.add(panelCurso);
		panelCurso.setLayout(null);

		JPanel panelActions = new JPanel();
		panelActions.setBounds(10, 382, 486, 69);
		contentPane.add(panelActions);
		panelActions.setLayout(null);

		String html = "<html><H1>" + curso.getTitulo() + "</H1><span>" + curso.getCategoria() + "</span><p>"
				+ curso.getDescripcion() + "</p></html>";

		JLabel lblDetalleCurso = new JLabel(html);
		lblDetalleCurso.setBounds(10, 10, 466, 304);
		panelCurso.add(lblDetalleCurso);

		if (usuario instanceof Alumno) {
			clases = ControllerClase.getClasesByCurso(curso.getId());
			model = new DefaultTableModel(
					new Object[] { "Título", "Clase", "Descripción", "Curso", "Tarea", "Entregado" }, 0);
			model.setRowCount(0);
			for (Clase clase : clases) {
				model.addRow(new Object[] { clase.getTitulo(), clase.getUrl(), clase.getDescripcion(), clase.getCurso(),
						clase.isHasTarea() ? "Tiene" : "No tiene", clase.getEntregado() });
			}

		} else if (usuario instanceof Profesor) {
			clases = ControllerClase.getClasesByCursoAndProfesor(curso.getId(), usuario.getId());
			model = new DefaultTableModel(
					new Object[] { "Título", "Clase", "Descripción", "Curso", "Tarea", "Entregas" }, 0);
			model.setRowCount(0);
			for (Clase clase : clases) {
				System.out.println(clase.getTitulo());
				model.addRow(new Object[] { clase.getTitulo(), clase.getUrl(), clase.getDescripcion(), clase.getCurso(),
						clase.isHasTarea() ? "Tiene" : "No tiene",
						clase.getCantEntregas() == 0 ? "Sin entregas" : clase.getCantEntregas() });

			}
		} else if (usuario instanceof Administrador) {

		}

		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(526, 10, 476, 441);

		contentPane.add(scrollPane);

		JButton btnClase = new JButton("Ver clase");
		btnClase.setBounds(24, 11, 130, 47);
		btnClase.setEnabled(false);
		btnClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalleClase menu = new DetalleClase(claseSeleccionada, curso);
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
		btnAgregarTarea.setBounds(332, 11, 130, 47);

		JButton btnEditar = new JButton("Editar Clase");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarClase editMenu = new EditarClase(claseSeleccionada, curso);
				editMenu.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBounds(178, 11, 130, 47);

		JButton btnComprar = new JButton("Comprar curso");
		btnComprar.setBounds(178, 11, 130, 47);
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
			btnClase.setVisible(estaInscripto);
		} else {
			btnComprar.setVisible(false);
		}
		panelActions.add(btnClase);
		panelActions.add(btnComprar);

		if (usuario instanceof Profesor) {
			panelActions.add(btnAgregarTarea);
			panelActions.add(btnEditar);
		}

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					claseSeleccionada = clases.get(row);
					btnEditar.setEnabled(true);
					btnClase.setEnabled(true);
					btnAgregarTarea.setEnabled(!claseSeleccionada.isHasTarea());
				}
			}
		});

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCursos menu = new MenuCursos();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 10, 137, 32);
		contentPane.add(btnVolver);

	}
}