package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BLL.Clase;
import BLL.Curso;
import BLL.Entrega;
import BLL.Tarea;
import BLL.Usuario;
import javax.swing.SwingConstants;

public class CrearEntrega extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private byte[] archivo;

	public CrearEntrega(Tarea tarea, Clase clase, Curso curso) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Entregar tarea");

		Usuario usuario = Usuario.logueado;

		if (usuario == null) {
			PantallaPrincipal inicio = new PantallaPrincipal();
			inicio.setVisible(true);
			dispose();
		}

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 565, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		String titulo;

		if (tarea.getTipo().equals("Choice")) {

			titulo = "Has click en el link para completar el formulario, una vez terminado has click en entregar.";
			JLabel lblUrl = new JLabel(tarea.getUrl());
			lblUrl.setBounds(259, 111, 46, 14);
			panel.add(lblUrl);
		} else {
			titulo = "Sube el archivo zip o rar.";

			JButton btnSubirArchivo = new JButton("Subir archivo");
			btnSubirArchivo.setBounds(219, 111, 126, 51);
			btnSubirArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos (RAR, ZIP)", "rar", "zip");
					chooser.setFileFilter(filter);
					int option = chooser.showOpenDialog(null);
					if (option == JFileChooser.APPROVE_OPTION) {

						File selectedFile = chooser.getSelectedFile();

						String nombreArchivo = selectedFile.getName().toLowerCase();
						if (!(nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".rar")
								|| nombreArchivo.endsWith(".zip"))) {
							JOptionPane.showMessageDialog(null, "Solo se permiten archivos RAR o ZIP.");
							return;
						}

						try {
							archivo = Files.readAllBytes(selectedFile.toPath());
							JOptionPane.showMessageDialog(null, "Archivo cargado correctamente.");
						} catch (IOException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
						}
					}

				}
			});
			panel.add(btnSubirArchivo);
		}

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(53, 71, 459, 43);
		panel.add(lblTitulo);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(86, 250, 413, 65);
		contentPane.add(buttonsPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuTarea menu = new MenuTarea(clase, curso);
				menu.setVisible(true);
				dispose();
			}
		});
		buttonsPanel.setLayout(null);

		btnCancelar.setBounds(53, 7, 126, 51);
		buttonsPanel.add(btnCancelar);

		JButton btnEntregar = new JButton("Entregar");
		btnEntregar.setBounds(232, 7, 126, 51);
		btnEntregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if ("Choice".equals(tarea.getTipo())) {
		            Entrega newEntrega = new Entrega(usuario.getId(), tarea.getId());
		            boolean exito = Entrega.crearEntrega(newEntrega);

		            PantallaEntregaCreada creado = new PantallaEntregaCreada(exito, curso);
		            creado.setVisible(true);
		            dispose();
		            return;
		        }

		        if (archivo == null) {
		            JOptionPane.showMessageDialog(
		                CrearEntrega.this,
		                "Debes subir un archivo antes de entregar.",
		                "Archivo requerido",
		                JOptionPane.WARNING_MESSAGE
		            );
		            return;
		        }

		        Entrega newEntrega = new Entrega(usuario.getId(), tarea.getId(), archivo);
		        boolean exito = Entrega.crearEntrega(newEntrega);

		        PantallaEntregaCreada creado = new PantallaEntregaCreada(exito, curso);
		        creado.setVisible(true);
		        dispose();
			}
		});
		buttonsPanel.add(btnEntregar);

		setVisible(true);

	}
}
