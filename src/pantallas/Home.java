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
import aplicaciones.Mapa;
import aplicaciones.Padre;
import image_fondo.Imagen;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.border.LineBorder;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, panel_1, paneconfig, panel_2, panel_3;
	private JButton tarea1, tarea4, tarea5, aplicacion2, bmini_bloc, bmini_calcu, aplicacion4, beve_mini, bmini_gestor,
			ejemplotxt, aplicacion2_1, aplicacion5_1, bmini_mapa, appmapa, aplicacion1, bmini_ify, aplicacion3,
			aplicacion5;
	private Imagen bg1;
	private static Home frame;
	private Padre pa;
	private Calculadora calcu;
	private Jorgify ify;
	private Evento eve;
	private Gestor_Hoteles gestor;
	private Mapa mapa;
	private JLabel lcalendar, lcom, licon, lbloc, lify, lcalcu, lhotel, ltxt, ltem, lcon, lhora, lmin, ldosptos,
			espacip, lfecha, lblFecha, larbol, lmapa, lbien;
	private JInternalFrame frameconfig, explorador, internalFrame;
	private JRadioButton rmed, rpeque, rgregoriano, rnormal, roscuro, rclaro, rgrande;
	private JRadioButtonMenuItem rdbtnmntmDdmmyyyy, rdbtnmntmYyyymmdd;
	private ButtonGroup bg, btg, bg3, bmenu, bhora, bfecha;
	private JSplitPane sp;
	private JSeparator separator_1, separator_4, separator_5;
	private JToolBar toolBar;
	private JTree tree;
	private Timer timer;
	private boolean gregoriano = true;
	private DefaultMutableTreeNode node_1, node_2;
	private Calendar cal = Calendar.getInstance();
	private JRadioButtonMenuItem mcontpeque, mcontmed, mcontgran;
	private JMenuBar menuBar, ini;
	private JMenu Ver, menusis, mini;
	private JMenuItem licon2, mntmNewMenuItem_2, mreini, mapag, musu;
	private JComboBox<String> comboBox;
	private JCheckBox com;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private Dimension d;
	
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
					frame = new Home("Usuario");
					frame.setVisible(true);
				} catch (Exception e) {

				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Object[] myWalk(TreeModel model, String s, String t) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		DefaultMutableTreeNode child;
		TreeNode[] returnPath = null;
		int childrenCount = root.getChildCount();
		for (int i = 0; i < childrenCount; i++) {
			child = (DefaultMutableTreeNode) root.getChildAt(i);
			if (child.toString().equals(s)) {

				int secondChildCount = child.getChildCount();
				DefaultMutableTreeNode secondLevelChild;
				for (int y = 0; y < secondChildCount; y++) {
					secondLevelChild = (DefaultMutableTreeNode) child.getChildAt(y);
					if (secondLevelChild.toString().equals(t)) {
						returnPath = secondLevelChild.getPath();
						// returnPath = new TreePath(new Object[] {root.toString(), child.toString(),
						// secondLevelChild.toString()});

						// Madre de dios hasta que entendi como funcina el puto JTree
					}
				}

			}

		}
		return returnPath;
	}

	private void iconGrande() {
		aplicacion1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/bloc_grande.png")));
		lbloc.setBounds(35, 105 + 35, 87, 22);

		aplicacion2.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calcu_grande.png")));
		lcalcu.setBounds(150, 140, 141, 22);

		aplicacion3.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3_grande.png")));
		lify.setBounds(300, 140, 103, 22);

		aplicacion4.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calen_grande.png")));
		lcalendar.setBounds(415, 140, 132, 22);

		aplicacion5.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon_grande.png")));
		lhotel.setBounds(10, 244, 141, 22);

		ejemplotxt.setIcon(new ImageIcon(Home.class.getResource("/multimedia/txt_grande.png")));
		ltxt.setBounds(0, 138, 132, 22);

		appmapa.setIcon(new ImageIcon(Home.class.getResource("/multimedia/mapa_icon_grande.png")));
		lmapa.setBounds(35, 390, 87, 22);

		mcontgran.setSelected(true);

	}

	private void iconMed() {
		aplicacion1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/Icono_Bloc.png")));
		lbloc.setBounds(35, 105, 87, 22);

		aplicacion2.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calculadora_icon.png")));
		lcalcu.setBounds(150, 105, 141, 22);

		aplicacion3.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3.png")));
		lify.setBounds(300, 105, 103, 22);

		aplicacion4.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calen (1).png")));
		lcalendar.setBounds(415, 105, 132, 22);

		aplicacion5.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon.png")));
		lhotel.setBounds(10, 224, 141, 22);

		ejemplotxt.setIcon(new ImageIcon(Home.class.getResource("/multimedia/txt.png")));
		ltxt.setBounds(0, 97, 132, 22);

		appmapa.setIcon(new ImageIcon(Home.class.getResource("/multimedia/mapa_icon (1) (1).png")));
		lmapa.setBounds(35, 356, 87, 22);

		mcontmed.setSelected(true);
	}

	private void iconPeque() {
		aplicacion1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/Icono_bloc_mini.png")));
		lbloc.setBounds(35, 90, 87, 22);

		aplicacion2.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calculadora_icon_mini.png")));
		lcalcu.setBounds(150, 90, 141, 22);

		aplicacion3.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3_mini.png")));
		lify.setBounds(300, 90, 103, 22);

		aplicacion4.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calen (1) (1).png")));
		lcalendar.setBounds(415, 90, 132, 22);

		aplicacion5.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon_mini.png")));
		lhotel.setBounds(10, 221, 141, 22);

		ejemplotxt.setIcon(new ImageIcon(Home.class.getResource("/multimedia/txt_mini.png")));
		ltxt.setBounds(0, 87, 132, 22);

		appmapa.setIcon(new ImageIcon(Home.class.getResource("/multimedia/mapa_icon_peque.png")));
		lmapa.setBounds(35, 341, 87, 22);

		mcontpeque.setSelected(true);
	}
	public Home(String nom) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/multimedia/icon_home.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.SOUTH);

		tarea1 = new JButton("Inicio");
		tarea1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				if (panel_3.isVisible()) {
					panel_3.setVisible(false);
				} else {
					panel_3.setVisible(true);
				}
			}
		});
		tarea1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/home.png")));
		toolBar.add(tarea1);

		tarea4 = new JButton("Explorador");
		tarea4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				explorador.setVisible(true);
				explorador.requestFocus();
				internalFrame.dispose();
			}
		});

		tarea4.setIcon(new ImageIcon(Home.class.getResource("/multimedia/explorador (1) .png")));
		toolBar.add(tarea4);

		tarea5 = new JButton("Configuración");
		tarea5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameconfig.setVisible(true);
				internalFrame.dispose();
			}
		});
		tarea5.setIcon(new ImageIcon(Home.class.getResource("/multimedia/config_icono.png")));
		toolBar.add(tarea5);

		tarea1.setOpaque(false);
		tarea1.setContentAreaFilled(false);
		tarea1.setBorderPainted(false);

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
				internalFrame.dispose();
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
				internalFrame.dispose();
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

		bmini_ify = new JButton("Jorgify");
		bmini_ify.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
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
				internalFrame.dispose();
				try {
					if (eve.ICONIFIED == 1) {
						eve.setState(Frame.NORMAL);
						eve.setVisible(true);
					} else if (eve.isVisible()) {

						if (!eve.hasFocus()) {
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
				internalFrame.dispose();
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

		lhora = new JLabel("HORA");
		lhora.setFont(new Font("Dialog", Font.BOLD, 18));
		lhora.setHorizontalAlignment(SwingConstants.CENTER);

		bmini_mapa = new JButton("JorgelMaps");
		bmini_mapa.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				if (mapa.ICONIFIED == 1) {
					mapa.setState(Frame.NORMAL);
					mapa.setVisible(true);
				} else if (mapa.isVisible()) {

					if (!mapa.hasFocus()) {
						mapa.requestFocus();
					}
					mapa.setVisible(false);

				} else {
					mapa.setState(ICONIFIED);

				}

			}
		});

		bmini_mapa.setIcon(new ImageIcon(Home.class.getResource("/multimedia/mapa_icon (1).png")));
		toolBar.add(bmini_mapa);
		toolBar.add(Box.createGlue());
		toolBar.add(lhora);

		ldosptos = new JLabel(":");
		ldosptos.setHorizontalAlignment(SwingConstants.CENTER);
		ldosptos.setFont(new Font("Dialog", Font.BOLD, 18));
		toolBar.add(ldosptos);

		lmin = new JLabel("MIN");
		lmin.setHorizontalAlignment(SwingConstants.CENTER);
		lmin.setFont(new Font("Dialog", Font.BOLD, 18));
		toolBar.add(lmin);

		espacip = new JLabel("          ");
		espacip.setHorizontalAlignment(SwingConstants.CENTER);
		espacip.setFont(new Font("Dialog", Font.BOLD, 18));
		toolBar.add(espacip);

		lfecha = new JLabel("FECHA");
		lfecha.setHorizontalAlignment(SwingConstants.CENTER);
		lfecha.setFont(new Font("Dialog", Font.BOLD, 18));
		toolBar.add(lfecha);

		class cronometro implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// Clock tiempo = Clock.systemUTC();
				GregorianCalendar tiempo = new GregorianCalendar();
				int hora, minutos;
				hora = tiempo.get(Calendar.HOUR);
				minutos = tiempo.get(Calendar.MINUTE);
				if (gregoriano || (hora + 12 > 23)) {
					lhora.setText(Integer.toString(hora));
				} else {

					lhora.setText(Integer.toString(hora + 12));
				}
				if (Integer.toString(minutos).length() != 2) {
					lmin.setText("0" + Integer.toString(minutos));
				} else {
					lmin.setText(Integer.toString(minutos));
				}

			}
		}
		timer = new Timer(1000, new cronometro());
		timer.start();

		String fecha = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MARCH) + 1) + "/" + cal.get(Calendar.YEAR);
		lfecha.setText(fecha);
		Toolkit tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();

		bg1 = new Imagen("/multimedia/back.png", (int) d.getWidth(), (int) d.getHeight());
		internalFrame = new JInternalFrame();

		internalFrame.setFrameIcon(new ImageIcon(Home.class.getResource("/multimedia/contex.png")));
		Ver = new JMenu("   Ver             >");

		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 122, 25);
		internalFrame.getContentPane().add(panel_2);

		menuBar = new JMenuBar();
		panel_2.add(menuBar);

		Ver.setMnemonic('V');
		Ver.setFont(new Font("Dialog", Font.BOLD, 12));

		menuBar.add(Ver);

		licon2 = new JMenuItem("Iconos");
		licon2.setFont(new Font("Dialog", Font.BOLD, 12));
		licon2.setEnabled(false);
		Ver.add(licon2);

		mcontgran = new JRadioButtonMenuItem("Grandes");
		mcontgran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconGrande();
				internalFrame.dispose();
				rgrande.setSelected(true);
			}
		});
		mcontgran.setFont(new Font("Dialog", Font.BOLD, 12));
		Ver.add(mcontgran);

		mcontmed = new JRadioButtonMenuItem("Medianos");
		mcontmed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconMed();
				internalFrame.dispose();
				rmed.setSelected(true);
			}
		});
		mcontmed.setFont(new Font("Dialog", Font.BOLD, 12));
		Ver.add(mcontmed);

		mcontpeque = new JRadioButtonMenuItem("Pequeños");
		mcontpeque.setFont(new Font("Dialog", Font.BOLD, 12));
		mcontpeque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconPeque();
				internalFrame.dispose();
				rpeque.setSelected(true);
			}
		});
		Ver.add(mcontpeque);

		JSeparator separator_2 = new JSeparator();
		Ver.add(separator_2);

		chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Pantalla Completa");
		chckbxmntmNewCheckItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isUndecorated()) {
					setResizable(true);

					dispose();
					setUndecorated(false);
					setVisible(true);
					com.setSelected(false);

				} else {
					setResizable(false);

					dispose();
					setUndecorated(true);
					setVisible(true);
					com.setSelected(true);
				}
				internalFrame.dispose();

			}
		});
		chckbxmntmNewCheckItem.setFont(new Font("Dialog", Font.BOLD, 12));
		chckbxmntmNewCheckItem.setSelected(true);
		Ver.add(chckbxmntmNewCheckItem);
		internalFrame.setVisible(true);
		bmenu = new ButtonGroup();
		bmenu.add(mcontgran);
		bmenu.add(mcontmed);
		bmenu.add(mcontpeque);
		mcontmed.setSelected(true);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 25, 122, 34);
		internalFrame.getContentPane().add(panel_2_1);

		JMenuBar menuBar_1 = new JMenuBar();
		panel_2_1.add(menuBar_1);

		menusis = new JMenu("Sistema       >");
		menusis.setFont(new Font("Dialog", Font.BOLD, 12));
		menusis.setMnemonic('S');
		menuBar_1.add(menusis);

		JMenuItem mntmNewMenuItem = new JMenuItem("Actualizar");
		mntmNewMenuItem.setFont(new Font("Dialog", Font.BOLD, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(true);
				internalFrame.dispose();
			}
		});
		menusis.add(mntmNewMenuItem);

		JSeparator separator_3 = new JSeparator();
		menusis.add(separator_3);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Hora");
		mntmNewMenuItem_1.setEnabled(false);
		mntmNewMenuItem_1.setFont(new Font("Dialog", Font.BOLD, 12));
		menusis.add(mntmNewMenuItem_1);

		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Base 12H");
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gregoriano = true;
				timer = new Timer(1000, new cronometro());
				timer.start();
				rgregoriano.setSelected(true);
				internalFrame.dispose();
				rgregoriano.setSelected(true);
			}
		});
		rdbtnmntmNewRadioItem.setFont(new Font("Dialog", Font.BOLD, 12));

		rdbtnmntmNewRadioItem.setSelected(true);
		menusis.add(rdbtnmntmNewRadioItem);

		JRadioButtonMenuItem rdbtnmntmBaseh = new JRadioButtonMenuItem("Base 24H");
		rdbtnmntmBaseh.setFont(new Font("Dialog", Font.BOLD, 12));
		rdbtnmntmBaseh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rnormal.setSelected(true);
				gregoriano = false;
				timer = new Timer(1000, new cronometro());
				timer.start();
				internalFrame.dispose();
				rnormal.setSelected(true);
			}
		});
		menusis.add(rdbtnmntmBaseh);
		panel_2.requestFocus();

		bhora = new ButtonGroup();
		bhora.add(rdbtnmntmNewRadioItem);
		bhora.add(rdbtnmntmBaseh);

		separator_4 = new JSeparator();
		menusis.add(separator_4);

		mntmNewMenuItem_2 = new JMenuItem("Fecha");
		mntmNewMenuItem_2.setFont(new Font("Dialog", Font.BOLD, 12));
		mntmNewMenuItem_2.setEnabled(false);
		menusis.add(mntmNewMenuItem_2);

		rdbtnmntmDdmmyyyy = new JRadioButtonMenuItem("DD/MM/YYYY");
		rdbtnmntmDdmmyyyy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String fecha = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MARCH) + 1) + "/"
						+ cal.get(Calendar.YEAR);

				lfecha.setText(fecha);

				comboBox.setSelectedIndex(0);
				internalFrame.dispose();
			}
		});
		rdbtnmntmDdmmyyyy.setSelected(true);
		rdbtnmntmDdmmyyyy.setFont(new Font("Dialog", Font.BOLD, 12));
		menusis.add(rdbtnmntmDdmmyyyy);

		rdbtnmntmYyyymmdd = new JRadioButtonMenuItem("YYYY/MM/DD");
		rdbtnmntmYyyymmdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MARCH) + 1) + "/"
						+ cal.get(Calendar.DATE);

				lfecha.setText(fecha);
				comboBox.setSelectedIndex(1);

				internalFrame.dispose();
			}
		});
		rdbtnmntmYyyymmdd.setFont(new Font("Dialog", Font.BOLD, 12));
		menusis.add(rdbtnmntmYyyymmdd);

		bfecha = new ButtonGroup();
		bfecha.add(rdbtnmntmDdmmyyyy);
		bfecha.add(rdbtnmntmYyyymmdd);

		bg1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				internalFrame.dispose();

				if (SwingUtilities.isRightMouseButton(e)) {
					try {
						internalFrame.setVisible(false);
						Ver = new JMenu("   Ver             >");

					} catch (Exception h) {

					}

					internalFrame.setVisible(true);
					internalFrame.setBounds(e.getX(), e.getY(), 132, 92);
					bg1.add(internalFrame);
					internalFrame.getContentPane().setLayout(null);
					internalFrame.requestFocus();
				} else {
					panel_3.setVisible(false);
					bg1.requestFocus();
				}
			}
		});

		bg1.setOpaque(false);
		bg1.setLayout(null);

		getContentPane().add(bg1);
		pa = new Padre("");

		aplicacion1 = new JButton("");
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
		aplicacion1.setContentAreaFilled(false);
		aplicacion1.setBorderPainted(false);
		aplicacion1.setBounds(10, 7, 132, 133);
		bg1.add(aplicacion1);

		lbloc = new JLabel("Bloc de Jorge");
		lbloc.setForeground(new Color(255, 255, 255));
		lbloc.setFont(new Font("Dialog", Font.BOLD, 13));
		lbloc.setHorizontalAlignment(SwingConstants.CENTER);
		lbloc.setBounds(28, 105, 99, 22);
		bg1.add(lbloc);

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
		aplicacion2.setBounds(149, 11, 141, 129);
		aplicacion2.setOpaque(true);
		aplicacion2.setContentAreaFilled(false);
		aplicacion2.setBorderPainted(false);
		bg1.add(aplicacion2);

		lcalcu = new JLabel("Calculadora de Jorge");
		lcalcu.setHorizontalAlignment(SwingConstants.CENTER);
		lcalcu.setForeground(Color.WHITE);
		lcalcu.setFont(new Font("Dialog", Font.BOLD, 13));
		lcalcu.setBounds(150, 105, 141, 22);
		bg1.add(lcalcu);

		aplicacion3 = new JButton("");
		aplicacion3.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				ify = new Jorgify();

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
				ify.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						bmini_ify.setVisible(false);
					}
				});

			}
		});

		aplicacion3.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3.png")));
		aplicacion3.setOpaque(true);
		aplicacion3.setContentAreaFilled(false);
		aplicacion3.setBorderPainted(false);
		aplicacion3.setBounds(291, 18, 132, 109);
		bg1.add(aplicacion3);

		lify = new JLabel("Jorgify");// .setBounds(150, 105, 141, 22);
		lify.setHorizontalAlignment(SwingConstants.CENTER);
		lify.setForeground(Color.WHITE);
		lify.setFont(new Font("Dialog", Font.BOLD, 13));
		lify.setBounds(300, 105, 103, 22);
		bg1.add(lify);
		aplicacion4 = new JButton("");
		aplicacion4.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				eve = new Evento();

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

				eve.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						beve_mini.setVisible(false);
					}
				});
			}

		});
		aplicacion4.setIcon(new ImageIcon(Home.class.getResource("/multimedia/calen (1).png")));
		aplicacion4.setOpaque(true);
		aplicacion4.setContentAreaFilled(false);
		aplicacion4.setBorderPainted(false);
		aplicacion4.setBounds(410, 15, 148, 121);
		bg1.add(aplicacion4);

		lcalendar = new JLabel("Eventos");
		lcalendar.setHorizontalAlignment(SwingConstants.CENTER);
		lcalendar.setForeground(Color.WHITE);
		lcalendar.setFont(new Font("Dialog", Font.BOLD, 13));
		lcalendar.setBounds(415, 105, 132, 22);
		bg1.add(lcalendar);

		aplicacion5 = new JButton("");
		aplicacion5.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				gestor = new Gestor_Hoteles();

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
				gestor.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						bmini_gestor.setVisible(false);
					}
				});

			}

		});

		aplicacion5.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon.png")));
		aplicacion5.setOpaque(true);
		aplicacion5.setContentAreaFilled(false);
		aplicacion5.setBorderPainted(false);
		aplicacion5.setBounds(20, 138, 131, 133);
		bg1.add(aplicacion5);

		lhotel = new JLabel("Gestor de Jorgeles");
		lhotel.setHorizontalAlignment(SwingConstants.CENTER);
		lhotel.setForeground(Color.WHITE);
		lhotel.setFont(new Font("Dialog", Font.BOLD, 13));
		lhotel.setBounds(10, 224, 141, 22);
		bg1.add(lhotel);

		frameconfig = new JInternalFrame("Configuración");

		frameconfig.setClosable(true);
		frameconfig.setFrameIcon(new ImageIcon(Home.class.getResource("/multimedia/config_icono.png")));
		frameconfig.setClosable(true);
		frameconfig.setBounds(161, 160, 652, 412);
		bg1.add(frameconfig);
		frameconfig.getContentPane().setLayout(null);

		paneconfig = new JPanel();
		paneconfig.setBounds(10, 11, 622, 361);
		frameconfig.getContentPane().add(paneconfig);
		paneconfig.setLayout(null);

		lcon = new JLabel("Configuración");

		lcon.setFont(new Font("Dialog", Font.BOLD, 25));
		lcon.setBounds(23, 11, 261, 62);
		paneconfig.add(lcon);

		lcom = new JLabel("Pantalla completa");
		lcom.setFont(new Font("Dialog", Font.BOLD, 15));
		lcom.setBounds(33, 84, 151, 42);
		paneconfig.add(lcom);

		com = new JCheckBox("");
		com.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				if (isUndecorated()) {
					setResizable(true);

					dispose();
					setUndecorated(false);
					setVisible(true);
					chckbxmntmNewCheckItem.setSelected(false);

				} else {
					setResizable(false);

					dispose();
					setUndecorated(true);
					setVisible(true);
					chckbxmntmNewCheckItem.setSelected(true);
				}

			}
		});
		com.setSelected(true);
		com.setBounds(190, 90, 79, 31);
		paneconfig.add(com);

		licon = new JLabel("Iconos");
		licon.setFont(new Font("Dialog", Font.BOLD, 15));
		licon.setBounds(33, 137, 79, 42);
		paneconfig.add(licon);

		rgrande = new JRadioButton("Grandes");
		rgrande.setFont(new Font("Dialog", Font.BOLD, 12));
		rgrande.setBounds(118, 149, 91, 23);
		paneconfig.add(rgrande);
		rgrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconGrande();

			}
		});

		rmed = new JRadioButton("Medianos");
		rmed.setSelected(true);
		rmed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconMed();

			}
		});

		rmed.setFont(new Font("Dialog", Font.BOLD, 12));
		rmed.setBounds(211, 149, 91, 23);

		paneconfig.add(rmed);

		rpeque = new JRadioButton("Pequeños");
		rpeque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				iconPeque();

			}
		});
		rpeque.setFont(new Font("Dialog", Font.BOLD, 12));
		rpeque.setBounds(304, 149, 91, 23);
		paneconfig.add(rpeque);

		bg = new ButtonGroup();
		bg.add(rgrande);
		bg.add(rmed);
		bg.add(rpeque);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 255));
		separator.setBounds(22, 135, 352, 2);
		paneconfig.add(separator);

		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLUE);
		separator_1.setBounds(23, 190, 352, 2);
		paneconfig.add(separator_1);

		ltem = new JLabel("Temática");
		ltem.setFont(new Font("Dialog", Font.BOLD, 15));
		ltem.setBounds(33, 203, 79, 42);
		paneconfig.add(ltem);

		roscuro = new JRadioButton("Oscuro");
		roscuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				Color blanco = new Color(240, 240, 240);
				Color negro = new Color(40, 40, 40);

				lbien.setForeground(negro);

				paneconfig.setBackground(negro);
				lcom.setForeground(blanco);
				licon.setForeground(blanco);
				rgrande.setForeground(blanco);
				rmed.setForeground(blanco);
				rpeque.setForeground(blanco);
				lcon.setForeground(blanco);
				ltem.setForeground(blanco);
				roscuro.setForeground(blanco);
				rclaro.setForeground(blanco);
				separator.setForeground(blanco);
				separator_1.setForeground(blanco);
				frameconfig.getContentPane().setBackground(negro);

				toolBar.setBackground(negro);
				tarea1.setForeground(blanco);
				bmini_gestor.setForeground(blanco);
				bmini_bloc.setForeground(blanco);
				tarea5.setForeground(blanco);
				tarea4.setForeground(blanco);
				bmini_calcu.setForeground(blanco);
				bmini_ify.setForeground(blanco);
				lhora.setForeground(blanco);
				lmin.setForeground(blanco);
				ldosptos.setForeground(blanco);
				lfecha.setForeground(blanco);

				larbol.setForeground(blanco);
				lbloc.setForeground(negro);
				lify.setForeground(negro);
				lcalcu.setForeground(negro);
				lhotel.setForeground(negro);
				lcalendar.setForeground(negro);
				sp.setBackground(negro);

				explorador.getContentPane().setBackground(negro);
				contentPane.setBackground(negro);

				panel.setBackground(negro);
				panel_1.setBackground(negro);
				ltxt.setForeground(blanco);
				tree.setBackground(negro);
				tree.setForeground(blanco);

				panel_3.setBackground(negro);
				ini.setBackground(negro);
				ini.setForeground(blanco);
				mini.setBackground(negro);
				mini.setForeground(blanco);

			}
		});
		roscuro.setFont(new Font("Dialog", Font.BOLD, 12));
		roscuro.setBounds(118, 215, 91, 23);
		paneconfig.add(roscuro);

		rclaro = new JRadioButton("Claro");
		rclaro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				Color blanco = new Color(240, 240, 240);
				Color negro = new Color(40, 40, 40);

				lbien.setForeground(blanco);

				paneconfig.setBackground(blanco);
				lcom.setForeground(Color.black);
				licon.setForeground(Color.black);
				rgrande.setForeground(Color.black);
				rmed.setForeground(Color.black);
				rpeque.setForeground(Color.black);
				lcon.setForeground(Color.black);
				ltem.setForeground(Color.black);
				roscuro.setForeground(Color.black);
				rclaro.setForeground(Color.black);
				separator.setForeground(Color.BLUE);
				separator_1.setForeground(Color.BLUE);
				frameconfig.getContentPane().setBackground(blanco);

				toolBar.setBackground(blanco);
				tarea1.setForeground(negro);
				bmini_gestor.setForeground(negro);
				bmini_bloc.setForeground(negro);
				tarea5.setForeground(negro);
				tarea4.setForeground(negro);
				bmini_calcu.setForeground(negro);
				bmini_ify.setForeground(negro);
				lhora.setForeground(negro);
				lmin.setForeground(negro);
				ldosptos.setForeground(negro);
				lfecha.setForeground(negro);
				larbol.setForeground(new Color(128, 128, 128));

				lbloc.setForeground(blanco);
				lify.setForeground(blanco);
				lcalcu.setForeground(blanco);
				lhotel.setForeground(blanco);
				lcalendar.setForeground(blanco);

				explorador.getContentPane().setBackground(blanco);
				contentPane.setBackground(blanco);
				panel.setBackground(blanco);
				panel_1.setBackground(blanco);
				ltxt.setForeground(negro);
				tree.setBackground(blanco);
				tree.setForeground(negro);
				sp.setBackground(blanco);

				panel_3.setBackground(blanco);
				ini.setBackground(blanco);
				ini.setForeground(negro);
				mini.setBackground(blanco);
				mini.setForeground(negro);

			}
		});
		rclaro.setFont(new Font("Dialog", Font.BOLD, 12));
		rclaro.setBounds(211, 215, 91, 23);
		rclaro.setSelected(true);
		paneconfig.add(rclaro);

		btg = new ButtonGroup();
		btg.add(roscuro);
		btg.add(rclaro);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLUE);
		separator_1_1.setBounds(23, 256, 352, 2);
		paneconfig.add(separator_1_1);

		JLabel lblRelojc = new JLabel("Reloj");
		lblRelojc.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRelojc.setBounds(33, 277, 79, 42);
		paneconfig.add(lblRelojc);

		rnormal = new JRadioButton("Base 24H");
		rnormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				gregoriano = false;
				timer = new Timer(1000, new cronometro());
				timer.start();

			}
		});
		rnormal.setFont(new Font("Dialog", Font.BOLD, 12));
		rnormal.setBounds(118, 286, 91, 23);
		paneconfig.add(rnormal);

		rgregoriano = new JRadioButton("Base 12H");
		rgregoriano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				gregoriano = true;
				timer = new Timer(1000, new cronometro());
				timer.start();
			}
		});
		rgregoriano.setSelected(true);
		rgregoriano.setFont(new Font("Dialog", Font.BOLD, 12));
		rgregoriano.setBounds(211, 286, 91, 23);
		paneconfig.add(rgregoriano);

		bg3 = new ButtonGroup();
		bg3.add(rnormal);
		bg3.add(rgregoriano);

		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFecha.setBounds(383, 84, 60, 42);
		paneconfig.add(lblFecha);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();

				if (comboBox.getSelectedItem().toString().equals("DD/MM/YYYY")) {
					String fecha = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MARCH) + 1) + "/"
							+ cal.get(Calendar.YEAR);

					lfecha.setText(fecha);
				} else {
					String fecha = cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MARCH) + 1) + "/"
							+ cal.get(Calendar.DATE);

					lfecha.setText(fecha);

				}

			}
		});
		comboBox.setFont(new Font("Dialog", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "DD/MM/YYYY", "YYYY/MM/DD" }));
		comboBox.setBounds(453, 92, 138, 25);
		paneconfig.add(comboBox);

		explorador = new JInternalFrame("Explorador de Jorgivos");

		explorador.setClosable(true);
		explorador.setFrameIcon(new ImageIcon(Home.class.getResource("/multimedia/explorador (1) .png")));
		explorador.setBounds(550, 179, 542, 390);
		explorador.setVisible(false);
		bg1.add(explorador);

		sp = new JSplitPane();
		sp.setEnabled(false);
		explorador.getContentPane().add(sp, BorderLayout.CENTER);

		panel = new JPanel();
		sp.setLeftComponent(panel);

		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@SuppressWarnings("static-access")
			public void valueChanged(TreeSelectionEvent e) {
				internalFrame.dispose();
				String node = e.getNewLeadSelectionPath().getLastPathComponent().toString();
				if (node.equals("Configuración")) {
					frameconfig.setVisible(true);
					frameconfig.toFront();
					frameconfig.moveToFront();
					frameconfig.requestFocus();
				} else if (node.equals("Bloc de Jorge")) {
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
				} else if (node.equals("Calculadora de Jorge")) {
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

				} else if (node.equals("Jorgify")) {

					ify = new Jorgify();

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
					ify.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							bmini_ify.setVisible(false);
						}
					});

				} else if (node.equals("Eventos")) {
					eve = new Evento();

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

					eve.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							beve_mini.setVisible(false);
						}
					});

				} else if (node.equals("Gestor de Jorgeles")) {
					gestor = new Gestor_Hoteles();

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
					gestor.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							bmini_gestor.setVisible(false);
						}
					});

				} else if (node.equals("JorgelMaps")) {

					mapa = new Mapa();
					mapa.setResizable(false);
					internalFrame.dispose();

					if (mapa.ICONIFIED == 1) {
						bmini_mapa.setVisible(true);
					}
					if (!mapa.isVisible()) {

						mapa.setVisible(true);
					} else {
						mapa.setState(Frame.NORMAL);
						mapa.requestFocus();
						mapa.toFront();// Funciona para minimizar la app

					}
					mapa.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							bmini_mapa.setVisible(false);
						}
					});

				}

			}
		});
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Simulador de SO") {
			/**
			 * 
			 */

			private static final long serialVersionUID = 1L;

			{
				node_1 = new DefaultMutableTreeNode("Usuario");
				node_1.add(new DefaultMutableTreeNode("Explorador de Jorgivos"));
				node_1.add(new DefaultMutableTreeNode("Configuración"));
				node_2 = new DefaultMutableTreeNode("Escritorio");
				node_2.add(new DefaultMutableTreeNode("Bloc de Jorge"));
				node_2.add(new DefaultMutableTreeNode("Calculadora de Jorge"));
				node_2.add(new DefaultMutableTreeNode("Jorgify"));
				node_2.add(new DefaultMutableTreeNode("Eventos"));
				node_2.add(new DefaultMutableTreeNode("Gestor de Jorgeles"));
				node_2.add(new DefaultMutableTreeNode("JorgelMaps"));

				node_1.add(node_2);
				add(node_1);

				// Si llegas aquí no se porque se pone solo a getContentPane().add(node_1); si
				// esta solo el add(node_1);
			}
		}));
		tree.setShowsRootHandles(true);

		TreeModel model = tree.getModel();
		Object[] getNode = myWalk(model, "Usuario", "Explorador de Jorgivos");
		TreePath tPath = new TreePath(getNode);
		tree.setSelectionPath(tPath);
		panel.add(tree);

		panel_1 = new JPanel();
		sp.setRightComponent(panel_1);
		panel_1.setLayout(null);

		ejemplotxt = new JButton("");
		ejemplotxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				String texto = "";
				File docu = new File("docu_home.txt");
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
		ejemplotxt.setIcon(new ImageIcon(Home.class.getResource("/multimedia/txt.png")));
		ejemplotxt.setContentAreaFilled(false);
		ejemplotxt.setBorderPainted(false);
		ejemplotxt.setBounds(0, 0, 132, 133);
		panel_1.add(ejemplotxt);

		ltxt = new JLabel("Docu_SO.txt");
		ltxt.setHorizontalAlignment(SwingConstants.CENTER);
		ltxt.setForeground(new Color(0, 0, 0));
		ltxt.setFont(new Font("Dialog", Font.BOLD, 13));
		ltxt.setBounds(0, 97, 132, 22);
		panel_1.add(ltxt);

		larbol = new JLabel("Pincha en algún elemento del árbol y se abre :p");
		larbol.setForeground(new Color(128, 128, 128));
		larbol.setBounds(0, 309, 301, 22);
		panel_1.add(larbol);

		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel_3.setBounds(0,(int)(d.getHeight()/2.05), 263, 308);
		bg1.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setVisible(false);

		ini = new JMenuBar();
		ini.setBounds(0, 286, 107, 22);
		panel_3.add(ini);

		mini = new JMenu("Apagar/Reiniciar");
		mini.setFont(new Font("Dialog", Font.BOLD, 12));
		ini.add(mini);

		mapag = new JMenuItem("Apagar");
		mapag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mapag.setFont(new Font("Dialog", Font.BOLD, 12));
		mini.add(mapag);

		mreini = new JMenuItem("Reiniciar");
		mreini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(true);
				internalFrame.dispose();
			}
		});
		mreini.setFont(new Font("Dialog", Font.BOLD, 12));
		mini.add(mreini);

		musu = new JMenuItem("Cambiar Usuario");
		musu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Bloqueo().setVisible(true);
			}
		});
		musu.setFont(new Font("Dialog", Font.BOLD, 12));
		mini.add(musu);

		separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setForeground(new Color(0, 0, 0));
		separator_5.setBounds(119, 0, 2, 308);
		panel_3.add(separator_5);

		JButton aplicacion3_1 = new JButton("");
		aplicacion3_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
				ify = new Jorgify();

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
				ify.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						bmini_ify.setVisible(false);
					}
				});

			}
		});

		aplicacion3_1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/icon_mp3.png")));
		aplicacion3_1.setOpaque(true);
		aplicacion3_1.setContentAreaFilled(false);
		aplicacion3_1.setBorderPainted(false);
		aplicacion3_1.setBounds(0, 0, 121, 83);
		panel_3.add(aplicacion3_1);

		aplicacion2_1 = new JButton("");
		aplicacion2_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
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
		aplicacion2_1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/Icono_Bloc.png")));
		aplicacion2_1.setOpaque(true);
		aplicacion2_1.setContentAreaFilled(false);
		aplicacion2_1.setBorderPainted(false);
		aplicacion2_1.setBounds(0, 83, 121, 109);
		panel_3.add(aplicacion2_1);

		aplicacion5_1 = new JButton("");
		aplicacion5_1.setBounds(120, -10, 131, 133);
		panel_3.add(aplicacion5_1);
		aplicacion5_1.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				gestor = new Gestor_Hoteles();
				internalFrame.dispose();

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
				gestor.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						bmini_gestor.setVisible(false);
					}
				});

			}

		});
		aplicacion5_1.setIcon(new ImageIcon(Home.class.getResource("/multimedia/gestoricon.png")));
		aplicacion5_1.setOpaque(true);
		aplicacion5_1.setContentAreaFilled(false);
		aplicacion5_1.setBorderPainted(false);

		appmapa = new JButton("");
		appmapa.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				mapa = new Mapa();
				mapa.setResizable(false);
				internalFrame.dispose();

				if (mapa.ICONIFIED == 1) {
					bmini_mapa.setVisible(true);
				}
				if (!mapa.isVisible()) {

					mapa.setVisible(true);
				} else {
					mapa.setState(Frame.NORMAL);
					mapa.requestFocus();
					mapa.toFront();// Funciona para minimizar la app

				}
				mapa.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						bmini_mapa.setVisible(false);
					}
				});

			}

		});
		bmini_mapa.setVisible(false);
		appmapa.setIcon(new ImageIcon(Home.class.getResource("/multimedia/mapa_icon (1) (1).png")));
		appmapa.setOpaque(true);
		appmapa.setContentAreaFilled(false);
		appmapa.setBorderPainted(false);
		appmapa.setBounds(10, 257, 148, 121);
		bg1.add(appmapa);

		lmapa = new JLabel("JorgelMaps");
		lmapa.setHorizontalAlignment(SwingConstants.CENTER);
		lmapa.setForeground(Color.WHITE);
		lmapa.setFont(new Font("Dialog", Font.BOLD, 13));
		lmapa.setBounds(35, 356, 87, 22);
		bg1.add(lmapa);

		lbien = new JLabel("Bienvenid@, " + nom);
		lbien.setForeground(new Color(255, 255, 255));
		lbien.setFont(new Font("Dialog", Font.BOLD, 40));
		lbien.setBounds((int)(d.getWidth()/1.5), 40, 550, 100);
		bg1.add(lbien);

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setUndecorated(true);
	}
}
