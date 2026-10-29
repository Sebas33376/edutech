package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import javax.swing.SwingConstants;

public class PantallaComprado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaComprado(boolean exito) {
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
			html += "<h1>¡Compra realizada con éxito!</h1>";
		} else {
			html += "<h1>¡Error al realizar la compra!</h1>";
		}
		contentPane.setLayout(null);

		JLabel txt = new JLabel(html);
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.setBounds(10, 13, 414, 191);
		contentPane.add(txt);

		JButton btnVolver = new JButton("Volver al inicio");
		btnVolver.setBounds(145, 210, 143, 40);
		btnVolver.addActionListener(e -> {
			MenuCursos menu = new MenuCursos();
			menu.setVisible(true);
			dispose();
		});
		contentPane.add(btnVolver);

	}

}
