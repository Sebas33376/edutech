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

import BLL.Clase;
import BLL.Curso;
import BLL.Usuario;
import DLL.ControllerClase;
import DLL.ControllerCurso;

public class MenuClases extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private LinkedList<Clase> clases;
	private Clase claseSeleccionada;

	public MenuClases(Usuario usuario, int cursoId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Clases");
		contentPane.setLayout(null);

		clases = ControllerClase.getClasesByCurso(cursoId, usuario.getId());

		model = new DefaultTableModel(new Object[] { "Título", "Clase", "Descripción", "Curso" }, 0);

		table = new JTable(model);

		model.setRowCount(0);
		for (Clase clase : clases) {
			model.addRow(new Object[] { clase.getTitulo(), clase.getUrl(), clase.getDescripcion(), clase.getCurso() });
		}

		JPanel panel = new JPanel();
		panel.setBounds(198, 350, 673, 103);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarClase editMenu = new EditarClase(claseSeleccionada, usuario);
				editMenu.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBounds(137, 25, 130, 52);
		panel.add(btnEditar);
		
		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					claseSeleccionada = clases.get(row);
					btnEditar.setEnabled(true);
				}
			}
		});

		JButton btnTareas = new JButton("Ver tareas");
		btnTareas.setBounds(404, 25, 130, 52);
		panel.add(btnTareas);


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
			PerfilUsuario perfilAlumno = new PerfilUsuario(usuario);
			perfilAlumno.setVisible(true);
			dispose();
		});
		toolBar.add(btnMiPerfil);

		JButton btnCursos = new JButton("Cursos");
		btnCursos.setMargin(new Insets(10, 14, 10, 14));
		btnCursos.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCursos.addActionListener(e -> {
			MenuCursos menu = new MenuCursos(usuario);
			menu.setVisible(true);
			dispose();
		});
		toolBar.add(btnCursos);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal inicio = new PantallaPrincipal();
				inicio.setVisible(true);
				dispose();
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(198, 10, 675, 340);
		getContentPane().add(scrollPane);
		btnCerrarSesion.setMargin(new Insets(10, 14, 10, 14));
		btnCerrarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));

		toolBar.add(btnCerrarSesion);
		setVisible(true);

	}
}
