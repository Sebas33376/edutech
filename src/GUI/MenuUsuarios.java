package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Administrador;
import BLL.Alumno;
import BLL.Profesor;
import BLL.Usuario;

public class MenuUsuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnProfesores;
	private JButton btnAlumnos;
	private JButton btnBaja;
	private Administrador admin;
	private LinkedList<Usuario> usuarios;
	private LinkedList<Usuario> usuariosEnTabla;
	private Usuario usuarioSeleccionado;
	private boolean verProfesores = true;

	public MenuUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Usuarios");
		contentPane.setLayout(null);

		if (!(Usuario.logueado instanceof Administrador)) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
			return;
		}
		admin = (Administrador) Usuario.logueado;
		usuarios = Usuario.getController().mostrarUsuarios();

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

		btnProfesores = new JButton("Profesores");
		btnProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verProfesores = true;
				getUsuarios();
			}
		});
		btnProfesores.setBounds(170, 10, 137, 32);
		contentPane.add(btnProfesores);

		btnAlumnos = new JButton("Alumnos");
		btnAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verProfesores = false;
				getUsuarios();
			}
		});
		btnAlumnos.setBounds(317, 10, 137, 32);
		contentPane.add(btnAlumnos);

		JButton btnCrearProfesor = new JButton("Crear profesor");
		btnCrearProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearProfesor crear = new CrearProfesor();
				crear.setVisible(true);
				dispose();
			}
		});
		btnCrearProfesor.setBounds(957, 10, 137, 32);
		contentPane.add(btnCrearProfesor);

		model = new DefaultTableModel(new Object[] { "Usuario", "Nombre", "Apellido", "Email", "Detalle" }, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 52, 1084, 340);
		contentPane.add(scrollPane);

		btnBaja = new JButton("Dar de baja");
		btnBaja.setBounds(10, 402, 155, 42);
		btnBaja.setEnabled(false);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Dar de baja a " + usuarioSeleccionado.getNombre()
						+ " " + usuarioSeleccionado.getApellido() + "?");
				if (respuesta != JOptionPane.YES_OPTION) {
					return;
				}
				if (admin.darDeBaja(usuarioSeleccionado)) {
					JOptionPane.showMessageDialog(null, "Usuario dado de baja");
					usuarios = Usuario.getController().mostrarUsuarios();
					getUsuarios();
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo dar de baja al usuario");
				}
			}
		});
		contentPane.add(btnBaja);

		table.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					usuarioSeleccionado = usuariosEnTabla.get(row);
					btnBaja.setEnabled(true);
				} else {
					btnBaja.setEnabled(false);
				}
			}
		});

		getUsuarios();
	}


	private void getUsuarios() {
		btnProfesores.setEnabled(!verProfesores);
		btnAlumnos.setEnabled(verProfesores);

		model.setRowCount(0);
		usuariosEnTabla = new LinkedList<>();
		for (Usuario usuario : usuarios) {
			if (verProfesores && usuario instanceof Profesor) {
				Profesor profesor = (Profesor) usuario;
				if (profesor.getCategoriaId() == admin.getCategoriaId()) {
					model.addRow(new Object[] { profesor.getUserName(), profesor.getNombre(), profesor.getApellido(),
							profesor.getEmail(), profesor.getLegajo() + " - " + profesor.getProfesion() });
					usuariosEnTabla.add(profesor);
				}
			} else if (!verProfesores && usuario instanceof Alumno) {
				Alumno alumno = (Alumno) usuario;
				model.addRow(new Object[] { alumno.getUserName(), alumno.getNombre(), alumno.getApellido(),
						alumno.getEmail(), "Ingreso: " + alumno.getFechaIngreso() });
				usuariosEnTabla.add(alumno);
			}
		}
	}
}

