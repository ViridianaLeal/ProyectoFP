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

import dao.daoConsulta;
import dao.daoUsuario;
import modelo.Consulta;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class vComentario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNA;
	private JTable tblConsulta;
	private JLabel lblID;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JScrollPane scrollPane;
	daoConsulta dao = new daoConsulta();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Consulta> lista = new ArrayList<Consulta>();
	Consulta consulta;
	int fila = -1;
	private JComboBox cboDestiny;
	private JTextField txtC;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vComentario frame = new vComentario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		txtNA.setText("");
		txtC.setText("");
	}

	public vComentario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vComentario.class.getResource("/img/logoDeo.png")));
		setTitle("COMENTARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 668);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel.setBounds(25, 11, 46, 23);
		contentPane.add(lblNewLabel);
		lblID = new JLabel("1");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(97, 7, 148, 34);
		contentPane.add(lblID);
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1.setBounds(10, 45, 86, 21);
		contentPane.add(lblNewLabel_1);
		txtNA = new JTextField();
		txtNA.setFont(new Font("Arial Unicode MS", Font.ITALIC, 15));
		txtNA.setBounds(120, 52, 308, 23);
		contentPane.add(txtNA);
		txtNA.setColumns(10);
		JLabel lblNewLabel_1_1 = new JLabel("Dudas, preguntas o consultas ");
		lblNewLabel_1_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(25, 213, 273, 23);
		contentPane.add(lblNewLabel_1_1);
		btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(vComentario.class.getResource("/img/Avion2.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNA.getText().equals("") || txtC.getText().equals("")||
							cboDestiny.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "LLENA CORRECTAMENTE TUS DATOS ");
						return;
					}
					Consulta user = new Consulta();
					user.setNombre(txtNA.getText());
					user.setComentario(txtC.getText());
					user.setDestino(cboDestiny.getSelectedItem().toString());
					if (dao.insertarComentario(user)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "EL COMENTARIO HA SIDO RECIBIDO");
					} else {
						JOptionPane.showMessageDialog(null, "HUBO UN ERROR EN LA MATRIX");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "QUE MAL  \n\n"
							+ " HUBO UN ERROR EN LA MATRIX :(");
				}
			}
		});
		btnAgregar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.focus"), Color.LIGHT_GRAY));
		btnAgregar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnAgregar.setBounds(810, 312, 73, 73);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("Modificar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNA.getText().equals("") || txtC.getText().equals("") 
							|| cboDestiny.getSelectedItem().equals(""))  {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					consulta.setNombre(txtNA.getText());
					consulta.setComentario(txtC.getText());
					consulta.setDestino(cboDestiny.getSelectedItem().toString());
					if (dao.editarConsulta(consulta)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "EL COMENTARIO SE ACTUALIZO  CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEditar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEditar.setBounds(619, 306, 89, 34);
		contentPane.add(btnEditar);
		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(vComentario.class.getResource("/img/Bote.jpg")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMIAR EL MENSAJE?",
							"ELIMINAR COMENTARIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarComentario(lista.get(fila).getID())) {
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
		btnEliminar.setBorder(new EtchedBorder(EtchedBorder.RAISED, UIManager.getColor("Button.focus"), new Color(192, 192, 192)));
		btnEliminar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEliminar.setBounds(718, 312, 66, 73);
		contentPane.add(btnEliminar);
		btnBorrar = new JButton("Borrar ambos campos");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtC.setText(null);
				txtNA.setText(null);
				limpiar();
			}
		});
		btnBorrar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBorrar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnBorrar.setBounds(539, 351, 169, 34);
		contentPane.add(btnBorrar);
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(25, 460, 856, 158);
		contentPane.add(scrollPane);
		tblConsulta = new JTable();
		tblConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblConsulta.getSelectedRow();
				fila = tblConsulta.getSelectedRow();
				consulta = lista.get(fila);
				lblID.setText("" + lista.get(fila).getID());
				txtNA.setText(""+ lista.get(fila).getNombre());
				txtC.setText(""+ lista.get(fila).getComentario());
				cboDestiny.setSelectedItem(""+lista.get(fila).getDestino());
			}
		});
		tblConsulta.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblConsulta);
		modelo.addColumn("NO° de consulta");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("COMENTARIO");
		modelo.addColumn("DESTINATARIO");
		tblConsulta.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Escribelas aqui debajo:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Nirmala UI", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(-18, 250, 273, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		cboDestiny = new JComboBox();
		cboDestiny.setFont(new Font("Arial Unicode MS", Font.ITALIC, 15));
		cboDestiny.setModel(new DefaultComboBoxModel(new String[] {"Profesor", "Jefe de control", "Orientadora en grupo"}));
		cboDestiny.setBounds(25, 150, 169, 34);
		contentPane.add(cboDestiny);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("¿Quien recibira el mensaje?");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Nirmala UI", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(0, 109, 273, 23);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtC = new JTextField();
		txtC.setFont(new Font("Arial Unicode MS", Font.ITALIC, 15));
		txtC.setHorizontalAlignment(SwingConstants.CENTER);
		txtC.setBorder(new LineBorder(Color.BLACK, 2, true));
		txtC.setBounds(25, 299, 301, 118);
		contentPane.add(txtC);
		txtC.setColumns(10);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchConsultas();
		for (Consulta u : lista) {
			Object o[] = new Object[4];
			o[0] = u.getID();
			o[1] = u.getNombre();
			o[2] = u.getComentario();
			o[3] = u.getDestino();
			modelo.addRow(o);
		}
		tblConsulta.setModel(modelo);
	}
}