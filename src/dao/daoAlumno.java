package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Alumno;



public class daoAlumno {
	conexion cx = null;

	public daoAlumno() {
		cx = new conexion();
	}

	public boolean insertarAlumno(Alumno user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO alumno  VALUES(null,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, user.getNumerocontrol());
			ps.setInt(2, user.getPlantel());
			ps.setString(3, user.getTurno());
			ps.setInt(4, user.getSemestre());
			ps.setInt(5, user.getCarrera());
			ps.setInt(6, user.getGrupo());
			ps.setString(7, user.getNombre());
			ps.setString(8, user.getApellidos());
			ps.setString(9, user.getImagen());
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
	
	

	public ArrayList<Alumno> buscar(String palabra) {
		ArrayList<Alumno> lista2 = new ArrayList<Alumno>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM alumno WHERE " + "(idAlumno LIKE ?) OR " + "(numerocontrol LIKE ?) OR"
					+ "(plantel LIKE ?) OR " + "(turno LIKE ?) OR " + "(semestre LIKE ?) OR " + "(carrera LIKE ?) OR "
					+ "(grupo LIKE ?) OR " + "(nombre LIKE ?) OR " + "(apellido LIKE ?) OR " + "(foto LIKE ?); ";
			ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");
			ps.setString(6, "%" + palabra + "%");
			ps.setString(7, "%" + palabra + "%");
			ps.setString(8, "%" + palabra + "%");
			ps.setString(9, "%" + palabra + "%");
			ps.setString(10, "%" + palabra + "%");
			// System.out.println("CONSULTA" + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Alumno p = new Alumno();
				p.setIdalumno(rs.getInt("idAlumno"));
				p.setNumerocontrol(rs.getInt("numerocontrol"));
				p.setPlantel(rs.getInt("plantel"));
				p.setTurno(rs.getString("turno"));
				p.setSemestre(rs.getInt("semestre"));
				p.setCarrera(rs.getInt("carrera"));
				p.setGrupo(rs.getInt("grupo"));
				p.setNombre(rs.getString("nombre"));
				p.setApellidos(rs.getString("apellido"));
				p.setImagen(rs.getString("foto"));
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

	public ArrayList<Alumno> fetcAlumnos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM alumno");
			rs = ps.executeQuery();
			while (rs.next()) {
				Alumno u = new Alumno();
				u.setIdalumno(rs.getInt("idAlumno"));
				u.setNumerocontrol(rs.getInt("numerocontrol"));
				u.setPlantel(rs.getInt("plantel"));
				u.setTurno(rs.getString("turno"));
				u.setSemestre(rs.getInt("semestre"));
				u.setCarrera(rs.getInt("carrera"));
				u.setGrupo(rs.getInt("grupo"));
				u.setNombre(rs.getString("nombre"));
				u.setApellidos(rs.getString("apellido"));
				u.setImagen(rs.getString("foto"));
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

	public boolean EliminarAlumno(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE  FROM alumno WHERE idAlumno=?");
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

	public boolean editarAlumno(Alumno user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE alumno SET numerocontrol=?,plantel=?,turno=?,semestre=?,carrera=?,grupo=?,nombre=?,apellido=?,foto=? WHERE idAlumno=?");
			ps.setInt(1, user.getNumerocontrol());
			ps.setInt(2, user.getPlantel());
			ps.setString(3, user.getTurno());
			ps.setInt(4, user.getSemestre());
			ps.setInt(5, user.getCarrera());
			ps.setInt(6, user.getGrupo());
			ps.setString(7, user.getNombre());
			ps.setString(8, user.getApellidos());		
			ps.setString(9, user.getImagen());
			ps.setInt(10, user.getIdalumno());
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
