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
import BLL.Profesor;
import BLL.Tarea;
import BLL.Usuario;
import DLL.ControllerEntrega;
import DLL.ControllerTarea;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MenuTareas extends JFrame {

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

	public MenuTareas(Clase clase, Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		setTitle("Tarea");
		contentPane.setLayout(null);

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}
      
		tarea = ControllerTarea.getTarea(clase.getId());

		entregas = ControllerEntrega.getEntregasByTp(tarea.getId());

		JLabel detalles = new JLabel("<html><h2>" + tarea.getTitulo() + "</h2>" + "<p>" + tarea.getTitulo() + "</p>"
				+ "<p>" + tarea.getDescripcion() + "</p>" + tarea.getUrl() + "</p> </html>");
		detalles.setBounds(10, 65, 440, 266);
		contentPane.add(detalles);

		model = new DefaultTableModel(new Object[] { "Usuario", "Fecha entregada", "Estado" }, 0);

		table = new JTable(model);

		model.setRowCount(0);
		for (Entrega entrega : entregas) {
			model.addRow(new Object[] { entrega.getAlumno(), entrega.getFecha(), entrega.getEstado() });
		}

		btnCorregir = new JButton("Corregir entrega");
		btnCorregir.setBounds(10, 11, 145, 51);
		btnCorregir.setEnabled(false);
		btnCorregir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarEntrega edit = new EditarEntrega(entregaSeleccionado, clase, curso);
				edit.setVisible(true);
				dispose();

			};
		});

		table.getSelectionModel().addListSelectionListener(e -> {

			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				if (row != -1) {
					entregaSeleccionado = entregas.get(row);
					if (entregaSeleccionado.getEstado().equals("Corregido")) {
						btnCorregir.setText("Editar corrección");
					} else {
						btnCorregir.setText("Corregir entrega");
					}
					btnCorregir.setEnabled(true);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(460, 11, 540, 404);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		panel.setBounds(10, 342, 440, 73);
		contentPane.add(panel);
		panel.setLayout(null);


		JButton btnEditar = new JButton("Editar tarea");
		btnEditar.setBounds(181, 11, 145, 51);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             EditarTarea editar = new EditarTarea(tarea, clase, curso);
             editar.setVisible(true);
             dispose();
			}
		});
		if (usuario instanceof Profesor) {
			panel.add(btnCorregir);
			panel.add(btnEditar);
		}

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 11, 152, 43);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuClases menu = new MenuClases(curso);
				menu.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVolver);
	}
}
