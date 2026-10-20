package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Alumno;
import BLL.Curso;
import BLL.Inscripcion;
import BLL.Usuario;
import DLL.ControllerCursos;

import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JSeparator;

public class MenuAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> listaCursos;
	private DefaultTableModel model;
	private LinkedList<Inscripcion> inscripciones;
	private LinkedList<Curso> cursos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario usuario = new Alumno(6, "alum_lucas", "Lucas", "Silva",
							"$2a$12$ehluRdL3jeeRwfSmPDfJDef1cb/TQgoWq5jvpKLHej9Zd93S6GFr.", true,
							"lucas.silva@gmail.com");
					MenuAlumno frame = new MenuAlumno(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * public MenuAlumno(Usuario usuario) {
	 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100, 1320,
	 * 500); contentPane = new JPanel(); contentPane.setBorder(new EmptyBorder(5, 5,
	 * 5, 5)); setContentPane(contentPane); contentPane.setLayout(null);
	 * 
	 * JLabel lblSeleccionado = new JLabel("Cursos"); lblSeleccionado.setBounds(10,
	 * 10, 1280, 20); contentPane.add(lblSeleccionado);
	 * 
	 * model = new DefaultTableModel(new String[] { "Titulo", "Descripcion",
	 * "Profesor", "Precio", "Categoría" }, 0); table = new JTable(model);
	 * JScrollPane scrollPane = new JScrollPane(table); scrollPane.setBounds(10, 40,
	 * 1280, 200); contentPane.add(scrollPane);
	 * 
	 * cargarTabla();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * private void cargarTabla() { LinkedList<Curso> cursos =
	 * ControllerCursos.mostrarCursos(); for (Curso i : cursos) {
	 * 
	 * model.addRow(new Object[] { i.getTitulo(), i.getDescripcion(),
	 * i.getProfesor(), i.getPrecio(), i.getCategoria() }); } }
	 **/

	public MenuAlumno(Usuario usuario) {
		setTitle("Alumno - Cursos");
		setSize(980, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		cursos = ControllerCursos.mostrarCursos();

		DefaultListModel<String> model = new DefaultListModel<>();

		for (Curso curso : cursos) {

			String precio = String.format("$%,.2f", curso.getPrecio());
			model.addElement(curso.getTitulo() + " - " + curso.getDescripcion() + " - " + curso.getProfesor() + " - "
					+ precio + " - " + curso.getCategoria());
		}

		listaCursos = new JList<>(model);

		listaCursos.addListSelectionListener(e -> {
			int index = listaCursos.getSelectedIndex();
			if (index >= 0) {
				Curso cursoSeleccionado = cursos.get(index);
				DetalleCurso detalles = new DetalleCurso(cursoSeleccionado, usuario);
				detalles.setVisible(true);
				dispose();
			}
		});

		add(listaCursos, BorderLayout.CENTER);

		setVisible(true);
	}
}
