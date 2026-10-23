package GUI;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class MenuCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> listaCursos;
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
		setTitle("Alumno - Cursos");
		setSize(980, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(0, 0, 92, 363);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);

		cursos = ControllerCurso.mostrarCursos();
		inscripciones = ControllerInscripcion.getInscripcionesByAlumnoId(usuario.getId());

		DefaultListModel<String> model = getCursos();

		listaCursos = new JList<>(model);
		listaCursos.setBounds(102, 0, 864, 363);
		listaCursos.addListSelectionListener(e -> {
			int index = listaCursos.getSelectedIndex();
			if (index >= 0) {
				Curso cursoSeleccionado;
				if (listaCursos.getModel() == getMisCursos()) {
					cursoSeleccionado = inscripciones.get(index).getCurso();
				} else {
					cursoSeleccionado = cursos.get(index);
				}
				DetalleCurso detalleCurso = new DetalleCurso(cursoSeleccionado, usuario);
				detalleCurso.setVisible(true);
				dispose();
			}
		});
		getContentPane().add(listaCursos);

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
				listaCursos.setModel(getMisCursos());
				
			} else if(btnCursos.getText().equals("Cursos")) {
				btnCursos.setText("Mis cursos");
				listaCursos.setModel(getCursos());

			}
		});
		toolBar.add(btnCursos);

		setVisible(true);
	}

	private DefaultListModel<String> getCursos() {
		DefaultListModel<String> modelCursos = new DefaultListModel<>();
		for (Curso curso : cursos) {
			String precio = String.format("%.2f", curso.getPrecio());
			modelCursos.addElement(curso.getTitulo() + " - " + curso.getDescripcion() + " - " + precio + " - "
					+ curso.getCategoria() + " - " + curso.getProfesor());
		}

		return modelCursos;
	}
	
	private DefaultListModel<String> getMisCursos() {
		DefaultListModel<String> modelInscripciones = new DefaultListModel<>();
		for (Inscripcion inscripcion : inscripciones) {
			Curso curso = inscripcion.getCurso();
			String precio = String.format("%.2f", curso.getPrecio());
			modelInscripciones.addElement(curso.getTitulo() + " - " + curso.getDescripcion() + " - " + precio
					+ " - " + curso.getCategoria() + " - " + curso.getProfesor());
		}
		return modelInscripciones;
	}
}
