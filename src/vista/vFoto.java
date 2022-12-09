package vista;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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

import Decoder.BASE64Decoder;

import dao.daoCarrera;
import dao.daoFoto;

import modelo.Carrera;
import modelo.Clase;
import modelo.Foto;
import modelo.Usuario;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
	ImageIcon imgOri = null;
	String imagenActual = "";
	int index = -1;

	private JButton btnNewButton;

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
		lblIdFoto.setText(imagenActual);
	}

	public vFoto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vFoto.class.getResource("/img/DeoClass.png")));
		setTitle("AGREGAR FOTOS");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 534, 526);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID FOTO");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel.setBounds(22, 21, 102, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("FOTO");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 57, 91, 31);
		contentPane.add(lblNewLabel_1);

		lblIdFoto = new JLabel("0");
		lblIdFoto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdFoto.setBounds(143, 19, 115, 18);
		contentPane.add(lblIdFoto);

		lblFoto = new JLabel("");
		lblFoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFoto.setBounds(134, 57, 124, 134);
		contentPane.add(lblFoto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 246, 498, 183);
		contentPane.add(scrollPane);

		tblFotos = new JTable();
		tblFotos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblFotos.getSelectedRow();
				foto = lista.get(fila);
				lblIdFoto.setText("" + lista.get(fila).getIdFoto());
				ImageIcon img = base64ToImage(foto.getFoto());
				java.awt.Image image = img.getImage();
				java.awt.Image newimg = image.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				ImageIcon i = new ImageIcon(newimg);
				lblFoto.setIcon(i);
			}
		});
		tblFotos.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, { null, null }, },
				new String[] { "New column", "New column" }));
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
					user.setFoto(imagenActual);
					System.out.print(""+imagenActual);
					if (dao.insertarFoto(user)) {
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
		btnAgregar.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-más-2-matemáticas-30.png")));
		btnAgregar.setBounds(77, 441, 30, 30);
		contentPane.add(btnAgregar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					foto.setFoto(imagenActual);
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
		btnEliminar.setIcon(new ImageIcon(vFoto.class.getResource("/img/eli.png")));
		btnEliminar.setBounds(240, 441, 30, 30);
		contentPane.add(btnEliminar);

		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIdFoto.setText("");
				lblFoto.setText(imagenActual);
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
		            File temp = new File(System.getProperty("java.io.tmpdir") + "ReporteFotos.pdf");
		            InputStream flujoEntrada = this.getClass().getResourceAsStream("/pdf/ReporteFotos.pdf");
		            FileOutputStream flujoSalida = new FileOutputStream(temp);         
					archivo = new FileOutputStream(temp);
					Document doc = new Document();
					PdfWriter.getInstance(doc, archivo);
					doc.open();
					java.awt.Image img2 = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/img/DeoClass.png"));
					Image img = Image.getInstance(getClass().getResource("/img/DeoClass.png"));
					img.setAlignment(Element.ALIGN_CENTER);
					img.scaleToFit(200, 200);
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
					Desktop.getDesktop().open(temp);
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

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(vFoto.class.getResource("/img/icons8-foto-30.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
		        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
		        selector.setFileFilter(filtroImagen);
		        int r = selector.showOpenDialog(null);
		        if (r == JFileChooser.APPROVE_OPTION) {
		            try {
		                File f = selector.getSelectedFile();
		                ImageIcon img = new ImageIcon(selector.getSelectedFile().toURL());
		                imgOri = img;
		                java.awt.Image image = img.getImage(); // transform it
		                java.awt.Image newimg = image.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), java.awt.Image.SCALE_SMOOTH);
		                URL urlImage = selector.getSelectedFile().toURL();
		                imagenActual = convetirImagen(urlImage);
		                System.out.print(imagenActual);
		                lblFoto.setIcon(new ImageIcon(newimg));
		            } catch (MalformedURLException ex) {
		                Logger.getLogger(vFoto.class.getName()).log(Level.SEVERE, null, ex);
		            }
				}
			}
		});
		btnNewButton.setBounds(276, 157, 30, 31);
		contentPane.add(btnNewButton);
	}

	public ImageIcon base64ToImage(String base64) {
		ImageIcon image = null;
		try {
			byte[] imageByte;
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(base64);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			BufferedImage bufferedImage = ImageIO.read(bis);
			image = new ImageIcon(bufferedImage);
			bis.close();
		} catch (IOException ex) {
			Logger.getLogger(vFoto.class.getName()).log(Level.SEVERE, null, ex);
		}
		return image;
	}

	public String convetirImagen(URL url) {
		String base64 = "";
		try {
			BufferedImage bImage = ImageIO.read(new File(url.getPath()));
			BufferedImage img = resize(bImage, 100, 100);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", bos);
			byte[] data = bos.toByteArray();
			base64 = Base64.getEncoder().encodeToString(data);
		} catch (MalformedURLException ex) {
			Logger.getLogger(vFoto.class.getName()).log(Level.SEVERE, null, ex);

		} catch (IOException ex) {
			Logger.getLogger(vFoto.class.getName()).log(Level.SEVERE, null, ex);
		}
		return base64;
	}

	public BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
		Graphics2D g = bufim.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return bufim;
	}

	public void actualizarTabla() {
		DefaultTableModel modeloTabla = new DefaultTableModel() {
			@Override // Redefinimos el método getColumnClass
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Object.class;
				case 1:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};
		modeloTabla.addColumn("ID FOTO");
		modeloTabla.addColumn("FOTO");
		lista = dao.fetcFotos();
		for (Foto a : lista) {
			Object[] columna = new Object[2];
			columna[0] = a.getIdFoto();
			columna[1] = base64ToImage(a.getFoto());
			modeloTabla.addRow(columna);
		}
		tblFotos.setModel(modeloTabla);
	}

}
