package aplicaciones;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import conexion.Gestor_Conexion;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Gestor_Hoteles extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tabla;
	private DefaultTableModel modelo = new DefaultTableModel() {

		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override // Método indica que los datos han cambiado
		public void fireTableDataChanged() {
			super.fireTableDataChanged();
		}
	};
	private JButton beditar, bcancelar, belim, bcan, bnuevo, bguardar_1, bcancelar_1, bcargar, bfomr, bno, bconf,
			bgrafo, bhotel, bimprimir;
	private JInternalFrame editar, elim, anadir, frameGrafos;
	private JScrollPane scrollPane;
	private JTextField nombre, tnombre_1, tcod;
	private JComboBox<String> comboServ, comboServ_1;
	private JSpinner spinner, spinner_1;
	private JDateChooser fecha_ini, fecha_fin, fecha_fin_1, fecha_ini_1;
	private String arr[];
	private final String CONTRA = "admin";
	private JPasswordField tcontra;
	private int i = 0;
	private static int p;
	private JPanel panel_1, pan_regis;
	private JLabel l2_1, l1_1, lblNDePersonas_1, lblFechaInicio_1, lblFechaDeFin_1, l1_2;
	private JTextArea most;
	private DefaultTableModel dtm;
	private Gestor_Conexion conect;
	private JTable tabla_regis;
	private static String registros[][];
	private String reg[];
	private static Grafo demo;
	private Connection con;
	private static String s;
	private static JFileChooser ch;
	private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .docx", "docx");
	private static InputStream in;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestor_Hoteles frame = new Gestor_Hoteles();
					frame.setVisible(true);
				} catch (Exception e) {

				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private void crearColumnas() {
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre del hotel");
		modelo.addColumn("Servicios");
		modelo.addColumn("Numero de personas");
		modelo.addColumn("Fecha de inicio");
		modelo.addColumn("Fecha de fin");
		tabla.setModel(modelo);
	}

	private void insertarRegis(String[][] arr) {
		try {
			con = null;
			conect = new Gestor_Conexion();
			con = conect.getConnection();
			s = "";
			p = 0;
			String sql = "insert into Hotel (Codigo,Nombre_del_Hotel,Servicios,Numero_de_personas,Dia_Uno,Dia_Fin) values (?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);

			for (int i = 0; i < arr.length; i++) {

				for (int j = 0; j < arr.length; j++) {
					s = arr[i][j];
					pst.setString(j + 1, s);

				}
				p = pst.executeUpdate();

			}
			if (p > 0) {
				JOptionPane.showMessageDialog(null, "DATOS GUARDADOS CORRECTAMENTE");
				recargarTabla();
				anadir.dispose();
				tabla.clearSelection();
			} else {
				JOptionPane.showMessageDialog(null, "DATOS NO GUARDADOS CORRECTAMENTE");
			}
		} catch (SQLException | HeadlessException l) {
			JOptionPane.showMessageDialog(null, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE" + l, "Error",
					JOptionPane.ERROR_MESSAGE);

		} finally {
			try {
				con.close();

			} catch (SQLException e) {

			}
		}

	}

	@SuppressWarnings("deprecation")
	public static void imprimirJtable(JTable jTable, String header, String footer) {

		try (XWPFDocument doc = new XWPFDocument()) {
			Calendar cal = Calendar.getInstance();
			ch = new JFileChooser();
			ch.setAcceptAllFileFilterUsed(false);

			ch.setFileFilter(filter);

			p = ch.showSaveDialog(null);

			if (p == JFileChooser.APPROVE_OPTION) {
				///////////////
				XWPFHeader cabecera = doc.createHeader(HeaderFooterType.DEFAULT);
				cabecera.createParagraph().createRun().setText("Gestoría de Jorgeles TM");
				XWPFFooter pie = doc.createFooter(HeaderFooterType.DEFAULT);

				pie.createParagraph().createRun().setText("Registros de Jorgeles TM");

				XWPFParagraph p7 = doc.createParagraph();
				p7.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r1 = p7.createRun(); // Texto dentro del p1
				r1.setBold(true);
				r1.setItalic(false);
				r1.setFontSize(22);
				r1.setFontFamily("Dialog");
				r1.setText("Informe de datos del Gestor de Jorgeles");

				XWPFTable table = doc.createTable();

				XWPFTableRow fila1 = table.getRow(0); // Obligatorio ponerlo así
				fila1.getCell(0).setText("Codigo");

				// Obligatorio ponerlo así
				for (int i = 1; i < tabla.getColumnCount(); i++) {

					fila1.addNewTableCell().setText(tabla.getColumnName(i));

				}

				for (int j = 0; j < tabla.getRowCount(); j++) {
					XWPFTableRow filaRow = table.createRow();// Para que me haga una fila por registro lo meto dentro y
																// C'est Fini Funciona

					for (int i = 0; i < tabla.getColumnCount(); i++) {
						if (i == 0) {// Si es cero por alguna razon hay que sacarlo fuera
							filaRow.getCell(0).setText(tabla.getValueAt(j, i).toString());
						} else
							filaRow.getCell(i).setText(tabla.getValueAt(j, i).toString());
					}
				}

				XWPFParagraph p6 = doc.createParagraph();
				XWPFRun r0 = p6.createRun(); // Texto dentro del p1
				r0.setBold(true);
				r0.setItalic(false);
				r0.setFontSize(21);
				r0.setFontFamily("Dialog");
				p6.setPageBreak(true);
				r0.setText("Grafos de datos");
				p6.setAlignment(ParagraphAlignment.CENTER);

				XWPFParagraph p5 = doc.createParagraph();
				XWPFRun r2 = p5.createRun(); // Texto dentro del p1
				r2.setBold(true);
				r2.setItalic(false);
				r2.setFontSize(18);
				r2.setFontFamily("Dialog");
				r2.setText("¿Qué épocas tienen más reservas?");
				p5.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r5 = p5.createRun();
				
				demo = new Grafo("Comparación", "", crearGrafo(4));// String[][]
				
				in = new ByteArrayInputStream(demo.convertirImagen());

				try {
					r5.addPicture(in, Document.PICTURE_TYPE_PNG, demo.toString(),

							Units.toEMU(350), Units.toEMU(300));

				} catch (InvalidFormatException e) {

				}

				XWPFParagraph m9 = doc.createParagraph();
				m9.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun m11 = m9.createRun();
				m11.setBold(false);
				m11.setItalic(false);
				m11.setFontSize(14);
				m11.setFontFamily("Dialog");
				String Servicio = "";
				String fechaStr = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MARCH) + 1) + "/"
						+ cal.get(Calendar.YEAR);

				m11.setText(
						"Estos datos se basan en la agrupación del campo 'Fecha de inicio', contando cuántas veces aparece cada mes de reserva en los registros del Gestor de Jorgeles TM");
				m11.addBreak();
				m11.addBreak();
				p = crearGrafo(4).length;
				Servicio = "";
				
				int x = Integer.parseInt(crearGrafo(4)[0][1].toString());

				for (int i = 1; i < p; i++) {
					int y = Integer.parseInt(crearGrafo(4)[i][1].toString());

					if (y > x) {
						x = y;
						Servicio = crearGrafo(4)[i][0].toString();
					} else {
						Servicio = crearGrafo(4)[i][0].toString();
					}

				}

				m11.setText("Este grafo muestra que el mes '" + Servicio
						+ "' es el mes con más reservas. Estos datos se han generado en " + fechaStr);

				XWPFParagraph p8 = doc.createParagraph();
				p8.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r7 = p8.createRun();
				r7.setBold(true);
				r7.setItalic(false);
				r7.setFontSize(16);
				r7.setFontFamily("Dialog");
				r7.setText("¿Qué servicios tienen más éxito?");
				demo = new Grafo("Comparación", "", crearGrafo(2));// String[][]
				in = new ByteArrayInputStream(demo.convertirImagen());

				try {
					r7.addPicture(in, Document.PICTURE_TYPE_PNG, demo.toString(),

							Units.toEMU(350), Units.toEMU(300));

				} catch (InvalidFormatException e) {

				}
				r7.addBreak();

				p = crearGrafo(2).length;

				x = Integer.parseInt(crearGrafo(2)[0][1].toString());

				for (int i = 1; i < p; i++) {
					int y = Integer.parseInt(crearGrafo(2)[i][1].toString());

					if (y > x) {
						x = y;
						Servicio = crearGrafo(2)[i][0].toString();
					} else {
						Servicio = crearGrafo(2)[i][0].toString();
					}

				}
				// Añadiendo que muestre el servicio/hotel/epoca mas exitosa, sacas de el
				// array[][] de Strgin que devuelve crearGrafo()

				XWPFParagraph m1 = doc.createParagraph();
				m1.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun m2 = m1.createRun();
				m2.setBold(false);
				m2.setItalic(false);
				m2.setFontSize(14);
				m2.setFontFamily("Dialog");

				m2.setText(
						"Estos datos se basan en la agrupación del campo 'Servicios', contando cuántas veces aparece cada tipo de servicio en los registros del Gestor de Jorgeles TM");
				m2.addBreak();
				m2.addBreak();

				m2.setText("Este grafo muestra que el servicio '" + Servicio
						+ "' es el servicio con más éxito. Estos datos se han generado en " + fechaStr);

				XWPFParagraph p9 = doc.createParagraph();
				p9.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r8 = p9.createRun();
				r8.setBold(true);
				r8.setItalic(false);
				r8.setFontSize(18);
				r8.setFontFamily("Dialog");
				r8.setText("¿Qué hoteles tienen más éxito?");
				demo = new Grafo("Comparación", "", crearGrafo(1));// String[][]
				in = new ByteArrayInputStream(demo.convertirImagen());

				try {
					r8.addPicture(in, Document.PICTURE_TYPE_PNG, demo.toString(),

							Units.toEMU(350), Units.toEMU(300));

				} catch (InvalidFormatException e) {

				}

				p = crearGrafo(1).length;
				x = Integer.parseInt(crearGrafo(1)[0][1].toString());
				Servicio = crearGrafo(1)[x][0].toString();
				for (int i = 1; i < p; i++) {
					int y = Integer.parseInt(crearGrafo(1)[i][1].toString());

					if (y >= x) {
						x = y;
						Servicio = crearGrafo(1)[i][0].toString();
					} else {

					}

				}
				// Añadiendo que muestre el servicio/hotel/epoca mas exitosa, sacas de el
				// array[][] de Strgin que devuelve crearGrafo()

				XWPFParagraph m3 = doc.createParagraph();
				m3.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun m4 = m3.createRun();
				m4.setBold(false);
				m4.setItalic(false);
				m4.setFontSize(14);
				m4.setFontFamily("Dialog");

				m4.setText(
						"Estos datos se basan en la agrupación del campo 'Hoteles', contando cuántas veces aparece cada hotel en los registros del Gestor de Jorgeles TM");
				m4.addBreak();
				m4.addBreak();

				m4.setText("Este grafo muestra que el hotel '" + Servicio
						+ "' es el hotel con más éxito. Estos datos se han generado en " + fechaStr);

				///////////////
				String n = ch.getSelectedFile().getName();
				while (n.contains(".docx")) {
					n = n.replace(".docx", "");// En el caso de que el documento contenga en su propio nombre la
												// extension .docx esta se elimina y mas tarde se añade
				}
				s = ch.getCurrentDirectory().toString().concat("\\" + n + ".docx");
				try (FileOutputStream out = new FileOutputStream(s)) {

					doc.write(out);
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " +

							s);

				} catch (IOException e) {

				}
			}
		} catch (IOException e1) {

		}

	}

	private static String[][] crearGrafo(int campo) {
		
		registros = new String[tabla.getRowCount()][2];
		String nombre[] = new String[tabla.getRowCount()];
		p = 0;
		
		if (campo != 4) {

			for (int i = 0; i < tabla.getRowCount(); i++) {
				nombre[i] = tabla.getValueAt(i, campo).toString();
			}

			for (int i = 0; i < registros.length; i++) {
				p = 0;
				for (int j = 0; j < registros.length; j++) {

					if (tabla.getValueAt(j, campo).toString().equals(nombre[i])) {
						p++;
					}

				}
				registros[i][0] = nombre[i];
				registros[i][1] = Integer.toString(p);

			}

		} else {
			for (int i = 0; i < nombre.length; i++) {
				nombre[i] = tabla.getValueAt(i, campo).toString().substring(3, 5);
			}
			for (int i = 0; i < registros.length; i++) {
				p = 0;
				for (int j = 0; j < registros.length; j++) {

					if (tabla.getValueAt(j, campo).toString().substring(3, 5).equals(nombre[i])) {
						p++;
					}

				}
				registros[i][0] = nombre[i];
				registros[i][1] = Integer.toString(p);
				if (campo == 4) {
					switch (nombre[i]) {
					case "01":
						registros[i][0] = "Enero";
						break;
					case "02":
						registros[i][0] = "Febrero";
						break;

					case "03":
						registros[i][0] = "Marzo";
						break;
					case "04":
						registros[i][0] = "Abril";
						break;
					case "05":
						registros[i][0] = "Mayo";
						break;
					case "06":
						registros[i][0] = "Junio";
						break;
					case "07":
						registros[i][0] = "Julio";
						break;
					case "08":
						registros[i][0] = "Agosto";
						break;
					case "09":
						registros[i][0] = "Septiembre";
						break;
					case "10":
						registros[i][0] = "Octubre";
						break;
					case "11":
						registros[i][0] = "Noviembre";
						break;
					case "12":
						registros[i][0] = "Diciembre";
						break;
					}

				}

			}
		}

		return registros;
	}

	private void recargarTabla() {
		try {

			reg = new String[400];// Solo me mostrara los 400 primero regisytos
			s = "select * from Hotel ";

			con = null;
			conect = new Gestor_Conexion();// Lo conecto a mi conexion de clientes
			con = conect.getConnection();

			st = con.createStatement();
			rs = st.executeQuery(s);
			modelo = new DefaultTableModel() {

				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}

				@Override // Método indica que los datos han cambiado
				public void fireTableDataChanged() {
					super.fireTableDataChanged();
				}
			};
			crearColumnas();

			while (rs.next()) {
				reg[0] = rs.getString("Codigo");

				reg[1] = rs.getString("Nombre_del_Hotel");
				reg[2] = rs.getString("Servicios");
				reg[3] = rs.getString("Numero_de_personas");
				reg[4] = rs.getString("Dia_Uno");
				reg[5] = rs.getString("Dia_Fin");

				reg[4] = reg[4].substring(0, 10);
				reg[5] = reg[5].substring(0, 10);
				modelo.addRow(reg);// Hago q se me muestren solo estos campos
				// Ya funciona
			}

			tabla.setModel(modelo);// Y aqui se munestran
			modelo.fireTableStructureChanged();
			modelo.fireTableDataChanged();
			tabla.clearSelection();
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < tabla.getColumnCount(); i++) {
				tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA", "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

			}
		}

	}

	private String genCod() {
		s = "a-";

		String n = tabla.getValueAt(tabla.getRowCount() - 1, 0).toString();
		int m = Integer.parseInt(n.substring(2));
		s = s.concat(Integer.toString(m + 1));

		return s;
	}

	private void elimRegis() {
		try {
			con = null;
			conect = new Gestor_Conexion();
			con = conect.getConnection();
			s = "delete from Hotel where Codigo like ?";// Le
														// concateno
														// el
			// valor q quiero

			// eliminar
			pst = con.prepareStatement(s);// Ejecuto query
			pst.setString(1, tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

			int n = pst.executeUpdate();
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "DATOS ELIMINADOS CORRECTAMENTE");

			}
			recargarTabla();
			elim.dispose();
			tcontra.setText(null);
			beditar.setEnabled(false);
			belim.setEnabled(false);
			bnuevo.setEnabled(true);
			tabla.clearSelection();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "DATOS NO ELIMINADOS CORRECTAMENTE" + ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

			}
		}
	}

	private void cargarTabla() {

		try {

			reg = new String[100];// Solo me mostrara los 100 primero regisytos
			s = "select * from Hotel ";

			;
			con = null;
			conect = new Gestor_Conexion();// Lo conecto a mi conexion de clientes
			con = conect.getConnection();

			st = con.createStatement();
			rs = st.executeQuery(s);

			while (rs.next()) {
				reg[0] = rs.getString("Codigo");

				reg[1] = rs.getString("Nombre_del_Hotel");
				reg[2] = rs.getString("Servicios");
				reg[3] = rs.getString("Numero_de_personas");
				reg[4] = rs.getString("Dia_Uno");
				reg[5] = rs.getString("Dia_Fin");

				reg[4] = reg[4].substring(0, 10);
				reg[5] = reg[5].substring(0, 10);
				modelo.addRow(reg);// Hago q se me muestren solo estos campos
				// Ya funciona
			}
			tabla.setModel(modelo);// Y aqui se munestran
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < tabla.getColumnCount(); i++) {
				tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA", "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

			}
		}
	}

	private void modificarCambios() {
		try {
			
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dini = fecha_ini.getDate();
			String ini = sf.format(dini);

			java.util.Date dfin = fecha_fin.getDate();
			String fin = sf.format(dfin);

			s = "UPDATE Hotel " + "SET Nombre_del_Hotel=?" + ", Servicios =? " + ", Numero_de_personas = ?,"
					+ " Dia_Uno = ?" + ",Dia_Fin =? " + "WHERE Codigo like ?";
			con = null;
			conect = new Gestor_Conexion();// Lo conecto a mi conexion de clientes
			con = conect.getConnection();

			pst = con.prepareStatement(s);

			pst.setString(1, nombre.getText().toString());
			pst.setString(2, comboServ.getSelectedItem().toString());
			pst.setString(3, spinner.getValue().toString());
			pst.setString(4, ini);
			pst.setString(5, fin);
			pst.setString(6, tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

			int n = pst.executeUpdate();
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "Edición completada");
			} else {
				JOptionPane.showMessageDialog(null, "Edición NO completada");
			}

			recargarTabla();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO SE PUEDEN ACTUALIZAR LOS DATOS DE LA TABLA", "Error",
					JOptionPane.ERROR_MESSAGE);

		} finally {
			try {
				con.close();
			} catch (SQLException e) {

			}
		}
	}

	public Gestor_Hoteles() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					demo.dispose();
				} catch (Exception p) {

				}
			}

			@Override
			public void windowIconified(WindowEvent e) {
				try {
					demo.dispose();
				} catch (Exception s2) {

				}
			}
		});
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Gestor_Hoteles.class.getResource("/multimedia/gestoricon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 0, 980, 706);
		setResizable(false);
		setTitle("Gestor de Jorgeles - Administrador");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Ayuda");
		mnNewMenu.setFont(new Font("Dialog", Font.BOLD, 12));
		mnNewMenu.setMnemonic('a');
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Ver documentación");
		mntmNewMenuItem.setMnemonic('V');
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = "";
				File docu = new File("docu_gestor.txt");
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(docu));
				} catch (FileNotFoundException e2) {

				}
				String linea = "";
				try {
					linea = br.readLine();
				} catch (IOException e1) {

				}
				while (linea != null) {
					texto += (linea + "\n");
					try {
						linea = br.readLine();
					} catch (IOException e1) {

					}
				}
				new Padre(texto).setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(219, 117, 108));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane.setBounds(41, 37, 886, 226);
		contentPane.add(scrollPane);

		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				beditar.setEnabled(true);
				belim.setEnabled(true);
			}
		});

		tabla.setFont(new Font("Dialog", Font.BOLD, 12));

		tabla.getTableHeader().setReorderingAllowed(false);// Deshabilita reordenar columnas
		scrollPane.setViewportView(tabla);

		beditar = new JButton("Editar");
		beditar.setForeground(new Color(0, 0, 0));
		beditar.setEnabled(false);
		beditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editar.setVisible(true);
				editar.toFront();
				beditar.setEnabled(false);
				belim.setEnabled(false);
				bnuevo.setEnabled(false);
				int row = tabla.getSelectedRow();

				arr = new String[tabla.getColumnCount()];
				
				for (int i = 0; i < tabla.getColumnCount(); i++) {
					arr[i] = tabla.getValueAt(row, i).toString();
				}

				for (int i = 0; i < comboServ.getItemCount(); i++) {
					comboServ.setSelectedIndex(i);

					if (arr[2].equals(comboServ.getSelectedItem())) {
						break;
					}
				}
				nombre.setText(arr[1]);
				spinner.setValue(Double.parseDouble(arr[3]));

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date fecha = null, fin = null;
				try {
					fecha = sdf.parse(arr[4]);
					fin = sdf.parse(arr[5]);
				} catch (ParseException e1) {

				}
				fecha_ini.setDate(fecha);
				fecha_fin.setDate(fin);

			}
		});
		beditar.setFont(new Font("Dialog", Font.BOLD, 12));
		beditar.setBounds(54, 324, 89, 23);
		beditar.requestFocus();
		contentPane.add(beditar);

		belim = new JButton("Eliminar");
		belim.setForeground(new Color(251, 72, 72));
		belim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elim.setVisible(true);
				bnuevo.setEnabled(false);
				elim.toFront();
				beditar.setEnabled(false);
				belim.setEnabled(false);
				i = 0;
				tcontra.setBorder(null);

			}
		});
		belim.setFont(new Font("Dialog", Font.BOLD, 12));
		belim.setEnabled(false);
		belim.setBounds(54, 404, 89, 23);
		contentPane.add(belim);

		bnuevo = new JButton("Añadir");
		bnuevo.setForeground(new Color(180, 111, 18));
		bnuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				belim.setEnabled(false);
				beditar.setEnabled(false);
				anadir.setVisible(true);
				tcod.setText(genCod());
				tnombre_1.setText(null);
				comboServ.setSelectedIndex(0);
				spinner_1.setValue(0);
				fecha_ini_1.setDate(null);
				fecha_fin_1.setDate(null);
				bnuevo.setEnabled(false);
				tabla.clearSelection();
				tabla.setRowSelectionAllowed(false);

			}
		});
		bnuevo.setFont(new Font("Dialog", Font.BOLD, 12));
		bnuevo.setBounds(54, 471, 89, 23);
		contentPane.add(bnuevo);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"La opci\u00F3n seleccionada se mostrar\u00E1 aqu\u00ED", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(190, 292, 750, 366);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(219, 117, 108));

		anadir = new JInternalFrame("Panel de agregación");
		anadir.setBounds(10, 77, 716, 155);
		panel_2.add(anadir);
		anadir.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				bnuevo.setEnabled(true);
				tabla.clearSelection();
				tabla.setRowSelectionAllowed(true);
			}
		});
		anadir.setFrameIcon(new ImageIcon(Gestor_Hoteles.class.getResource("/multimedia/anda.png")));
		anadir.getContentPane().setLayout(null);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 706, 126);
		anadir.getContentPane().add(panel_1);

		l1_1 = new JLabel("Nombre del Hotel");
		l1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		l1_1.setBounds(133, 11, 110, 23);
		panel_1.add(l1_1);

		l2_1 = new JLabel("Servicios");
		l2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		l2_1.setBounds(253, 11, 76, 23);
		panel_1.add(l2_1);

		lblNDePersonas_1 = new JLabel("Nº de personas");
		lblNDePersonas_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNDePersonas_1.setBounds(366, 11, 94, 23);
		panel_1.add(lblNDePersonas_1);

		lblFechaInicio_1 = new JLabel("Fecha de inicio");
		lblFechaInicio_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaInicio_1.setBounds(477, 11, 102, 23);
		panel_1.add(lblFechaInicio_1);

		lblFechaDeFin_1 = new JLabel("Fecha de fin");
		lblFechaDeFin_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaDeFin_1.setBounds(594, 11, 102, 23);
		panel_1.add(lblFechaDeFin_1);

		tnombre_1 = new JTextField();
		tnombre_1.setFont(new Font("Dialog", Font.BOLD, 12));
		tnombre_1.setColumns(10);
		tnombre_1.setBounds(133, 36, 96, 41);
		tnombre_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (Character.isLetter(c) || Character.isWhitespace(c)) {

				} else {
					getToolkit().beep();
					ke.consume();

				}

			}
		});
		panel_1.add(tnombre_1);

		comboServ_1 = new JComboBox<String>();
		comboServ_1.setModel(new DefaultComboBoxModel<String>(new String[] { "Ninguno", "Bajo", "Medio", "Alto" }));

		comboServ_1.setFont(new Font("Dialog", Font.BOLD, 12));
		comboServ_1.setBounds(253, 41, 88, 22);
		panel_1.add(comboServ_1);

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinner_1.setFont(new Font("Dialog", Font.BOLD, 12));
		spinner_1.setBounds(376, 36, 84, 41);
		panel_1.add(spinner_1);

		fecha_ini_1 = new JDateChooser();
		fecha_ini_1.setDateFormatString("dd/MM/yyyy");
		fecha_ini_1.setBounds(477, 33, 102, 41);
		panel_1.add(fecha_ini_1);

		fecha_fin_1 = new JDateChooser();
		fecha_fin_1.setDateFormatString("dd/MM/yyyy");
		fecha_fin_1.setBounds(589, 33, 107, 41);
		panel_1.add(fecha_fin_1);

		bguardar_1 = new JButton("Guardar cambios");
		bguardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pattern p = Pattern.compile("\\D+");
				Matcher m = p.matcher(tnombre_1.getText());
				java.util.Date ini = fecha_ini_1.getDate();
				java.util.Date fin = fecha_fin_1.getDate();
				try {
					if (ini.compareTo(fin) >= 0) {// COMPARA LAS FECHAS Y ENTRA SI FIN ES ANTES O IGUAL QUE INICIO
						fecha_ini_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDA",
								TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
						fecha_fin_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDA",
								TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));

					} else {
						fecha_ini_1.setBorder(null);
						fecha_fin_1.setBorder(null);
					}
				} catch (Exception f) {
					fecha_ini_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDA",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
					fecha_fin_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDA",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
				}

				if (tnombre_1.getText().isEmpty() || !m.matches()) {
					tnombre_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDO",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
				} else {
					tnombre_1.setBorder(null);
				}

				if (Integer.parseInt(spinner_1.getValue().toString()) <= 0) {
					spinner_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDO",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
				} else {
					spinner_1.setBorder(null);
				}
				try {
					if (!(ini.compareTo(fin) >= 0) && (!tnombre_1.getText().isEmpty() || m.matches())
							&& !(Integer.parseInt(spinner_1.getValue().toString()) <= 0)) {
						try {
							SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
							java.util.Date dini = fecha_ini_1.getDate();
							String ini_1 = sf.format(dini);

							java.util.Date dfin = fecha_fin_1.getDate();
							String fin_1 = sf.format(dfin);

							Connection con = null;
							conect = new Gestor_Conexion();
							con = conect.getConnection();

							s = "insert into Hotel (Codigo,Nombre_del_Hotel,Servicios,Numero_de_personas,Dia_Uno,Dia_Fin) values (?,?,?,?,?,?)";
							pst = con.prepareStatement(s);

							pst.setString(1, tcod.getText());
							pst.setString(2, tnombre_1.getText());
							pst.setString(3, comboServ_1.getSelectedItem().toString());
							pst.setString(4, spinner_1.getValue().toString());
							pst.setString(5, ini_1);
							pst.setString(6, fin_1);
							int n = pst.executeUpdate();
							if (n > 0) {
								JOptionPane.showMessageDialog(null, "DATOS GUARDADOS CORRECTAMENTE");
								recargarTabla();
								anadir.dispose();
								tabla.clearSelection();
							} else {
								JOptionPane.showMessageDialog(null, "DATOS NO GUARDADOS CORRECTAMENTE");
							}
						} catch (SQLException | HeadlessException l) {
							JOptionPane.showMessageDialog(null, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE" + e,
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception s) {
				}
			}
		});

		bguardar_1.setFont(new Font("Dialog", Font.BOLD, 12));
		bguardar_1.setBounds(557, 92, 139, 23);
		panel_1.add(bguardar_1);

		bcancelar_1 = new JButton("Cancelar");
		bcancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadir.dispose();
				bnuevo.setEnabled(true);
				tabla.setRowSelectionAllowed(true);
			}
		});
		bcancelar_1.setFont(new Font("Dialog", Font.BOLD, 12));
		bcancelar_1.setBounds(376, 93, 139, 23);
		panel_1.add(bcancelar_1);

		l1_2 = new JLabel("Codigo");
		l1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		l1_2.setBounds(10, 11, 110, 23);
		panel_1.add(l1_2);

		tcod = new JTextField();
		tcod.setForeground(new Color(0, 0, 255));
		tcod.setEnabled(false);
		tcod.setFont(new Font("Dialog", Font.BOLD, 12));
		tcod.setColumns(10);
		tcod.setBounds(10, 43, 96, 20);
		panel_1.add(tcod);

		bcargar = new JButton("Cargar registro...");
		bcargar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame = new JInternalFrame("Registro Externo");
				internalFrame.setBounds(84, 30, 629, 287);
				panel_2.add(internalFrame);
				internalFrame.setClosable(true);
				internalFrame.getContentPane().setLayout(null);
				anadir.dispose();
				internalFrame.toFront();

				internalFrame.addInternalFrameListener(new InternalFrameAdapter() {
					@Override
					public void internalFrameClosing(InternalFrameEvent e) {
						anadir.setVisible(true);

					}
				});

				JTabbedPane tabula = new JTabbedPane(JTabbedPane.LEFT);
				tabula.setFont(new Font("Dialog", Font.BOLD, 12));
				tabula.setBounds(10, 11, 599, 236);
				internalFrame.getContentPane().add(tabula);

				JPanel panel = new JPanel();
				tabula.addTab("Seleccion", null, panel, null);
				panel.setLayout(null);

				pan_regis = new JPanel();
				pan_regis.setFont(new Font("Dialog", Font.BOLD, 12));
				internalFrame.setVisible(true);

				JLabel lblNewLabel = new JLabel("Seleccione el documento con los registros a introducir");
				lblNewLabel.setForeground(new Color(0, 128, 0));
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				lblNewLabel.setBounds(0, 11, 360, 33);
				panel.add(lblNewLabel);

				JButton btnNewButton = new JButton("Explorar...");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ch = new JFileChooser();
						ch.setAcceptAllFileFilterUsed(false);
						FileNameExtensionFilter f = new FileNameExtensionFilter("Archivos txt", "txt");
						ch.setFileFilter(f);

						ch.setVisible(true);

						int returnVal = ch.showOpenDialog(null);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							String texto = "";
							StringBuffer sb = new StringBuffer();
							File file = new File(ch.getSelectedFile().toString());

							try {
								@SuppressWarnings("resource")
								Scanner sc = new Scanner(file);

								while (sc.hasNextLine()) {
									sb.append(sc.nextLine() + "\n");

								}

								// COMPROBACION DE DATOS
								texto = sb.toString().replace("\n", ";");
								StringTokenizer st = new StringTokenizer(texto, ";");

								Pattern p = Pattern.compile(
										"\\D+,\\D+,\\d+\\,\\d\\d/\\d\\d/\\d\\d\\d\\d,\\d\\d/\\d\\d/\\d\\d\\d\\d");
								Matcher comp;

								// Los valores de columnas son separados por , mientras que los
								// registros por ;
								boolean permitir = true;

								while (st.hasMoreTokens()) {
									s = st.nextToken().toString() + ";";
									comp = p.matcher(s.substring(0, s.indexOf(";")));
									if (!comp.matches()) {
										most.append(
												"El patrón del documento es erróneo debe contener comas entre los valores y puntos y coma entre registros.");
										permitir = false;
										break;

									}
								}

								if (permitir) {

									most.append(texto);
									bfomr.setEnabled(true);
									tabula.addTab("Muestra", null, pan_regis, null);

								}

								// AQUI TAB NUEVA

							} catch (FileNotFoundException e1) {
								// Errores...
							}

						} else {
							JOptionPane.showMessageDialog(null, "Ha cancelado la selección");
						}

					}
				});
				btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				btnNewButton.setBounds(370, 17, 111, 23);
				panel.add(btnNewButton);
				bfomr = new JButton("Formatear");
				bfomr.setEnabled(false);
				bfomr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// COMPROBAR SI CUMPLE EL PATRON
						String texto = "";
						texto = (most.getText().toString().replace(";", "\n"));
						most.setText("Hotel\tServicios\tPersonas\tInicio\tFin\n");
						most.append("==========================================================\n");
						most.append(texto);

						most.setText(most.getText().toString().replace(",", "\t"));
						most.append("==========================================================\n");

						most.append("Hotel\tServicios\tPersonas\tInicio\tFin\n");

						// APARTE
						texto = "";
						texto = (most.getText().toString().replace("=", ""));
						texto = texto.replace("Hotel\tServicios\tPersonas\tInicio\tFin\n", "");
						texto = texto.replace("\t", ",");
						texto = texto.replace("\n", ";");
						texto = texto.replaceFirst(";", "");
						
						StringTokenizer st = new StringTokenizer(texto, ";");
						registros = new String[st.countTokens()][6];

						int n = st.countTokens();
						int d = 0;
						for (int i = 0; i < n; i++) {
							s = st.nextToken();
							StringTokenizer data = new StringTokenizer(s, ",");
							d = data.countTokens();
							for (int j = 0; j < d + 1; j++) {
								if (j == 0) {
									registros[i][j] = "a-"
											.concat(Integer.toString(Integer.parseInt(genCod().substring(2)) + i));

								} else
									registros[i][j] = data.nextToken();

							}

						}
						dtm = new DefaultTableModel();

						dtm.addColumn("Codigo");
						dtm.addColumn("Hotel");
						dtm.addColumn("Servicios");
						dtm.addColumn("Personas");
						dtm.addColumn("Inicio");
						dtm.addColumn("Fin");

						for (int i = 0; i < registros.length; i++) {

							dtm.addRow(registros[i]);

						}

						tabla_regis.setModel(dtm);
						dtm.fireTableDataChanged();
						dtm.fireTableStructureChanged();
					}
				});
				bfomr.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				bfomr.setBounds(410, 208, 111, 23);
				panel.add(bfomr);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(39, 55, 458, 149);
				panel.add(scrollPane);

				most = new JTextArea();
				scrollPane.setViewportView(most);
				most.setEnabled(false);
				most.setFont(new Font("Dialog", Font.BOLD, 12));
				most.setLineWrap(true);
				pan_regis.setLayout(null);

				JLabel lblAsiSeMostrarian = new JLabel("Asi se mostrarian los nuevos registros en la tabla");
				lblAsiSeMostrarian.setForeground(new Color(0, 128, 0));
				lblAsiSeMostrarian.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				lblAsiSeMostrarian.setBounds(119, 5, 283, 16);
				pan_regis.add(lblAsiSeMostrarian);

				JScrollPane stabla = new JScrollPane();
				stabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				stabla.setBounds(34, 26, 452, 163);
				pan_regis.add(stabla);

				tabla_regis = new JTable();
				tabla_regis.setFont(new Font("Dialog", Font.BOLD, 12));
				tabla_regis.getTableHeader().setReorderingAllowed(false);
				stabla.setViewportView(tabla_regis);

				dtm = new DefaultTableModel() {

					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				dtm.addColumn("Codigo");
				dtm.addColumn("Hotel");
				dtm.addColumn("Servicios");
				dtm.addColumn("Personas");
				dtm.addColumn("Inicio");
				dtm.addColumn("Fin");

				tabla_regis.setModel(dtm);

				bconf = new JButton("Agregar");
				bconf.setFont(new Font("Dialog", Font.BOLD, 12));
				bconf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						most.setText(null);
						bfomr.setEnabled(false);
						internalFrame.dispose();
						bnuevo.setEnabled(true);

						for (int i = 0; i < registros.length; i++) {
							modelo.addRow(registros[i]);
						}
						tabla.setModel(modelo);
						modelo.fireTableDataChanged();
						insertarRegis(registros);
						tabla.setRowSelectionAllowed(true);

					}
				});
				bconf.setBounds(383, 197, 89, 23);
				pan_regis.add(bconf);

				bno = new JButton("Cancelar");
				bno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						most.setText(null);
						bfomr.setEnabled(false);
						internalFrame.dispose();
						bnuevo.setEnabled(true);
						tabla.setRowSelectionAllowed(true);

					}
				});
				bno.setFont(new Font("Dialog", Font.BOLD, 12));
				bno.setBounds(78, 200, 89, 23);
				pan_regis.add(bno);

				internalFrame.setVisible(true);
			}
		});
		bcargar.setForeground(new Color(0, 128, 0));
		bcargar.setFont(new Font("Dialog", Font.BOLD, 12));
		bcargar.setBounds(31, 93, 139, 23);
		panel_1.add(bcargar);
		anadir.setResizable(false);
		anadir.setClosable(true);

		elim = new JInternalFrame("Panel de eliminación");
		elim.setBounds(0, 131, 409, 108);
		panel_2.add(elim);
		elim.addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				tabla.clearSelection();
				bnuevo.setEnabled(true);
			}
		});
		elim.setFrameIcon(new ImageIcon(Gestor_Hoteles.class.getResource("/multimedia/elim.png")));
		elim.setVisible(false);
		elim.getContentPane().setLayout(null);

		JLabel lblNewLabel1 = new JLabel("Introduzca la contraseña para proceder");
		lblNewLabel1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel1.setBounds(10, 8, 239, 14);
		elim.getContentPane().add(lblNewLabel1);

		tcontra = new JPasswordField();
		tcontra.setBackground(new Color(192, 192, 192));

		tcontra.setBounds(20, 26, 130, 35);
		elim.getContentPane().add(tcontra);

		JButton bcontra_elim = new JButton("Eliminar");
		bcontra_elim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s = "";
				try {
					for (int i = 0; i < tcontra.getPassword().toString().length(); i++) {
						s += tcontra.getPassword()[i];
					}
				} catch (Exception ñ) {
					// Mierda de password
				}
				if (i >= 3) {
					JOptionPane.showMessageDialog(null, "DEMASIADOS INTENTOS FALLIDOS CANCELANDO...");
					elim.dispose();
					belim.setEnabled(true);
					beditar.setEnabled(true);
				}

				if (s.equals(CONTRA)) {
					tcontra.setBorder(null);

					int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere eliminar el registro "
							+ (tabla.getValueAt(tabla.getSelectedRow(), 0)) + "?");
					if (resp == 0) {// ELIMINAR
						elimRegis();

					} else {// NADA
						tcontra.setText(null);
						elim.dispose();
						belim.setEnabled(true);
						beditar.setEnabled(true);
					}

				} else {
					tcontra.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INCORRECTA",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
					tcontra.setFont(new Font("Dialog", Font.BOLD, 12));
					tcontra.requestFocus();
					i++;
				}
			}
		});
		bcontra_elim.setForeground(new Color(255, 0, 0));
		bcontra_elim.setFont(new Font("Dialog", Font.BOLD, 12));
		bcontra_elim.setBounds(160, 33, 89, 23);
		elim.getContentPane().add(bcontra_elim);

		bcan = new JButton("Cancelar");
		bcan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elim.dispose();
				beditar.setEnabled(true);
				belim.setEnabled(true);
				bnuevo.setEnabled(true);
			}
		});
		bcan.setForeground(new Color(0, 0, 255));
		bcan.setFont(new Font("Dialog", Font.BOLD, 12));
		bcan.setBounds(278, 33, 89, 23);
		elim.getContentPane().add(bcan);

		editar = new JInternalFrame("Panel de edición");
		editar.setBounds(22, 21, 593, 155);
		panel_2.add(editar);
		editar.setFrameIcon(new ImageIcon(Gestor_Hoteles.class.getResource("/multimedia/edit.png")));
		editar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		editar.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {

				editar.dispose();
				bnuevo.setEnabled(true);
				tabla.clearSelection();
			}
		});
		editar.setResizable(false);
		editar.setClosable(true);
		editar.getContentPane().setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 583, 126);
		editar.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel l1 = new JLabel("Nombre del Hotel");
		l1.setFont(new Font("Dialog", Font.BOLD, 12));
		l1.setBounds(10, 11, 110, 23);
		panel_3.add(l1);

		JLabel l2 = new JLabel("Servicios");
		l2.setFont(new Font("Dialog", Font.BOLD, 12));
		l2.setBounds(130, 11, 76, 23);
		panel_3.add(l2);

		JLabel lblNDePersonas = new JLabel("Nº de personas");
		lblNDePersonas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNDePersonas.setBounds(243, 11, 94, 23);
		panel_3.add(lblNDePersonas);

		JLabel lblFechaInicio = new JLabel("Fecha de inicio");
		lblFechaInicio.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaInicio.setBounds(354, 11, 102, 23);
		panel_3.add(lblFechaInicio);

		JLabel lblFechaDeFin = new JLabel("Fecha de fin");
		lblFechaDeFin.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaDeFin.setBounds(471, 11, 102, 23);
		panel_3.add(lblFechaDeFin);

		nombre = new JTextField();
		nombre.setFont(new Font("Dialog", Font.BOLD, 12));
		nombre.setBounds(10, 42, 96, 20);
		panel_3.add(nombre);
		nombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (Character.isLetter(c) || Character.isWhitespace(c)) {

				} else {
					getToolkit().beep();
					ke.consume();

				}

			}
		});
		nombre.setColumns(10);

		comboServ = new JComboBox<String>();
		comboServ.setModel(new DefaultComboBoxModel<String>(new String[] { "Ninguno", "Bajo", "Medio", "Alto" }));
		comboServ.setFont(new Font("Dialog", Font.BOLD, 12));
		comboServ.setBounds(130, 41, 88, 22);
		panel_3.add(comboServ);

		spinner = new JSpinner();
		spinner.setFont(new Font("Dialog", Font.BOLD, 12));
		spinner.setBounds(253, 41, 65, 24);
		panel_3.add(spinner);

		fecha_ini = new JDateChooser();

		fecha_ini.setDateFormatString("dd/MM/yyyy");
		fecha_ini.setBounds(354, 33, 102, 41);
		panel_3.add(fecha_ini);

		fecha_fin = new JDateChooser();

		fecha_fin.setDateFormatString("dd/MM/yyyy");
		fecha_fin.setBounds(466, 33, 107, 41);
		panel_3.add(fecha_fin);

		JButton bguardar = new JButton("Guardar cambios");
		bguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date ini = fecha_ini.getDate();
				java.util.Date fin = fecha_fin.getDate();

				if (ini.compareTo(fin) >= 0) {// COMPARA LAS FECHAS Y ENTRA SI FIN ES ANTES O IGUAL QUE INICIO
					fecha_ini.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDA",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
					fecha_fin.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 0)), "INV\u00C1LIDA",
							TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));

				} else {
					fecha_ini.setBorder(null);
					fecha_fin.setBorder(null);
					modificarCambios();
					bnuevo.setEnabled(true);
					editar.dispose();
				}

			}
		});
		bguardar.setFont(new Font("Dialog", Font.BOLD, 12));
		bguardar.setBounds(434, 92, 139, 23);
		panel_3.add(bguardar);

		bcancelar = new JButton("Cancelar");
		bcancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar.dispose();
				beditar.setEnabled(true);
				belim.setEnabled(true);
				bnuevo.setEnabled(true);

			}
		});
		bcancelar.setFont(new Font("Dialog", Font.BOLD, 12));
		bcancelar.setBounds(253, 93, 139, 23);
		panel_3.add(bcancelar);

		frameGrafos = new JInternalFrame("Seleccione un Grafo");
		frameGrafos.setBounds(36, 11, 639, 124);
		frameGrafos.setResizable(true);
		frameGrafos.getContentPane().setLayout(null);
		frameGrafos.setFrameIcon(new ImageIcon(Gestor_Hoteles.class.getResource("/multimedia/grafo_ico.png")));
		panel_2.add(frameGrafos);
		frameGrafos.setVisible(false);

		frameGrafos.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				bnuevo.setEnabled(true);
				tabla.clearSelection();
				tabla.setRowSelectionAllowed(true);
				try {
					demo.dispose();
				} catch (Exception v) {

				}

			}
		});

		bhotel = new JButton("Hoteles");
		bhotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					demo.dispose();

				} catch (Exception b) {

				}
				demo = new Grafo("Comparación", "¿Qué hoteles tienen más éxito?", crearGrafo(1));// String[][]
																									// array con
				// Texto de la
				// ocurrencia y veces
				// que ocurra
				demo.pack();
				demo.setVisible(true);
				demo.setBounds(500, 400, 500, 270);

			}
		});
		bhotel.setForeground(new Color(0, 0, 255));
		bhotel.setFont(new Font("Dialog", Font.BOLD, 12));
		bhotel.setBounds(30, 35, 89, 23);
		frameGrafos.getContentPane().add(bhotel);

		JButton btnServicios = new JButton("Servicios");
		btnServicios.setForeground(Color.BLUE);
		btnServicios.setFont(new Font("Dialog", Font.BOLD, 12));
		btnServicios.setBounds(171, 36, 89, 23);
		frameGrafos.getContentPane().add(btnServicios);
		btnServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					demo.dispose();

				} catch (Exception b) {

				}
				demo = new Grafo("Comparación", "¿Qué servicios tienen más éxito?", crearGrafo(2));// String[][]
																									// array con
				// Texto de la
				// ocurrencia y veces
				// que ocurra
				demo.pack();
				demo.setVisible(true);
				demo.setBounds(500, 400, 500, 270);

			}
		});

		JButton btnNPersonas = new JButton("Épocas");
		btnNPersonas.setForeground(Color.BLUE);
		btnNPersonas.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNPersonas.setBounds(314, 36, 108, 23);
		frameGrafos.getContentPane().add(btnNPersonas);

		btnNPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					demo.dispose();

				} catch (Exception b) {

				}
				demo = new Grafo("Comparación", "¿Qué época tiene más reservas?", crearGrafo(4));// String[][]
				// array con
				// Texto de la
				// ocurrencia y veces
				// que ocurra
				demo.pack();
				demo.setVisible(true);
				demo.setBounds(500, 400, 500, 270);

			}
		});

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setForeground(new Color(255, 0, 0));
		btnSalir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSalir.setBounds(538, 22, 81, 48);
		frameGrafos.getContentPane().add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bnuevo.setEnabled(true);
				frameGrafos.dispose();

				try {
					demo.dispose();
				} catch (Exception v) {

				}

			}
		});

		bgrafo = new JButton("Grafos");

		bgrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameGrafos.setVisible(true);
				bnuevo.setEnabled(false);
				belim.setEnabled(false);
				beditar.setEnabled(false);
			}
		});
		bgrafo.setForeground(new Color(0, 0, 255));
		bgrafo.setFont(new Font("Dialog", Font.BOLD, 12));
		bgrafo.setBounds(54, 554, 89, 23);
		contentPane.add(bgrafo);

		bimprimir = new JButton("Imprimir");
		bimprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirJtable(tabla, "Registros de reservas", "Jorgeles TM");
			}
		});
		bimprimir.setForeground(new Color(128, 0, 255));
		bimprimir.setFont(new Font("Dialog", Font.BOLD, 12));
		bimprimir.setBounds(54, 612, 89, 23);
		contentPane.add(bimprimir);

		crearColumnas();
		cargarTabla();

	}
}