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


import dao.daoCalificaciones;

import modelo.Calificaciones;
import modelo.Usuario;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class vCalificaciones extends JFrame {

	private JPanel contentPane;
	private JLabel lblIdCal;
	private JTextField txtAlumno;
	private JTextField txtProfesor;
	private JComboBox cboCarrera;
	private JComboBox cboSemestre;
	private JComboBox cboGrupo;
	private JComboBox cboAsignatura;
	private JTable tblCalificaciones;
	private JButton btnEditar;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnPdf;
	private JTextField txtBuscar;
	daoCalificaciones dao = new daoCalificaciones();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Calificaciones> lista =new ArrayList<Calificaciones>();
	int fila = -1;
	Calificaciones calificaciones;
	private JSpinner spnCal;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCalificaciones frame = new vCalificaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblIdCal.setText("");
		txtAlumno.setText("");
		txtProfesor.setText("");
		cboSemestre.setSelectedItem("");
		cboCarrera.setSelectedItem("");
		cboGrupo.setSelectedItem("");
		cboAsignatura.setSelectedItem("");
		spnCal.setValue(0);
	}

	public vCalificaciones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vCalificaciones.class.getResource("/img/DeoClass.png")));
		setTitle("AGREGAR CALIFICACIONES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1145, 462);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID CALIFICACIONES");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 23, 165, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlumno = new JLabel("ALUMNO");
		lblAlumno.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblAlumno.setBounds(10, 62, 165, 14);
		contentPane.add(lblAlumno);
		
		JLabel lblNewLabel_1_1 = new JLabel("PROFESOR");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 100, 165, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("SEMESTRE");
		lblNewLabel_1_2.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 138, 165, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("CARERA");
		lblNewLabel_1_3.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(10, 172, 165, 14);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("GRUPO");
		lblNewLabel_1_4.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(10, 216, 165, 14);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("ASIGNATURA");
		lblNewLabel_1_5.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(10, 256, 165, 14);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("CALIFICACIONES");
		lblNewLabel_1_6.setFont(new Font("Consolas", Font.PLAIN, 16));
		lblNewLabel_1_6.setBounds(10, 296, 165, 14);
		contentPane.add(lblNewLabel_1_6);
		
		lblIdCal = new JLabel("");
		lblIdCal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdCal.setBounds(197, 23, 92, 13);
		contentPane.add(lblIdCal);
		
		txtAlumno = new JTextField();
		txtAlumno.setBounds(197, 58, 181, 20);
		contentPane.add(txtAlumno);
		txtAlumno.setColumns(10);
		
		txtProfesor = new JTextField();
		txtProfesor.setColumns(10);
		txtProfesor.setBounds(197, 96, 181, 20);
		contentPane.add(txtProfesor);
		
		cboSemestre = new JComboBox();
		cboSemestre.setBounds(197, 133, 182, 22);
		contentPane.add(cboSemestre);
		
		cboCarrera = new JComboBox();
		cboCarrera.setBounds(197, 167, 181, 22);
		contentPane.add(cboCarrera);
		
		cboGrupo = new JComboBox();
		cboGrupo.setModel(new DefaultComboBoxModel(new String[] {"305"}));
		cboGrupo.setBounds(197, 211, 181, 22);
		contentPane.add(cboGrupo);
		
		cboAsignatura = new JComboBox();
		cboAsignatura.setBounds(197, 251, 181, 22);
		contentPane.add(cboAsignatura);
		
		spnCal = new JSpinner();
		spnCal.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
		spnCal.setBounds(195, 292, 183, 20);
		contentPane.add(spnCal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(388, 23, 720, 333);
		contentPane.add(scrollPane);
		
		tblCalificaciones = new JTable();
		tblCalificaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblCalificaciones.getSelectedRow();
				calificaciones= lista.get(fila);
				lblIdCal.setText("" + lista.get(fila).getIdCalificaciones());
				txtAlumno.setText(calificaciones.getAlumno());
				txtProfesor.setText(calificaciones.getProfesor());
				cboSemestre.setSelectedItem(calificaciones.getSemestre());
				cboCarrera.setSelectedItem(calificaciones.getCarrera());
				cboGrupo.setSelectedItem(calificaciones.getGrupo());
				cboAsignatura.setSelectedItem(calificaciones.getAsignatura());
				spnCal.setValue(calificaciones.getCalificaciones());
			}
		});
		tblCalificaciones.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblCalificaciones);
		modelo.addColumn("ID ");
		modelo.addColumn("Alumno");
		modelo.addColumn("PROFESOR");
		modelo.addColumn("SEMESTRE");
		modelo.addColumn("CARRERA");
		modelo.addColumn("GRUPO");
		modelo.addColumn("ASIGNATURA");
		modelo.addColumn("CALIFICACIONES");
		tblCalificaciones.setModel(modelo);
		actualizarTabla();
		
		btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAlumno.getText().equals("") || txtProfesor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Calificaciones user = new Calificaciones();
					user.setAlumno(txtAlumno.getText());
					user.setProfesor(txtProfesor.getText());
					user.setSemestre(""+cboSemestre.getSelectedItem());
					user.setCarrera(""+cboCarrera.getSelectedItem());
					user.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					user.setAsignatura(""+cboAsignatura.getSelectedItem());
					user.setCalificaciones(Double.parseDouble(spnCal.getValue().toString()));
					if (dao.insertarCalificacion(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vCalificaciones.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(44, 355, 30, 30);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtAlumno.getText().equals("") || txtProfesor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					calificaciones.setAlumno(txtAlumno.getText());
					calificaciones.setProfesor(txtProfesor.getText());
					calificaciones.setSemestre("" + cboSemestre.getSelectedItem());
					calificaciones.setCarrera(""+cboCarrera.getSelectedItem());
					calificaciones.setGrupo(Integer.parseInt(cboGrupo.getSelectedItem().toString()));
					calificaciones.setAsignatura(""+cboAsignatura.getSelectedItem());
					calificaciones.setCalificaciones(Double.parseDouble(spnCal.getValue().toString()));
					if (dao.editarCalificaciones(calificaciones)) {
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
		btnEditar.setIcon(new ImageIcon(vCalificaciones.class.getResource("/img/icons8-lápiz-30.png")));
		btnEditar.setBounds(97, 355, 30, 30);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTA CALIFICACIÓN?",
							"ELIMINAR CALIFICACIÓN", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarCalificacion(lista.get(fila).getIdCalificaciones())) {
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
		btnEliminar.setIcon(new ImageIcon(vCalificaciones.class.getResource("/img/icons8-eliminar-30.png")));
		btnEliminar.setBounds(145, 355, 30, 30);
		contentPane.add(btnEliminar);
		
		btnPdf = new JButton("");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File(
							"C:\\Users\\virip\\OneDrive\\Escritorio\\Repositorios\\ProyectoFP\\src\\pdf\\ReporteCalificaciones.pdf");
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
					p.add("CATALOGO DE CALIFICACIONES");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(8);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID CALIFICACIONES", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" ALUMNO", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" PROFESOR", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" SEMESTRE", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase(" CARRERA", negrita));
					PdfPCell c6 = new PdfPCell(new Phrase(" GRUPO", negrita));
					PdfPCell c7 = new PdfPCell(new Phrase(" ASIGNATURA", negrita));
					PdfPCell c8 = new PdfPCell(new Phrase(" CALIFICACIONES", negrita));
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
					
					

					for (Calificaciones u : lista) {
						tabla.addCell("" + u.getIdCalificaciones());
						tabla.addCell(u.getAlumno());
						tabla.addCell(u.getProfesor());
						tabla.addCell(u.getSemestre());
						tabla.addCell(u.getCarrera());
						tabla.addCell(""+u.getGrupo());
						tabla.addCell(u.getAsignatura());
						tabla.addCell(""+u.getIdCalificaciones());
						

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
		btnPdf.setIcon(new ImageIcon(vCalificaciones.class.getResource("/img/icons8-pdf-30.png")));
		btnPdf.setBounds(197, 355, 30, 30);
		contentPane.add(btnPdf);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(vCalificaciones.class.getResource("/img/icons8-buscar-cliente-30.png")));
		lblNewLabel_1.setBounds(561, 374, 30, 29);
		contentPane.add(lblNewLabel_1);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(604, 383, 312, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
	}
	
	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.feCalificaciones();
		for (Calificaciones u : lista) {
			Object o[] = new Object[8];
			o[0] = u.getIdCalificaciones();
			o[1] = u.getAlumno();
			o[2] = u.getProfesor();
			o[3] = u.getSemestre();
			o[4] = u.getCarrera();
			o[5] = u.getGrupo();
			o[6] = u.getAsignatura();
			o[7] = u.getCalificaciones();
			modelo.addRow(o);
		}
		tblCalificaciones.setModel(modelo);
	}

	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.buscar(palabra);
		for (Calificaciones p : lista) {
			Object item[] = new Object[8];
			item[0] = p.getIdCalificaciones();
			item[1] = p.getAlumno();
			item[2] = p.getProfesor();
			item[3] = p.getSemestre();
			item[4] = p.getCarrera();
			item[5] = p.getGrupo();
			item[6] = p.getAsignatura();
			item[7] = p.getIdCalificaciones();
			modelo.addRow(item);
		}
		tblCalificaciones.setModel(modelo);

	}
}
