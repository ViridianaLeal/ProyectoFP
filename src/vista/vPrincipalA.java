package vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vPrincipalA extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmCerrarsesion;
	private JToolBar barraHerramientas;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipalA frame = new vPrincipalA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vPrincipalA() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincipalA.class.getResource("/img/DeoClass.png")));
		setTitle("ALUMNOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1441, 40);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("GESTIÓN");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("USUARIO");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				barraHerramientas.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("ALUMNOS");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("REPORTES");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("ALUMNOS");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("PROFESORES");
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("ASIGNATURAS");
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("CLASES");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("CALIFICACIONES");
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("CARRERAS");
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("PROMEDIO");
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenu mnNewMenu_2 = new JMenu("SALIR");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Salir");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		mntmCerrarsesion = new JMenuItem("Cerrar sesión");
		mntmCerrarsesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	
				
			}
		});
		mnNewMenu_2.add(mntmCerrarsesion);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 39, 1441, 795);
		contentPane.add(desktopPane);
		
		barraHerramientas = new JToolBar();
		barraHerramientas.setBounds(0, 0, 1441, 45);
		desktopPane.add(barraHerramientas);
		
		JButton btnNewButton = new JButton("PROFESORES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		barraHerramientas.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ASIGNATURAS");
		barraHerramientas.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		barraHerramientas.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("UNIR");
		barraHerramientas.add(btnNewButton_3);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
