package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Clase;
import BLL.Entrega;
import BLL.Usuario;

public class EditEntrega extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public EditEntrega(Usuario usuario, Entrega entrega, Clase clase) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Editar Entrega");
		
		JLabel lblCorrecion = new JLabel("Corrección");
		lblCorrecion.setBounds(160, 169, 75, 14);
		contentPane.add(lblCorrecion);
		
		JTextArea textArea = new JTextArea(entrega.getCorreccion());
		textArea.setBounds(160, 185, 271, 214);
		contentPane.add(textArea);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TareasMenu menu = new TareasMenu(clase, usuario);
				menu.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(170, 410, 108, 40);
		contentPane.add(btnCancelar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(317, 410, 108, 40);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entrega entregaEdit = new Entrega(entrega.getId(), entrega.getAlumnoId(), entrega.getTareaId(), "Corregido", entrega.getFecha() ,textArea.getText());
				boolean exito = Entrega.editarEntrega(entregaEdit);
				PantallaEditado editado = new PantallaEditado(exito, usuario, clase.getCursoId());
				editado.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnEditar);
		
		setVisible(true);

		
	}

}
