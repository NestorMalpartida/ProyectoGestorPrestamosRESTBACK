package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Grupo;
import com.prestamo.service.GrupoService;
import com.prestamo.util.AppSettings;

/*
 * Autor: Harrinson Flores
 * */
@RestController
@RequestMapping("/url/grupo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class GrupoRegistroController {
	@Autowired
	private GrupoService servicio;
	
	@GetMapping
	public ResponseEntity<List<Grupo>> lista(){
		List<Grupo> salida = servicio.listaGrupos();
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping
	public ResponseEntity<?> registro(@RequestBody Grupo bean){
		HashMap<String, Object> salida = new HashMap<>();
		bean.setFechaRegistro(new Date());
		bean.setFechaActualizacion(new Date());
		bean.setEstado(AppSettings.ACTIVO);
		
		Grupo objsalida = servicio.insertarActualizarGrupo(bean);
		if(objsalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de Grupo con el ID: >>> " + bean.getIdGrupo() + 
					" >>> Nombre: >> "+ bean.getDescripcion());
		}
		return ResponseEntity.ok(salida);
	}
	@GetMapping("/validarNombreRegistro")
	public String validarNombre(@RequestParam(name = "descripcion")String desc) {
		List<Grupo> salida = servicio.listaGrupoPorNombreIgual(desc);
		if(salida.isEmpty()) {
			return "{\"valid\":true}";
		 }else {
			 return "{\"valid\":false}";
		 }
	}
}
