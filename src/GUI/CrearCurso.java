package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import BLL.Profesor;
import BLL.Usuario;

public class CrearCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTitulo;
	private JTextArea taDescripcion;
	private JSpinner spCantidad;

	public CrearCurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Crear curso");

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(60, 20, 100, 14);
		contentPane.add(lblTitulo);

		tfTitulo = new JTextField();
		tfTitulo.setBounds(60, 38, 330, 26);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(60, 76, 100, 14);
		contentPane.add(lblDescripcion);

		JScrollPane scrollDescripcion = new JScrollPane();
		scrollDescripcion.setBounds(60, 94, 330, 100);
		contentPane.add(scrollDescripcion);

		taDescripcion = new JTextArea();
		taDescripcion.setLineWrap(true);
		taDescripcion.setWrapStyleWord(true);
		scrollDescripcion.setViewportView(taDescripcion);

		JLabel lblCantidad = new JLabel("Cantidad de clases");
		lblCantidad.setBounds(60, 208, 140, 14);
		contentPane.add(lblCantidad);

		spCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spCantidad.setBounds(60, 226, 80, 26);
		contentPane.add(spCantidad);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCursos menu = new MenuCursos();
				menu.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(60, 275, 108, 36);
		contentPane.add(btnCancelar);

		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Usuario.logueado instanceof Profesor)) {
					JOptionPane.showMessageDialog(null, "Solo un profesor puede crear cursos.");
					return;
				}
				if (tfTitulo.getText().trim().isEmpty() || taDescripcion.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingresá el título y la descripción del curso.");
					return;
				}
				CrearClases clases = new CrearClases(tfTitulo.getText().trim(), taDescripcion.getText().trim(),
						(Integer) spCantidad.getValue());
				clases.setVisible(true);
				dispose();
			}
		});
		btnSiguiente.setBounds(282, 275, 108, 36);
		contentPane.add(btnSiguiente);
	}
}