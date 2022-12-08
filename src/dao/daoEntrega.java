package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Actividades;
import modelo.Entrega;

public class daoEntrega {
	conexion cx = null;
	
	public daoEntrega() {
		cx = new conexion();
	}
	
	public boolean insertarEntrega(Entrega user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO entrega VALUES(null,?,?,?,?)");
			ps.setString(1,user.getClase());
			ps.setString(2, user.getActividad());
			ps.setString(3,user.getFecha());
			ps.setString(4, user.getEntrega());
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
	
	

	public ArrayList<Entrega> fetcEntregas() {
		ArrayList<Entrega> lista = new ArrayList<Entrega>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM entrega");
			rs = ps.executeQuery();
			while (rs.next()) {
				Entrega u = new Entrega();
				u.setIdEntrega(rs.getInt("idEntrega"));
				u.setClase(rs.getString("clase"));
				u.setActividad(rs.getString("actividad"));
                u.setFecha(rs.getString("fecha"));
                u.setEntrega(rs.getString("entrega"));
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

	

	public boolean EliminarEntrega(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM entrega WHERE idEntrega=?");
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

	public boolean editarEntrega(Entrega user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE entrega SET clase=?,actividad=?,fecha=?,entrega=? WHERE idEntrega=?");
			ps.setString(1,user.getClase());
			ps.setString(2, user.getActividad());
			ps.setString(3, user.getFecha());
			ps.setString(4, user.getEntrega());
			ps.setInt(5, user.getIdEntrega());
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
