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

public class MenuAlumno extends JFrame {

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
					MenuAlumno frame = new MenuAlumno(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public MenuAlumno(Usuario usuario) {
		setTitle("Alumno - Cursos");
		setSize(980, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);
		
		JButton btnMiPerfil = new JButton("Mi perfil");
		toolBar.add(btnMiPerfil);
		
		JButton btnMisCursos = new JButton("Mis cursos");
		toolBar.add(btnMisCursos);
		
		cursos = ControllerCurso.mostrarCursos();
		inscripciones = ControllerInscripcion.getInscripcionesByAlumnoId(usuario.getId());
		
		DefaultListModel<String> model = new DefaultListModel<>();
		for (Curso curso : cursos) {
			String precio = String.format("%.2f", curso.getPrecio());
		    model.addElement(curso.getTitulo() + " - " + curso.getDescripcion() + " - " + precio + " - " + curso.getCategoria() + " - " + curso.getProfesor());
		}
		
		listaCursos = new JList<>(model);
		getContentPane().add(listaCursos);


		

		setVisible(true);
	}
}
