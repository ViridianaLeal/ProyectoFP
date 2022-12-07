package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

import dao.daoAsignatura;
import dao.daoProfesor;
import modelo.Alumno;
import modelo.Asignatura;
import modelo.Profesor;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vAsignatura extends JFrame {

	private JPanel contentPane;
	private JLabel lblIdAsignatura;
	private JTextField txtAsig;
	private JTable tblAsig;
	private JButton btnAgregar;
	private JButton btnPdf;
	private JButton btnEliminar;
	private JButton btnEditar;
	DefaultTableModel modelo = new DefaultTableModel();
	daoAsignatura dao = new daoAsignatura();
	Asignatura asignatura;
	int fila = -1;
	ArrayList<Asignatura> lista = new ArrayList<Asignatura>();

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
	
	

	public void limpiar() {
		lblIdAsignatura.setText("");
		txtAsig.setText("");
	}

	public vAsignatura() {
		setTitle("ASIGNATURAS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(vAsignatura.class.getResource("/img/logoclass.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID ASIGNATURA");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel.setBounds(33, 33, 204, 24);
		contentPane.add(lblNewLabel);

		JLabel lblIasignatura = new JLabel("ASIGNATURA");
		lblIasignatura.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblIasignatura.setBounds(43, 68, 204, 24);
		contentPane.add(lblIasignatura);

		lblIdAsignatura = new JLabel("");
		lblIdAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdAsignatura.setBounds(263, 21, 84, 30);
		contentPane.add(lblIdAsignatura);

		txtAsig = new JTextField();
		txtAsig.setFont(new Font("Verdana", Font.BOLD, 15));
		txtAsig.setBounds(273, 69, 230, 30);
		contentPane.add(txtAsig);
		txtAsig.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 556, 238);
		contentPane.add(scrollPane);

		tblAsig = new JTable();
		tblAsig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblAsig.getSelectedRow();
				asignatura = lista.get(fila);
				lblIdAsignatura.setText("" + lista.get(fila).getIdAsignatura());
				txtAsig.setText(asignatura.getAsignatura());
			}
		});
		tblAsig.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblAsig);
		modelo.addColumn("ID ASIGNATURA");
		modelo.addColumn("ASIGNATURA");
		tblAsig.setModel(modelo);

		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAsig.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						return;
					}
					Asignatura user = new Asignatura();
					user.setAsignatura(txtAsig.getText());
					if (dao.insertarAsignatura(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vAsignatura.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(72, 458, 30, 30);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAsig.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					asignatura.setAsignatura(txtAsig.getText());
					if (dao.editarAsignatura(asignatura)) {
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
		btnEditar.setIcon(new ImageIcon(vAsignatura.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(139, 458, 30, 30);
		contentPane.add(btnEditar);

		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTA ASIGNATURA?",
							"ELIMINAR ASIGNATURA", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarAsignatura(lista.get(fila).getIdAsignatura())) {
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
		btnEliminar.setIcon(new ImageIcon(vAsignatura.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(207, 458, 30, 30);
		contentPane.add(btnEliminar);

		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/pdf/RAsignatura.pdf").toString());
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
					com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("CATALOGO DE ASIGNATURAS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					// Tabla de datos
					PdfPTable tabla = new PdfPTable(2);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase("ID ASIGNATURA", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase("ASIGNATURA", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					// Agregar los registros
					for (Asignatura pro : lista) {
						tabla.addCell("" + pro.getIdAsignatura());
						tabla.addCell(pro.getAsignatura());
					}
					doc.add(tabla);
					Paragraph p1 = new Paragraph(10);
					p1.add(Chunk.NEWLINE);
					p1.add("NÚMERO DE REGISTROS: " + lista.size());
					p1.add(Chunk.NEWLINE);
					p1.add(Chunk.NEWLINE);
					p1.setAlignment(Element.ALIGN_RIGHT);
					doc.add(p1);
					doc.close();
					archivo.close();
					Desktop.getDesktop().open(file);
				} catch (FileNotFoundException ex) {

				} catch (DocumentException ex) {

				} catch (IOException ex) {

				} catch (URISyntaxException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnPdf.setIcon(new ImageIcon(vAsignatura.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(284, 458, 30, 30);
		contentPane.add(btnPdf);
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetcAsignaturas();
		for (Asignatura u : lista) {
			Object o[] = new Object[2];
			o[0] = u.getIdAsignatura();
			o[1] = u.getAsignatura();
			modelo.addRow(o);
		}
		tblAsig.setModel(modelo);
	}
}
