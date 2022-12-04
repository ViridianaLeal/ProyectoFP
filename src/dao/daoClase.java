package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Clase;
import modelo.Profesor;

public class daoClase {
	conexion cx = null;
	public daoClase() {
		cx = new conexion();
	}
	
	public boolean insertarClase(Clase user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO clase VALUES(null,?,?,?,?)");
			ps.setString(1, user.getClave());
			ps.setString(2, user.getProfesor());
			ps.setInt(3,user.getGrupo());
			ps.setString(4, user.getClase());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public ArrayList<Clase> buscar(String palabra) {
        ArrayList<Clase> lista2 = new ArrayList<Clase>();
        try {
            String sql = "SELECT * FROM clase WHERE "
                    + "(profesor LIKE ?) OR"
                    + "(grupo LIKE ?) OR "
                    + "(clave LIKE ?) OR "
                    + "(clase LIKE ?); ";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            //System.out.println("CONSULTA" + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Clase p = new Clase();
                p.setIdClase(rs.getInt("idClase"));
                p.setClave(rs.getString("clave"));
                p.setProfesor(rs.getString("profesor"));
                p.setGrupo(rs.getInt("grupo"));
                p.setClase(rs.getString("clase")); 
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

	public ArrayList<Clase> fetcClases() {
		ArrayList<Clase> lista = new ArrayList<Clase>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM clase");
			rs = ps.executeQuery();
			while (rs.next()) {
				Clase u = new Clase();
				u.setIdClase(rs.getInt("idClase"));
				 u.setClave(rs.getString("clave"));
                u.setProfesor(rs.getString("profesor"));
                u.setGrupo(rs.getInt("grupo"));
                u.setClase(rs.getString("clase")); 
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	

	public boolean EliminarClase(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM clase WHERE idClase=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarClase(Clase user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE clase SET clave=?,profesor=?,grupo=?,clase=? WHERE idClase=?");
			ps.setString(1, user.getClave());
			ps.setString(2, user.getProfesor());
			ps.setInt(3,user.getGrupo());
			ps.setString(4, user.getClase());
			ps.setInt(5, user.getIdClase());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}



