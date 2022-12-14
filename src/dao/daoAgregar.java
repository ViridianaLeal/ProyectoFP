package dao;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.AgregarCuenta;
import modelo.Usuario;

public class daoAgregar {
	protected static final boolean EliminarUsuario = false;
	conexion cx = null;
	FileInputStream fis;
	int longitudBytes;

	public  daoAgregar() {		
		cx = new conexion();
	}

	public boolean insertarUsuario(AgregarCuenta user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				ps.close();
				ps = null;
				cx.desconectar();
			} catch (SQLException e) {
				System.out.println("ERROR AL CERRAR EDITAR USUARIO");
				e.printStackTrace();
			}
		}

	}

	public ArrayList<AgregarCuenta> fetchAgregarCuentas() {
		ArrayList<AgregarCuenta> lista = new ArrayList<AgregarCuenta>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM usuario");
			rs = ps.executeQuery();
			while (rs.next()) {
				AgregarCuenta u = new AgregarCuenta();
				u.setId(rs.getInt("id"));
				u.setUser(rs.getString("user"));
				u.setPassword(rs.getString("password"));
				u.setNombre(rs.getString("nombre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				ps = null;
				rs.close();
				rs = null;
				cx.desconectar();
			} catch (SQLException e) {
				System.out.println("ERROR AL CERRAR EDITAR USUARIO");
				e.printStackTrace();
			}
		}
		return lista;
	}

	public boolean loginUsuario(AgregarCuenta user) {
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
		}finally {
			try {
				ps.close();
				ps = null;
				rs.close();
				rs = null;
				cx.desconectar();
			} catch (SQLException e) {
				System.out.println("ERROR AL CERRAR EDITAR USUARIO");
				e.printStackTrace();
			}
		}

	}

	public boolean EliminarUsuario(String U) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM usuario WHERE user=?");
			ps.setString(1, U);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				ps.close();
				ps = null;
				cx.desconectar();
			} catch (SQLException e) {
				System.out.println("ERROR AL CERRAR EDITAR USUARIO");
				e.printStackTrace();
			}
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