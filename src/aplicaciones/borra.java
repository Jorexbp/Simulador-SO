package aplicaciones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class borra extends JFrame {

	private JPanel contentPane;
	JTextArea most;
	JPanel pan_regis;
	JButton bfomr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					borra frame = new borra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public borra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(84, 11, 629, 287);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JTabbedPane tabula = new JTabbedPane(JTabbedPane.LEFT);
		tabula.setFont(new Font("Dialog", Font.BOLD, 12));
		tabula.setBounds(10, 11, 599, 236);
		internalFrame.getContentPane().add(tabula);

		JPanel panel = new JPanel();
		tabula.addTab("Seleccion", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione el documento con los registros a introducir");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(0, 11, 360, 33);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Explorar...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser regis = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .txt", "txt");
				regis.setAcceptAllFileFilterUsed(false);
				regis.setFileFilter(filter);

				regis.setVisible(true);

				int returnVal = regis.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String texto = "";
					StringBuffer sb = new StringBuffer();
					File file = new File(regis.getSelectedFile().toString());

					try {
						@SuppressWarnings("resource")
						Scanner sc = new Scanner(file);

						while (sc.hasNextLine()) {
							sb.append(sc.nextLine() + "\n");

						}

						texto = sb.toString().replace("\n", ";");

						// Los valores de columnas son separados por , mientras que los
						// registros por ;

						most.append(texto);
						bfomr.setEnabled(true);
						
						//AQUI TAB NUEVA

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
				String texto = "";
				texto = (most.getText().toString().replace(";", "\n"));
				most.setText("Hotel\tServicios\tPersonas\tInicio\tFin\n");
				most.append("==========================================================\n");
				most.append(texto);

				most.setText(most.getText().toString().replace(",", "\t"));
				most.append("==========================================================\n");

				most.append("Hotel\tServicios\tPersonas\tInicio\tFin\n");

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

		 pan_regis = new JPanel();
		pan_regis.setFont(new Font("Dialog", Font.BOLD, 12));	
		tabula.addTab("Muestra", null, pan_regis, null);
		pan_regis.setLayout(null);
		
		JLabel lblAsiSeMostrarian = new JLabel("Asi se mostrarian los nuevos registros en la tabla");
		lblAsiSeMostrarian.setForeground(new Color(0, 128, 0));
		lblAsiSeMostrarian.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblAsiSeMostrarian.setBounds(119, 11, 360, 33);
		pan_regis.add(lblAsiSeMostrarian);
		
		JScrollPane stabla = new JScrollPane();
		stabla.setBounds(10, 44, 501, 143);
		pan_regis.add(stabla);
		
		JTable tabla_regis = new JTable();
		tabla_regis.setFont(new Font("Dialog", Font.BOLD, 12));
		stabla.setViewportView(tabla_regis);
		
		
		internalFrame.setVisible(true);
	}
}
