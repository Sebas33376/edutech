package GUI;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Curso;
import BLL.Inscripcion;
import BLL.Usuario;
import DLL.ControllerCursos;

import javax.swing.JTable;

public class MenuAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;


	public MenuAlumno(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1320, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeleccionado = new JLabel("Cursos");
		lblSeleccionado.setBounds(10, 10, 1280, 20);
		contentPane.add(lblSeleccionado);

		model = new DefaultTableModel(new String[] { "Titulo", "Descripcion", "Profesor", "Precio", "Categoría" }, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 40, 1280, 200);
		contentPane.add(scrollPane);
		
		cargarTabla();
		
		

	}
	
	

	private void cargarTabla() {
		LinkedList<Curso> cursos = ControllerCursos.mostrarCursos();
		for (Curso i : cursos) {

			model.addRow(new Object[] { i.getTitulo(), i.getDescripcion(), i.getProfesor(), i.getPrecio(),
					i.getCategoria() });
		}
	}
}
