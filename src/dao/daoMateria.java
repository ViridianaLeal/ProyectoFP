package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Materias;
import modelo.Semestre;

public class daoMateria {
	conexion cx = null;
	public daoMateria() {
		cx = new conexion();		
	}
	
	public boolean insertarMateria(Materias user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO materias VALUES(null,?)");
			ps.setString(1, user.getMateria());	
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Materias> fetchMaterias() {
		ArrayList<Materias> lista = new ArrayList<Materias>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM materias");
			rs = ps.executeQuery();
			while (rs.next()) {
				Materias u = new Materias();
				u.setIdMateria(rs.getInt("idMateria"));
				u.setMateria(rs.getString("materia"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	

	public boolean EliminarMateria(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM materias WHERE idMateria=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarMateria(Materias user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE materias SET materia=? WHERE idMateria=?");
			ps.setString(1, user.getMateria());
			ps.setInt(2, user.getIdMateria());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	
}






