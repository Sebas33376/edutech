package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Clase;
import BLL.Curso;
import BLL.Tarea;
import BLL.TipoDeEntrega;
import BLL.Usuario;
import javax.swing.JComboBox;

public class EditarTarea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public EditarTarea(Tarea tarea, Clase clase, Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Editar tarea");

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(160, 41, 46, 14);
		contentPane.add(lblTitulo);

		JTextField tfTitulo = new JTextField(tarea.getTitulo());
		tfTitulo.setBounds(160, 56, 265, 30);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo de entrega");
		lblTipo.setBounds(160, 153, 137, 14);
		contentPane.add(lblTipo);
		DefaultComboBoxModel<String> lista = new DefaultComboBoxModel<>();
		for (TipoDeEntrega tipo : TipoDeEntrega.values()) {
			String opcion = tipo.name();
			lista.addElement(opcion.substring(0, 1) + opcion.substring(1).toLowerCase());
		}

		JLabel lblUrl = new JLabel("URL de la clase");
		lblUrl.setBounds(160, 97, 88, 14);
		contentPane.add(lblUrl);

		JTextField tfUrl = new JTextField(tarea.getUrl());
		tfUrl.setColumns(10);
		tfUrl.setBounds(160, 112, 265, 30);
		contentPane.add(tfUrl);

		JComboBox<String> comboBox = new JComboBox<>(lista);
		comboBox.setSelectedItem(tarea.getTipo());
		boolean habilitarUrl = "Choice".equals(tarea.getTipo());

		lblUrl.setEnabled(habilitarUrl);
		tfUrl.setEnabled(habilitarUrl);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = (String) comboBox.getSelectedItem();
				boolean habilitarUrl = "Choice".equals(tipo);

				lblUrl.setEnabled(habilitarUrl);
				tfUrl.setEnabled(habilitarUrl);
			}
		});
		comboBox.setBounds(160, 168, 265, 30);
		contentPane.add(comboBox);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(160, 209, 75, 14);
		contentPane.add(lblDescripcion);

		JTextArea textArea = new JTextArea(tarea.getDescripcion());
		textArea.setBounds(160, 228, 271, 160);
		contentPane.add(textArea);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuTareas menu = new MenuTareas(clase, curso);
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
				String tipo = (String) comboBox.getSelectedItem();
				Tarea newTarea = new Tarea(tarea.getId(), tfTitulo.getText(), textArea.getText(), tipo,
						tipo.equals("Choice") ? tfUrl.getText() : "", clase.getId());
				boolean exito = Tarea.editTarea(newTarea);
				PantallaTareaEditada editada = new PantallaTareaEditada(exito, curso);
				editada.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnEditar);

		setVisible(true);

	}
}
