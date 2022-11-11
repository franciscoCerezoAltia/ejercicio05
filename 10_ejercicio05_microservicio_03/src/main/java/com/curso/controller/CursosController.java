package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@RestController
public class CursosController {
	
	@Value("${eureka.instance.instance-id}")
	String instancia;
	
	@Autowired
	CursoService service;
	
	@ApiOperation(value = "Devuelve la lista de los cursos que cumplen que están entre dos valores de precio")
	@GetMapping(value="curso/{precioMin}/{precioMax}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCurso (@ApiParam(value="Precio mínimo para la búsqueda") @PathVariable int precioMin,@ApiParam(value="Precio máximo para la búsqueda") @PathVariable int precioMax) {
		System.out.println(instancia);
		return service.buscarCursoPrecio(precioMin, precioMax);
	}
	
	@ApiOperation(value = "Busca un curso por su código")
	@ApiParam
	@GetMapping(value="curso/{codCurso}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso (@ApiParam(value="Código del curso a buscar") @PathVariable("codCurso") String codCurso) {
		System.out.println(instancia);
		return service.buscarCurso(codCurso);
	}
		
	@ApiOperation(value = "Devuelve la lista de todos los cursos")
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursos () {
		System.out.println(instancia);
		return service.cursos();
	}
	
	@ApiOperation(value = "Realiza el alta de un curso")
	@ApiParam
	@PutMapping(value="curso",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void Curso (@ApiParam(value="Curso completo a dar de alta") @RequestBody Curso curso) {
		System.out.println(instancia);
		service.altaCurso(curso);
	}
	
	@ApiOperation(value = "Modifica la duración de un curso")
	@PutMapping(value="curso/{codCurso}/{duracion}")
	public void Curso (@ApiParam(value="Código del curso") @PathVariable("codCurso") String codCurso,@ApiParam(value="nueva duración del curso") @PathVariable("duracion") int duracion) {
		System.out.println(instancia);
		service.actualizarCurso(codCurso, duracion);
	}
	
	@ApiOperation(value = "Elimina un curso")
	@DeleteMapping(value="curso/{codCurso}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminar (@ApiParam(value="Código del curso a eliminar") @PathVariable("codCurso") String codCurso) {
		System.out.println(instancia);
		return service.eliminarCurso(codCurso);
	}
	

}
