package aplicaciones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class Jorgify extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediaPlayer mediaPlayer;
	private Media hit;
	private final JButton bIniciar = new JButton("Añadir a la cola");
	private JFileChooser archivo;
	private JSlider timeSlider;
	private JButton bstop;
	private Duration length;
	private JLabel ltotal;
	private JLabel lcurrent;
	private JButton b_reini;
	private JButton b_sigui;
	private boolean cambio = true;
	private JScrollPane scroll;
	private JTable tabla;
	private boolean mom = true;
	private DefaultTableModel modelo;
	int s;
	private int cola = 0;
	private LinkedList<String> lista_cola = new LinkedList<String>();
	private Object[] registro;
	private JLabel lblNewLabel;
	private JLabel lnom;
	private JSpinner vol;
	private String nom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Jorgify frame = new Jorgify();
						frame.setVisible(true);
					} catch (Exception e) {

					}
				}
			});
		} catch (Exception e) {

		}
	}

	/**
	 * Create the frame.
	 */

	private void anadirCola(String nombre, String path) {
		// System.out.print(lista_cola.toString().substring(1, path.length() + 1));
		if (lista_cola.contains(path)) {
			JOptionPane.showMessageDialog(null, "No puedes añadir dos veces la misma cancion a la cola");
		} else {
			lista_cola.add(path);// [C:\Users\Jorge\Desktop\MONEY UP.mp3]

			if (cola == 0) {
				try {
					Media buscado = new Media(new File(path).toURI().toString());
					MediaPlayer med = new MediaPlayer(buscado);
					// System.out.println(med.getTotalDuration());
					med.setOnReady(new Runnable() {

						@Override
						public void run() {

							s = (int) buscado.getDuration().toSeconds();

							int m = 0;
							while (s >= 60) {
								m++;
								s = s - 60;
							}
							String tiempo = "";
							if (Integer.toString(s).length() == 1) {
								tiempo = Integer.toString(m).concat(":").concat("0").concat(Integer.toString(s));
							} else {
								tiempo = Integer.toString(m).concat(":").concat(Integer.toString(s));
							}

							int posi = tabla.getRowCount() + 1;
							registro = new Object[3];
							if (posi == 1) {
								registro[0] = "En curso";

							} else {
								registro[0] = posi;

							}
							registro[1] = nombre;
							registro[2] = tiempo;

							modelo.addRow(registro);
							modelo.fireTableStructureChanged();
							iniciarMusica(lista_cola.toString().substring(1, path.length() + 1));

						}

					});
				} catch (Exception e) {

				}
			}
		}

	}

	private void siguienteCola() {
		try {
			String path = lista_cola.getFirst();
			iniciarMusica(lista_cola.toString().substring(1, path.length() + 1));
			tabla.setValueAt("En curso", 0, 0);
		} catch (Exception e) {
			timeSlider.setValue(0);
		}
	}

	private void iniciarMusica(String mp3) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < tabla.getColumnCount(); i++) {
				tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
			}
			hit = new Media(new File(mp3).toURI().toString());
			mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();

			initialize();

		} catch (Exception e) {

		}
	}

	private void initialize() {
		if (mediaPlayer.getStatus() == MediaPlayer.Status.UNKNOWN) {
			mediaPlayer.statusProperty().addListener((obs, oldStatus, newStatus) -> {
				if (newStatus == MediaPlayer.Status.READY) {
					initializeSlider();
				}
			});
		} else {
			initializeSlider();
		}
	}

	private void initializeSlider() {
		if (mom) {
			lnom.setText(nom);
			mediaPlayer.setVolume(Double.parseDouble((vol.getValue()).toString()) / 100);
			timeSlider.setMaximum((int) mediaPlayer.getTotalDuration().toSeconds());
			mediaPlayer.currentTimeProperty()
					.addListener((obs, oldTime, newTime) -> timeSlider.setValue((int) newTime.toSeconds()));
		}
	}

	private void pararMusica() {
		if (bstop.getText().equals("| |")) {
			bstop.setText(">");
			length = mediaPlayer.getCurrentTime();

			mediaPlayer.pause();

		} else {
			bstop.setText("| |");
			mediaPlayer.seek(length);

			mediaPlayer.play();

		}
	}

	public Jorgify() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Jorgify.class.getResource("/multimedia/icon_mp3.png")));
		setTitle("Jorgify - Mp3 Deluxe");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					mediaPlayer.stop();
					dispose();
				} catch (Exception f) {
					dispose();
				}

				// Si se quiere cerrar que pregunte antes y compruebe

			}
		});

		setBounds(100, 100, 450, 324);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(99, 156, 108));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Runnable runme = new Runnable() {

			@Override
			public void run() {
				
			}
			
		};
			try {	Platform.startup(runme);// Si quito esto no se inicia el sonido y si no lo quito sale un error que no se
			}catch(Exception m) {
				
			}
		setContentPane(contentPane);
		contentPane.setLayout(null);
		bIniciar.setFont(new Font("Dialog", Font.BOLD, 12));
		bIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ruta = "";
					archivo = new JFileChooser();
					archivo.setCurrentDirectory(new File(".")); // Elegimos directorio a abrir
					archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Música MP3", "mp3");
					archivo.setFileFilter(filtro1);
					int r = archivo.showOpenDialog(null); // Opciones del cuadro de diálogo
					if (r == JFileChooser.APPROVE_OPTION) { // Si se Abre
						nom = archivo.getSelectedFile().getName().replace(".mp3", "");
						bstop.setEnabled(true);
						b_reini.setEnabled(true);
						b_sigui.setEnabled(true);
						ruta = archivo.getSelectedFile().getPath();
						anadirCola(nom, ruta);
						vol.setEnabled(true);
						mediaPlayer.setVolume(Double.parseDouble((vol.getValue()).toString()) / 100);
					} else if (r == JFileChooser.CANCEL_OPTION) { // Si se cancela
						JOptionPane.showMessageDialog(null, "Ha cancelado su selección");
					} else {
						JOptionPane.showMessageDialog(null, "Error en la selección");
					}

					

					

				} catch (Exception f) {
					// CANCELADO
				}
			}
		});
		bIniciar.setBounds(10, 137, 179, 41);
		contentPane.add(bIniciar);

		timeSlider = new JSlider();
		timeSlider.setValue(0);
		timeSlider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				cambio = false;
				mediaPlayer.pause();
				// Me quiero morir, lo que me costo hasta que me di cuenta que lo devuelve en ms
				// no s

				Duration d = new Duration(timeSlider.getValue() * 1000);
				length = d;
				mediaPlayer.seek(length);
				mediaPlayer.play();
				cambio = true;
			}
		});

		timeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cambio) {
					int sec = timeSlider.getMaximum();
					int min = 0;

					while (sec >= 60) {
						min++;
						sec = sec - 60;
					}
					if (Integer.toString(sec).length() == 1) {
						ltotal.setText(Integer.toString(min).concat(":").concat("0").concat(Integer.toString(sec)));
					} else {
						ltotal.setText(Integer.toString(min).concat(":").concat(Integer.toString(sec)));
					}
					int c_sec = timeSlider.getValue();
					int c_min = 0;

					while (c_sec >= 60) {
						c_min++;
						c_sec = c_sec - 60;
					}

					if (Integer.toString(c_sec).length() == 1) {
						lcurrent.setText(
								Integer.toString(c_min).concat(":").concat("0").concat(Integer.toString(c_sec)));
					} else {
						lcurrent.setText(Integer.toString(c_min).concat(":").concat(Integer.toString(c_sec)));
					}
					try {
						if (timeSlider.getValue() == timeSlider.getMaximum()) {
							lista_cola.remove(0);
							modelo.removeRow(0);
							siguienteCola();
							modelo.setValueAt(modelo.getRowCount(), 0, 0);
							lnom.setText(null);
						}
					} catch (Exception g) {

					}

				}
			}
		});
		timeSlider.setBounds(68, 246, 294, 30);
		contentPane.add(timeSlider);

		bstop = new JButton("| |");
		bstop.setEnabled(false);
		bstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pararMusica();
			}
		});
		bstop.setFont(new Font("Dialog", Font.BOLD, 12));
		bstop.setBounds(183, 202, 61, 41);
		contentPane.add(bstop);

		ltotal = new JLabel("");
		ltotal.setFont(new Font("Dialog", Font.BOLD, 12));
		ltotal.setBounds(365, 251, 49, 14);
		contentPane.add(ltotal);

		lcurrent = new JLabel("");
		lcurrent.setFont(new Font("Dialog", Font.BOLD, 12));
		lcurrent.setBounds(20, 246, 49, 14);
		contentPane.add(lcurrent);

		b_reini = new JButton("←");
		b_reini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediaPlayer.stop();
				mediaPlayer.play();
			}
		});
		b_reini.setFont(new Font("Dialog", Font.BOLD, 18));
		b_reini.setEnabled(false);
		b_reini.setBounds(100, 202, 61, 41);
		contentPane.add(b_reini);

		b_sigui = new JButton("→");
		b_sigui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediaPlayer.stop();
				length = mediaPlayer.getCycleDuration();
				timeSlider.setValue(timeSlider.getMaximum());
				mediaPlayer.seek(length);
				bstop.setText("| |");
			}
		});
		b_sigui.setFont(new Font("Dialog", Font.BOLD, 18));
		b_sigui.setEnabled(false);
		b_sigui.setBounds(262, 202, 61, 41);
		contentPane.add(b_sigui);

		scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(20, 21, 406, 91);
		contentPane.add(scroll);

		tabla = new JTable();

		String columnas[] = { "En cola", "Canci\u00F3n", "Duraci\u00F3n" };
		modelo = new DefaultTableModel(null, columnas);
		tabla.setModel(modelo);
		scroll.setViewportView(tabla);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}

		lblNewLabel = new JLabel("Canción:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(235, 128, 74, 14);
		contentPane.add(lblNewLabel);

		lnom = new JLabel("");
		lnom.setFont(new Font("Dialog", Font.BOLD, 14));
		lnom.setBounds(245, 151, 169, 27);
		contentPane.add(lnom);

		vol = new JSpinner();
		vol.setEnabled(false);

		vol.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				mediaPlayer.setVolume(Double.parseDouble((vol.getValue()).toString()) / 100);
			}
		});
		vol.setFont(new Font("Dialog", Font.BOLD, 18));
		vol.setModel(new SpinnerNumberModel(4.0, 0.0, 100.0, 1.0));
		vol.setBounds(20, 202, 75, 36);
		contentPane.add(vol);

		JLabel lblNewLabel_1 = new JLabel("Volumen:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 186, 59, 14);
		contentPane.add(lblNewLabel_1);

	}
}