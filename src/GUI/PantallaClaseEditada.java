package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import javax.swing.SwingConstants;

public class PantallaClaseEditada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaClaseEditada(boolean exito, int cursoId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		String html = "<html>";
		if (exito) {

			html += "<h1>¡La clase fue editada con éxito!</h1>";

		} else {
			html += "<h1>¡Error al editar la clase!</h1>";
		}

		html += "</html>";
		contentPane.setLayout(null);

		JLabel txt = new JLabel(html);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.setBounds(10, 13, 414, 188);
		contentPane.add(txt);

		JButton btnVolver = new JButton("Volver al inicio");
		btnVolver.setBounds(145, 210, 143, 40);
		btnVolver.addActionListener(e -> {
			MenuClases menu = new MenuClases(cursoId);
			menu.setVisible(true);
			dispose();
		});
		contentPane.add(btnVolver);

	}

}
