package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.swing.JButton;
import java.awt.Font;
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

import dao.daoUsuario;
import modelo.Profesor;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JLabel lblNewLabel_2;
	private JTextField txtBuscar;

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
	}

	public vUsuario() {
		setFont(new Font("Arial", Font.ITALIC, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(vUsuario.class.getResource("/img/logoDeo.png")));
		setTitle("USUARIO");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 566, 503);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel.setBounds(25, 32, 46, 23);
		contentPane.add(lblNewLabel);
		lblID = new JLabel("1");
		lblID.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblID.setBounds(164, 32, 46, 22);
		contentPane.add(lblID);
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1.setBounds(25, 66, 86, 21);
		contentPane.add(lblNewLabel_1);
		txtUser = new JTextField();
		txtUser.setBounds(164, 67, 169, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(25, 100, 129, 23);
		contentPane.add(lblNewLabel_1_1);
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(164, 97, 169, 20);
		contentPane.add(txtPassword);
		JLabel lblNewLabel_1_2 = new JLabel("NOMBRE");
		lblNewLabel_1_2.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(25, 131, 86, 23);
		contentPane.add(lblNewLabel_1_2);
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(164, 136, 169, 20);
		contentPane.add(txtNombre);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setVerticalAlignment(SwingConstants.TOP);
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBorder(null);
		btnAgregar.setBorderPainted(false);
		btnAgregar.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnAgregar.setBackground(new Color(43, 81, 111));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
		btnAgregar.setFont(new Font("Calibri Light", Font.BOLD, 18));
		btnAgregar.setBounds(47, 187, 95, 23);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("EDITAR");
		btnEditar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBorder(null);
		btnEditar.setBorderPainted(false);
		btnEditar.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnEditar.setBackground(new Color(43, 81, 111));
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
		btnEditar.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnEditar.setBounds(283, 187, 89, 23);
		contentPane.add(btnEditar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBorder(null);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnEliminar.setBackground(new Color(43, 81, 111));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int opcion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE ELIMINAR ESTE USUARIO?",
							"ELIMINAR USUARIO", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.EliminarUsuario(lista.get(fila).getId())) {
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
		btnEliminar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEliminar.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnEliminar.setBounds(159, 187, 103, 23);
		contentPane.add(btnEliminar);

		btnPdf = new JButton("PDF");
		btnPdf.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPdf.setForeground(Color.WHITE);
		btnPdf.setBorder(null);
		btnPdf.setBorderPainted(false);
		btnPdf.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		btnPdf.setBackground(new Color(43, 81, 111));
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream archivo;
					URI uri = new URI(getClass().getResource("/pdf/ReporteUsuarios.pdf").toString());
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
					p.add("CATALOGO DE USUARIOS");
					p.add(Chunk.NEWLINE);
					p.add(Chunk.NEWLINE);
					p.setAlignment(Element.ALIGN_CENTER);
					doc.add(p);
					PdfPTable tabla = new PdfPTable(4);
					tabla.setWidthPercentage(100);
					PdfPCell c1 = new PdfPCell(new Phrase(" ID USUARIO", negrita));
					PdfPCell c2 = new PdfPCell(new Phrase(" USUARIO", negrita));
					PdfPCell c3 = new PdfPCell(new Phrase(" PASSWORD", negrita));
					PdfPCell c4 = new PdfPCell(new Phrase(" NOMBRE", negrita));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					c3.setHorizontalAlignment(Element.ALIGN_CENTER);
					c4.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.GRAY);
					c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
					c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
					tabla.addCell(c1);
					tabla.addCell(c2);
					tabla.addCell(c3);
					tabla.addCell(c4);

					for (Usuario u : lista) {
						tabla.addCell("" + u.getId());
						tabla.addCell(u.getUser());
						tabla.addCell(u.getPassword());
						tabla.addCell(u.getNombre());

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
		btnPdf.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPdf.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnPdf.setBounds(404, 187, 89, 23);
		contentPane.add(btnPdf);
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(22, 240, 503, 158);
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
		tblUsuarios.setModel(modelo);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(vUsuario.class.getResource("/img/icons8-buscar-cliente-30.png")));
		lblNewLabel_2.setBounds(164, 415, 30, 30);
		contentPane.add(lblNewLabel_2);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());

			}
		});
		txtBuscar.setBounds(204, 422, 221, 23);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.fetchUsuarios();
		for (Usuario u : lista) {
			Object o[] = new Object[4];
			o[0] = u.getId();
			o[1] = u.getUser();
			o[2] = u.getPassword();
			o[3] = u.getNombre();
			modelo.addRow(o);
		}
		tblUsuarios.setModel(modelo);
	}

	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.buscar(palabra);
		for (Usuario p : lista) {
			Object item[] = new Object[4];
			item[0] = p.getId();
			item[1] = p.getUser();
			item[2] = p.getPassword();
			item[3] = p.getNombre();

			modelo.addRow(item);
		}
		tblUsuarios.setModel(modelo);

	}

}
