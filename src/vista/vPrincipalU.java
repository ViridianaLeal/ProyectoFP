package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JLabel;

public class vPrincipalU extends JFrame {
	int idUsuario=-1;
	double ancho=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private JDesktopPane desktopPane;
	private JButton btnUnir;
	private JButton btnCrear;
	private JButton btnCalificaciones;
	private JButton btnPromedio;
	private JButton btnFotos;
	private JButton btnProfesores;
	private JButton btnAlumnos;
	vUsuario vUsuario = new vUsuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipalU frame = new vPrincipalU(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vPrincipalU(int idUsuario) {
		this.idUsuario=idUsuario;
		setTitle("DeoClass");
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincipalU.class.getResource("/img/DeoClass.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 731, 612);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.textHighlight);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("GESTIÓN");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("USUARIO");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vUsuario usuario = new vUsuario();
				usuario.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("PROFESOR");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProfesor prof = new vProfesor();
				prof.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("ALUMNO");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAlumno alumn=new vAlumno();
				alumn.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_13);
		
		JMenu AÑADIR = new JMenu("AÑADIR");
		menuBar.add(AÑADIR);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("PLANTEL");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPlantel plan = new vPlantel();
				plan.setVisible(true);
			}
		});
		AÑADIR.add(mntmNewMenuItem_14);
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("SEMESTRE");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vSemestre sem = new vSemestre();
				sem.setVisible(true);
			}
		});
		AÑADIR.add(mntmNewMenuItem_15);
		
		JMenuItem mntmNewMenuItem_17 = new JMenuItem("CARRERA");
		mntmNewMenuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCarrera ca = new vCarrera();
				ca.setVisible(true);
			}
		});
		AÑADIR.add(mntmNewMenuItem_17);
		
		JMenuItem mntmNewMenuItem_18 = new JMenuItem("GRUPO");
		mntmNewMenuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vGrupo gru = new vGrupo();
				gru.setVisible(true);
			}
		});
		AÑADIR.add(mntmNewMenuItem_18);
		
		JMenuItem mntmNewMenuItem_16 = new JMenuItem("MATERIAS");
		mntmNewMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMateria ma = new vMateria();
				ma.setVisible(true);
			}
		});
		AÑADIR.add(mntmNewMenuItem_16);
		
		JMenu mnNewMenu_1 = new JMenu("REPORTES");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("USUARIOS");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vUsuario.GenerarReportePDF();
					

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("ALUMNOS");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAlumno vAlumno = new vAlumno();
				vAlumno.GenerarPDF();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("PROFESORES");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProfesor vProfesor = new vProfesor();
				vProfesor.GenerarPDF();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("CALIFICACIONES");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCalif vCalif = new vCalif();
				vCalif.GenerarPDF();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("ACTIVIDADES");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vActividad vActividad = new vActividad();
				vActividad.GenerarPDF();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("CLASES");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vClase vClase =new vClase(idUsuario);
				vClase.GenerarPDF();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("ASIGNATURA");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAsignatura vAsignatura = new vAsignatura();
				vAsignatura.GenerarPDF();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_2 = new JMenu("ACERCA DE");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("MANUAL USUARIO");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FileOutputStream archivo;
					File temp = new File(System.getProperty("java.io.tmpdir") + "MANUALUSUARIO.pdf");
					InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/pdf/MANUALUSUARIO.pdf");
					FileOutputStream flujoSalida = new FileOutputStream(temp);
					archivo = new FileOutputStream(temp);
					 FileWriter fw = new FileWriter(temp);
					           byte[] buffer = new byte[1024*512];
					           int control;
					           while ((control = flujoEntrada.read(buffer)) != -1){
					               flujoSalida.write(buffer, 0, control);
					           }
					Desktop.getDesktop().open(temp);
					} catch (IOException ex) {
					System.out.println(ex);
					}

			}
			
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("CONTACTO");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contacto con = new Contacto();
				con.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_19 = new JMenuItem("MANUAL TECNICO");
		mntmNewMenuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FileOutputStream archivo;
					File temp = new File(System.getProperty("java.io.tmpdir") + "MANUALTECNICO.pdf");
					InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/pdf/MANUALTECNICO.pdf");
					FileOutputStream flujoSalida = new FileOutputStream(temp);
					archivo = new FileOutputStream(temp);
					 FileWriter fw = new FileWriter(temp);
					           byte[] buffer = new byte[1024*512];
					           int control;
					           while ((control = flujoEntrada.read(buffer)) != -1){
					               flujoSalida.write(buffer, 0, control);
					           }
					Desktop.getDesktop().open(temp);
					} catch (IOException ex) {
					System.out.println(ex);
					}

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_19);
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_3 = new JMenu("SALIR");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("CERRAR SESIÓN");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcioncerrar = JOptionPane.showConfirmDialog(null, "¿DESEA CERRAR SESIÓN?","CERRA SESIÓN",JOptionPane.YES_NO_OPTION);
				if(opcioncerrar ==0) {
					JOptionPane.showMessageDialog(null, "CERRANDO SESIÓN");
					vLogin loginv = new vLogin();
					loginv.setVisible(true);
					dispose();
				}
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("SALIR");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					int opcion = JOptionPane.showConfirmDialog(null, "¿SALIR?",
							"SALIR", JOptionPane.YES_NO_OPTION);
				if(opcion==0) {
					if(opcion==0)
						JOptionPane.showMessageDialog(null, "GOODBYE");
					System.exit(0);
				}else {
					int cancelar = JOptionPane.showConfirmDialog(null, "CANCELAR SALIDA","CANCELAR",JOptionPane.YES_NO_OPTION);
					if(cancelar ==0) {
						JOptionPane.showMessageDialog(null, ":D");
					}
				}
				
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_2);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		scrollPane.setViewportView(desktopPane);
		
		btnUnir = new JButton("UNIR");
		btnUnir.setForeground(Color.WHITE);
		btnUnir.setBorder(null);
		btnUnir.setBorderPainted(false);
		btnUnir.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnUnir.setBackground(new Color(43, 81, 111));
		btnUnir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Unir unir = new Unir();
				unir.setVisible(true);
			}
			
			
		});
		btnUnir.setBounds(475, 11, 114, 33);
		desktopPane.add(btnUnir);
		
		btnCrear = new JButton("CREAR");
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setBorder(null);
		btnCrear.setBorderPainted(false);
		btnCrear.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnCrear.setBackground(new Color(43, 81, 111));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vClase clases = new vClase(idUsuario);
				clases.setVisible(true);
			}
		});
		btnCrear.setBounds(599, 11, 104, 33);
		desktopPane.add(btnCrear);
		
		btnCalificaciones = new JButton("CALIFICACIONES");
		btnCalificaciones.setForeground(Color.WHITE);
		btnCalificaciones.setBorder(null);
		btnCalificaciones.setBorderPainted(false);
		btnCalificaciones.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCalificaciones.setBackground(new Color(43, 81, 111));
		btnCalificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCalif cali = new vCalif();
				cali.setVisible(true);
			}
		});
		btnCalificaciones.setBounds(10, 44, 119, 67);
		desktopPane.add(btnCalificaciones);
		
		btnPromedio = new JButton("PROMEDIO");
		btnPromedio.setForeground(Color.WHITE);
		btnPromedio.setBorder(null);
		btnPromedio.setBorderPainted(false);
		btnPromedio.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnPromedio.setBackground(new Color(43, 81, 111));
		btnPromedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPromedio prom = new  vPromedio();
				prom.setVisible(true);
			}
		});
		btnPromedio.setBounds(10, 136, 119, 67);
		desktopPane.add(btnPromedio);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(vPrincipalU.class.getResource("/img/DeoClass.png")));
		lblNewLabel.setBounds(724, 136, 623, 517);
		desktopPane.add(lblNewLabel);
		
		btnAlumnos = new JButton("ALUMNOS");
		btnAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAlumno alum = new vAlumno();
				alum.setVisible(true);
			}
		});
		btnAlumnos.setForeground(Color.WHITE);
		btnAlumnos.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnAlumnos.setBorderPainted(false);
		btnAlumnos.setBorder(null);
		btnAlumnos.setBackground(new Color(43, 81, 111));
		btnAlumnos.setBounds(10, 240, 119, 67);
		desktopPane.add(btnAlumnos);
		
		btnProfesores = new JButton("PROFESORES");
		btnProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProfesor pro = new vProfesor();
				pro.setVisible(true);
			}
		});
		btnProfesores.setForeground(Color.WHITE);
		btnProfesores.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnProfesores.setBorderPainted(false);
		btnProfesores.setBorder(null);
		btnProfesores.setBackground(new Color(43, 81, 111));
		btnProfesores.setBounds(10, 335, 119, 67);
		desktopPane.add(btnProfesores);
		
		btnFotos = new JButton("FOTOS");
		btnFotos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vFoto fotos = new vFoto();
				fotos.setVisible(true);
			}
		});
		btnFotos.setForeground(Color.WHITE);
		btnFotos.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnFotos.setBorderPainted(false);
		btnFotos.setBorder(null);
		btnFotos.setBackground(new Color(43, 81, 111));
		btnFotos.setBounds(10, 438, 119, 67);
		desktopPane.add(btnFotos);
	}
	
	public ImageIcon cambiar(ImageIcon img, int ancho, int alto) {
		Image imgEscalada = img.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(imgEscalada);
		return image;
	}
	
	public void iniciarComponenetes() {
		JPanel jpanel = new  JPanel();
		
	}
}
