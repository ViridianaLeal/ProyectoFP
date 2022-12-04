package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import com.toedter.calendar.JDayChooser;

import dao.daoActividades;
import dao.daoProfesor;
import modelo.Actividades;
import modelo.Alumno;
import modelo.Profesor;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vActividad extends JFrame {

	private JPanel contentPane;
	private JDateChooser dcFecha;
	private JTextField txtAsignatura;
	private JTextField txtActividad;
	private JLabel lblIdActividad;
	private JComboBox cboClase;
	private JScrollPane scrollPane;
	private JTable tblActividades;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnPdf;
	daoActividades dao = new daoActividades();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Actividades> lista = new ArrayList<Actividades>();
	Actividades actividades;
	int fila = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vActividad frame = new vActividad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblIdActividad.setText("");
		txtActividad.setText("");
		txtAsignatura.setText("");
		cboClase.setSelectedItem("");
		dcFecha.setDateFormatString("");
	}
	public vActividad() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vActividad.class.getResource("/img/DeoClass.png")));
		setTitle("ACTIVIDADES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 478);
		setLocationRelativeTo(cboClase);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID ACTIVIDAD");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 114, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblActividad = new JLabel("ACTIVIDAD");
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblActividad.setBounds(10, 49, 114, 19);
		contentPane.add(lblActividad);
		
		JLabel lblAsignatura = new JLabel("ASIGNATURA");
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsignatura.setBounds(10, 79, 114, 19);
		contentPane.add(lblAsignatura);
		
		JLabel lblGrupo = new JLabel("CLASE");
		lblGrupo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrupo.setBounds(10, 109, 114, 19);
		contentPane.add(lblGrupo);
		
		dcFecha = new JDateChooser();
		dcFecha.setBounds(117, 139, 123, 20);
		contentPane.add(dcFecha);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 139, 44, 20);
		contentPane.add(lblFecha);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cboClase = new JComboBox();
		cboClase.setBounds(117, 109, 123, 22);
		contentPane.add(cboClase);
		
		txtAsignatura = new JTextField();
		txtAsignatura.setBounds(117, 79, 123, 20);
		contentPane.add(txtAsignatura);
		txtAsignatura.setColumns(10);
		
		txtActividad = new JTextField();
		txtActividad.setBounds(116, 50, 271, 20);
		contentPane.add(txtActividad);
		txtActividad.setColumns(10);
		
		lblIdActividad = new JLabel("");
		lblIdActividad.setBounds(117, 15, 123, 14);
		contentPane.add(lblIdActividad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 522, 192);
		contentPane.add(scrollPane);
		
		tblActividades = new JTable();
		tblActividades.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		tblActividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblActividades.getSelectedRow();
				actividades = lista.get(fila);
				lblIdActividad.setText("" + lista.get(fila).getIdActividades());
				txtActividad.setText(actividades.getActividad());
				txtAsignatura.setText(actividades.getAsignatura());
				cboClase.setSelectedItem(actividades.getClase());
				dcFecha.setDateFormatString(actividades.getFecha());
			}
		});
		scrollPane.setViewportView(tblActividades);
		modelo.addColumn("ID ACTIVIDADES");
		modelo.addColumn("ACTIVIDAD");
		modelo.addColumn("ASIGNATURA");
		modelo.addColumn("CLASE");
		modelo.addColumn("FECHA");
		tblActividades.setModel(modelo);
		actualizarTabla();
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtActividad.getText().equals("") || txtAsignatura.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Actividades user = new Actividades();
					user.setActividad(txtActividad.getText());
					user.setAsignatura(txtAsignatura.getText());
					user.setClase(""+cboClase.getSelectedItem());
					user.setFecha(dcFecha.getDateFormatString());
					if (dao.insertarActividad(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vActividad.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(10, 392, 30, 30);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtActividad.getText().equals("") || txtAsignatura.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					actividades.setActividad(txtActividad.getText());
					actividades.setAsignatura(txtAsignatura.getText());
					actividades.setClase(""+cboClase.getSelectedItem());
					actividades.setFecha(dcFecha.getDateFormatString());
					if (dao.editarActividades(actividades)) {
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
		btnEditar.setIcon(new ImageIcon(vActividad.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(63, 392, 30, 30);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTA ACTIVIDAD?",
							"ELIMINAR ACTIVIDAD", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarActividad(lista.get(fila).getIdActividades())) {
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
		btnEliminar.setIcon(new ImageIcon(vActividad.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(117, 392, 30, 30);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File(
							"C:\\Users\\virip\\OneDrive\\Escritorio\\Repositorios\\ProyectoFP\\src\\pdf\\ReporteActividades.pdf");
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					Image img = Image.getInstance(
							"C:\\Users\\virip\\OneDrive\\Escritorio\\Repositorios\\ProyectoFP\\src\\img\\DeoClass.png");
					img.setAlignment(Element.ALIGN_CENTER);
					img.scaleToFit(100, 100);
					doc.add(img);
					Paragraph p = new Paragraph(10);
					com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(
							com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("CATALOGO DE ACTIVIDADES");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID ACTIVIDAD", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" ACTIVIDAD", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" ASIGNATURA", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" CLASE", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" FECHA", negrita));
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
					

					for (Actividades u : lista) {
						tabla.addCell("" + u.getIdActividades());
						tabla.addCell(u.getActividad());
						tabla.addCell(u.getAsignatura());
						tabla.addCell(""+u.getClase());
						tabla.addCell(""+u.getFecha());
					}

					doc.add(tabla);
					Paragraph p1 = new Paragraph(10);
					p1.add(Chunk.NEWLINE);
					p1.add("NÚMERO DE REGISTRO " + lista.size());
					p1.add(Chunk.NEWLINE);
					p1.add(Chunk.NEWLINE);
					p1.setAlignment(Element.ALIGN_RIGHT);
					doc.add(p1);
					doc.close();
					archivo.close();
					Desktop.getDesktop().open(file);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "ERROR AL CREAR ARCHIVO");
				} catch (DocumentException e1) {
					JOptionPane.showMessageDialog(null, "ERROR AL CREAR DOCUMENTO PDF");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "ERROR AL CREAR IO");
			}
			}
		});
		btnPdf.setIcon(new ImageIcon(vActividad.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(172, 392, 30, 30);
		contentPane.add(btnPdf);
	}
	
	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetcActividades();
		for (Actividades u : lista) {
			Object o[] = new Object[5];
			o[0] = u.getIdActividades();
			o[1] = u.getActividad();
			o[2] = u.getAsignatura();
			o[3] = u.getClase();
			o[4] = u.getFecha();
			modelo.addRow(o);
		}
		tblActividades.setModel(modelo);
	}
}
