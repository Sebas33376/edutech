package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Administrador;
import BLL.Alumno;
import BLL.Profesor;
import BLL.Usuario;

import javax.swing.JLabel;

public class PerfilUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PerfilUsuario(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(html(usuario));
		lblNewLabel.setBounds(10, 10, 604, 305);
		contentPane.add(lblNewLabel);
		
		

	}
	
	private String html(Usuario usuario) {
		String html = "<html>";
		if (usuario == null) {
			
		}
		
		if (usuario instanceof Administrador) {
			Administrador admin = (Administrador) usuario;
			html += "<h2>" + admin.getNombre() + " " + admin.getApellido() + "</h2>";
		} else if (usuario instanceof Profesor) {
			 Profesor profesor = (Profesor) usuario;
			 html += "<h2>" + profesor.getNombre() + " " + profesor.getApellido() + "</h2>";
		} else if (usuario instanceof Alumno) {
			Alumno alumno = (Alumno) usuario;
			html += "<h2>" + alumno.getNombre() + " " + alumno.getApellido() + "</h2>";
		}
		 html += "</html>";
		return html;
		
	}
	
}
