package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class vPrincipalU extends JFrame {

	private JPanel contentPane;
	private JPanel panelSuperior;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipalU frame = new vPrincipalU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vPrincipalU() {
		setTitle("DeoClass");
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincipalU.class.getResource("/img/DeoClass.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 434, 450);

		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		panelPrincipal.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("GESTIÓN");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("USUARIO");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("PROFESOR");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("PLANTELES");
		mnNewMenu.add(mntmNewMenuItem_13);
		
		JMenu mnNewMenu_1 = new JMenu("REPORTES");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("ALUMNOS");
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("PROFESORES");
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("CALIFICACIONES");
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("PROMEDIO");
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("PLANTEL");
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("GRUPOS");
		mnNewMenu_1.add(mntmNewMenuItem_11);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("CARRERAS");
		mnNewMenu_1.add(mntmNewMenuItem_12);
		
		JMenu mnNewMenu_2 = new JMenu("SALIR");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("CERRAR SESIÓN");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("SALIR");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_3 = new JMenu("ACERCA DE");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("CONTACTO");
		mnNewMenu_3.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("SALIR");
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 22, 434, 22);
		panelPrincipal.add(panelSuperior);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(0, 58, 434, 402);
		panelPrincipal.add(panelCentral);
		panelCentral.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, -15, 152, 426);
		panelCentral.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("CALIFICACIÓN");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCalif cali = new vCalif();
				cali.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(10, 30, 110, 51);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("PROMEDIO");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPromedio pro=new vPromedio();
				pro.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(10, 109, 110, 71);
		panel.add(btnNewButton_3);
		
		JPanel misPaneles = new JPanel();
		misPaneles.setBounds(154, -15, 460, 426);
		panelCentral.add(misPaneles);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(0, 244, 434, 17);
		panelPrincipal.add(panelInferior);
		
		JButton btnNewButton_4 = new JButton("UNIR");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Unir unir = new Unir();
				unir.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(258, 43, 89, 23);
		panelPrincipal.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("CREAR");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vClase clase = new vClase();
				clase.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(345, 43, 89, 23);
		panelPrincipal.add(btnNewButton_5);
	}
}
