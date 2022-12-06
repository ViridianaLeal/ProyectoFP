package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;

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

import dao.daoActividades;
import dao.daoEntrega;
import modelo.Actividades;
import modelo.Entrega;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class vEntrega extends JFrame {

	private JPanel contentPane;
	private JLabel lblIdEntrega;
	private JDateChooser dcFecha;
	private JComboBox cboEntrega;
	private JTable tblEntrega;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnPdf;
	daoEntrega dao = new daoEntrega();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Entrega> lista = new ArrayList<Entrega>();
	Entrega entrega;
	int fila = -1;
	private JTextField txtClase;
	private JTextField txtActividad;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vEntrega frame = new vEntrega();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblIdEntrega.setText("");
		txtClase.setText("");
		txtActividad.setText("");
		dcFecha.setDateFormatString("");
		cboEntrega.setSelectedItem("");
	}

	
	public vEntrega() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vEntrega.class.getResource("/img/DeoClass.png")));
		setTitle("ENTREGA");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 520);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID ENTREGA");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel.setBounds(20, 18, 149, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblClase = new JLabel("CLASE");
		lblClase.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblClase.setBounds(20, 51, 149, 22);
		contentPane.add(lblClase);
		
		JLabel lblActividad = new JLabel("ACTIVIDAD");
		lblActividad.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblActividad.setBounds(20, 94, 149, 22);
		contentPane.add(lblActividad);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblFecha.setBounds(20, 136, 149, 22);
		contentPane.add(lblFecha);
		
		JLabel lblEntrega = new JLabel("ENTREGA");
		lblEntrega.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblEntrega.setBounds(20, 169, 149, 22);
		contentPane.add(lblEntrega);
		
		lblIdEntrega = new JLabel("");
		lblIdEntrega.setBounds(149, 11, 98, 24);
		contentPane.add(lblIdEntrega);
		
		dcFecha = new JDateChooser();
		dcFecha.setBounds(149, 136, 194, 20);
		contentPane.add(dcFecha);
		
		cboEntrega = new JComboBox();
		cboEntrega.setModel(new DefaultComboBoxModel(new String[] {"A TIEMPO", "RETRASO", "INCOMPLETA"}));
		cboEntrega.setBounds(149, 168, 194, 22);
		contentPane.add(cboEntrega);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 215, 613, 239);
		contentPane.add(scrollPane);
		
		tblEntrega = new JTable();
		tblEntrega.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		tblEntrega.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblEntrega.getSelectedRow();
				entrega = lista.get(fila);
				lblIdEntrega.setText("" + lista.get(fila).getIdEntrega());
				txtClase.setText(entrega.getClase());
				txtActividad.setText(entrega.getActividad());
				dcFecha.setDateFormatString(entrega.getFecha());
				cboEntrega.setSelectedItem(entrega.getEntrega());
			}
		});
		scrollPane.setViewportView(tblEntrega);
		modelo.addColumn("ID ENTREGA");
		modelo.addColumn("CLASE");
		modelo.addColumn("ACTIVIDAD");
		modelo.addColumn("FECHA");
		modelo.addColumn("ENTREGA");
		tblEntrega.setModel(modelo);
		actualizarTabla();
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					Entrega user = new Entrega();
					user.setClase(txtClase.getText());
					user.setActividad(txtActividad.getText());
					user.setFecha(dcFecha.getDateFormatString());
					user.setEntrega(""+cboEntrega.getSelectedItem());
					if (dao.insertarEntrega(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vEntrega.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(393, 50, 30, 30);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					entrega.setClase(txtClase.getText());
					entrega.setActividad(txtActividad.getText());
					entrega.setFecha(dcFecha.getDateFormatString());
					entrega.setEntrega(""+cboEntrega.getSelectedItem());
					if (dao.editarEntrega(entrega)) {
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
		btnEditar.setIcon(new ImageIcon(vEntrega.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(454, 50, 30, 30);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ENTREGA?",
							"ELIMINAR ENTREGA", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarEntrega(lista.get(fila).getIdEntrega())) {
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
		btnEliminar.setIcon(new ImageIcon(vEntrega.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(510, 50, 30, 30);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/pdf/ReporteEntrega.pdf").toString());
					File file = new File(uri);
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					java.awt.Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/DeoClass.png"));
					Image img = Image.getInstance(getClass().getResource("/img/DeoClass.png"));
					img.setAlignment(Element.ALIGN_CENTER);
					img.scaleToFit(200, 200);
					doc.add(img);
					Paragraph p = new Paragraph(10);
					com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(
							com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("REPORTE DE ENTREGAS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID ENTREGA", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" CLASE", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" ACTIVIDAD", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" FECHA", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" ENTREGA", negrita));
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
					

					for (Entrega u : lista) {
						tabla.addCell("" + u.getIdEntrega());
						tabla.addCell(u.getClase());
						tabla.addCell(u.getActividad());
						tabla.addCell(u.getFecha());
						tabla.addCell(u.getEntrega());
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
			} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPdf.setIcon(new ImageIcon(vEntrega.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(569, 50, 30, 30);
		contentPane.add(btnPdf);
		
		txtClase = new JTextField();
		txtClase.setBounds(148, 51, 195, 20);
		contentPane.add(txtClase);
		txtClase.setColumns(10);
		
		txtActividad = new JTextField();
		txtActividad.setBounds(145, 94, 198, 20);
		contentPane.add(txtActividad);
		txtActividad.setColumns(10);
	}
	
	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetcEntregas();
		for (Entrega u : lista) {   
			Object o[] = new Object[5];
			o[0] = u.getIdEntrega();
			o[1] = u.getClase();
			o[2] = u.getActividad();
			o[3] = u.getFecha();
			o[4] = u.getEntrega();
			modelo.addRow(o);
		}
		tblEntrega.setModel(modelo);
	}
}
