package pantallas;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatLightLaf;

import aplicaciones.Calculadora;
import aplicaciones.Evento;
import aplicaciones.Gestor_Hoteles;
import aplicaciones.Jorgify;
import aplicaciones.Padre;
import image_fondo.Imagen;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton tarea1;
	private JButton tarea2;
	private JButton tarea4;
	private JButton tarea3;
	private JButton tarea5;
	private Imagen bg1;
	private JButton aplicacion1;
	private static Home frame;
	private JLabel lblNewLabel;
	private Padre pa;
	private JButton aplicacion2;
	private Calculadora calcu;
	private JButton bmini_bloc;
	private JButton bmini_calcu;
	private Jorgify ify;
	private JLabel lblCalendarioDeEventos;
	private JButton aplicacion4;
	private Evento eve;
	private JButton beve_mini;
	private JButton bmini_gestor;
	private Gestor_Hoteles gestor;

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
					frame = new Home();
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

	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/multimedia/icon_home.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setUndecorated(true);
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.SOUTH);

		tarea1 = new JButton("Icono_tarea");
		tarea1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/home.png")));
		toolBar.add(tarea1);

		tarea2 = new JButton("Icono_tarea");
		toolBar.add(tarea2);

		tarea3 = new JButton("Icono_tarea");
		toolBar.add(tarea3);

		tarea4 = new JButton("Icono_tarea");
		toolBar.add(tarea4);

		tarea5 = new JButton("Icono_tarea");
		toolBar.add(tarea5);

		tarea1.setOpaque(false);
		tarea1.setContentAreaFilled(false);
		tarea1.setBorderPainted(false);

		tarea2.setOpaque(false);
		tarea2.setContentAreaFilled(false);
		tarea2.setBorderPainted(false);

		tarea3.setOpaque(false);
		tarea3.setContentAreaFilled(false);
		tarea3.setBorderPainted(false);

		tarea4.setOpaque(false);
		tarea4.setContentAreaFilled(false);
		tarea4.setBorderPainted(false);

		tarea5.setOpaque(false);
		tarea5.setContentAreaFilled(false);
		tarea5.setBorderPainted(false);

		bmini_bloc = new JButton("Bloc de Jorge");
		bmini_bloc.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (pa.ICONIFIED == 1) {
					pa.setState(Frame.NORMAL);
					pa.setVisible(true);
				} else if (pa.isVisible()) {

					if (!pa.hasFocus()) {
						pa.requestFocus();
					}
					pa.setVisible(false);

				} else {
					pa.setState(ICONIFIED);

				}

			}
		});
		bmini_bloc.setVisible(false);
		bmini_bloc.setIcon(new ImageIcon(Home.class.getResource("/multimedia/Icono_Bloc_mini.png")));
		toolBar.add(bmini_bloc);

		bmini_calcu = new JButton("Calculadora de Jorge");
		bmini_calcu.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (calcu.ICONIFIED == 1) {
					calcu.setState(Frame.NORMAL);
					calcu.setVisible(true);
				} else if (calcu.isVisible()) {

					if (!calcu.hasFocus()) {
						calcu.requestFocus();
					}
					calcu.setVisible(false);

				} else {
					calcu.setVisible(true);
				}

			}
		});
		bmini_calcu.setVisible(false);
		bmini_calcu.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calculadora_icon_mini.png")));
		toolBar.add(bmini_calcu);

		JButton bmini_ify = new JButton("Jorgify");
		bmini_ify.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (ify.ICONIFIED == 1) {
					ify.setState(Frame.NORMAL);
					ify.setVisible(true);
				} else if (ify.isVisible()) {

					if (!ify.hasFocus()) {
						ify.requestFocus();
					}
					ify.setVisible(false);

				} else {
					ify.setVisible(true);
				}

			}
		});
		bmini_ify.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3_mini.png")));
		bmini_ify.setVisible(false);
		toolBar.add(bmini_ify);

		beve_mini = new JButton("Eventos");
		beve_mini.setVisible(false);
		beve_mini.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					if (eve.ICONIFIED == 1) {
						eve.setState(Frame.NORMAL);
						eve.setVisible(true);
					} else if (eve.isVisible()) {

						if (!ify.hasFocus()) {
							eve.requestFocus();
						}
						eve.setVisible(false);

					} else {
						eve.setVisible(true);
					}
				} catch (Exception w) {
					eve = new Evento();
				}
			}

		});
		beve_mini.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calen (1) (1).png")));
		beve_mini.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar.add(beve_mini);
		
		bmini_gestor = new JButton("Gestor de Jorgeles");
		bmini_gestor.setVisible(false);
		bmini_gestor.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (gestor.ICONIFIED == 1) {
					gestor.setState(Frame.NORMAL);
					gestor.setVisible(true);
				} else if (gestor.isVisible()) {

					if (!gestor.hasFocus()) {
						gestor.requestFocus();
					}
					gestor.setVisible(false);

				} else {
					gestor.setVisible(true);
				}

			}
		});
			
		bmini_gestor.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon_mini.png")));
		toolBar.add(bmini_gestor);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		bg1 = new Imagen("/multimedia/back.png", (int) d.getWidth(), (int) d.getHeight());
		bg1.setOpaque(false);
		bg1.setLayout(null);

		getContentPane().add(bg1);

		pa = new Padre();
		aplicacion1 = new JButton("Bloc de Jorge");
		aplicacion1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (pa.ICONIFIED == 1) {
					bmini_bloc.setVisible(true);
				}
				if (!pa.isVisible()) {

					pa.setVisible(true);
				} else {
					pa.setState(Frame.NORMAL);
					pa.requestFocus();
					pa.toFront();// Funciona para minimizar la app

				}

			}
		});

		pa.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bmini_bloc.setVisible(false);
			}
		});

		aplicacion1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/Icono_Bloc.png")));
		aplicacion1.setOpaque(true);
		aplicacion1.setContentAreaFilled(false);
		aplicacion1.setBorderPainted(false);
		aplicacion1.setBounds(50, 58, 67, 61);
		bg1.add(aplicacion1);

		lblNewLabel = new JLabel("Bloc de Jorge");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 118, 87, 22);
		bg1.add(lblNewLabel);

		calcu = new Calculadora();
		aplicacion2 = new JButton("");
		aplicacion2.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (calcu.ICONIFIED == 1) {
					bmini_calcu.setVisible(true);
				}
				if (!calcu.isVisible()) {

					calcu.setVisible(true);

				} else {
					calcu.setState(Frame.NORMAL);
					calcu.requestFocus();
					calcu.toFront();// Funciona para minimizar la app

				}

			}
		});

		calcu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bmini_calcu.setVisible(false);
			}
		});
		aplicacion2.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calculadora_icon.png")));
		aplicacion2.setBounds(168, 58, 73, 61);
		aplicacion2.setOpaque(true);
		aplicacion2.setContentAreaFilled(false);
		aplicacion2.setBorderPainted(false);
		bg1.add(aplicacion2);

		JLabel lblCalculadoraDeJorge = new JLabel("Calculadora de Jorge");
		lblCalculadoraDeJorge.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculadoraDeJorge.setForeground(Color.WHITE);
		lblCalculadoraDeJorge.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCalculadoraDeJorge.setBounds(140, 118, 141, 22);
		bg1.add(lblCalculadoraDeJorge);

		ify = new Jorgify();
		JButton aplicacion3 = new JButton("");
		aplicacion3.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (ify.ICONIFIED == 1) {
					bmini_ify.setVisible(true);
				}
				if (!ify.isVisible()) {

					ify.setVisible(true);
				} else {
					ify.setState(Frame.NORMAL);
					ify.requestFocus();
					ify.toFront();// Funciona para minimizar la app

				}

			}
		});
		ify.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bmini_ify.setVisible(false);
			}
		});
		aplicacion3.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3.png")));
		aplicacion3.setOpaque(true);
		aplicacion3.setContentAreaFilled(false);
		aplicacion3.setBorderPainted(false);
		aplicacion3.setBounds(318, 58, 73, 61);
		bg1.add(aplicacion3);

		JLabel lblJorgify = new JLabel("Jorgify");
		lblJorgify.setHorizontalAlignment(SwingConstants.CENTER);
		lblJorgify.setForeground(Color.WHITE);
		lblJorgify.setFont(new Font("Dialog", Font.BOLD, 13));
		lblJorgify.setBounds(300, 118, 103, 22);
		bg1.add(lblJorgify);
		eve = new Evento();
		eve.setVisible(false);
		aplicacion4 = new JButton("");
		aplicacion4.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				if (eve.ICONIFIED == 1) {
					beve_mini.setVisible(true);
				}
				if (!eve.isVisible()) {

					eve.setVisible(true);
				} else {
					eve.setState(Frame.NORMAL);
					eve.requestFocus();
					eve.toFront();// Funciona para minimizar la app

				}

			}

		});
		aplicacion4.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calen (1).png")));
		aplicacion4.setOpaque(true);
		aplicacion4.setContentAreaFilled(false);
		aplicacion4.setBorderPainted(false);
		aplicacion4.setBounds(444, 58, 73, 61);
		bg1.add(aplicacion4);
		try {
			eve.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					beve_mini.setVisible(false);
				}
			});
		} catch (Exception j) {

		}
		lblCalendarioDeEventos = new JLabel("Eventos");
		lblCalendarioDeEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalendarioDeEventos.setForeground(Color.WHITE);
		lblCalendarioDeEventos.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCalendarioDeEventos.setBounds(415, 122, 132, 22);
		bg1.add(lblCalendarioDeEventos);
		
		
		gestor = new Gestor_Hoteles();
		JButton aplicacion5 = new JButton("");
		aplicacion5.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (gestor.ICONIFIED == 1) {
					bmini_gestor.setVisible(true);
				}
				if (!gestor.isVisible()) {

					gestor.setVisible(true);
				} else {
					gestor.setState(Frame.NORMAL);
					gestor.requestFocus();
					gestor.toFront();// Funciona para minimizar la app

				}

			}

		});
		gestor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bmini_gestor.setVisible(false);
			}
		});
		aplicacion5.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon.png")));
		aplicacion5.setOpaque(true);
		aplicacion5.setContentAreaFilled(false);
		aplicacion5.setBorderPainted(false);
		aplicacion5.setBounds(35, 170, 82, 69);
		bg1.add(aplicacion5);
		
		JLabel lblGestorDeJorgeles = new JLabel("Gestor de Jorgeles");
		lblGestorDeJorgeles.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestorDeJorgeles.setForeground(Color.WHITE);
		lblGestorDeJorgeles.setFont(new Font("Dialog", Font.BOLD, 13));
		lblGestorDeJorgeles.setBounds(10, 231, 141, 22);
		bg1.add(lblGestorDeJorgeles);

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setUndecorated(true);
	}
}
