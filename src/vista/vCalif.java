package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
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

import dao.daoCalif;
import modelo.Calificacion;
import modelo.Promedio;

import javax.swing.JButton;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vCalif extends JFrame {

	private JPanel contentPane;
	private JLabel lblIdCali;
	private JTextField txtAlumno;
	private JTextField txtProfesor;
	private JTextField txtSemestre;
	private JTextField txtCarrera;
	private JTextField txtGrupo;
	private JTextField txtAsignatura;
	private JTable tblCalificaciones;
	private JButton btnAgregar;
	private JButton btnPdf;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DefaultTableModel modelo = new DefaultTableModel();
	int fila=-1;
	Calificacion calificacion;
	ArrayList<Calificacion> lista = new ArrayList<Calificacion>();
	daoCalif dao = new daoCalif();
	private JSpinner spnCalif;
	private JLabel lblNewLabel_1;
	private JTextField txtBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCalif frame = new vCalif();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblIdCali.setText("");
		txtAlumno.setText("");
		txtProfesor.setText("");
		txtSemestre.setText("");
		txtCarrera.setText("");
		txtGrupo.setText("");
		txtAsignatura.setText("");
		spnCalif.setValue(0);	
		}
	
	public vCalif() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vCalif.class.getResource("/img/DeoClass.png")));
		setTitle("CALIFICACIONES");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID CALIFICACIONES");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 26, 209, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlumno = new JLabel("ALUMNO");
		lblAlumno.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblAlumno.setBounds(10, 73, 209, 36);
		contentPane.add(lblAlumno);
		
		JLabel lblProfesor = new JLabel("PROFESOR");
		lblProfesor.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblProfesor.setBounds(10, 116, 209, 36);
		contentPane.add(lblProfesor);
		
		JLabel lblSemestre = new JLabel("SEMESTRE");
		lblSemestre.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblSemestre.setBounds(10, 163, 209, 36);
		contentPane.add(lblSemestre);
		
		JLabel lblNewLabel_2_1 = new JLabel("CARRERA");
		lblNewLabel_2_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(10, 215, 209, 36);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("GRUPO");
		lblNewLabel_2_2.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(10, 263, 209, 36);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("ASIGNATURA");
		lblNewLabel_2_3.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel_2_3.setBounds(10, 317, 209, 36);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("CALIFICACIÓN");
		lblNewLabel_2_4.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel_2_4.setBounds(10, 374, 209, 36);
		contentPane.add(lblNewLabel_2_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 26, 656, 327);
		contentPane.add(scrollPane);
		
		tblCalificaciones = new JTable();
		tblCalificaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblCalificaciones.getSelectedRow();
				calificacion= lista.get(fila);
				lblIdCali.setText("" + lista.get(fila).getIdCali());
				txtAlumno.setText(calificacion.getAlumno());
				txtProfesor.setText(calificacion.getProfesor());
				txtSemestre.setText(calificacion.getSemestre());
				txtCarrera.setText(calificacion.getCarrera());
				txtGrupo.setText(""+calificacion.getGrupo());
				txtAsignatura.setText(calificacion.getAsignatura());
				spnCalif.setValue(calificacion.getCalificacio());
			}
		});
		tblCalificaciones.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblCalificaciones);
		modelo.addColumn("ID CALIFICACION");
		modelo.addColumn("ALUMNO");
		modelo.addColumn("PROFESOR");
		modelo.addColumn("SEMESTRE");
		modelo.addColumn("CARRERA");
		modelo.addColumn("GRUPO");
		modelo.addColumn("ASIGNATURA");
		modelo.addColumn("CALIFICACIONES");
		tblCalificaciones.setModel(modelo);
		actualizarTabla();
		
		lblIdCali = new JLabel("");
		lblIdCali.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdCali.setBounds(239, 26, 128, 24);
		contentPane.add(lblIdCali);
		
		txtAlumno = new JTextField();
		txtAlumno.setBounds(239, 73, 201, 27);
		contentPane.add(txtAlumno);
		txtAlumno.setColumns(10);
		
		txtProfesor = new JTextField();
		txtProfesor.setColumns(10);
		txtProfesor.setBounds(239, 116, 201, 27);
		contentPane.add(txtProfesor);
		
		txtSemestre = new JTextField();
		txtSemestre.setColumns(10);
		txtSemestre.setBounds(239, 163, 201, 27);
		contentPane.add(txtSemestre);
		
		txtCarrera = new JTextField();
		txtCarrera.setColumns(10);
		txtCarrera.setBounds(239, 209, 201, 27);
		contentPane.add(txtCarrera);
		
		txtGrupo = new JTextField();
		txtGrupo.setColumns(10);
		txtGrupo.setBounds(239, 263, 201, 27);
		contentPane.add(txtGrupo);
		
		txtAsignatura = new JTextField();
		txtAsignatura.setColumns(10);
		txtAsignatura.setBounds(239, 317, 201, 27);
		contentPane.add(txtAsignatura);
		
		spnCalif = new JSpinner();
		spnCalif.setBounds(239, 374, 201, 27);
		contentPane.add(spnCalif);
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAlumno.getText().equals("") || txtProfesor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Calificacion user = new Calificacion();
					user.setAlumno(txtAlumno.getText());
					user.setProfesor(txtProfesor.getText());
					user.setSemestre(txtSemestre.getText());
					user.setCarrera(txtCarrera.getText());
					user.setGrupo(Integer.parseInt(txtGrupo.getText().toString()));
					user.setAsignatura(txtAsignatura.getText());
					user.setCalificacio(Double.parseDouble(spnCalif.getValue().toString()));
					if (dao.insertarCal(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vCalif.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(10, 432, 30, 30);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAlumno.getText().equals("") || txtProfesor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					calificacion.setAlumno(txtAlumno.getText());
					calificacion.setProfesor(txtProfesor.getText());
					calificacion.setSemestre(txtSemestre.getText());
					calificacion.setCarrera(txtCarrera.getText());
					calificacion.setGrupo(Integer.parseInt(txtGrupo.getText().toString()));
					calificacion.setAsignatura(txtAsignatura.getText());
					calificacion.setCalificacio(Double.parseDouble(spnCalif.getValue().toString()));
					if (dao.editarCali(calificacion)) {
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
		btnEditar.setIcon(new ImageIcon(vCalif.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(50, 432, 30, 30);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTA CALIFICACIÓN?",
							"ELIMINAR CALIFICACIÓN", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarCali(lista.get(fila).getIdCali())) {
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
		btnEliminar.setIcon(new ImageIcon(vCalif.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(98, 432, 30, 30);
		contentPane.add(btnEliminar);
		
		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setIcon(new ImageIcon(vCalif.class.getResource("/img/icons8-broom-with-a-lot-of-dust-30.png")));
		btnLimpiar.setBounds(149, 432, 30, 30);
		contentPane.add(btnLimpiar);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/pdf/ReporteCalificaciones.pdf").toString());
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
					p.add("CATALOGO DE CALIFICACIONES");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(8);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID CALIFICACIÓN", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" ALUMNO", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" PROFESOR", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" SEMESTRE", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" CARRERA", negrita));
					PdfPCell c6 = new PdfPCell(new Phrase(" GRUPO", negrita));
					PdfPCell c7 = new PdfPCell(new Phrase(" ASIGNATURA", negrita));
					PdfPCell c8 = new PdfPCell(new Phrase(" CALIFICACIÓN", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c5.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c7.setHorizontalAlignment(Element.ALIGN_CENTER);
					c8.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c7.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c8.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);
					tabla.addCell(c5);
					tabla.addCell(c6);
					tabla.addCell(c7);
					tabla.addCell(c8);
					

					for (Calificacion u : lista) {
						tabla.addCell("" + u.getIdCali());
						tabla.addCell(u.getAlumno());
						tabla.addCell(u.getProfesor());
						tabla.addCell(u.getSemestre());
						tabla.addCell(u.getCarrera());
						tabla.addCell(""+u.getGrupo());
						tabla.addCell(u.getAsignatura());
						tabla.addCell(""+u.getCalificacio());

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
		btnPdf.setIcon(new ImageIcon(vCalif.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(200, 432, 30, 30);
		contentPane.add(btnPdf);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(vCalif.class.getResource("/img/icons8-buscar-cliente-30.png")));
		lblNewLabel_1.setBounds(606, 403, 30, 30);
		contentPane.add(lblNewLabel_1);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(660, 413, 350, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
	}
	
public void actualizarTabla() {
		
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fechsCalificacions();
		for (Calificacion u : lista) {
			Object o[] = new Object[8];
			o[0] = u.getIdCali();
			o[1] = u.getAlumno();
			o[2] = u.getProfesor();
			o[3] = u.getSemestre();
			o[4] = u.getCarrera();
			o[5] = u.getGrupo();
			o[6] = u.getAsignatura();
			o[7] = u.getCalificacio();
			modelo.addRow(o);
		}
		tblCalificaciones.setModel(modelo);
	}

	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.buscar(palabra);
		for (Calificacion p : lista) {
			Object item[] = new Object[8];
			item[0] = p.getIdCali();
			item[1] = p.getAlumno();
			item[2] = p.getProfesor();
			item[3] = p.getSemestre();
			item[4] = p.getCarrera();
			item[5] = p.getGrupo();
			item[6] = p.getAsignatura();
			item[7] = p.getCalificacio();
			modelo.addRow(item);
		}
		tblCalificaciones.setModel(modelo);

	}
}
