package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Clase;
import BLL.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTitulo;
	private JTextField tfUrl;

	public EditarClase(Clase clase, Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Clase - " + clase.getTitulo());
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(160, 30, 46, 14);
		contentPane.add(lblTitulo);
		
		tfTitulo = new JTextField(clase.getTitulo());
		tfTitulo.setBounds(160, 45, 265, 30);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		
		JLabel lblUrl = new JLabel("URL de la clase");
		lblUrl.setBounds(160, 97, 88, 14);
		contentPane.add(lblUrl);
		
		tfUrl = new JTextField(clase.getUrl());
		tfUrl.setColumns(10);
		tfUrl.setBounds(160, 112, 265, 30);
		contentPane.add(tfUrl);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(160, 169, 75, 14);
		contentPane.add(lblDescripcion);
		
		JTextArea textArea = new JTextArea(clase.getDescripcion());
		textArea.setBounds(160, 185, 271, 214);
		contentPane.add(textArea);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuClases menu = new MenuClases(usuario, clase.getCursoId());
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
				Clase claseEdit = new Clase(clase.getId(), tfTitulo.getText(), tfUrl.getText(), textArea.getText(), clase.getCursoId());
				boolean exito = Clase.editarClase(claseEdit);
				PantallaEditado editado = new PantallaEditado(exito, usuario, clase.getCursoId());
				editado.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnEditar);
		
		setVisible(true);
	}
}
