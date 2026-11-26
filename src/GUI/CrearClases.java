package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import BLL.Clase;
import BLL.Curso;
import BLL.Profesor;
import BLL.Tarea;
import BLL.TipoDeEntrega;
import BLL.Usuario;

public class CrearClases extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JList<String> listaClases;
	private DefaultListModel<String> modeloLista;
	private JTextField tfTitulo;
	private JTextArea taDescripcion;
	private JTextField tfUrl;
	private JCheckBox chkTarea;
	private JTextField tfTareaTitulo;
	private JComboBox<String> cbTareaTipo;
	private JLabel lblTareaUrl;
	private JTextField tfTareaUrl;
	private JTextArea taTareaDescripcion;

	private String tituloCurso;
	private String descripcionCurso;
	private LinkedList<Clase> clases = new LinkedList<>();
	private LinkedList<Tarea> tareas = new LinkedList<>();
	private int indiceActual = 0;
	private boolean actualizando = false;

	public CrearClases(String tituloCurso, String descripcionCurso, int cantidad) {
		this.tituloCurso = tituloCurso;
		this.descripcionCurso = descripcionCurso;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Clases de " + tituloCurso);

		JLabel lblClases = new JLabel("Clases del curso");
		lblClases.setBounds(10, 10, 200, 14);
		contentPane.add(lblClases);

		modeloLista = new DefaultListModel<>();
		listaClases = new JList<>(modeloLista);
		listaClases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaClases.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() || actualizando) {
					return;
				}
				int seleccionado = listaClases.getSelectedIndex();
				if (seleccionado < 0 || seleccionado == indiceActual) {
					return;
				}
				guardarFormulario();
				indiceActual = seleccionado;
				cargarFormulario(seleccionado);
			}
		});

		JScrollPane scrollLista = new JScrollPane(listaClases);
		scrollLista.setBounds(10, 30, 230, 420);
		contentPane.add(scrollLista);

		JButton btnAgregarClase = new JButton("Agregar");
		btnAgregarClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarClase();
			}
		});
		btnAgregarClase.setBounds(10, 460, 112, 30);
		contentPane.add(btnAgregarClase);

		JButton btnQuitarClase = new JButton("Quitar");
		btnQuitarClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarClase();
			}
		});
		btnQuitarClase.setBounds(128, 460, 112, 30);
		contentPane.add(btnQuitarClase);

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(270, 10, 100, 14);
		contentPane.add(lblTitulo);

		tfTitulo = new JTextField();
		tfTitulo.setBounds(270, 28, 600, 26);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(270, 64, 100, 14);
		contentPane.add(lblDescripcion);

		JScrollPane scrollDescripcion = new JScrollPane();
		scrollDescripcion.setBounds(270, 82, 600, 80);
		contentPane.add(scrollDescripcion);

		taDescripcion = new JTextArea();
		taDescripcion.setLineWrap(true);
		taDescripcion.setWrapStyleWord(true);
		scrollDescripcion.setViewportView(taDescripcion);

		JLabel lblUrl = new JLabel("URL de la clase");
		lblUrl.setBounds(270, 172, 120, 14);
		contentPane.add(lblUrl);

		tfUrl = new JTextField();
		tfUrl.setBounds(270, 190, 600, 26);
		contentPane.add(tfUrl);
		tfUrl.setColumns(10);

		chkTarea = new JCheckBox("Esta clase tiene una tarea");
		chkTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarEstadoTarea();
			}
		});
		chkTarea.setBounds(270, 230, 250, 22);
		contentPane.add(chkTarea);

		JLabel lblTareaTitulo = new JLabel("Título de la tarea");
		lblTareaTitulo.setBounds(290, 262, 150, 14);
		contentPane.add(lblTareaTitulo);

		tfTareaTitulo = new JTextField();
		tfTareaTitulo.setBounds(290, 280, 580, 26);
		contentPane.add(tfTareaTitulo);
		tfTareaTitulo.setColumns(10);

		JLabel lblTareaTipo = new JLabel("Tipo de entrega");
		lblTareaTipo.setBounds(290, 316, 150, 14);
		contentPane.add(lblTareaTipo);

		DefaultComboBoxModel<String> tipos = new DefaultComboBoxModel<>();
		for (TipoDeEntrega tipo : TipoDeEntrega.values()) {
			String opcion = tipo.name();
			tipos.addElement(opcion.substring(0, 1) + opcion.substring(1).toLowerCase());
		}
		cbTareaTipo = new JComboBox<>(tipos);
		cbTareaTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarEstadoTarea();
			}
		});
		cbTareaTipo.setBounds(290, 334, 200, 26);
		contentPane.add(cbTareaTipo);

		lblTareaUrl = new JLabel("URL de la tarea");
		lblTareaUrl.setBounds(510, 316, 150, 14);
		contentPane.add(lblTareaUrl);

		tfTareaUrl = new JTextField();
		tfTareaUrl.setBounds(510, 334, 360, 26);
		contentPane.add(tfTareaUrl);
		tfTareaUrl.setColumns(10);

		JLabel lblTareaDescripcion = new JLabel("Descripción de la tarea");
		lblTareaDescripcion.setBounds(290, 370, 200, 14);
		contentPane.add(lblTareaDescripcion);

		JScrollPane scrollTarea = new JScrollPane();
		scrollTarea.setBounds(290, 388, 580, 80);
		contentPane.add(scrollTarea);

		taTareaDescripcion = new JTextArea();
		taTareaDescripcion.setLineWrap(true);
		taTareaDescripcion.setWrapStyleWord(true);
		scrollTarea.setViewportView(taTareaDescripcion);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "Se van a perder los datos cargados. ¿Salir igual?",
						"Cancelar", JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					MenuCursos menu = new MenuCursos();
					menu.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setBounds(640, 505, 108, 36);
		contentPane.add(btnCancelar);

		JButton btnCrear = new JButton("Crear curso");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCurso();
			}
		});
		btnCrear.setBounds(762, 505, 108, 36);
		contentPane.add(btnCrear);

		for (int i = 0; i < cantidad; i++) {
			clases.add(new Clase(0, "", "", "", 0));
			tareas.add(null);
			modeloLista.addElement(etiqueta(i));
		}
		listaClases.setSelectedIndex(0);
		cargarFormulario(0);
	}

	private void agregarClase() {
		if (clases.size() >= 100) {
			JOptionPane.showMessageDialog(null, "El máximo es de 100 clases por curso.");
			return;
		}
		guardarFormulario();
		clases.add(new Clase(0, "", "", "", 0));
		tareas.add(null);
		int nuevo = clases.size() - 1;
		actualizando = true;
		modeloLista.addElement(etiqueta(nuevo));
		listaClases.setSelectedIndex(nuevo);
		actualizando = false;
		indiceActual = nuevo;
		cargarFormulario(nuevo);
		tfTitulo.requestFocus();
	}

	private void quitarClase() {
		if (clases.size() <= 1) {
			JOptionPane.showMessageDialog(null, "El curso necesita al menos una clase.");
			return;
		}
		int r = JOptionPane.showConfirmDialog(null, "¿Quitar la clase " + (indiceActual + 1) + "?", "Quitar clase",
				JOptionPane.YES_NO_OPTION);
		if (r != JOptionPane.YES_OPTION) {
			return;
		}
		actualizando = true;
		clases.remove(indiceActual);
		tareas.remove(indiceActual);
		modeloLista.remove(indiceActual);
		for (int i = 0; i < clases.size(); i++) {
			modeloLista.set(i, etiqueta(i));
		}
		int nuevo = Math.min(indiceActual, clases.size() - 1);
		listaClases.setSelectedIndex(nuevo);
		actualizando = false;
		indiceActual = nuevo;
		cargarFormulario(nuevo);
	}

	private String etiqueta(int i) {
		String titulo = clases.get(i).getTitulo();
		if (titulo.isEmpty()) {
			titulo = "(sin título)";
		}
		return (i + 1) + ". " + titulo + (tareas.get(i) != null ? "  [tarea]" : "");
	}

	private void guardarFormulario() {
		clases.set(indiceActual, new Clase(0, tfTitulo.getText().trim(), tfUrl.getText().trim(),
				taDescripcion.getText().trim(), 0));

		if (chkTarea.isSelected()) {
			String tipo = (String) cbTareaTipo.getSelectedItem();
			String url = "Choice".equals(tipo) ? tfTareaUrl.getText().trim() : "";
			tareas.set(indiceActual,
					new Tarea(tfTareaTitulo.getText().trim(), taTareaDescripcion.getText().trim(), tipo, url, 0));
		} else {
			tareas.set(indiceActual, null);
		}
		modeloLista.set(indiceActual, etiqueta(indiceActual));
	}

	private void cargarFormulario(int i) {
		Clase clase = clases.get(i);
		Tarea tarea = tareas.get(i);

		tfTitulo.setText(clase.getTitulo());
		taDescripcion.setText(clase.getDescripcion());
		taDescripcion.setCaretPosition(0);
		tfUrl.setText(clase.getUrl());

		chkTarea.setSelected(tarea != null);
		tfTareaTitulo.setText(tarea != null ? tarea.getTitulo() : "");
		cbTareaTipo.setSelectedItem(tarea != null ? tarea.getTipo() : "Choice");
		tfTareaUrl.setText(tarea != null ? tarea.getUrl() : "");
		taTareaDescripcion.setText(tarea != null ? tarea.getDescripcion() : "");
		taTareaDescripcion.setCaretPosition(0);
		actualizarEstadoTarea();
	}

	private void actualizarEstadoTarea() {
		boolean conTarea = chkTarea.isSelected();
		boolean conUrl = conTarea && "Choice".equals(cbTareaTipo.getSelectedItem());

		tfTareaTitulo.setEnabled(conTarea);
		cbTareaTipo.setEnabled(conTarea);
		taTareaDescripcion.setEnabled(conTarea);
		lblTareaUrl.setEnabled(conUrl);
		tfTareaUrl.setEnabled(conUrl);
		if (!conUrl) {
			tfTareaUrl.setText("");
		}
	}

	private String validarClases() {
		for (int i = 0; i < clases.size(); i++) {
			Clase clase = clases.get(i);
			Tarea tarea = tareas.get(i);
			String error = null;
			if (clase.getTitulo().isEmpty()) {
				error = "falta el título";
			} else if (clase.getDescripcion().isEmpty()) {
				error = "falta la descripción";
			} else if (clase.getUrl().isEmpty()) {
				error = "falta la URL";
			} else if (tarea != null && tarea.getTitulo().isEmpty()) {
				error = "la tarea no tiene título";
			} else if (tarea != null && tarea.getDescripcion().isEmpty()) {
				error = "la tarea no tiene descripción";
			}
			if (error != null) {
				listaClases.setSelectedIndex(i);
				return "Clase " + (i + 1) + ": " + error + ".";
			}
		}
		return null;
	}

	private void crearCurso() {
		guardarFormulario();
		String error = validarClases();
		if (error != null) {
			JOptionPane.showMessageDialog(null, error);
			return;
		}

		Profesor profesor = (Profesor) Usuario.logueado;
		Curso curso = new Curso(0, tituloCurso, descripcionCurso, 0.0, profesor.getCategoriaId(), profesor.getId());

		if (Curso.crear(curso, clases, tareas)) {
			JOptionPane.showMessageDialog(null,
					"Curso creado correctamente.");
			MenuCursos menu = new MenuCursos();
			menu.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo crear el curso. No se guardó nada.");
		}
	}
}
