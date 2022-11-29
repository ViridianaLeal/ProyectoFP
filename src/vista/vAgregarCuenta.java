package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.daoAgregar;
import modelo.AgregarCuenta;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class vAgregarCuenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JButton btnAgregar;
	private JButton btnEliminar;
	daoAgregar dao =new daoAgregar();
	int fila = -1;
	AgregarCuenta agregarcuenta;
	AgregarCuenta user = new AgregarCuenta();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAgregarCuenta frame = new vAgregarCuenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		txtNombre.setText("");
		txtUsuario.setText("");
		txtPassword.setText("");
	}
	public vAgregarCuenta() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(60, 104, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBounds(60, 166, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(60, 213, 123, 14);
		contentPane.add(lblPassword);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(172, 101, 183, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(172, 163, 184, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CREA TU CUENTA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(96, 41, 249, 34);
		contentPane.add(lblNewLabel_1);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE USUARIO?",
							"ELIMINAR USUARIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarUsuario) {
							JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
						} else {
							JOptionPane.showMessageDialog(null, "ERROR");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEliminar.setBounds(256, 281, 89, 23);
		contentPane.add(btnEliminar);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUsuario.getText().equals("") || txtPassword.getText().equals("")
							|| txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					AgregarCuenta user = new AgregarCuenta();
					user.setUser(txtUsuario.getText());
					user.setPassword(txtPassword.getText());
					user.setNombre(txtNombre.getText());
					if (dao.insertarUsuario(user)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(106, 281, 89, 23);
		contentPane.add(btnAgregar);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(169, 210, 186, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
	}
}
