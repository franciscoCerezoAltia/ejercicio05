package com.curso.model;

public class Formacion {
	private String curso;
	private int asignaturas;
	private Float precio;
	
	public Formacion() {
		super();
	}

	public Formacion(String curso, int asignaturas, Float precio) {
		super();
		this.curso = curso;
		this.asignaturas = asignaturas;
		this.precio = precio;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(int asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}	

}
