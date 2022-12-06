package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Calificacion;
import modelo.Promedio;

public class daoCalif {
	conexion cx = null;
	public daoCalif() {
		cx = new conexion();
	}
	
	public boolean insertarCal(Calificacion user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO cali VALUES(null,?,?,?,?,?,?,?)");
			ps.setString(1, user.getAlumno());
			ps.setString(2, user.getProfesor());
			ps.setString(3, user.getSemestre ());
			ps.setString(4, user.getCarrera());
			ps.setInt(5, user.getGrupo());
			ps.setString(6, user.getAsignatura());
			ps.setDouble(7, user.getCalificacio());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Calificacion> buscar(String palabra) {
		ArrayList<Calificacion> lista2 = new ArrayList<Calificacion>();
		try {
			String sql = "SELECT * FROM cali WHERE "
		            + "(idCali LIKE ?) OR " 
					+ "(alumno LIKE ?) OR"
					+ "(profesor LIKE ?) OR " 
					+ "(semestre LIKE ?) OR "
					+ "(carrera LIKE ?) OR "
					+ "(grupo LIKE ?) OR "
					+ "(asignatura LIKE ?) OR " 
					+ "(calificacion LIKE ?); ";
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
				Calificacion p = new Calificacion();
				p.setIdCali(rs.getInt("idCali"));
				p.setAlumno(rs.getString("alumno"));
				p.setProfesor(rs.getString("profesor"));
				p.setSemestre(rs.getString("semestre"));
				p.setCarrera(rs.getString("carrera"));
				p.setGrupo(rs.getInt("grupo"));
				p.setAsignatura(rs.getString("asignatura"));
				p.setCalificacio(rs.getDouble("calificacion"));
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

	public ArrayList<Calificacion> fechsCalificacions() {
		ArrayList<Calificacion> lista = new ArrayList<Calificacion>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM cali");
			rs = ps.executeQuery();
			while (rs.next()) {
				Calificacion u = new Calificacion();
				u.setIdCali(rs.getInt("idCali"));
				u.setAlumno(rs.getString("alumno"));
				u.setProfesor(rs.getString("profesor"));
				u.setSemestre(rs.getString("semestre"));
				u.setCarrera(rs.getString("carrera"));
				u.setGrupo(rs.getInt("grupo"));
				u.setAsignatura(rs.getString("asignatura"));
				u.setCalificacio(rs.getDouble("calificacion"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarCali(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM cali WHERE idCali=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarCali(Calificacion user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE cali SET alumno=?,profesor=?,semestre=?,carrera=?,grupo=?,asignatura=?,calificacion=? WHERE idCali=?");
			ps.setString(1, user.getAlumno());
			ps.setString(2, user.getProfesor());
			ps.setString(3, user.getSemestre ());
			ps.setString(4, user.getCarrera());
			ps.setInt(5, user.getGrupo());
			ps.setString(6, user.getAsignatura());
			ps.setDouble(7, user.getCalificacio());
			ps.setDouble(8, user.getIdCali());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
