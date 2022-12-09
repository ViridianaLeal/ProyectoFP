package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VMiclase extends JFrame {

	private JPanel contentPane;
	private JButton btnActividades;
	private JButton btnComentarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMiclase frame = new VMiclase();
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
	public VMiclase() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOVEDADES");
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 27));
		lblNewLabel.setBounds(194, 26, 166, 50);
		contentPane.add(lblNewLabel);
		
		btnActividades = new JButton("ACTIVIDADES");
		btnActividades.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnActividades.setForeground(Color.WHITE);
		btnActividades.setBorder(null);
		btnActividades.setBorderPainted(false);	
		btnActividades.setBackground(new Color(43, 81, 111));
		btnActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vActividad acividad = new vActividad();
				acividad.setVisible(true);
			}
		});
		btnActividades.setBounds(102, 87, 136, 77);
		contentPane.add(btnActividades);
		
		btnComentarios = new JButton("COMENTARIOS");
		btnComentarios.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnComentarios.setForeground(Color.WHITE);
		btnComentarios.setBorder(null);
		btnComentarios.setBorderPainted(false);	
		btnComentarios.setBackground(new Color(43, 81, 111));
		btnComentarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vComentario com = new vComentario();
				com.setVisible(true);
			}
		});
		btnComentarios.setBounds(286, 87, 136, 77);
		contentPane.add(btnComentarios);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setForeground(SystemColor.menu);
		btnNewButton.setIcon(new ImageIcon(VMiclase.class.getResource("/img/icons8-eliminar-30 (1).png")));
		btnNewButton.setBounds(491, 11, 42, 34);
		contentPane.add(btnNewButton);
	}
}
