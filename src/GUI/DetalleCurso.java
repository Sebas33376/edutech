package GUI;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Curso;
import BLL.Usuario;

public class DetalleCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DetalleCurso(Curso curso, Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel detalles = new JLabel("<html><h2>" + curso.getTitulo() + "</h2>" + "<p>" + curso.getDescripcion()
				+ "</p>" + curso.getProfesor() + "</p> </html>");

		contentPane.add(detalles);

		JButton btnInscripcion = new JButton("Anotarse al curso");

		contentPane.add(btnInscripcion);
	}

}
