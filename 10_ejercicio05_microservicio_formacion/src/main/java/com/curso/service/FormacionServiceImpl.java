package com.curso.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.curso.model.Curso;
import com.curso.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService{
	@Autowired
	RestTemplate template;

	private String url="http://servicioCurso/";

	@Override
	public List<Formacion> listadoCursos() {
		List<Curso> listadoCursos = listadoCursosInterno();
		List<Formacion> listadoFormacion = new ArrayList<Formacion>();
		listadoCursos.stream().forEach(l->listadoFormacion.add(new Formacion(l.getNombre(),(l.getDuracion())<=50?5:10,l.getPrecio().floatValue())));
		return listadoFormacion;
	}

	@Override
	public void altaCurso(Formacion formacion) {
		if (!existeCurso(formacion.getCurso())) {
			int duracion = formacion.getAsignaturas()*10;
			Curso curso = new Curso(formacion.getCurso().substring(0,3)+String.valueOf(duracion), formacion.getCurso(), duracion, formacion.getPrecio().intValue());
			template.put(url+"curso",curso);
		}
	}
	
	private List<Curso> listadoCursosInterno() {
		return Arrays.asList(template.getForObject(url + "curso", Curso[].class));
	}
	
	private boolean existeCurso (String nombre) {
		List<Curso> listadoCursos = listadoCursosInterno();
		return listadoCursos.stream().filter(l->l.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null)!=null;
	}
	
}
