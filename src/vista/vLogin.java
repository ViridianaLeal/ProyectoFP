package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.netbeans.lib.awtextra.AbsoluteConstraints;

import dao.daoUsuario;
import modelo.Usuario;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
import javax.swing.JSeparator;
import java.awt.Toolkit;


public class vLogin extends JFrame {

	private JPanel contentPane;
	private JButton btnCancelar;
	public PlaceHolderPasswordField txtPassword  = new PlaceHolderPasswordField();
	private JLabel lblLogo;
	daoUsuario dao = new daoUsuario();
	Font lemon = new Font("Klavika", Font.BOLD, 15);
	private JButton btnEntrar;
	private JLabel lblNewLabel_1;
	public PlaceHolderTextField txtUser;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(vLogin.class.getResource("/img/DeoClass.png")));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 345);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("USUARIO");
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 15));
		lblNewLabel.setBounds(27, 83, 86, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Calibri Light", Font.BOLD, 15));
		lblPassword.setBounds(27, 153, 86, 20);
		contentPane.add(lblPassword);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBorder(null);
		btnCancelar.setBorderPainted(false);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(new Color(0, 156, 223));
			}
		});
		btnCancelar.setBackground(new Color(43, 81, 111));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setBackground(new Color(0, 156, 223));
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		btnCancelar.setBounds(166, 261, 89, 23);
		contentPane.add(btnCancelar);

		txtPassword = new PlaceHolderPasswordField();
		txtPassword.setBounds(27, 184, 228, 27);
        txtPassword.setPlaceholder("Introduce password");
        txtPassword.setBorder(null);
        txtPassword.setFont(lemon);
        txtPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtPassword.getText().length() >= 20) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        }
        );
        this.getContentPane().add(txtPassword, new AbsoluteConstraints(160, 225, 200, 35));

        

		
		
		txtUser = new PlaceHolderTextField(); //JTEXTFLIED MEJORADO CON MARCA DE AGUA O PLACEHOLDER
		txtUser.setForeground(Color.LIGHT_GRAY);
		txtUser.setBounds(27, 97, 234, 30);
        txtUser.setPlaceholder("Introduce usuario"); //TEXTO DE MARCA DE AGUA
        txtUser.setBorder(null);// QUITAR BORDE DE CAMPO DE TEXTO
        txtUser.setFont(new Font("Calibri Light", Font.BOLD, 15));//ASIGNAR EL TIPO DE FUENTE DE TEXTO
        txtUser.addKeyListener(new KeyListener() {// METODO PARA VALIDAR EL NÚMERO DE CARACTERES A 20 LETRAS
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtUser.getText().length() >= 20) {// VALIDAR SOLO 20 CARACTERES
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        }
        );
        this.getContentPane().add(txtUser, new AbsoluteConstraints(160, 160, 200, 35));

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(vLogin.class.getResource("/img/DeoClass.png")));
		lblLogo.setBounds(307, 61, 190, 197);
		contentPane.add(lblLogo);
		
		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setBorder(null);
		btnEntrar.setBorderPainted(false);
		btnEntrar.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnEntrar.setBackground(new Color(43, 81, 111));
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
		btnEntrar.setBounds(48, 261, 89, 23);
		contentPane.add(btnEntrar);
		
		lblNewLabel_1 = new JLabel("INICIAR SESIÓN");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1.setBounds(26, 22, 229, 43);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(27, 128, 228, 45);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(27, 213, 228, 45);
		contentPane.add(separator_1);
		
		
	}
}
