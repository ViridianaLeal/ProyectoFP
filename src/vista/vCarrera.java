package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.daoCarrera;
import dao.daoUsuario;
import modelo.Carrera;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

public class vCarrera extends JFrame {

	private JPanel contentPane;
	private JTextField txtCarrera;
	private JLabel lblIdCarrera;
	private JTable tblCarreras;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JButton btnPdf;
	private JButton btnAgregar;
	daoCarrera dao = new daoCarrera();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Carrera> lista = new ArrayList<Carrera>();
	Carrera carrera;
	int fila = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCarrera frame = new vCarrera();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblIdCarrera.setText("");
		txtCarrera.setText("");
	}

	public vCarrera() {
		setTitle("AGREGAR CARRERAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(vCarrera.class.getResource("/img/DeoClass.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 376);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID CARRERA");
		lblNewLabel.setBounds(22, 31, 114, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CARRERA");
		lblNewLabel_1.setBounds(22, 71, 64, 14);
		contentPane.add(lblNewLabel_1);

		txtCarrera = new JTextField();
		txtCarrera.setBounds(111, 68, 300, 20);
		contentPane.add(txtCarrera);
		txtCarrera.setColumns(10);

		lblIdCarrera = new JLabel("0");
		lblIdCarrera.setBounds(112, 31, 46, 14);
		contentPane.add(lblIdCarrera);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 110, 389, 113);
		contentPane.add(scrollPane);

		tblCarreras = new JTable();
		tblCarreras
				.setModel(
						new DefaultTableModel(
								new Object[][] { { null, null }, { null, null }, { null, null }, { null, null },
										{ null, null }, { null, null }, },
								new String[] { "New column", "New column" }));
		tblCarreras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblCarreras.getSelectedRow();
				carrera = lista.get(fila);
				lblIdCarrera.setText("" + lista.get(fila).getIdCarrera());
				txtCarrera.setText(carrera.getCarrera());
			}
		});
		scrollPane.setViewportView(tblCarreras);
		modelo.addColumn("ID CARRERA");
		modelo.addColumn("CARRERA");
		tblCarreras.setModel(modelo);
		actualizarTabla();

		btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtCarrera.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Carrera user = new Carrera();
					user.setCarrera(txtCarrera.getText());
					if (dao.insertarCarrera(user)) {
						actualizarTabla();
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
		btnAgregar.setBounds(82, 260, 30, 30);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtCarrera.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					carrera.setCarrera(txtCarrera.getText());
					if (dao.editarCarrera(carrera)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE ACTUALIZO  CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(147, 260, 30, 30);
		contentPane.add(btnEditar);

		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR LA CARRERA?",
							"ELIMINAR CARRERA", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarCarrera(lista.get(fila).getIdCarrera())) {
							actualizarTabla();
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
		btnEliminar.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(215, 260, 30, 30);
		contentPane.add(btnEliminar);

		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-broom-with-a-lot-of-dust-30.png")));
		btnLimpiar.setBounds(277, 251, 37, 39);
		contentPane.add(btnLimpiar);

		btnPdf = new JButton("");
		btnPdf.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(343, 251, 30, 39);
		contentPane.add(btnPdf);
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchCarreras();
		for (Carrera u : lista) {
			Object o[] = new Object[2];
			o[0] = u.getIdCarrera();
			o[1] = u.getCarrera();
			modelo.addRow(o);
		}
		tblCarreras.setModel(modelo);
	}
}
