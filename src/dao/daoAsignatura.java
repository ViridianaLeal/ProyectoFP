package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Alumno;
import modelo.Asignatura;

public class daoAsignatura {
	conexion cx = null;
	public daoAsignatura() {
		cx = new conexion();
	}
	
	public boolean insertarAsignatura(Asignatura user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO asignatura  VALUES(null,?)");
			ps.setString(1, user.getAsignatura());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	

	

	public ArrayList<Asignatura> fetcAsignaturas() {
		ArrayList<Asignatura> lista = new ArrayList<Asignatura>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM asignatura");
			rs = ps.executeQuery();
			while (rs.next()) {
				Asignatura u = new Asignatura();
				u.setIdAsignatura(rs.getInt("idAsignatura"));
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
			ps = cx.conectar().prepareStatement("DELETE  FROM asignatura WHERE idAsignatura=?");
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
			ps = cx.conectar().prepareStatement("UPDATE asignatura SET asignatura=? WHERE idAsignatura=?");
			ps.setString(1, user.getAsignatura());
			ps.setInt(2, user.getIdAsignatura());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
