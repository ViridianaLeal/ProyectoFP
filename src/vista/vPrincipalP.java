package vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;

public class vPrincipalP extends JFrame {

	private JPanel contentPane;
	double ancho=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto=Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipalP frame = new vPrincipalP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vPrincipalP() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincipal.class.getResource("/img/jyujyu.png")));
		setTitle("PROFESORES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5, 5, 5));
		contentPane.setSize((int)ancho,(int)alto);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1183, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("GESTION");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("USUARIOS");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("REPORTES");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("SALIR");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 21, 1920, 1001);
		contentPane.add(desktopPane);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 1920, 34);
		desktopPane.add(menuBar_1);
		
		JButton btnNewButton = new JButton("New button");
		menuBar_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		menuBar_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		menuBar_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		menuBar_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		menuBar_1.add(btnNewButton_4);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
