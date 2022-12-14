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
			ps.setInt(3,user.getClase());
			ps.setString(4, user.getProfesor());
			ps.setString(5,user.getFecha());
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
	
	public ArrayList<Actividades> buscar(String palabra) {
        ArrayList<Actividades> lista2 = new ArrayList<Actividades>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql = "SELECT * FROM actividades WHERE "
                    + "(actividad LIKE ?) OR "
                    + "(asignatura LIKE ?) OR"
                    + "(clase LIKE ?) OR "
                    + "(profesor LIKE ?) OR "
                    + "(fecha LIKE ?); ";
            ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            ps.setString(5, "%" + palabra + "%");
            //System.out.println("CONSULTA" + ps.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Actividades p = new Actividades();
                p.setActividad(rs.getString("actividad"));
                p.setAsignatura(rs.getString("asignatura"));
                p.setClase(rs.getInt("clase"));
                p.setProfesor(rs.getString("profesor"));
                p.setFecha(rs.getString("fecha"));
                lista2.add(p);
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
            System.out.println("Error en BUSCAR");
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
        return lista2;

    }

	public ArrayList<Actividades> fetcActividades() {
		ArrayList<Actividades> lista = new ArrayList<Actividades>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM actividades");
			rs = ps.executeQuery();
			while (rs.next()) {
				Actividades u = new Actividades();
				u.setIdActividades(rs.getInt("idActividades"));
				u.setActividad(rs.getString("actividad"));
                u.setAsignatura(rs.getString("asignatura"));
                u.setClase(rs.getInt("clase"));
                u.setProfesor(rs.getString("profesor"));
                u.setFecha(rs.getString("fecha"));
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

	

	public boolean EliminarActividad(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM actividades WHERE idActividades=?");
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

	public boolean editarActividades(Actividades user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE actividades SET actividad=?,asignatura=?,clase=?,profesor=?,fecha=? WHERE idActividades=?");
			ps.setString(1, user.getActividad());
			ps.setString(2, user.getAsignatura());
			ps.setInt(3,user.getClase());
			ps.setString(4, user.getProfesor());
			ps.setString(5, user.getFecha());
			ps.setInt(6, user.getIdActividades());
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
