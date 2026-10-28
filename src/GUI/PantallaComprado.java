package GUI;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

public class PantallaComprado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaComprado(boolean exito, Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String html = "<html>";
		if (exito) {

			html += "<h1>¡Compra realizada con éxito!</h1>";

		} else {
			html += "<h1>¡Error al realizar la compra!</h1>";
		}

		JLabel txt = new JLabel(html);
		contentPane.add(txt);
		
		JButton btnVolver = new JButton("Volver al inicio");
		btnVolver.addActionListener(e -> {
			MenuCursos menu = new MenuCursos(usuario);
			menu.setVisible(true);
			dispose();
		});
		contentPane.add(btnVolver);

	}

}
