package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Administrador;
import BLL.Alumno;
import BLL.MedioDePago;
import BLL.Profesor;
import BLL.Usuario;
import DLL.ControllerMedioDePago;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Font;

public class PerfilUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public PerfilUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		JLabel lblHtml = new JLabel(html(usuario));
		lblHtml.setBounds(10, 36, 604, 96);
		contentPane.add(lblHtml);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCursos menu = new MenuCursos();
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
			html += "<p>" + admin.getNombre() + " " + admin.getApellido() + "</p>";
			
		} else if (usuario instanceof Profesor) {
			
			Profesor profesor = (Profesor) usuario;
			html += "<p>" + profesor.getNombre() + " " + profesor.getApellido() + "</p>";
			
		} else if (usuario instanceof Alumno) {
			
			Alumno alumno = (Alumno) usuario;
			html += "<p>" + alumno.getNombre() + " " + alumno.getApellido() + "</p>";
			html += "<p>" + alumno.getUserName() + "</p>";
			html += "<p>" + alumno.getEmail() + "</p>";
			html += "<p>" + alumno.getFechaIngreso() + "</p>";

			JLabel lblMediosDePago = new JLabel("Medios de pago");
			lblMediosDePago.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMediosDePago.setBounds(10, 150, 112, 20);
			contentPane.add(lblMediosDePago);
			
			LinkedList<MedioDePago> mediosDePago = ControllerMedioDePago.getMediosDePagosByUsuario(usuario.getId());
			DefaultTableModel model = new DefaultTableModel(new Object[] {"Nombre", "Titular", "Nuemro de tajeta", "Fecha Vencimiento", "Tipo" }, 0);
			
			table = new JTable(model);
			table.setBounds(10, 180, 604, 180);
			model.setRowCount(0);
			LinkedList<MedioDePago> mediosEnTabla = new LinkedList<>();
			for (MedioDePago medioDePago : mediosDePago) {
				model.addRow(new Object[] {medioDePago.getNombre(), medioDePago.getTitular(), medioDePago.getNumero(), medioDePago.getFechaVencimiento(), medioDePago.getTipo() });
				mediosEnTabla.add(medioDePago);
			}
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 180, 604, 180);
			contentPane.add(scrollPane);
			scrollPane.add(table);

		}
		
		html += "</html>";
		return html;

	}
}
