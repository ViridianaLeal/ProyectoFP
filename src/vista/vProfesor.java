package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class vProfesor extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("ID PROFESOR");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProfesor frame = new vProfesor();
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
	public vProfesor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(25, 44, 98, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(25, 103, 98, 13);
		contentPane.add(lblNewLabel_1);
		
		javax.swing.JButton boton = new javax.swing.JButton("Hola mundo");
		boton.setLocation(111,190);
		boton.setSize(100,50);
		boton.setBackground(new java.awt.Color(255,50,50));
		getContentPane().add(boton);
	}
}
