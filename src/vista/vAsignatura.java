package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.daoAsignatura;
import dao.daoUsuario;
import modelo.Asignatura;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class vAsignatura extends JFrame {

	private JPanel contentPane;
	int fila = -1;
	private JTable tblca;
	private JLabel lblAsig;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JScrollPane scrollPane;
	daoAsignatura dao = new daoAsignatura();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Asignatura> lista =new ArrayList<Asignatura>();
	Asignatura asignatura;
	private JTextField txtAsignatura;
	private JComboBox cboProfe;
	private JLabel lblIdAsignatura;

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
	public void limpiar() {
		txtAsignatura.setText(null);
	}

	public vAsignatura() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vAsignatura.class.getResource("/img/Java.jpg")));
		setTitle("CRUD ASIGNATURA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 401);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("ID profesor");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel.setBounds(10, 32, 113, 23);
		contentPane.add(lblNewLabel);
		lblAsig = new JLabel("1");
		lblAsig.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAsig.setBounds(144, 73, 63, 23);
		contentPane.add(lblAsig);
		JLabel lblNewLabel_1_2 = new JLabel("Asignatura");
		lblNewLabel_1_2.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1_2.setBounds(10, 115, 109, 23);
		contentPane.add(lblNewLabel_1_2);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAsignatura.getText().equals("")|| cboProfe.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Asignatura user = new Asignatura();
					user.setProfesor(cboProfe.getSelectedItem().toString());
					user.setAsignatura(txtAsignatura.getText());
					if (dao.insertarAsignatura(user)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "LA ASIGNATURA SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAgregar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnAgregar.setBounds(293, 21, 118, 34);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cboProfe.getSelectedItem().equals("") || txtAsignatura.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					asignatura.setAsignatura(txtAsignatura.getText());
					if (dao.editarAsignatura(asignatura)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE ACTUALIZO  CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEditar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEditar.setBounds(439, 21, 96, 34);
		contentPane.add(btnEditar);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR LA ASIGNATURA?",
							"ELIMINAR ASIGNATURA", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarAsignatura(lista.get(fila).getIDasignatura())) {
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
		btnEliminar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEliminar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEliminar.setBounds(293, 82, 118, 34);
		contentPane.add(btnEliminar);
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAsignatura.setText(null);
				limpiar();
			}
		});
		btnBorrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBorrar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnBorrar.setBounds(439, 82, 96, 34);
		contentPane.add(btnBorrar);
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(25, 170, 503, 158);
		contentPane.add(scrollPane);
		tblca = new JTable();
		tblca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblca.getSelectedRow();
				fila = tblca.getSelectedRow();
				asignatura = lista.get(fila);
				lblAsig.setText("" + lista.get(fila).getIDasignatura());
				txtAsignatura.setText(""+ lista.get(fila).getAsignatura());
				cboProfe.setSelectedItem(""+lista.get(fila).getProfesor());
			}
		});
		tblca.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblca);
		modelo.addColumn("ID asignatura");
		modelo.addColumn("profesor");
		modelo.addColumn("asignatura");
		tblca.setModel(modelo);
		
		txtAsignatura = new JTextField();
		txtAsignatura.setBounds(144, 114, 127, 33);
		contentPane.add(txtAsignatura);
		txtAsignatura.setColumns(10);
		
		cboProfe = new JComboBox();
		cboProfe.setModel(new DefaultComboBoxModel(new String[] {"436738", "78270", "6832"}));
		cboProfe.setBounds(144, 31, 127, 33);
		contentPane.add(cboProfe);
		
		lblIdAsignatura = new JLabel("ID asignatura");
		lblIdAsignatura.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblIdAsignatura.setBounds(10, 67, 128, 30);
		contentPane.add(lblIdAsignatura);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchAsignatura();
		for (Asignatura u : lista) {
			Object o[] = new Object[3];
			o[0] = u.getIDasignatura();
			o[1] = u.getProfesor();
			o[2] = u.getAsignatura();
			modelo.addRow(o);
		}
		tblca.setModel(modelo);
	}
}
