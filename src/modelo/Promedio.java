package modelo;

public class Promedio {
	int idPromedio;
	String alumno;
	String profesor;
	String semestre;
	String carrera;
	int grupo;
	String asignaturas;
	Double calificaciones;
	Double promedio;
	
	
	public Promedio() {
		
	}


	public int getIdPromedio() {
		return idPromedio;
	}


	public void setIdPromedio(int idPromedio) {
		this.idPromedio = idPromedio;
	}


	public String getAlumno() {
		return alumno;
	}


	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}


	public String getSemestre() {
		return semestre;
	}


	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}


	public String getCarrera() {
		return carrera;
	}


	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}


	public int getGrupo() {
		return grupo;
	}


	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}


	public String getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(String asignaturas) {
		this.asignaturas = asignaturas;
	}


	public Double getCalificaciones() {
		return calificaciones;
	}


	public void setCalificaciones(Double calificaciones) {
		this.calificaciones = calificaciones;
	}


	public Double getPromedio() {
		return promedio;
	}


	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}


	public String getProfesor() {
		return profesor;
	}


	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	

}
