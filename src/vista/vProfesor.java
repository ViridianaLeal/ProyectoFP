package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
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

import dao.daoProfesor;
import dao.daoUsuario;
import modelo.Alumno;
import modelo.Profesor;
import modelo.Usuario;

import javax.swing.JButton;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vProfesor extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("ID PROFESOR");
	private JLabel lblIdProfesor;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtClave;
	private JTextField txtCarrera;
	private JLabel lblFoto;
	private JTable tblProfesotes;
	private JTextField txtBuscar;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnPdf;
	daoProfesor dao = new daoProfesor();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Profesor> lista = new ArrayList<Profesor>();
	Profesor profesor;
	int fila = -1;
	private JScrollPane scrollPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProfesor frame = new vProfesor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblIdProfesor.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtClave.setText("");
		txtCarrera.setText("");
		lblFoto.setText("");
	}

	public vProfesor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vProfesor.class.getResource("/img/DeoClass.png")));
		setTitle("AGREGAR PROFESOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1116, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 130, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("NOMBRE");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 36, 98, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("APELLIDOS");
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 74, 100, 22);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CLAVE");
		lblNewLabel_3.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 107, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("CARRERA");
		lblNewLabel_4.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 138, 175, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("FOTO");
		lblNewLabel_5.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 174, 63, 30);
		contentPane.add(lblNewLabel_5);

		lblIdProfesor = new JLabel("");
		lblIdProfesor.setFont(new Font("Consolas", Font.BOLD, 16));
		lblIdProfesor.setBounds(150, 11, 63, 24);
		contentPane.add(lblIdProfesor);

		txtNombre = new JTextField();
		txtNombre.setBounds(150, 40, 220, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(150, 74, 223, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtClave = new JTextField();
		txtClave.setBounds(150, 103, 224, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);

		txtCarrera = new JTextField();
		txtCarrera.setBounds(150, 134, 225, 20);
		contentPane.add(txtCarrera);
		txtCarrera.setColumns(10);

		lblFoto = new JLabel("");
		lblFoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFoto.setBounds(66, 178, 225, 156);
		contentPane.add(lblFoto);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 21, 680, 262);
		contentPane.add(scrollPane);

		tblProfesotes = new JTable();
		tblProfesotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblProfesotes.getSelectedRow();
				profesor = lista.get(fila);
				lblIdProfesor.setText("" + lista.get(fila).getIdProfesor());
				txtNombre.setText(profesor.getNombre());
				txtApellidos.setText(profesor.getApellidos());
				txtClave.setText("" + profesor.getClave());
				txtCarrera.setText(profesor.getCarrera());
				lblFoto.setText(profesor.getFoto());
			}
		});
		tblProfesotes.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null },
				{ null, null, null, null, null, null }, { null, null, null, null, null, null },
				{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblProfesotes);
		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("CLAVE");
		modelo.addColumn("CARRERA");
		modelo.addColumn("FOTO");
		tblProfesotes.setModel(modelo);
		actualizarTabla();

		btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(vProfesor.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || txtApellidos.getText().equals("")
							|| txtClave.getText().equals("") || txtCarrera.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Profesor user = new Profesor();
					user.setNombre(txtNombre.getText());
					user.setApellidos(txtApellidos.getText());
					user.setClave(Integer.parseInt(txtClave.getText().toString()));
					user.setCarrera(txtCarrera.getText());
					user.setFoto(lblFoto.getText());
					if (dao.insertarProfesor(user)) {
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
		btnAgregar.setPreferredSize(new Dimension(30, 30));
		btnAgregar.setBounds(926, 303, 30, 31);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || txtApellidos.getText().equals("")
							|| txtClave.getText().equals("") || txtCarrera.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					profesor.setNombre(txtNombre.getText());
					profesor.setApellidos(txtApellidos.getText());
					profesor.setClave(Integer.parseInt(txtClave.getText().toString()));
					profesor.setCarrera(txtCarrera.getText());
					profesor.setFoto(lblFoto.getText());
					if (dao.editarProfesor(profesor)) {
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
		btnEditar.setIcon(new ImageIcon(vProfesor.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setPreferredSize(new Dimension(30, 30));
		btnEditar.setBounds(960, 303, 30, 31);
		contentPane.add(btnEditar);

		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE PROFESOR?",
							"ELIMINAR PROFESOR", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarProfesor(lista.get(fila).getIdProfesor())) {
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
		btnEliminar.setIcon(new ImageIcon(vProfesor.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setPreferredSize(new Dimension(30, 30));
		btnEliminar.setBounds(1000, 303, 30, 31);
		contentPane.add(btnEliminar);

		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/pdf/ReporteProfesores.pdf").toString());
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
					p.add("CATALOGO DE PROFESORES");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(6);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID PROFESOR", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" NOMBRE", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" APELLIDOS", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" CLAVE", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" CARRERA", negrita));
					PdfPCell c6 = new PdfPCell(new Phrase(" FOTO", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c5.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);
					tabla.addCell(c5);
					tabla.addCell(c6);
					

					for (Profesor u : lista) {
						tabla.addCell("" + u.getIdProfesor());
						tabla.addCell(u.getNombre());
						tabla.addCell(u.getApellidos());
						tabla.addCell(""+u.getClave());
						tabla.addCell(u.getCarrera());
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
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnPdf.setIcon(new ImageIcon(vProfesor.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setPreferredSize(new Dimension(30, 30));
		btnPdf.setBounds(1040, 303, 30, 31);
		contentPane.add(btnPdf);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(vProfesor.class.getResource("/img/icons8-buscar-cliente-30.png")));
		lblNewLabel_6.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(547, 309, 30, 31);
		contentPane.add(lblNewLabel_6);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(587, 316, 282, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(vProfesor.class.getResource("/img/icons8-foto-30.png")));
		btnNewButton.setBounds(302, 303, 30, 29);
		contentPane.add(btnNewButton);
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetcProfesors();
		for (Profesor u : lista) {
			Object o[] = new Object[6];
			o[0] = u.getIdProfesor();
			o[1] = u.getNombre();
			o[2] = u.getApellidos();
			o[3] = u.getClave();
			o[4] = u.getCarrera();
			o[5] = u.getFoto();
			modelo.addRow(o);
		}
		tblProfesotes.setModel(modelo);
	}
	
	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.buscar(palabra);
		for (Profesor p : lista) {
			Object item[] = new Object[6];
			item[0] = p.getIdProfesor();
			item[1] = p.getNombre();
			item[2] = p.getApellidos();
			item[3] = p.getClave();
			item[4] = p.getCarrera();
			item[5] = p.getFoto();
			
			modelo.addRow(item);
		}
		tblProfesotes.setModel(modelo);

	}
}
