package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.Consulta;

public class daoConsulta {
	conexion cx = null;

	public daoConsulta() {
		cx = new conexion();
	}

	public boolean insertarComentario(Consulta user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO consulta VALUES(null,?,?,?)");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getComentario());
			ps.setString(3, user.getDestino());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Consulta> fetchConsultas() {
		ArrayList<Consulta> lista = new ArrayList<Consulta>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM consulta");
			rs = ps.executeQuery();
			while (rs.next()) {
				Consulta u = new Consulta();
				u.setID(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setComentario(rs.getString("comentario"));
				u.setDestino(rs.getString("destino"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarComentario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM consulta WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarConsulta(Consulta user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE consulta SET nombre=?,comentario=?,destino=? WHERE id=?");
			ps.setInt(1, user.getID());
			ps.setString(2, user.getNombre());
			ps.setString(3, user.getComentario());
			ps.setString(4, user.getDestino());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}