package GUI;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

public class PantallaEditado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaEditado(boolean exito, Usuario usuario, int cursoId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String html = "<html>";
		if (exito) {

			html += "<h1>¡La clase fue editada con éxito!</h1>";

		} else {
			html += "<h1>¡Error al editar la clase!</h1>";
		}

		JLabel txt = new JLabel(html);
		contentPane.add(txt);
		
		JButton btnVolver = new JButton("Volver al inicio");
		btnVolver.addActionListener(e -> {
			MenuClases menu = new MenuClases(usuario, cursoId);
			menu.setVisible(true);
			dispose();
		});
		contentPane.add(btnVolver);

	}

}
