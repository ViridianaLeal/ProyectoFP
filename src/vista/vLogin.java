package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.daoUsuario;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class vLogin extends JFrame {

	private JPanel contentPane;
	private JButton btnEntrar;
	private JButton btnCancelar;
	private JPasswordField txtPassword;
	private JTextField txtUser;
	private JLabel lblLogo;
	daoUsuario dao = new daoUsuario();
	Font lemon = new Font("Klavika", Font.BOLD, 15);
	private JLabel linea2;
	private JLabel linea2_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
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
	public vLogin() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 482);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		//this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblNewLabel.setBounds(38, 213, 86, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(69, 296, 86, 20);
		contentPane.add(lblPassword);

		btnEntrar = new JButton("Entrar");// CREAR EL BOTON Y ASIGNAR TEXTO DENTRO DEL BOTON
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEntrar.setBackground(new Color(0, 156, 223));
			}
		});
		btnEntrar.setBackground(new Color(0, 156, 223));// COLOR DE FONDO DEL BOTON
		btnEntrar.setBorder(new LineBorder(new Color(231, 0, 32), 2));// COLOCAR BORDE DE COLOR Y ANCHO 2
		btnEntrar.setFont(lemon);// ASIGNAR FUENTE DE TEXTO
		btnEntrar.setForeground(SystemColor.activeCaption);// COLOR DE TEXTO
		//getContentPane().add(btnEntrar, new AbsoluteConstraints(100, 300, 130, 50));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEntrar.setBackground(new Color(0, 156, 223));
				Usuario user = new Usuario();
				user.setUser(txtUser.getText());
				user.setPassword(String.valueOf(txtPassword.getPassword()));
				if (dao.loginUsuario(user)) {
					JOptionPane.showMessageDialog(null, "BIENVENIDO");
					vCargando cargando = new vCargando();
					setVisible(false);
					cargando.setVisible(true);
				} else {

					JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta");
				}
			}
		});
		btnEntrar.setBounds(72, 382, 89, 23);
		contentPane.add(btnEntrar);

		btnCancelar = new JButton("Cancel");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(new Color(0, 156, 223));
			}
		});
		btnCancelar.setBackground(SystemColor.textHighlight);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setBackground(new Color(0, 156, 223));
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		btnCancelar.setBounds(180, 384, 89, 23);
		contentPane.add(btnCancelar);

		txtPassword = new JPasswordField();
		txtPassword.setBorder(null);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtPassword.getText().length() > 10) {
					e.consume();
				}
			}
		});
		txtPassword.setText("");
		txtPassword.setBounds(69, 327, 194, 20);
		contentPane.add(txtPassword);

		txtUser = new JTextField();
		txtUser.setBorder(null);
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtUser.getText().length() > 10) {
					e.consume();
				}
			}
		});
		txtUser.setBounds(71, 238, 198, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		lblLogo = new JLabel("New label");
		lblLogo.setIcon(new ImageIcon(vLogin.class.getResource("/img/DeoClass.png")));
		lblLogo.setBounds(86, 21, 164, 170);
		contentPane.add(lblLogo);
		
		linea2 = new JLabel();
		linea2.setIcon(new ImageIcon(vLogin.class.getResource("/img/32016.jpg")));
		linea2.setBorder(UIManager.getBorder("Button.border"));
		linea2.setForeground(SystemColor.inactiveCaptionText);
		linea2.setBackground(SystemColor.inactiveCaptionText);
		linea2.setBounds(63, 352, 200, 2);
		contentPane.add(linea2);
		
		linea2_1 = new JLabel();
		linea2_1.setIcon(new ImageIcon(vLogin.class.getResource("/img/32016.jpg")));
		linea2_1.setBounds(38, 283, 255, 2);
		contentPane.add(linea2_1);
	}
}
