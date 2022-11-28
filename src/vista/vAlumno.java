package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class vAlumno extends JFrame {

	private JPanel contentPane;
	private JLabel lblIdAlumno;
	private JTextField txtNControl;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JComboBox cboPlantel;
	private JComboBox cboTurno;
	private JComboBox cboSemestre;
	private JComboBox cboCarrera;
	private JComboBox cboGrupo;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnPdf;
	private JTable tblAlumnos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAlumno frame = new vAlumno();
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
	public vAlumno() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vAlumno.class.getResource("/img/DeoClass.png")));
		setTitle("CRUD ALUMNOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID ALUMNO");
		lblNewLabel.setBounds(10, 24, 101, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNDeControl = new JLabel("N° DE CONTROL");
		lblNDeControl.setBounds(10, 58, 101, 23);
		contentPane.add(lblNDeControl);
		
		JLabel lblPlantel = new JLabel("PLANTEL");
		lblPlantel.setBounds(10, 92, 101, 23);
		contentPane.add(lblPlantel);
		
		JLabel lblTurno = new JLabel("TURNO");
		lblTurno.setBounds(10, 134, 101, 23);
		contentPane.add(lblTurno);
		
		JLabel lblSemestre = new JLabel("SEMESTRE");
		lblSemestre.setBounds(10, 168, 101, 23);
		contentPane.add(lblSemestre);
		
		JLabel lblCarrera = new JLabel("CARRERA");
		lblCarrera.setBounds(10, 202, 101, 23);
		contentPane.add(lblCarrera);
		
		JLabel lblGrupo = new JLabel("GRUPO");
		lblGrupo.setBounds(10, 236, 101, 23);
		contentPane.add(lblGrupo);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 270, 101, 23);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("APELLIDOS");
		lblApellidos.setBounds(10, 304, 101, 23);
		contentPane.add(lblApellidos);
		
		lblIdAlumno = new JLabel("");
		lblIdAlumno.setBounds(119, 28, 46, 14);
		contentPane.add(lblIdAlumno);
		
		txtNControl = new JTextField();
		txtNControl.setBounds(114, 59, 195, 20);
		contentPane.add(txtNControl);
		txtNControl.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(114, 270, 195, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(114, 304, 195, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		cboPlantel = new JComboBox();
		cboPlantel.setBounds(114, 92, 195, 22);
		contentPane.add(cboPlantel);
		
		cboTurno = new JComboBox();
		cboTurno.setBounds(114, 134, 195, 22);
		contentPane.add(cboTurno);
		
		cboSemestre = new JComboBox();
		cboSemestre.setBounds(114, 168, 195, 22);
		contentPane.add(cboSemestre);
		
		cboCarrera = new JComboBox();
		cboCarrera.setBounds(114, 202, 195, 22);
		contentPane.add(cboCarrera);
		
		cboGrupo = new JComboBox();
		cboGrupo.setBounds(114, 236, 195, 22);
		contentPane.add(cboGrupo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 28, 741, 302);
		contentPane.add(scrollPane);
		
		tblAlumnos = new JTable();
		tblAlumnos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblAlumnos);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(1076, 92, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(1076, 134, 89, 23);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(1076, 180, 89, 23);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("PDF");
		btnPdf.setBounds(1076, 224, 89, 23);
		contentPane.add(btnPdf);
	}
	
	
}
