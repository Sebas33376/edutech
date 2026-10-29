package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUsaurio;
	private JTextField inputContrasena;

	public PantallaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Nombre de usuario o email");
		lblUsuario.setBounds(154, 43, 128, 12);
		contentPane.add(lblUsuario);

		inputUsaurio = new JTextField();
		inputUsaurio.setBounds(154, 65, 128, 18);
		contentPane.add(inputUsaurio);
		inputUsaurio.setColumns(10);

		inputContrasena = new JTextField();
		inputContrasena.setColumns(10);
		inputContrasena.setBounds(154, 130, 128, 18);
		contentPane.add(inputContrasena);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(154, 108, 128, 12);
		contentPane.add(lblContrasena);

		JCheckBox chckbxMostrarContrasena = new JCheckBox("Mostrar contraseña");
		chckbxMostrarContrasena.setBounds(154, 154, 128, 20);
		contentPane.add(chckbxMostrarContrasena);

		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario.logueado = Usuario.Login(inputUsaurio.getText(), inputContrasena.getText());

				if (Usuario.logueado == null) {
					JOptionPane.showConfirmDialog(null, "El usuario o contraseña son incorrectos");
				} else {

					MenuCursos menu = new MenuCursos();
					menu.setVisible(true);
					dispose();

				}
			}
		});
		btnLogin.setBounds(162, 194, 111, 20);
		contentPane.add(btnLogin);

	}
}
