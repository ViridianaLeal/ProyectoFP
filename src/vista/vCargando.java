package vista;

import java.awt.EventQueue;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class vCargando extends JFrame {

	private JPanel contentPane;
	private JProgressBar barCargando = new JProgressBar();
	int idUsuario=-1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCargando frame = new vCargando(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Cargar() {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i <= 100; i++) {
					barCargando.setValue(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i==100) {
						setVisible(false);
						vPrincipalU p=new vPrincipalU(idUsuario);
						p.setVisible(true);
					}
				}
			}
		});
		hilo.start();
	}

	public vCargando(int idUsuario) {
		this.idUsuario=idUsuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 96);
		contentPane = new JPanel();
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		barCargando.setForeground(Color.WHITE);
		barCargando.setBorderPainted(false);
		barCargando.setFont(new Font("Consolas", Font.PLAIN, 14));
		barCargando.setInheritsPopupMenu(true);
		barCargando.setDoubleBuffered(true);
		barCargando.setBorder(null);
		barCargando.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		barCargando.setAlignmentX(Component.RIGHT_ALIGNMENT);
		barCargando.setBackground(new Color(43, 81, 111));
		barCargando.setForeground(SystemColor.activeCaption);
		barCargando.setStringPainted(true);
		barCargando.setBounds(0, 0, 726, 96);
		contentPane.add(barCargando);
		Cargar();
	}
}
