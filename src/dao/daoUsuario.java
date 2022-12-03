package dao;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.Profesor;
import modelo.Usuario;

public class daoUsuario {
	conexion cx = null;
	FileInputStream fis;
	int longitudBytes;

	public daoUsuario() {
		cx = new conexion();
	}

	public boolean insertarUsuario(Usuario user) {      
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public ArrayList<Usuario> buscar(String palabra) {
        ArrayList<Usuario> lista2 = new ArrayList<Usuario>();
        try {
            String sql = "SELECT * FROM usuario WHERE "
                    + "(id LIKE ?) OR "
                    + "(user LIKE ?) OR"
                    + "(password LIKE ?) OR "
                    + "(nombre LIKE ?); ";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            //System.out.println("CONSULTA" + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario p = new Usuario();
                p.setId(rs.getInt("id"));
                p.setUser(rs.getString("user"));
                p.setPassword(rs.getString("password"));
                p.setNombre(rs.getString("nombre"));               
                lista2.add(p);
            }
            ps.close();
            ps = null;
            cx.desconectar();
        } catch (SQLException ex) {
        	ex.printStackTrace();
            System.out.println("Error en BUSCAR");
        }
        return lista2;

    }


	public ArrayList<Usuario> fetchUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM usuario");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setUser(rs.getString("user"));
				u.setPassword(rs.getString("password"));
				u.setNombre(rs.getString("nombre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean loginUsuario(Usuario user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM usuario WHERE user=? AND password=?");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean EliminarUsuario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM usuario WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarUsuario(Usuario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE usuario SET user=?,password=?,nombre=? WHERE id=?");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();

		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}
}