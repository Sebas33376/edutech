package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Alumno;
import BLL.Curso;
import BLL.Inscripcion;
import BLL.MedioDePago;
import BLL.Usuario;
import DLL.ControllerMedioDePago;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class PantallaPago extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaPago(Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCursos menu = new MenuCursos();
				menu.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		btnVolver.setBounds(10, 10, 100, 30);
		contentPane.add(btnVolver);

		LinkedList<MedioDePago> mediosDePago = ControllerMedioDePago.getMediosDePagosByUsuario(usuario.getId());
		DefaultComboBoxModel<String> lista = new DefaultComboBoxModel<>();
		for (MedioDePago medioDePago : mediosDePago) {
			lista.addElement(medioDePago.getNombre());
		}

		JComboBox<String> comboBox = new JComboBox<>(lista);
		comboBox.setBounds(136, 226, 427, 33);
		contentPane.add(comboBox);

		JLabel lblHtml = new JLabel(html(curso));
		lblHtml.setBounds(136, 60, 427, 156);
		contentPane.add(lblHtml);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPagar.setBounds(263, 293, 173, 43);
		contentPane.add(btnPagar);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuario instanceof Alumno) {
					int select = comboBox.getSelectedIndex();
					System.out.println(curso);
					boolean exito = Inscripcion.Inscribirce(usuario.getId(), curso.getId(),
							mediosDePago.get(select).getId());
					PantallaComprado comprado = new PantallaComprado(exito);
					comprado.setVisible(true);
					dispose();
				}
			}
		});

	}

	private String html(Curso curso) {
		String html = "<html>";
		if (curso == null) {
			html += "<h2>Curso no encontrado</h2>";
		} else {
			html += "<h2>" + curso.getTitulo() + "</h2>";
			html += "<p>" + curso.getDescripcion() + "</p>";
			html += "<p>Profesor: " + curso.getProfesor() + "</p>";
			html += "<p>Precio: " + String.format("$%.2f", curso.getPrecio()) + "</p>";
		}

		html += "</html>";
		return html;

	}
}
