package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JDesktopPane desktopPane;
	private JToolBar barraherramientas;
	double ancho=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	vUsuario vusuario=new vUsuario();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipal frame = new vPrincipal();
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
	public vPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincipal.class.getResource("/img/jyujyu.png")));
		setTitle("SISTEMAS POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("CRUDS");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Usuario");
		mnNewMenu.add(mntmNewMenuItem);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 59, 365, 191);
		desktopPane.setSize((int)ancho,(int)alto);		
		contentPane.add(desktopPane);
		desktopPane.add(vusuario);
		
		barraherramientas = new JToolBar();
		barraherramientas.setBounds(10, 32, 843, 16);
		contentPane.add(barraherramientas);
		
		JButton btnNewButton = new JButton("CRUD USUARIO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vusuario.setVisible(true);
			}
		});
		barraherramientas.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		barraherramientas.add(btnNewButton_1);
	}
}
