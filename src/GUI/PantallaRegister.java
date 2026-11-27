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

import BLL.Alumno;
import BLL.Usuario;
import repository.Validaciones;

public class PantallaRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsuario;
	private JTextField inputNombre;
	private JTextField inputApellido;
	private JTextField inputEmail;
	private JTextField inputContrasena;
	private JTextField inputRepetirContrasena;

	public PantallaRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Registro");

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

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal inicio = new PantallaPrincipal();
				inicio.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(60, 350, 108, 36);
		contentPane.add(btnCancelar);

		JButton btnRegistrarse = new JButton("Registrarme");
		btnRegistrarse.addActionListener(new ActionListener() {
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
				} else {
					Alumno alumno = new Alumno(0, inputUsuario.getText().trim(), inputNombre.getText().trim(),
							inputApellido.getText().trim(), inputContrasena.getText(), true,
							inputEmail.getText().trim());
					if (Usuario.registrar(alumno)) {
						JOptionPane.showMessageDialog(null, "Registro exitoso. Ya podés iniciar sesión");
						PantallaLogin login = new PantallaLogin();
						login.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnRegistrarse.setBounds(282, 350, 108, 36);
		contentPane.add(btnRegistrarse);

	}

}
