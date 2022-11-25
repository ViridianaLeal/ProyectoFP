package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

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

import dao.daoUsuario;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Choice;

public class vUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPassword;
	private JTextField txtNombre;
	private JTable tblUsuarios;
	private JLabel lblID;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnPdf;
	private JScrollPane scrollPane;
	daoUsuario dao = new daoUsuario();
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Usuario> lista = new ArrayList<Usuario>();
	Usuario usuario;
	int fila = -1;
	private JTextField txtImagen;
	private JButton btnSeleccionarI;
	private JLabel lblFoto;
	FileInputStream fis;
	int longitudBytes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void limpiar() {
		txtUser.setText("");
		txtPassword.setText("");
		txtNombre.setText("");
		lblID.setText("");
		txtImagen.setText("");
		lblFoto.setText("");
	}

	public vUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vUsuario.class.getResource("/img/jyujyu.png")));
		setTitle("CRUD USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 598);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel.setBounds(25, 32, 46, 23);
		contentPane.add(lblNewLabel);
		lblID = new JLabel("1");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblID.setBounds(164, 40, 46, 14);
		contentPane.add(lblID);
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1.setBounds(25, 66, 86, 21);
		contentPane.add(lblNewLabel_1);
		txtUser = new JTextField();
		txtUser.setBounds(164, 67, 169, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(25, 100, 129, 23);
		contentPane.add(lblNewLabel_1_1);
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(164, 97, 169, 20);
		contentPane.add(txtPassword);
		JLabel lblNewLabel_1_2 = new JLabel("NOMBRE");
		lblNewLabel_1_2.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1_2.setBounds(25, 131, 86, 23);
		contentPane.add(lblNewLabel_1_2);
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(164, 136, 169, 20);
		contentPane.add(txtNombre);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream archivofoto;
					if (txtUser.getText().equals("") || txtPassword.getText().equals("")
							|| txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					Usuario user = new Usuario();
					user.setUser(txtUser.getText());
					user.setPassword(txtPassword.getText());
					user.setNombre(txtNombre.getText());
					if (dao.insertarUsuario(user)) {
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
		btnAgregar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAgregar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnAgregar.setBounds(104, 312, 106, 23);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUser.getText().equals("") || txtPassword.getText().equals("")
							|| txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS ");
						return;
					}
					usuario.setUser(txtUser.getText());
					usuario.setPassword(txtPassword.getText());
					usuario.setNombre(txtNombre.getText());
					if (dao.editarUsuario(usuario)) {
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
		btnEditar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEditar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEditar.setBounds(391, 312, 89, 23);
		contentPane.add(btnEditar);
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE USUARIO?",
							"ELIMINAR USUARIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarUsuario(lista.get(fila).getId())) {
							actualizarTabla();
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
		btnEliminar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEliminar.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnEliminar.setBounds(244, 312, 103, 23);
		contentPane.add(btnEliminar);
		btnPdf = new JButton("Pdf");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					File file = new File("C:\\Users\\VIRI\\Desktop\\Repositorios\\ProyectoFP\\src\\PDF\\RegistroUsuarios.pdf");
					archivo = new FileOutputStream(file);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					Image img = Image.getInstance(
							"C:\\Users\\VIRI\\Desktop\\Repositorios\\ProyectoFP\\src\\img\\jyujyu.png");
					img.setAlignment(Element.ALIGN_CENTER);
					img.scaleToFit(100, 100);
					doc.add(img);
					Paragraph p = new Paragraph(10);
					com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(
							com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
					p.add(Chunk.NEWLINE);
					p.add("CATALOGO DE USUARIOS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(5);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID USUARIO", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" USUARIO", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" PASSWORD", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" NOMBRE", negrita));
					PdfPCell c5 = new PdfPCell(new Phrase("FOTO",negrita));
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

					for (Usuario u : lista) {
						tabla.addCell("" + u.getId());
						tabla.addCell(u.getUser());
						tabla.addCell(u.getPassword());
						tabla.addCell(u.getNombre());
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
		btnPdf.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPdf.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnPdf.setBounds(511, 312, 89, 23);
		contentPane.add(btnPdf);
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(21, 369, 621, 158);
		contentPane.add(scrollPane);
		tblUsuarios = new JTable();
		tblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblUsuarios.getSelectedRow();
				fila = tblUsuarios.getSelectedRow();
				usuario = lista.get(fila);
				lblID.setText("" + lista.get(fila).getId());
				txtUser.setText(usuario.getUser());
				txtPassword.setText(usuario.getPassword());
				txtNombre.setText(usuario.getNombre());
				txtImagen.setText(usuario.getFoto());
			}
		});
		tblUsuarios.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblUsuarios);
		modelo.addColumn("ID");
		modelo.addColumn("USER");
		modelo.addColumn("PASSWORD");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("FOTO");
		tblUsuarios.setModel(modelo);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("IMAGEN:");
		lblNewLabel_1_2_1.setFont(new Font("Nirmala UI", Font.BOLD, 19));
		lblNewLabel_1_2_1.setBounds(25, 218, 86, 23);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtImagen = new JTextField();
		txtImagen.setEditable(false);
		txtImagen.setColumns(10);
		txtImagen.setBounds(121, 223, 246, 20);
		contentPane.add(txtImagen);
		
		btnSeleccionarI = new JButton("Seleccionar");
		btnSeleccionarI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filtro=new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)","jpg","jpeg");
				JFileChooser archivo =new JFileChooser();
				archivo.addChoosableFileFilter(filtro);
				archivo.setDialogTitle("Abrir Archivo");
				File ruta=new File("D:/usuarios");
				archivo.setCurrentDirectory(ruta);
				int ventana=archivo.showOpenDialog(null);
				if(ventana== JFileChooser.APPROVE_OPTION) {
										
					File file =archivo.getSelectedFile();
					txtImagen.setText(String.valueOf(file));
					java.awt.Image foto=getToolkit().getImage(txtImagen.getText());
					foto=foto.getScaledInstance(300, 300, java.awt.Image.SCALE_DEFAULT);
					lblFoto.setIcon(new ImageIcon(foto));
				}
			}
		});
		btnSeleccionarI.setFont(new Font("Imprint MT Shadow", Font.ITALIC, 17));
		btnSeleccionarI.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSeleccionarI.setBounds(395, 220, 106, 23);
		contentPane.add(btnSeleccionarI);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(391, 32, 251, 148);
		contentPane.add(lblFoto);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchUsuarios();
		for (Usuario u : lista) {
			Object o[] = new Object[5];
			o[0] = u.getId();
			o[1] = u.getUser();
			o[2] = u.getPassword();
			o[3] = u.getNombre();
			o[4] = u.getFoto();
			modelo.addRow(o);
		}
		tblUsuarios.setModel(modelo);
	}
}
