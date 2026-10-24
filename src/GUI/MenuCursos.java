package GUI;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import BLL.Alumno;
import BLL.Curso;
import BLL.Inscripcion;
import BLL.Usuario;
import DLL.ControllerCurso;
import DLL.ControllerInscripcion;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class MenuCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
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
		toolBar.setBounds(0, 0, 0, 0);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);

		cursos = ControllerCurso.mostrarCursos();
		inscripciones = ControllerInscripcion.getInscripcionesByAlumnoId(usuario.getId());

		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "Título", "Descripción", "Categoría", "Profesor", "Precio" }, cursos.size());
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(198, 10, 675, 443);
		getContentPane().add(scrollPane);

		JButton btnMiPerfil = new JButton("Mi perfil");
		btnMiPerfil.addActionListener(e -> {
			PerfilUsuario perfilAlumno = new PerfilUsuario(usuario);
			perfilAlumno.setVisible(true);
			dispose();
		});
		toolBar.add(btnMiPerfil);

		JButton btnCursos = new JButton("Mis cursos");
		btnCursos.addActionListener(e -> {
			if (btnCursos.getText().equals("Mis cursos")) {
				btnCursos.setText("Cursos");

			} else if (btnCursos.getText().equals("Cursos")) {
				btnCursos.setText("Mis cursos");

			}
		});
		toolBar.add(btnCursos);

		setVisible(true);
	}
}
