package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Alumno;
import modelo.Calificaciones;

public class daoCalificaciones {
	conexion cx = null;

	public daoCalificaciones() {
		cx = new conexion();
		
	}
	
	public boolean insertarCalificacion(Calificaciones user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO calificaciones VALUES(null,?,?,?,?,?,?,?)");
			ps.setString(1, user.getAlumno());
			ps.setString(2, user.getProfesor());
			ps.setString(3, user.getSemestre ());
			ps.setString(4, user.getCarrera());
			ps.setInt(5, user.getGrupo());
			ps.setString(6, user.getAsignatura());
			ps.setDouble(7, user.getCalificaciones());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Calificaciones> buscar(String palabra) {
		ArrayList<Calificaciones> lista2 = new ArrayList<Calificaciones>();
		try {
			String sql = "SELECT * FROM calificaciones WHERE "
		            + "(idCalificaciones LIKE ?) OR " 
					+ "(alumno LIKE ?) OR"
					+ "(profesor LIKE ?) OR " 
					+ "(semestre LIKE ?) OR "
					+ "(carrera LIKE ?) OR "
					+ "(grupo LIKE ?) OR "
					+ "(asignatura LIKE ?) OR " 
					+ "(calificaciones LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");
			ps.setString(6, "%" + palabra + "%");
			ps.setString(7, "%" + palabra + "%");
			ps.setString(8, "%" + palabra + "%");
			// System.out.println("CONSULTA" + ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Calificaciones p = new Calificaciones();
				p.setIdCalificaciones(rs.getInt("idCalificaciones"));
				p.setAlumno(rs.getString("alumno"));
				p.setProfesor(rs.getString("profesor"));
				p.setSemestre(rs.getString("semestre"));
				p.setCarrera(rs.getString("carrera"));
				p.setGrupo(rs.getInt("grupo"));
				p.setAsignatura(rs.getString("asignatura"));
				p.setCalificaciones(rs.getDouble("calificaciones"));
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

	public ArrayList<Calificaciones> feCalificaciones() {
		ArrayList<Calificaciones> lista = new ArrayList<Calificaciones>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM calificaciones");
			rs = ps.executeQuery();
			while (rs.next()) {
				Calificaciones u = new Calificaciones();
				u.setIdCalificaciones(rs.getInt("idCalificaciones"));
				u.setAlumno(rs.getString("alumno"));
				u.setProfesor(rs.getString("profesor"));
				u.setSemestre(rs.getString("semestre"));
				u.setCarrera(rs.getString("carrera"));
				u.setGrupo(rs.getInt("grupo"));
				u.setAsignatura(rs.getString("asignatura"));
				u.setCalificaciones(rs.getDouble("calificaciones"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarCalificacion(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM calificaciones WHERE idCalificaciones=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarCalificaciones(Calificaciones user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE calificaciones SET alumno=?,profesor=?,semestre=?,carrera=?,grupo=?,asignatura=?,calificaciones=? WHERE idCalificaciones=?");
			ps.setString(1, user.getAlumno());
			ps.setString(2, user.getProfesor());
			ps.setString(3, user.getSemestre ());
			ps.setString(4, user.getCarrera());
			ps.setInt(5, user.getGrupo());
			ps.setString(6, user.getAsignatura());
			ps.setDouble(7, user.getCalificaciones());
			ps.setInt(8, user.getIdCalificaciones());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}



