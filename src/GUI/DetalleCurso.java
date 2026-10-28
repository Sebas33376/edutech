package GUI;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Curso;
import BLL.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DetalleCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DetalleCurso(Curso curso, Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnInscripcion = new JButton("Anotarse al curso");
		btnInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPago pantallaPago = new PantallaPago(usuario, curso);
				pantallaPago.setVisible(true);
				dispose();
			}
		});
		btnInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInscripcion.setBounds(258, 325, 146, 44);
		
		contentPane.add(btnInscripcion);

		JLabel detalles = new JLabel("<html><h2>" + curso.getTitulo() + "</h2>" + "<p>" + curso.getDescripcion()
				+ "</p>" + curso.getProfesor() + "</p> </html>");
		detalles.setBounds(10, 41, 643, 274);

		contentPane.add(detalles);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCursos menu = new MenuCursos(usuario);
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 10, 100, 30);
		contentPane.add(btnVolver);
	}
}
