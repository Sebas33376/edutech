package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Clase;
import BLL.Curso;
import BLL.Entrega;
import BLL.Tarea;
import BLL.Usuario;
import DLL.ControllerEntrega;
import DLL.ControllerTarea;
import javax.swing.JButton;


public class TareasMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Tarea tarea;
	private Entrega entregaSeleccionado;
	private LinkedList<Entrega> entregas;
	private JTable table;
	private DefaultTableModel model;
	private JPanel panel;
	private JButton btnCorregir;
	private JButton btnVolver;

	public TareasMenu(Clase clase, Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Tareas");
		contentPane.setLayout(null);
		
		tarea = ControllerTarea.getTarea(clase.getId());
	
		entregas = ControllerEntrega.getEntregasByTp(tarea.getId());

		JLabel detalles = new JLabel("<html><h2>" + tarea.getTitulo() + "</h2>" + "<p>"+ tarea.getTitulo() + "</p>" + "<p>" + tarea.getDescripcion()
				+ "</p>" + tarea.getUrl() + "</p> </html>");
		detalles.setBounds(10, 65, 440, 236);
		contentPane.add(detalles);
		
		model = new DefaultTableModel(new Object[] { "Usuario", "Fecha entregada", "Estado"}, 0);

		table = new JTable(model);

		model.setRowCount(0);
		for (Entrega entrega : entregas) {
			model.addRow(new Object[] { entrega.getAlumno(), entrega.getFecha(), entrega.getEstado() });
		}
		
		btnCorregir = new JButton("Corregir entrega");
		btnCorregir.setBounds(10, 11, 145, 81);
		btnCorregir.setEnabled(false);
		btnCorregir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditEntrega edit = new EditEntrega(usuario, entregaSeleccionado, clase);
				edit.setVisible(true);
				dispose();
				
			};
		});
		
		table.getSelectionModel().addListSelectionListener(e -> {

			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
				    entregaSeleccionado = entregas.get(row);
				    btnCorregir.setEnabled(true);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(460, 11, 540, 404);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBounds(10, 312, 440, 103);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.add(btnCorregir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 11, 152, 43);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuClases menu = new MenuClases(usuario, clase.getCursoId());
				menu.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVolver);
	}
}
