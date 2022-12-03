package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.daoCarrera;
import dao.daoPlantel;
import dao.daoSemestre;
import dao.daoUsuario;
import modelo.Carrera;
import modelo.Plantel;
import modelo.Semestre;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Toolkit;

public class vSemestre extends JFrame {

	private JPanel contentPane;
	private JTextField txtSemestre;
	private JLabel lblIdSemestre;
	private JTable tblSemestre;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JButton btnPdf;
	private JButton btnAgregar;
	daoSemestre dao = new daoSemestre();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Semestre> lista = new ArrayList<Semestre>();
	Semestre semestre;
	int fila = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vSemestre frame = new vSemestre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblIdSemestre.setText("");
		txtSemestre.setText("");
	}

	public vSemestre() {
		setTitle("AGREGAR SEMESTRES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(vCarrera.class.getResource("/img/DeoClass.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 376);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID SEMESTRE");
		lblNewLabel.setBounds(22, 31, 114, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("SEMESTRE");
		lblNewLabel_1.setBounds(22, 71, 64, 14);
		contentPane.add(lblNewLabel_1);

		txtSemestre = new JTextField();
		txtSemestre.setBounds(111, 68, 300, 20);
		contentPane.add(txtSemestre);
		txtSemestre.setColumns(10);

		lblIdSemestre = new JLabel("0");
		lblIdSemestre.setBounds(112, 31, 46, 14);
		contentPane.add(lblIdSemestre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 110, 389, 113);
		contentPane.add(scrollPane);

		tblSemestre = new JTable();
		tblSemestre
				.setModel(
						new DefaultTableModel(
								new Object[][] { { null, null }, { null, null }, { null, null }, { null, null },
										{ null, null }, { null, null }, },
								new String[] { "New column", "New column" }));
		tblSemestre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblSemestre.getSelectedRow();
				semestre = lista.get(fila);
				lblIdSemestre.setText("" + lista.get(fila).getIdSemestre());
				txtSemestre.setText(semestre.getSemestre());
			}
		});
		scrollPane.setViewportView(tblSemestre);
		modelo.addColumn("ID SEMESTRE");
		modelo.addColumn("SEMESTRE");
		tblSemestre.setModel(modelo);
		actualizarTabla();

		btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtSemestre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Semestre user = new Semestre();
					user.setSemestre(txtSemestre.getText());
					if (dao.insertarSemestre(user)) {
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
					if (txtSemestre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					semestre.setSemestre(txtSemestre.getText());
					if (dao.editarSemestre(semestre)) {
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

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR SEMESTRE?",
							"ELIMINAR SEMESTRE", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarSemestre(lista.get(fila).getIdSemestre())) {
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
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File(
							"C:\\Users\\virip\\OneDrive\\Escritorio\\Repositorios\\ProyectoFP\\src\\pdf\\ReporteSemestre.pdf");
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
					p.add("CATALOGO DE SEMESTRE");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(2);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID SEMESTRE", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase("SEMESTRE", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);

					for (Semestre u : lista) {
						tabla.addCell("" + u.getIdSemestre());
						tabla.addCell(u.getSemestre());

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
		btnPdf.setIcon(new ImageIcon(vCarrera.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(343, 251, 30, 39);
		contentPane.add(btnPdf);
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchSemestres();
		for (Semestre u : lista) {
			Object o[] = new Object[2];
			o[0] = u.getIdSemestre();
			o[1] = u.getSemestre();
			modelo.addRow(o);
		}
		tblSemestre.setModel(modelo);
	}
}
