package dao;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Alumno;
import modelo.Profesor;
import modelo.Usuario;

public class daoProfesor {
	conexion cx = null;

	public daoProfesor() {
		cx = new conexion();
	}

	public boolean insertarProfesor(Profesor user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO profesor VALUES(null,?,?,?,?,?,?)");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setInt(3,user.getClave());
			ps.setString(4, user.getCarrera());
			ps.setString(5,user.getAsignatura());
			ps.setInt(6, user.getGrupos());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public ArrayList<Profesor> buscar(String palabra) {
        ArrayList<Profesor> lista2 = new ArrayList<Profesor>();
        try {
            String sql = "SELECT * FROM profesor WHERE "
                    + "(nombre LIKE ?) OR "
                    + "(apellidos LIKE ?) OR"
                    + "(clave LIKE ?) OR "
                    + "(carrera LIKE ?) OR "
                    + "(asignatura LIKE ?) OR "
                    + "(grupo LIKE ?); ";
            PreparedStatement ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            ps.setString(5, "%" + palabra + "%");
            ps.setString(6, "%" + palabra + "%");
            //System.out.println("CONSULTA" + ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setIdProfesor(rs.getInt("idProfesor"));
                p.setNombre(rs.getString("nombre"));
                p.setApellidos(rs.getString("apellidos"));
                p.setClave(rs.getInt("clave"));
                p.setCarrera(rs.getString("carrera"));              
                p.setAsignatura(rs.getString("asignatura"));
                p.setGrupos(rs.getInt("grupo"));
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

	public ArrayList<Profesor> fetcProfesors() {
		ArrayList<Profesor> lista = new ArrayList<Profesor>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM profesor");
			rs = ps.executeQuery();
			while (rs.next()) {
				Profesor u = new Profesor();
				u.setIdProfesor(rs.getInt("idProfesor"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setClave(rs.getInt("clave"));
                u.setCarrera(rs.getString("carrera"));              
                u.setAsignatura(rs.getString("asignatura"));
                u.setGrupos(rs.getInt("grupo"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	

	public boolean EliminarProfesor(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM profesor WHERE idProfesor=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarProfesor(Profesor user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE profesor SET nombrel=?,apellidos=?,clave=?,carrera=?,asignatura=?,grupo=? WHERE idProfesor=?");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setInt(3,user.get());
			ps.setString(4, user.getSemestre());
			ps.setString(5,user.getCarrera());
			ps.setInt(6, user.getGrupo());
			ps.setString(7, user.getNombre());
			ps.setString(8, user.getApellidos());
			ps.setInt(9,user.getIdalumno());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}
