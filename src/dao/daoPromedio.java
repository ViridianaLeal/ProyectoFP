package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Promedio;

public class daoPromedio {
	conexion cx = null;
	public daoPromedio() {
		cx = new conexion();
	}
	
	public boolean insertarPromedio(Promedio user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO promedio VALUES(null,?,?,?,?,?,?,?)");
			ps.setString(1, user.getAlumno());
			ps.setString(2, user.getProfesor());
			ps.setString(3, user.getSemestre ());
			ps.setString(4, user.getCarrera());
			ps.setInt(5, user.getGrupo());
			ps.setString(6, user.getAsignaturas());
			ps.setDouble(7, user.getPromedio());
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

	public ArrayList<Promedio> buscar(String palabra) {
		ArrayList<Promedio> lista2 = new ArrayList<Promedio>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM promedio WHERE "
		            + "(idPromedio LIKE ?) OR " 
					+ "(alumno LIKE ?) OR"
					+ "(profesor LIKE ?) OR " 
					+ "(semestre LIKE ?) OR "
					+ "(carrera LIKE ?) OR "
					+ "(grupo LIKE ?) OR "
					+ "(asignatura LIKE ?) OR " 
					+ "(promedio LIKE ?); ";
			ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");
			ps.setString(6, "%" + palabra + "%");
			ps.setString(7, "%" + palabra + "%");
			ps.setString(8, "%" + palabra + "%");
			// System.out.println("CONSULTA" + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Promedio p = new Promedio();
				p.setIdPromedio(rs.getInt("idPromedio"));
				p.setAlumno(rs.getString("alumno"));
				p.setProfesor(rs.getString("profesor"));
				p.setSemestre(rs.getString("semestre"));
				p.setCarrera(rs.getString("carrera"));
				p.setGrupo(rs.getInt("grupo"));
				p.setAsignaturas(rs.getString("asignatura"));
				p.setPromedio(rs.getDouble("promedio"));
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
				rs=null;
				cx.desconectar();
			} catch (SQLException e) {
				System.out.println("ERROR AL CERRAR EDITAR USUARIO");
				e.printStackTrace();
			}
		}
		return lista2;

	}

	public ArrayList<Promedio> fechsPromedios() {
		ArrayList<Promedio> lista = new ArrayList<Promedio>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM promedio");
			rs = ps.executeQuery();
			while (rs.next()) {
				Promedio u = new Promedio();
				u.setIdPromedio(rs.getInt("idPromedio"));
				u.setAlumno(rs.getString("alumno"));
				u.setProfesor(rs.getString("profesor"));
				u.setSemestre(rs.getString("semestre"));
				u.setCarrera(rs.getString("carrera"));
				u.setGrupo(rs.getInt("grupo"));
				u.setAsignaturas(rs.getString("asignatura"));
				u.setPromedio(rs.getDouble("promedio"));
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
				rs=null;
				cx.desconectar();
			} catch (SQLException e) {
				System.out.println("ERROR AL CERRAR EDITAR USUARIO");
				e.printStackTrace();
			}
		}
		return lista;
	}

	public boolean EliminarPromedio(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM promedio WHERE idPromedio=?");
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

	public boolean editarPromedio(Promedio user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE promedio SET alumno=?,profesor=?,semestre=?,carrera=?,grupo=?,asignatura=?,promedio=? WHERE idPromedio=?");
			ps.setString(1, user.getAlumno());
			ps.setString(2, user.getProfesor());
			ps.setString(3, user.getSemestre ());
			ps.setString(4, user.getCarrera());
			ps.setInt(5, user.getGrupo());
			ps.setString(6, user.getAsignaturas());
			ps.setDouble(7, user.getIdPromedio());
			ps.setInt(8, user.getIdPromedio());
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






