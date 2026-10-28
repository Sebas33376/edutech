package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		
		JLabel lblHtml = new JLabel(html(usuario));
		lblHtml.setBounds(10, 10, 604, 305);
		contentPane.add(lblHtml);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCursos menu = new MenuCursos(usuario);
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 10, 84, 20);
		contentPane.add(btnVolver);
		
		

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
