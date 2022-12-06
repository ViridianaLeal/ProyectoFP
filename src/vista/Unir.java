package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.daoClase;
import modelo.Clase;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Unir extends JFrame {

	private JPanel contentPane;
	private JTextField txtClase;
	private JTextField txtClave;
	daoClase dao = new daoClase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Unir frame = new Unir();
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
	public Unir() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Unir.class.getResource("/img/DeoClass.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLASE");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel.setBounds(54, 52, 150, 36);
		contentPane.add(lblNewLabel);
		
		txtClase = new JTextField();
		txtClase.setBounds(82, 99, 285, 20);
		contentPane.add(txtClase);
		txtClase.setColumns(10);
		
		JButton btnNewButton = new JButton("UNIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Clase user = new Clase ();
			user.setClase(txtClase.getText());
			user.setClave(txtClave.getText());
			if (dao.loginUniraClase(user)) {
				JOptionPane.showMessageDialog(null, "BIENVENIDO");
				vEntrega entrega = new vEntrega();
				setVisible(false);
				entrega.setVisible(true);
			} else {

				JOptionPane.showMessageDialog(null, "No se puedo unir a la clase");
			}
		}
		});
		btnNewButton.setBounds(178, 208, 89, 23);
		contentPane.add(btnNewButton);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(82, 177, 285, 20);
		contentPane.add(txtClave);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblClave.setBounds(73, 130, 150, 36);
		contentPane.add(lblClave);
	}

}
