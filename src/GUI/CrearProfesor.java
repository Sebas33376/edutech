package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Profesor;
import BLL.Usuario;
import repository.Validaciones;

public class CrearProfesor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsuario;
	private JTextField inputNombre;
	private JTextField inputApellido;
	private JTextField inputEmail;
	private JTextField inputContrasena;
	private JTextField inputRepetirContrasena;
	private JTextField inputProfesion;

	public CrearProfesor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Crear profesor");

		if (!(Usuario.logueado instanceof Administrador)) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
			return;
		}
		Administrador admin = (Administrador) Usuario.logueado;

		JLabel lblUsuario = new JLabel("Nombre de usuario");
		lblUsuario.setBounds(60, 20, 200, 14);
		contentPane.add(lblUsuario);

		inputUsuario = new JTextField();
		inputUsuario.setBounds(60, 38, 330, 24);
		contentPane.add(inputUsuario);
		inputUsuario.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(60, 72, 200, 14);
		contentPane.add(lblNombre);

		inputNombre = new JTextField();
		inputNombre.setBounds(60, 90, 330, 24);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(60, 124, 200, 14);
		contentPane.add(lblApellido);

		inputApellido = new JTextField();
		inputApellido.setBounds(60, 142, 330, 24);
		contentPane.add(inputApellido);
		inputApellido.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(60, 176, 200, 14);
		contentPane.add(lblEmail);

		inputEmail = new JTextField();
		inputEmail.setBounds(60, 194, 330, 24);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(60, 228, 200, 14);
		contentPane.add(lblContrasena);

		inputContrasena = new JTextField();
		inputContrasena.setBounds(60, 246, 330, 24);
		contentPane.add(inputContrasena);
		inputContrasena.setColumns(10);

		JLabel lblRepetirContrasena = new JLabel("Repetir contraseña");
		lblRepetirContrasena.setBounds(60, 280, 200, 14);
		contentPane.add(lblRepetirContrasena);

		inputRepetirContrasena = new JTextField();
		inputRepetirContrasena.setBounds(60, 298, 330, 24);
		contentPane.add(inputRepetirContrasena);
		inputRepetirContrasena.setColumns(10);

		JLabel lblProfesion = new JLabel("Profesión");
		lblProfesion.setBounds(60, 332, 200, 14);
		contentPane.add(lblProfesion);

		inputProfesion = new JTextField();
		inputProfesion.setBounds(60, 350, 330, 24);
		contentPane.add(inputProfesion);
		inputProfesion.setColumns(10);

		JLabel lblLegajo = new JLabel("Legajo (automático): " + admin.siguienteLegajo());
		lblLegajo.setBounds(60, 392, 330, 14);
		contentPane.add(lblLegajo);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuarios menu = new MenuUsuarios();
				menu.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(60, 435, 108, 36);
		contentPane.add(btnCancelar);

		JButton btnCrear = new JButton("Crear profesor");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Validaciones.esUserName(inputUsuario.getText())) {
					JOptionPane.showMessageDialog(null,
							"El usuario debe tener entre 4 y 20 caracteres: letras, números, _ o . (sin espacios)");
				} else if (!Validaciones.esNombre(inputNombre.getText())) {
					JOptionPane.showMessageDialog(null, "El nombre solo puede tener letras (entre 2 y 40 caracteres)");
				} else if (!Validaciones.esNombre(inputApellido.getText())) {
					JOptionPane.showMessageDialog(null, "El apellido solo puede tener letras (entre 2 y 40 caracteres)");
				} else if (!Validaciones.esEmail(inputEmail.getText())) {
					JOptionPane.showMessageDialog(null, "El email no es válido");
				} else if (!Validaciones.esContrasena(inputContrasena.getText())) {
					JOptionPane.showMessageDialog(null,
							"La contraseña debe tener entre 8 y 30 caracteres, con letras y números, sin espacios");
				} else if (!Validaciones.coinciden(inputContrasena.getText(), inputRepetirContrasena.getText())) {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
				} else if (!Validaciones.esProfesion(inputProfesion.getText())) {
					JOptionPane.showMessageDialog(null,
							"La profesión debe tener entre 3 y 50 caracteres (letras, espacios, puntos o guiones)");
				} else {
					Profesor profesor = admin.crearProfesor(inputUsuario.getText().trim(), inputNombre.getText().trim(),
							inputApellido.getText().trim(), inputEmail.getText().trim(), inputContrasena.getText(),
							inputProfesion.getText().trim());
					if (profesor != null) {
						JOptionPane.showMessageDialog(null, "Profesor creado. Legajo: " + profesor.getLegajo());
						MenuUsuarios menu = new MenuUsuarios();
						menu.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnCrear.setBounds(240, 435, 150, 36);
		contentPane.add(btnCrear);

	}
}
