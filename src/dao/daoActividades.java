package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Actividades;

public class daoActividades {
	conexion cx = null;
	
	public daoActividades() {
		cx = new conexion();
	}
	
	public boolean insertarActividad(Actividades user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO actividades VALUES(null,?,?,?,?,?)");
			ps.setString(1, user.getActividad());
			ps.setString(2, user.getAsignatura());
			ps.setString(3,user.getClase());
			ps.setString(4, user.getProfesor());
			ps.setString(5,user.getFecha());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public ArrayList<Actividades> buscar(String palabra) {
        ArrayList<Actividades> lista2 = new ArrayList<Actividades>();
        try {
            String sql = "SELECT * FROM actividades WHERE "
                    + "(actividad LIKE ?) OR "
                    + "(asignatura LIKE ?) OR"
                    + "(clase LIKE ?) OR "
                    + "(profesor LIKE ?) OR "
                    + "(fecha LIKE ?); ";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            ps.setString(5, "%" + palabra + "%");
            //System.out.println("CONSULTA" + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Actividades p = new Actividades();
                p.setActividad(rs.getString("actividad"));
                p.setAsignatura(rs.getString("asignatura"));
                p.setClase(rs.getString("clase"));
                p.setProfesor(rs.getString("profesor"));
                p.setFecha(rs.getString("fecha"));
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

	public ArrayList<Actividades> fetcActividades() {
		ArrayList<Actividades> lista = new ArrayList<Actividades>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM actividades");
			rs = ps.executeQuery();
			while (rs.next()) {
				Actividades u = new Actividades();
				u.setIdActividades(rs.getInt("idActividad"));
				u.setActividad(rs.getString("actividad"));
                u.setAsignatura(rs.getString("asignatura"));
                u.setClase(rs.getString("clase"));
                u.setProfesor(rs.getString("profesor"));
                u.setFecha(rs.getString("fecha"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	

	public boolean EliminarActividad(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM actividades WHERE idActividad=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarActividades(Actividades user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE actividades SET actividad=?,asignatura=?,clase=?,profesor=?,fecha=? WHERE idActividad=?");
			ps.setString(1, user.getActividad());
			ps.setString(2, user.getAsignatura());
			ps.setString(3,user.getClase());
			ps.setString(4, user.getProfesor());
			ps.setString(5, user.getFecha());
			ps.setInt(6, user.getIdActividades());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
