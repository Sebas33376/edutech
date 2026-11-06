package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Clase;

public class DetalleClase extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DetalleClase(Clase clase) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Clase " + clase.getTitulo());
		contentPane.setLayout(null);
		
		JPanel panelCurso = new JPanel();
		panelCurso.setBounds(10, 10, 486, 443);
		contentPane.add(panelCurso);
		
		JPanel panelClases = new JPanel();
		panelClases.setBounds(506, 10, 496, 443);
		contentPane.add(panelClases);
		
		

	}
}
