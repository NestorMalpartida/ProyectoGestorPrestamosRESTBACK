package com.prestamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Usuario;
import com.prestamo.service.PrestatarioService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/consultaPrestatario")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class PrestatarioConsultaController {
	
	@Autowired
	private PrestatarioService prestatarioService;
	
	@GetMapping("/consultaPrestatarioLikeCompleja")
	public List<Usuario> consulta(  @RequestParam("login") String login, 
									@RequestParam("nombres") String nombres, 
									@RequestParam("apellidos") String apellidos, 
									@RequestParam("direccion") String direccion,
									@RequestParam("estado") int estado) {
		List<Usuario> lstSalida = prestatarioService.listaUsuarioPorLoginLikeCompleja("%"+login+"%", nombres, apellidos, direccion, estado);
		return lstSalida;
	}
	
	@GetMapping("/consultaPrestatarioLikeComplejaMejorado")
	public List<Usuario> consultaPrestatario(  @RequestParam("login") String login, 
									@RequestParam("idUsuario") int idUsuario, 
									@RequestParam("nombres") String nombres, 
									@RequestParam("apellidos") String apellidos, 
									@RequestParam("direccion") String direccion,
									@RequestParam("estado") int estado) {
		List<Usuario> lstSalida = prestatarioService.listaPrestatariosDeUnPrestamistaCompleja("%"+login+"%", idUsuario, nombres, apellidos, direccion, estado);
		return lstSalida;
	}
	
	
}
