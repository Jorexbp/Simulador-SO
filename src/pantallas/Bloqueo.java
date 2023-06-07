package pantallas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import image_fondo.Imagen;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bloqueo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3619575366333364128L;
	private JPanel contentPane;
	private Imagen bg1;
	private JTextField textField;
	private JButton b;
	protected int m_min = 0, m_max = 100, m_counter = 0;
	protected JProgressBar jpb;
	protected JLabel n, t;
	private Runnable runme;
	private Thread runner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e1) {

		}
		UIManager.put("Button.arc", 999);
		UIManager.put("Component.arc", 999);
		UIManager.put("ProgressBar.arc", 999);
		UIManager.put("TextComponent.arc", 999);
		UIManager.put("Component.innerFocusWidth", 1);
		UIManager.put("Button.innerFocusWidth", 1);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bloqueo frame = new Bloqueo();
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
	public Bloqueo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		// de ventana
		int ancho = (int) d.getWidth();
		int alto = (int) d.getHeight();
		setBounds(0, 0, ancho, alto);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);
		setContentPane(contentPane);

		bg1 = new Imagen("/multimedia/bloqe.png", ancho, alto); // Instanciamos
		bg1.setBounds(0, 0, ancho, alto);
		bg1.setOpaque(false); // Requiere la opacidad a false para que se vea el fondo
		bg1.setLayout(null);
		getContentPane().add(bg1);

		n = new JLabel("Usuario");
		n.setForeground(Color.BLUE);
		n.setFont(new Font("Dialog", Font.BOLD, 20));
		n.setBounds(ancho / 2 - 50, alto / 2 - 40, 100, 50);
		bg1.add(n);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (Character.isLetter(c) || Character.isWhitespace(c)) {
					t.setVisible(false);
					t.setText("Cargando");
				} else {
					getToolkit().beep();
					t.setText("No se pueden introducir números");
					t.setVisible(true);
					ke.consume();

				}

			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				b.setEnabled(true);
			}
		});

		textField.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField.setBackground(Color.gray);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Dialog", Font.BOLD, 12));
		textField.setBounds(ancho / 2 - 60, alto / 2, 96, 20);
		bg1.add(textField);

		b = new JButton("Continuar");
		b.setFont(new Font("Dialog", Font.BOLD, 16));
		b.setForeground(Color.BLUE);
		b.setBounds(ancho / 2 - 90, alto / 2 + 50, 150, 50);
		b.setEnabled(false);

		bg1.add(b);

		setUndecorated(true);

		jpb = new JProgressBar();
		jpb.setOrientation(SwingConstants.HORIZONTAL);
		jpb.setMinimum(m_min);
		jpb.setMaximum(m_max);
		jpb.setVisible(false);
		jpb.setBounds(50, alto-100, ancho-80, 50);
		jpb.setStringPainted(true); // Muestra el % de progreso

		t = new JLabel("Cargando");
		t.setFont(new Font("Dialog", Font.BOLD, 18));
		t.setForeground(Color.BLUE);
		t.setBounds(ancho / 2 - 50, (int)(alto/1.25), 1150, 50);
		t.setVisible(false);
		bg1.add(t);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b.getText().equals("Continuar")) {
					b.setText("Cancelar");
					jpb.setVisible(true);
					t.setVisible(true);

				} else {
					b.setText("Continuar");
					textField.setText(null);
					jpb.setValue(0);
					jpb.setVisible(false);
					t.setVisible(false);
					b.setEnabled(false);
				}

				Pattern p = Pattern.compile("\\D+");
				Matcher m = p.matcher(textField.getText());

				runner = new Thread() {
					public void run() {
						m_counter = m_min;

						while (m_counter <= m_max) {
							t.setText(t.getText().concat("."));
							if (t.getText().contains("...")) {
								t.setText("Cargando");
							} else {

								runme = new Runnable() {
									public void run() {
										jpb.setValue(m_counter);
										if (jpb.getValue() == 100) {
											if (jpb.isVisible()) {

												if (m.matches()) {
													dispose();// PROGRESSBAR
													new Home(textField.getText()).setVisible(true);
												} else {
													JOptionPane.showMessageDialog(null,
															"Ese usuario no está permitido");
													textField.setText(null);
													b.setEnabled(false);
													t.setVisible(false);
													jpb.setVisible(false);
												}
											}
										}
									}
								};
								SwingUtilities.invokeLater(runme);
								m_counter++;

								try {
									Thread.sleep(30); // Valor modificable
								} catch (Exception ex) {
								}
							}

						}
					}
				};
				runner.start();

			}
		});

		bg1.add(jpb, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 49, 14);
		contentPane.add(lblNewLabel);

	}
}
