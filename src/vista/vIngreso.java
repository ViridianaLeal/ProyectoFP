package vista;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class vIngreso extends JFrame {

	private JPanel contentPane;
	private JRadioButton rbdConsulta;
	private JRadioButton rdbAlumno;
	ButtonGroup grupo = new ButtonGroup();
	vPrincipalC principalC = new vPrincipalC();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vIngreso frame = new vIngreso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public vIngreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 252);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 359, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		rbdConsulta = new JRadioButton("CONSULTA");
		rbdConsulta.setBackground(Color.WHITE);
		rbdConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principalC.setVisible(true);
			}
		});
		rbdConsulta.setBounds(123, 75, 109, 23);
		panel.add(rbdConsulta);
		
		rdbAlumno = new JRadioButton("USUARIO");
		rdbAlumno.setBackground(Color.WHITE);
		rdbAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//principalU.setVisible(true);
			}
		});
		rdbAlumno.setBounds(123, 122, 109, 23);
		panel.add(rdbAlumno);
		
		JLabel lblNewLabel = new JLabel("INGRESAR COMO:");
		lblNewLabel.setForeground(new Color(43, 81, 111));
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		lblNewLabel.setBounds(123, 21, 207, 47);
		panel.add(lblNewLabel);
		
		grupo.add(rbdConsulta);
		grupo.add(rdbAlumno);
		
	}
}
