package vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class vPrincipalC extends JFrame {

	private JPanel contentPane;
	int idUsuario = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipalC frame = new vPrincipalC(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vPrincipalC(int idUsuario) {
		this.idUsuario=idUsuario;
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincipalC.class.getResource("/img/jyujyu.png")));
		setTitle("Consulta");
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnG = new JMenu("Gestion");
		menuBar.add(mnG);

		JMenuItem mntmNewMenuItem = new JMenuItem("Usuario");
		mnG.add(mntmNewMenuItem);

		JMenu mnR = new JMenu("Reporte");
		menuBar.add(mnR);

		JMenuItem mntmActivi = new JMenuItem("Actividades");
		mntmActivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\ReporteActividades.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmActivi);

		JMenuItem mntmPromedio = new JMenuItem("Promedio");
		mntmPromedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\ReportePromedio.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmPromedio);

		JMenuItem mntmGrupos = new JMenuItem("Grupos");
		mntmGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\ReporteGrupos.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmGrupos);

		JMenuItem mntmCalificacion = new JMenuItem("Calificaciones");
		mntmCalificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\ReporteCalificaciones.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmCalificacion);

		JMenuItem mntmClases = new JMenuItem("Clases");
		mntmClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\ReporteClases.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmClases);

		JMenuItem mntmAlumno = new JMenuItem("Alumno");
		mntmAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\RAlumnos.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmAlumno);

		JMenuItem mntmProfesor = new JMenuItem("Profesor");
		mntmProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File path = new File("src\\pdf\\Reporte.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		mnR.add(mntmProfesor);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnSalir.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 14));
		btnSalir.setBounds(1479, 102, 99, 35);
		contentPane.add(btnSalir);

		JButton btnCerrar = new JButton("Cerrar Sesion");
		btnCerrar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnCerrar.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 14));
		btnCerrar.setBounds(1463, 25, 115, 35);
		contentPane.add(btnCerrar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}