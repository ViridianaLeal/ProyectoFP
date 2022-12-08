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
			ps = cx.conectar().prepareStatement("INSERT INTO profesor VALUES(null,?,?,?,?,?)");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setInt(3,user.getClave());
			ps.setString(4, user.getCarrera());
			ps.setString(5,user.getFoto());
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
	
	public ArrayList<Profesor> buscar(String palabra) {
        ArrayList<Profesor> lista2 = new ArrayList<Profesor>();
        PreparedStatement ps = null;
		ResultSet rs = null;
        try {
            String sql = "SELECT * FROM profesor WHERE "
                    + "(nombre LIKE ?) OR "
                    + "(apellidos LIKE ?) OR"
                    + "(clave LIKE ?) OR "
                    + "(carrera LIKE ?) OR "
                    + "(foto LIKE ?); ";
            ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            ps.setString(5, "%" + palabra + "%");
            //System.out.println("CONSULTA" + ps.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setIdProfesor(rs.getInt("idProfesor"));
                p.setNombre(rs.getString("nombre"));
                p.setApellidos(rs.getString("apellidos"));
                p.setClave(rs.getInt("clave"));
                p.setCarrera(rs.getString("carrera"));              
                p.setFoto(rs.getString("foto"));
                lista2.add(p);
            }
            ps.close();
            ps = null;
            cx.desconectar();
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
                u.setFoto(rs.getString("foto"));
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

	public boolean editarProfesor(Profesor user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE profesor SET nombre=?,apellidos=?,clave=?,carrera=?,foto=? WHERE idProfesor=?");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setInt(3,user.getClave());
			ps.setString(4, user.getCarrera());
			ps.setString(5, user.getFoto());
			ps.setInt(6, user.getIdProfesor());
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
