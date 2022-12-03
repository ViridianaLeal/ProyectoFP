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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.daoCarrera;
import dao.daoFoto;
import modelo.Carrera;
import modelo.Foto;
import modelo.Usuario;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class vFoto extends JFrame {

	private JPanel contentPane;
	private JLabel lblFoto;
	private JLabel lblIdFoto;
	private JTable tblFotos;
	private JButton btnPdf;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JButton btnEditar;
	daoFoto dao = new daoFoto();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Foto> lista = new ArrayList<Foto>();
	Foto foto;
	int fila = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vFoto frame = new vFoto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public void limpiar() {
		lblFoto.setText("");
		lblIdFoto.setText("");
	}
	
	public vFoto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vFoto.class.getResource("/img/DeoClass.png")));
		setTitle("AGREGAR FOTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 526);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID FOTO");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel.setBounds(22, 36, 102, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FOTO");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(21, 75, 91, 31);
		contentPane.add(lblNewLabel_1);
		
		lblIdFoto = new JLabel("0");
		lblIdFoto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdFoto.setBounds(134, 36, 115, 18);
		contentPane.add(lblIdFoto);
		
		lblFoto = new JLabel("");
		lblFoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFoto.setBounds(132, 82, 138, 131);
		contentPane.add(lblFoto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 237, 498, 183);
		contentPane.add(scrollPane);
		
		tblFotos = new JTable();
		tblFotos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblFotos.getSelectedRow();
				foto = lista.get(fila);
				lblIdFoto.setText("" + lista.get(fila).getIdFoto());
				lblFoto.setText(foto.getFoto());
			}
		});
		tblFotos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane.setViewportView(tblFotos);
		modelo.addColumn("ID FOTO");
		modelo.addColumn("FOTO");
		tblFotos.setModel(modelo);
		actualizarTabla();
		
		JButton btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Foto user = new Foto();
					user.setFoto(lblFoto.getText());
					if (dao.insertarFoto(user)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(77, 441, 30, 30);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					foto.setFoto(lblFoto.getText());
					if (dao.editarFoto(foto)) {
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
		btnEditar.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(146, 441, 30, 30);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR LA FOTO?",
							"ELIMINAR FOTO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarFoto(lista.get(fila).getIdFoto())) {
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
		btnEliminar.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(240, 441, 30, 30);
		contentPane.add(btnEliminar);
		
		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-broom-with-a-lot-of-dust-30.png")));
		btnLimpiar.setBounds(329, 441, 30, 30);
		contentPane.add(btnLimpiar);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File(
							"C:\\Users\\virip\\OneDrive\\Escritorio\\Repositorios\\ProyectoFP\\src\\pdf\\ReporteFotos.pdf");
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
					p.add("CATALOGO DE FOTOS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(2);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID FOTO", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase("FOTO", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);

					for (Foto u : lista) {
						tabla.addCell("" + u.getIdFoto());
						tabla.addCell(u.getFoto());

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
		btnPdf.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(413, 441, 30, 30);
		contentPane.add(btnPdf);
	}
	
	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetcFotos();
		for (Foto u : lista) {
			Object o[] = new Object[2];
			o[0] = u.getIdFoto();
			o[1] = u.getFoto();
			modelo.addRow(o);
		}
		tblFotos.setModel(modelo);
	}
	
}
