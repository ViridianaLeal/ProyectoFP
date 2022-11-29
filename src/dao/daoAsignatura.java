package dao;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.conexion;
import modelo.Asignatura;
import modelo.Usuario;

public class daoAsignatura {
	conexion cx = null;
	FileInputStream fis;
	int longitudBytes;

	public daoAsignatura() {
		cx = new conexion();
	}

	public boolean insertarAsignatura(Asignatura user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO asignarura VALUES(null,?,?,?)");
			ps.setInt(1, user.getIDprofesor());
			ps.setInt(2, user.getIDasignatura());
			ps.setString(3, user.getAsignatura());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Asignatura> fetchAsignatura() {
		ArrayList<Asignatura> lista = new ArrayList<Asignatura>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM asignatura");
			rs = ps.executeQuery();
			while (rs.next()) {
				Asignatura u = new Asignatura();
				u.setIDprofesor(rs.getInt("idprofesor"));
				u.setIDasignatura(rs.getInt("idasignatura"));
				u.setAsignatura(rs.getString("asignatura"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarAsignatura(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM Asignatura WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarAsignatura(Asignatura user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE usuario SET asignatura=? WHERE idprofesor=?, idasignatura");
			ps.setInt(1, user.getIDprofesor());
			ps.setInt(3, user.getIDasignatura());
			ps.setString(4, user.getAsignatura());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}