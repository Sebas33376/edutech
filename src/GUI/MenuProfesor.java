package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;

public class MenuProfesor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MenuProfesor(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Menú Profesor - " + usuario.getNombre());
		JLabel detalles = new JLabel(
			    "<html><h2>" + usuario.getNombre() + " " + usuario.getApellido() + "</h2>"
			    + "<p>" + usuario.getEmail() + "</p></html>"
			);
		detalles.setBounds(10, 10, 400, 100);
		contentPane.add(detalles);
	}

}
