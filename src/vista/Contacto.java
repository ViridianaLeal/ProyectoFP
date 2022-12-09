package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Contacto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contacto frame = new Contacto();
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
	public Contacto() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("VIRIDIANA LEAL RAMOS");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(166, 62, 258, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CANAAN URIEL TELLEZ RODRÍGUEZ");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(166, 98, 258, 36);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("JADE VIRIDIANA MORALES MARTINEZ");
		lblNewLabel_1_2.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(166, 134, 258, 36);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("AMERICA YARELI JIMENEZ VILCHES");
		lblNewLabel_1_3.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(166, 181, 258, 36);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Contacto.class.getResource("/img/logitoooooo.png")));
		lblNewLabel.setBounds(10, 36, 133, 181);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("CONTACTANOS POR:");
		lblNewLabel_2.setBounds(10, 240, 186, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("fourprogramming@gmail.com");
		lblNewLabel_2_1.setForeground(SystemColor.desktop);
		lblNewLabel_2_1.setBounds(134, 240, 186, 36);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Contacto.class.getResource("/img/icons8-eliminar-30 (1).png")));
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(385, 28, 39, 23);
		contentPane.add(btnNewButton);
	}

}
