package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Carrera;
import modelo.Foto;
import modelo.Usuario;

public class daoFoto {
	conexion cx = null;
	public daoFoto() {
		cx = new conexion();
	}
	
	public boolean insertarFoto(Foto user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO foto VALUES(null,?)");
			ps.setString(1, user.getFoto());	
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

	public ArrayList<Foto> fetcFotos() {
		ArrayList<Foto> lista = new ArrayList<Foto>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM foto");
			rs = ps.executeQuery();
			while (rs.next()) {
				Foto u = new Foto();
				u.setIdFoto(rs.getInt("idFoto"));
				u.setFoto(rs.getString("Foto"));
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

	public boolean EliminarFoto(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM foto WHERE idFoto=?");
			ps.setInt(1, Id);
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

	public boolean editarFoto(Foto user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE foto SET foto=? WHERE idFoto=?");
			ps.setString(1, user.getFoto());
			ps.setInt(2, user.getIdFoto());
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

	
}

	
