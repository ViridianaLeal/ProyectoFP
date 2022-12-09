package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
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

import dao.daoClase;
import dao.daoGrupo;
import dao.daoProfesor;
import modelo.Alumno;
import modelo.Clase;
import modelo.Grupo;
import modelo.Profesor;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class vClase extends JFrame {

	private JPanel contentPane;
	private JLabel lblIdClase;
	private JTextField txtclave;
	private JTextField txtProfesor;
	private JTextField txtClase;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnPdf;
	private JTextField txtBuscar;
	private JTable tblClases;
	daoClase dao = new daoClase();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Clase> lista = new ArrayList<Clase>();
	Clase clase;
	int fila = -1;
	daoGrupo daoG = new daoGrupo();
	ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();
	private JTextField txtGrupo;
	private JButton btnLimpiar;
	int idUsuario=-1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vClase frame = new vClase(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblIdClase.setText("");
		txtclave.setText("");
		txtProfesor.setText("");
		txtGrupo.setText("");
		txtClase.setText("");
	}

	

	public vClase(int idUsuario) {
		this.idUsuario=idUsuario;
		setIconImage(Toolkit.getDefaultToolkit().getImage(vClase.class.getResource("/img/DeoClass.png")));
		setTitle("AGREGAR CLASE");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 634, 562);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID CLASE");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 111, 24);
		contentPane.add(lblNewLabel);

		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblClave.setBounds(10, 46, 111, 24);
		contentPane.add(lblClave);

		JLabel lblProfesor = new JLabel("PROFESOR");
		lblProfesor.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblProfesor.setBounds(10, 86, 111, 24);
		contentPane.add(lblProfesor);

		JLabel lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblGrupo.setBounds(10, 127, 111, 24);
		contentPane.add(lblGrupo);

		JLabel lblClase = new JLabel("CLASE");
		lblClase.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblClase.setBounds(10, 166, 111, 24);
		contentPane.add(lblClase);

		lblIdClase = new JLabel("");
		lblIdClase.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdClase.setBounds(153, 15, 46, 14);
		contentPane.add(lblIdClase);

		txtclave = new JTextField();
		txtclave.setBounds(150, 47, 170, 20);
		contentPane.add(txtclave);
		txtclave.setColumns(10);

		txtProfesor = new JTextField();
		txtProfesor.setColumns(10);
		txtProfesor.setBounds(150, 87, 170, 20);
		contentPane.add(txtProfesor);

		txtClase = new JTextField();
		txtClase.setColumns(10);
		txtClase.setBounds(150, 167, 170, 20);
		contentPane.add(txtClase);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 584, 233);
		contentPane.add(scrollPane);

		tblClases = new JTable();
		tblClases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblClases.getSelectedRow();
				clase = lista.get(fila);
				lblIdClase.setText("" + lista.get(fila).getIdClase());
				txtclave.setText(clase.getClave());
				txtProfesor.setText(clase.getProfesor());
				txtGrupo.setText(""+clase.getGrupo());
				txtClase.setText(clase.getClase());
			}
		});
		tblClases.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblClases);
		modelo.addColumn("ID CLASE");
		modelo.addColumn("CLAVE");
		modelo.addColumn("PROFESOR");
		modelo.addColumn("GRUPO");
		modelo.addColumn("CLASE");
		tblClases.setModel(modelo);
		actualizarTabla();

		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtclave.getText().equals("") || txtProfesor.getText().equals("")
							|| txtClase.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Clase user = new Clase();
					user.setClave(txtclave.getText());
					user.setProfesor(txtProfesor.getText());
					user.setGrupo(Integer.parseInt(txtGrupo.getText().toString()));
					user.setClase(txtClase.getText());
					user.setIdusuario(idUsuario);
					if (dao.insertarClase(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vClase.class.getResource("/img/icons8-más-2-matemáticas-29 (1).png")));
		btnAgregar.setBounds(358, 100, 30, 30);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtclave.getText().equals("") || txtProfesor.getText().equals("")
							|| txtClase.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					clase.setClave(txtclave.getText());
					clase.setProfesor(txtProfesor.getText());
					clase.setGrupo(Integer.parseInt(txtGrupo.getText().toString()));
					clase.setClase(txtClase.getText());
					if (dao.editarClase(clase)) {
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
		btnEditar.setIcon(new ImageIcon(vClase.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(417, 100, 30, 30);
		contentPane.add(btnEditar);

		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTA CLASE?",
							"ELIMINAR CLASE", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarClase(lista.get(fila).getIdClase())) {
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
		btnEliminar.setIcon(new ImageIcon(vClase.class.getResource("/img/eli.png")));
		btnEliminar.setBounds(483, 100, 30, 30);
		contentPane.add(btnEliminar);

		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/pdf/ReporteClases.pdf").toString());
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
					p.add("CATALOGO DE CLASES");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					// Tabla de datos
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase("ID CLASE", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase("CLAVE", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase("PROFESOR", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase("GRUPO", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase("CLASE", negrita));
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
					for (Clase pro : lista) {
						tabla.addCell("" + pro.getIdClase());
						tabla.addCell("" + pro.getClave());
						tabla.addCell(pro.getProfesor());
						tabla.addCell(""+pro.getGrupo());
						tabla.addCell(pro.getClase());
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
		btnPdf.setIcon(new ImageIcon(vClase.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(550, 100, 30, 30);
		contentPane.add(btnPdf);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(vClase.class.getResource("/img/icons8-buscar-cliente-30.png")));
		lblNewLabel_1.setBounds(131, 471, 30, 30);
		contentPane.add(lblNewLabel_1);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(183, 481, 274, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		txtGrupo = new JTextField();
		txtGrupo.setBounds(149, 128, 170, 20);
		contentPane.add(txtGrupo);
		txtGrupo.setColumns(10);
		
		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(vClase.class.getResource("/img/icons8-broom-with-a-lot-of-dust-30.png")));
		btnLimpiar.setBounds(447, 140, 30, 30);
		contentPane.add(btnLimpiar);
	}

	

	public void actualizarTabla() {
		
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetcClases(idUsuario);
		for (Clase u : lista) {
			Object o[] = new Object[5];
			o[0] = u.getIdClase();
			o[1] = u.getClave();
			o[2] = u.getProfesor();
			o[3] = u.getGrupo();
			o[4] = u.getClase();
			modelo.addRow(o);
		}
		tblClases.setModel(modelo);
	}

	public void refrescarTabla2(String palabra) {
		
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.buscar(palabra, idUsuario);
		for (Clase p : lista) {
			Object item[] = new Object[5];
			item[0] = p.getIdClase();
			item[1] = p.getClave();
			item[2] = p.getProfesor();
			item[3] = p.getGrupo();
			item[4] = p.getClass();

			modelo.addRow(item);
		}
		tblClases.setModel(modelo);

	}
}
