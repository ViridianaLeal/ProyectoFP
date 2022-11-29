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

public class vAsignatura extends JFrame {

	private JPanel contentPane;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnPdf;
	private JTable tblAlumnos;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAsignatura frame = new vAsignatura();
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
	public vAsignatura() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vAsignatura.class.getResource("/img/DeoClass.png")));
		setTitle("ASIGNATURA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID Profesor");
		lblNewLabel.setBounds(10, 65, 101, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblidasignatura = new JLabel("ID ASIGNATURA");
		lblidasignatura.setBounds(10, 31, 101, 23);
		contentPane.add(lblidasignatura);
		
		JLabel lblasignatura = new JLabel("ASIGNATURA");
		lblasignatura.setBounds(10, 99, 77, 23);
		contentPane.add(lblasignatura);
		
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
		btnAgregar.setBounds(93, 160, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(93, 203, 89, 23);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(93, 237, 89, 23);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("PDF");
		btnPdf.setBounds(93, 277, 89, 23);
		contentPane.add(btnPdf);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(110, 11, 51, 32);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(93, 65, 185, 23);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(97, 95, 181, 30);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
