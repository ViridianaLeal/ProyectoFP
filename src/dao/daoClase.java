package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.conexion;
import modelo.Clase;
import modelo.Profesor;
import modelo.Usuario;

public class daoClase {
	conexion cx = null;
	public daoClase() {
		cx = new conexion();
	}
	
	public boolean insertarClase(Clase user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO clase VALUES(null,?,?,?,?,?)");
			ps.setString(1, user.getClave());
			ps.setString(2, user.getProfesor());
			ps.setInt(3,user.getGrupo());
			ps.setString(4, user.getClase());
			ps.setInt(5,user.getIdusuario());
			ps.execute();
			ps.close();
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
	
	public ArrayList<Clase> buscar(String palabra, int idUsuario) {
        ArrayList<Clase> lista2 = new ArrayList<Clase>();
        PreparedStatement ps = null;
		ResultSet rs = null;
        try {
            String sql = "SELECT * FROM clase WHERE  ("
                    + "(profesor LIKE ?) OR"
                    + "(grupo LIKE ?) OR "
                    + "(clave LIKE ?) OR "
                    + "(clase LIKE ?) ) AND idUsuario=?";
            ps = cx.conectar().prepareStatement(sql);
            ps.setString(1, "%" + palabra + "%");
            ps.setString(2, "%" + palabra + "%");
            ps.setString(3, "%" + palabra + "%");
            ps.setString(4, "%" + palabra + "%");
            ps.setInt(5, idUsuario);
            //System.out.println("CONSULTA" + ps.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Clase p = new Clase();
                p.setIdClase(rs.getInt("idClase"));
                p.setClave(rs.getString("clave"));
                p.setProfesor(rs.getString("profesor"));
                p.setGrupo(rs.getInt("grupo"));
                p.setClase(rs.getString("clase")); 
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
	
	public boolean loginUniraClase(Clase user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM clase WHERE clase=? AND clave=?");
			ps.setString(1, user.getClase());
			ps.setString(2, user.getClave());
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
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

	}

	public ArrayList<Clase> fetcClases(int idUsuario) {
		ArrayList<Clase> lista = new ArrayList<Clase>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM clase WHERE idusuario=?");
			ps.setInt(1,idUsuario);
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



