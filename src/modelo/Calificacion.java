package modelo;

public class Calificacion {
	int idCali;
	String alumno;
	String profesor;
	String semestre;
	String carrera;
	int grupo;
	String asignatura;
	Double calificacio;
	
	public Calificacion() {
		
	}

	public int getIdCali() {
		return idCali;
	}

	public void setIdCali(int idCali) {
		this.idCali = idCali;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
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

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public Double getCalificacio() {
		return calificacio;
	}

	public void setCalificacio(Double calificacio) {
		this.calificacio = calificacio;
	}
	

}
