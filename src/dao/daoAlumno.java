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

public class daoAlumno {
	conexion cx = null;

	public daoAlumno() {
		cx = new conexion();
	}

	public boolean insertarAlumno(Alumno user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO alumno VALUES(null,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, user.getNumerocontrol());
			ps.setString(2, user.getPlantel());
			ps.setString(3, user.getTurno());
			ps.setString(4, user.getSemestre());
			ps.setString(5, user.getCarrera());
			ps.setInt(6, user.getGrupo());
			ps.setString(7, user.getNombre());
			ps.setString(8, user.getApellidos());
			ps.setString(9, user.getFoto());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public ArrayList<Alumno> selectAlumnos() {
        ArrayList<Alumno> listax = new ArrayList<Alumno>();
        try {
            String sql="SELECT * FROM alumno";
            PreparedStatement st = cx.conectar().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Alumno x = new Alumno();
                x.setIdalumno(rs.getInt("idAlumno"));
				x.setNumerocontrol(rs.getInt("numerocontrol"));
				x.setPlantel(rs.getString("plantel"));
				x.setTurno(rs.getString("turno"));
				x.setSemestre(rs.getString("semestre"));
				x.setCarrera(rs.getString("carrera"));
				x.setGrupo(rs.getInt("grupo"));
				x.setNombre(rs.getString("nombre"));
				x.setApellidos(rs.getString("apellido"));
				x.setFoto(rs.getString("foto"));
                listax.add(x);
            }
            cx.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listax;
    }

	public ArrayList<Alumno> buscar(String palabra) {
		ArrayList<Alumno> lista2 = new ArrayList<Alumno>();
		try {
			String sql = "SELECT * FROM alumno WHERE " + "(idAlumno LIKE ?) OR " + "(numerocontrol LIKE ?) OR"
					+ "(plantel LIKE ?) OR " + "(turno LIKE ?) OR " + "(semestre LIKE ?) OR " + "(carrera LIKE ?) OR "
					+ "(grupo LIKE ?) OR " + "(nombre LIKE ?) OR " + "(apellido LIKE ?) OR " + "(foto LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
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
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno p = new Alumno();
				p.setIdalumno(rs.getInt("idAlumno"));
				p.setNumerocontrol(rs.getInt("numerocontrol"));
				p.setPlantel(rs.getString("plantel"));
				p.setTurno(rs.getString("turno"));
				p.setSemestre(rs.getString("semestre"));
				p.setCarrera(rs.getString("carrera"));
				p.setGrupo(rs.getInt("grupo"));
				p.setNombre(rs.getString("nombre"));
				p.setApellidos(rs.getString("apellido"));
				p.setFoto(rs.getString("foto"));
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
				u.setPlantel(rs.getString("plantel"));
				u.setTurno(rs.getString("turno"));
				u.setSemestre(rs.getString("semestre"));
				u.setCarrera(rs.getString("carrera"));
				u.setGrupo(rs.getInt("grupo"));
				u.setNombre(rs.getString("nombre"));
				u.setApellidos(rs.getString("apellido"));
				u.setFoto(rs.getString("foto"));
				lista.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public boolean EliminarAlumno(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM alumno WHERE idAlumno=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarAlumno(Alumno user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE alumno SET numerocontrol=?,plantel=?,turno=?,semestre=?,carrera=?,grupo=?,nombre=?,apellido=?,foto=? WHERE idAlumno=?");
			ps.setInt(1, user.getNumerocontrol());
			ps.setString(2, user.getPlantel());
			ps.setString(3, user.getTurno());
			ps.setString(4, user.getSemestre());
			ps.setString(5, user.getCarrera());
			ps.setInt(6, user.getGrupo());
			ps.setString(7, user.getNombre());
			ps.setString(8, user.getApellidos());		
			ps.setString(9, user.getFoto());
			ps.setInt(10, user.getIdalumno());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}
