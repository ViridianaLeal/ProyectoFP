package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Carrera;
import modelo.Plantel;

public class daoPlantel {
	conexion cx = null;
	public daoPlantel() {
		cx = new conexion();		
	}
	
	public boolean insertarPlantel(Plantel user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO plantel VALUES(null,?)");
			ps.setString(1, user.getPlantel());	
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Plantel> fetchPlantels() {
		ArrayList<Plantel> lista = new ArrayList<Plantel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM plantel");
			rs = ps.executeQuery();
			while (rs.next()) {
				Plantel u = new Plantel();
				u.setIdPlantel(rs.getInt("idPlantel"));
				u.setPlantel(rs.getString("plantel"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	

	public boolean EliminarPlantel(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM plantel WHERE idPlantel=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarPlantel(Plantel user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE plantel SET plantel=? WHERE idPlantel=?");
			ps.setString(1, user.getPlantel());
			ps.setInt(2, user.getIdPlantel());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	
}


