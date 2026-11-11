package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Clase;
import BLL.Curso;

public class DetalleClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DetalleClase(Clase clase, Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Clase " + clase.getTitulo());
		contentPane.setLayout(null);
		
	
		JPanel panelTarea = new JPanel();
		panelTarea.setBounds(506, 10, 496, 443);
		contentPane.add(panelTarea);
		
		JPanel panelClase = new JPanel();
		panelClase.setBounds(10, 48, 486, 324);
		contentPane.add(panelClase);
		panelClase.setLayout(null);

		JPanel panelActions = new JPanel();
		panelActions.setBounds(10, 382, 486, 69);
		contentPane.add(panelActions);
		panelActions.setLayout(null);
		
		JButton btnTarea = new JButton("Ver Tarea");
		btnTarea.setBounds(24, 11, 130, 47);
		btnTarea.setEnabled(clase.isHasTarea());
		btnTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuTarea menu = new MenuTarea(clase, curso);
				menu.setVisible(true);
				dispose();
			}
		});
		panelActions.add(btnTarea);

		String html = "<html><H1>" + clase.getTitulo() + "</H1><span>" + clase.getUrl() + "</span><p>"
				+ clase.getDescripcion() + "</p></html>";

		JLabel lblDetalleClase = new JLabel(html);
		lblDetalleClase.setBounds(10, 10, 466, 304);
		panelClase.add(lblDetalleClase);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetalleCurso menu = new DetalleCurso(curso);
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 10, 137, 32);
		contentPane.add(btnVolver);

	}
}
