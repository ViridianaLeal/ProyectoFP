package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.security.TSAClientBouncyCastle;

import dao.daoAlumno;
import dao.daoUsuario;
import modelo.Alumno;
import modelo.Usuario;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.Phaser;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	daoAlumno dao = new daoAlumno();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Alumno> lista = new ArrayList<Alumno>();
	Alumno alumno;
	int fila = -1;
	private JTable tblAlumnos;
	private JLabel lblFoto;

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
	
	public void limpiar() {
		lblIdAlumno.setText("");
		txtNControl.setText("");
		cboPlantel.setSelectedItem("");
		cboTurno.setSelectedItem("");
		cboSemestre.setSelectedItem("");
		cboCarrera.setSelectedItem("");
		cboGrupo.setSelectedItem("");
		txtNombre.setText("");
		txtApellidos.setText("");
	}
	
	public vAlumno() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vAlumno.class.getResource("/img/DeoClass.png")));
		setTitle("CRUD ALUMNOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 540);
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
		cboTurno.setModel(new DefaultComboBoxModel(new String[] {"Matutino", "Vespertino"}));
		cboTurno.setBounds(114, 134, 195, 22);
		contentPane.add(cboTurno);
		
		cboSemestre = new JComboBox();
		cboSemestre.setBounds(114, 168, 195, 22);
		contentPane.add(cboSemestre);
		
		cboCarrera = new JComboBox();
		cboCarrera.setBounds(114, 202, 195, 22);
		contentPane.add(cboCarrera);
		
		cboGrupo = new JComboBox();
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] {"233"}));
		cboGrupo.setBounds(114, 236, 195, 22);
		contentPane.add(cboGrupo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 28, 741, 302);
		contentPane.add(scrollPane);
		
		tblAlumnos = new JTable();
		tblAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblAlumnos.getSelectedRow();
				alumno= lista.get(fila);
				lblIdAlumno.setText("" + lista.get(fila).getIdalumno());
				txtNControl.setText(""+alumno.getNumerocontrol());;
				cboPlantel.setSelectedItem(alumno.getPlantel());
				cboTurno.setSelectedItem(alumno.getTurno());
				cboSemestre.setSelectedItem(alumno.getSemestre());
				cboCarrera.setSelectedItem(alumno.getCarrera());
				cboGrupo.setSelectedItem(""+alumno.getGrupo());
				txtNombre.setText(alumno.getNombre());
				txtApellidos.setText(alumno.getApellidos());
			}
		});
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
		modelo.addColumn("ID ALUMNO");
		modelo.addColumn("NÚMERO DE CONTROL");
		modelo.addColumn("PLANTEL");
		modelo.addColumn("TURNO");
		modelo.addColumn("SEMESTRE");
		modelo.addColumn("CARRERA");
		modelo.addColumn("GRUPO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDOS");
		tblAlumnos.setModel(modelo);
		actualizarTabla();
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					if (txtNControl.getText().equals("") || txtNombre.getText().equals("") || txtApellidos.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Alumno user = new Alumno();
					user.setNumerocontrol(Integer.parseInt(txtNControl.getText().toString()));
					user.setPlantel(""+cboPlantel.getSelectedItem());
					user.setTurno(""+cboTurno.getSelectedItem());
					user.setSemestre(""+cboSemestre.getSelectedIndex());
					user.setCarrera(""+cboCarrera.getSelectedIndex());
					user.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));					
					user.setNombre(txtNombre.getText());
					user.setApellidos(txtApellidos.getText());
					if (dao.insertarAlumno(user)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
			
		});
		btnAgregar.setBounds(1076, 92, 89, 23);
		contentPane.add(btnAgregar);
		
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNControl.getText().equals("") || txtNombre.getText().equals("")
							|| txtApellidos.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					alumno.setNumerocontrol(Integer.parseInt(txtNControl.getText().toString()));
					alumno.setPlantel(""+cboPlantel.getSelectedItem());
					alumno.setTurno(""+cboTurno.getSelectedItem());
					alumno.setSemestre(""+cboSemestre.getSelectedIndex());
					alumno.setCarrera(""+cboCarrera.getSelectedIndex());
					alumno.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));					
					alumno.setNombre(txtNombre.getText());
					alumno.setApellidos(txtApellidos.getText());
					if (dao.editarAlumno(alumno)) {
						actualizarTabla();
						limpiar();
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
		btnEditar.setBounds(1076, 134, 89, 23);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE PRODUCTO?",
							"ELIMINAR PRODUCTO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarAlumno(lista.get(fila).getIdalumno())) {
							actualizarTabla();
							limpiar();
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
		btnEliminar.setBounds(1076, 180, 89, 23);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					// File file = new
					// File("C:\\Users\\Rene\\Documents\\NetBeansProjects\\Java_MVC_MySQL\\src\\pdf\\reporteProducto.pdf");
					URI uri = new URI(getClass().getResource("/pdf/RAlumnos.pdf").toString());
					File file = new File(uri);
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					java.awt.Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/jyujyu.png"));
					// Image img =
					// Image.getInstance("C:\\Users\\Rene\\Documents\\NetBeansProjects\\Java_MVC_MySQL\\src\\img\\i2.png");
					Image img = Image.getInstance(getClass().getResource("/img/jyujyu.png"));
					img.setAlignment(Element.ALIGN_CENTER);
					img.scaleToFit(200, 200);
					doc.add(img);
					Paragraph p = new Paragraph(10);
					Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("CATALOGO DE ALUMNOS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					// Tabla de datos
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase("ID ALUMNO", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase("NÚMERO DE CONTROL", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase("PLANTEL", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase("TURNO", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase("SEMESTRE", negrita));
					PdfPCell c6 = new PdfPCell(new Phaser("CARRERA")
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c5.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);
					tabla.addCell(c5);
					// Agregar los registros
					for (Proveedor pro : listaProveedors) {
						tabla.addCell("" + pro.getIdProveedor());
						tabla.addCell(pro.getNombreProveedor());
						tabla.addCell("" + pro.getNombreContacto());
						tabla.addCell("" + pro.getTelefonoProveedor());
						tabla.addCell("" + pro.getCiudadProveedor());

					}
					doc.add(tabla);
					Paragraph p1 = new Paragraph(10);
					p1.add(Chunk.NEWLINE);
					p1.add("NÚMERO DE REGISTROS: " + listaProveedors.size());
					p1.add(Chunk.NEWLINE);
					p1.add(Chunk.NEWLINE);
					p1.setAlignment(Element.ALIGN_RIGHT);
					doc.add(p1);
					doc.close();
					archivo.close();
					Desktop.getDesktop().open(file);
				} catch (FileNotFoundException ex) {
					Logger.getLogger(vistaProveedor.class.getName()).log(Level.SEVERE, null, ex);
				} catch (DocumentException ex) {
					Logger.getLogger(vistaProveedor.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(vistaProveedor.class.getName()).log(Level.SEVERE, null, ex);
				} catch (URISyntaxException ex) {
					Logger.getLogger(vistaProveedor.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btnPdf.setBounds(1076, 224, 89, 23);
		contentPane.add(btnPdf);
		
		lblFoto = new JLabel("New label");
		lblFoto.setBounds(98, 354, 108, 125);
		contentPane.add(lblFoto);
	}
	
	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchAlumnos();
		for (Alumno u : lista) {
			Object o[] = new Object[9];
			o[0] = u.getIdalumno();
			o[1] = u.getNumerocontrol();
			o[2] = u.getPlantel();
			o[3] = u.getTurno();
			o[4] = u.getSemestre();
			o[5] = u.getCarrera();
			o[6] = u.getGrupo();
			o[7] = u.getNombre();
			o[8] = u.getApellidos();
 			modelo.addRow(o);
		}
		tblAlumnos.setModel(modelo);
	}
}
