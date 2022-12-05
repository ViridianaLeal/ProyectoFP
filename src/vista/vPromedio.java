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

import dao.daoProfesor;
import dao.daoPromedio;
import modelo.Calificaciones;
import modelo.Profesor;
import modelo.Promedio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Toolkit;

public class vPromedio extends JFrame {

	private JPanel contentPane;
	private JTextField txtAlumno;
	private JTextField txtProfesor;
	private JComboBox cboSemestre;
	private JComboBox cboCarrera;
	private JComboBox cboGrupo;
	private JTextField txtAsignatura;
	private JSpinner spnCali;
	private JSpinner spnPromedio;
	private JTable tblPromedios;
	private JButton btnAgregar;
	private JButton btnPdf;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JTextField txtBuscar;
	daoPromedio dao = new daoPromedio();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Promedio> lista = new ArrayList<Promedio>();
	Promedio promedio;
	int fila = -1;
	private JLabel lblPromedioId;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPromedio frame = new vPromedio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblPromedioId.setText("");
		txtAlumno.setText("");
		txtProfesor.setText("");
		cboSemestre.setSelectedItem("");
		cboCarrera.setSelectedItem("");
		cboGrupo.setSelectedItem("");
		txtAsignatura.setText("");
		spnCali.setValue(0);
		spnPromedio.setValue(0);
	}

	
	public vPromedio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPromedio.class.getResource("/img/DeoClass.png")));
		setTitle("AGREGAR PROMEDIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 422);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID PROMEDIO");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 171, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlumno = new JLabel("ALUMNO");
		lblAlumno.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblAlumno.setBounds(10, 47, 171, 24);
		contentPane.add(lblAlumno);
		
		JLabel lblProfesor = new JLabel("PROFESOR");
		lblProfesor.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblProfesor.setBounds(10, 82, 171, 24);
		contentPane.add(lblProfesor);
		
		JLabel lblSemestre = new JLabel("SEMESTRE");
		lblSemestre.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblSemestre.setBounds(10, 117, 171, 24);
		contentPane.add(lblSemestre);
		
		JLabel lblCarrera = new JLabel("CARRERA");
		lblCarrera.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblCarrera.setBounds(10, 149, 171, 24);
		contentPane.add(lblCarrera);
		
		JLabel lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblGrupo.setBounds(10, 184, 171, 24);
		contentPane.add(lblGrupo);
		
		JLabel lblAsignatura = new JLabel("ASIGNATURA");
		lblAsignatura.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblAsignatura.setBounds(10, 219, 171, 24);
		contentPane.add(lblAsignatura);
		
		JLabel lblCalificaciones = new JLabel("CALIFICACIONES");
		lblCalificaciones.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblCalificaciones.setBounds(10, 257, 171, 24);
		contentPane.add(lblCalificaciones);
		
		JLabel lblPromedio = new JLabel("PROMEDIO");
		lblPromedio.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblPromedio.setBounds(10, 296, 171, 24);
		contentPane.add(lblPromedio);
		
		lblPromedioId = new JLabel("");
		lblPromedioId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPromedioId.setBounds(191, 11, 85, 17);
		contentPane.add(lblPromedioId);
		
		txtAlumno = new JTextField();
		txtAlumno.setBounds(190, 48, 197, 20);
		contentPane.add(txtAlumno);
		txtAlumno.setColumns(10);
		
		txtProfesor = new JTextField();
		txtProfesor.setBounds(191, 83, 196, 20);
		contentPane.add(txtProfesor);
		txtProfesor.setColumns(10);
		
		cboSemestre = new JComboBox();
		cboSemestre.setBounds(191, 117, 196, 22);
		contentPane.add(cboSemestre);
		
		cboCarrera = new JComboBox();
		cboCarrera.setBounds(191, 149, 196, 22);
		contentPane.add(cboCarrera);
		
		cboGrupo = new JComboBox();
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] {"101", "102", "103", "104", "105", "106", "107"}));
		cboGrupo.setBounds(191, 184, 197, 22);
		contentPane.add(cboGrupo);
		
		txtAsignatura = new JTextField();
		txtAsignatura.setBounds(190, 220, 197, 20);
		contentPane.add(txtAsignatura);
		txtAsignatura.setColumns(10);
		
		spnCali = new JSpinner();
		spnCali.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
		spnCali.setBounds(191, 258, 196, 20);
		contentPane.add(spnCali);
		
		spnPromedio = new JSpinner();
		spnPromedio.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
		spnPromedio.setBounds(191, 297, 196, 20);
		contentPane.add(spnPromedio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(403, 28, 632, 281);
		contentPane.add(scrollPane);
		
		tblPromedios = new JTable();
		tblPromedios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblPromedios.getSelectedRow();
				promedio= lista.get(fila);
				lblPromedioId.setText("" + lista.get(fila).getIdPromedio());
				txtAlumno.setText(promedio.getAlumno());
				txtProfesor.setText(promedio.getProfesor());
				cboSemestre.setSelectedItem(promedio.getSemestre());
				cboCarrera.setSelectedItem(promedio.getCarrera());
				cboGrupo.setSelectedItem(promedio.getGrupo());
				txtAsignatura.setText(promedio.getAsignaturas());
				spnCali.setValue(promedio.getCalificaciones());
				spnPromedio.setValue(promedio.getPromedio());
			}
		});
		tblPromedios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblPromedios);
		modelo.addColumn("ID PROMEDIO");
		modelo.addColumn("ALUMNO");
		modelo.addColumn("PROFESOR");
		modelo.addColumn("SEMESTRE");
		modelo.addColumn("CARRERA");
		modelo.addColumn("GRUPO");
		modelo.addColumn("ASINATURA");
		modelo.addColumn("CALIFICACIONES");
		modelo.addColumn("PROMEDIO");
		tblPromedios.setModel(modelo);
		actualizarTabla();
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAlumno.getText().equals("") || txtProfesor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Promedio user = new Promedio();
					user.setAlumno(txtAlumno.getText());
					user.setProfesor(txtProfesor.getText());
					user.setSemestre(""+cboSemestre.getSelectedItem());
					user.setCarrera(""+cboCarrera.getSelectedItem());
					user.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					user.setAsignaturas(txtAsignatura.getText());
					user.setCalificaciones(Double.parseDouble(spnCali.getValue().toString()));
					user.setPromedio(Double.parseDouble(spnPromedio.getValue().toString()));
					if (dao.insertarPromedio(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vPromedio.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(802, 349, 30, 30);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAlumno.getText().equals("") || txtProfesor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					promedio.setAlumno(txtAlumno.getText());
					promedio.setProfesor(txtProfesor.getText());
					promedio.setSemestre("" + cboSemestre.getSelectedItem());
					promedio.setCarrera(""+cboCarrera.getSelectedItem());
					promedio.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					promedio.setAsignaturas(txtAsignatura.getText());
					promedio.setCalificaciones(Double.parseDouble(spnCali.getValue().toString()));
					promedio.setPromedio(Double.parseDouble(spnPromedio.getValue().toString()));
					if (dao.editarPromedio(promedio)) {
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
		btnEditar.setIcon(new ImageIcon(vPromedio.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(849, 349, 30, 30);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE PROMEDIO?",
							"ELIMINAR PROMEDIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarPromedio(lista.get(fila).getIdPromedio())) {
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
		btnEliminar.setIcon(new ImageIcon(vPromedio.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(900, 349, 30, 30);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File(
							"C:\\Users\\virip\\OneDrive\\Escritorio\\Repositorios\\ProyectoFP\\src\\pdf\\ReportePromedio.pdf");
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
					p.add("CATALOGO DE PROMEDIOS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(9);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID PROMEDIO", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" ALUMNO", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" PROFESOR", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" SEMESTRE", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" CARRERA", negrita));
					PdfPCell c6 = new PdfPCell(new Phrase(" GRUPO", negrita));
					PdfPCell c7 = new PdfPCell(new Phrase(" ASIGNATURA", negrita));
					PdfPCell c8 = new PdfPCell(new Phrase(" CALIFICACIONES", negrita));
					PdfPCell c9 = new PdfPCell(new Phrase(" PROMEDIO", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c5.setHorizontalAlignment(Element.ALIGN_CENTER);
					c6.setHorizontalAlignment(Element.ALIGN_CENTER);
					c7.setHorizontalAlignment(Element.ALIGN_CENTER);
					c8.setHorizontalAlignment(Element.ALIGN_CENTER);
					c9.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c7.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c8.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c9.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);
					tabla.addCell(c5);
					tabla.addCell(c6);
					tabla.addCell(c7);
					tabla.addCell(c8);
					tabla.addCell(c9);
					

					for (Promedio u : lista) {
						tabla.addCell("" + u.getIdPromedio());
						tabla.addCell(u.getAlumno());
						tabla.addCell(u.getProfesor());
						tabla.addCell(u.getSemestre());
						tabla.addCell(u.getCarrera());
						tabla.addCell(""+u.getGrupo());
						tabla.addCell(u.getAsignaturas());
						tabla.addCell(""+u.getCalificaciones());
						tabla.addCell(""+u.getPromedio());

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
		btnPdf.setIcon(new ImageIcon(vPromedio.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(957, 349, 30, 30);
		contentPane.add(btnPdf);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(vPromedio.class.getResource("/img/icons8-buscar-cliente-30.png")));
		lblNewLabel_2.setBounds(461, 341, 30, 30);
		contentPane.add(lblNewLabel_2);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(501, 349, 272, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
	}
	
	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fechsPromedios();
		for (Promedio u : lista) {
			Object o[] = new Object[9];
			o[0] = u.getIdPromedio();
			o[1] = u.getAlumno();
			o[2] = u.getProfesor();
			o[3] = u.getSemestre();
			o[4] = u.getCarrera();
			o[5] = u.getGrupo();
			o[6] = u.getAsignaturas();
			o[7] = u.getCalificaciones();
			o[8] = u.getPromedio();
			modelo.addRow(o);
		}
		tblPromedios.setModel(modelo);
	}

	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.buscar(palabra);
		for (Promedio p : lista) {
			Object item[] = new Object[9];
			item[0] = p.getIdPromedio();
			item[1] = p.getAlumno();
			item[2] = p.getProfesor();
			item[3] = p.getSemestre();
			item[4] = p.getCarrera();
			item[5] = p.getGrupo();
			item[6] = p.getAsignaturas();
			item[7] = p.getCalificaciones();
			item[8] = p.getPromedio();
			modelo.addRow(item);
		}
		tblPromedios.setModel(modelo);

	}
}