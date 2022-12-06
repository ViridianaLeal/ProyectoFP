package modelo;

import java.sql.Date;

public class Actividades {
	int idActividades;
	String actividad;
	String asignatura;
	int clase;
	String profesor;
	String fecha;
	
	public Actividades() {
		
	}

	public int getIdActividades() {
		return idActividades;
	}

	public void setIdActividades(int idActividades) {
		this.idActividades = idActividades;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public int getClase() {
		return clase;
	}

	public void setClase(int clase) {
		this.clase = clase;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	
	

	
	
}
