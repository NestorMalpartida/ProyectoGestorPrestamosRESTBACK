package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Usuario;
import com.prestamo.service.PrestatarioService;
import com.prestamo.util.AppSettings;

/***
 * 	@author Jhonatan Chavez
 */

@RestController
@RequestMapping("/url/prestatario")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class PrestatarioRegistroController {
	
	@Autowired
	private PrestatarioService prestatarioService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> lista(){
		List<Usuario> lstSalida = prestatarioService.listaUsuario();
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping
	public ResponseEntity<?> registra(@RequestBody Usuario objPrestatario){
		HashMap<String, Object> salida = new HashMap<>();
		objPrestatario.setFechaRegistro(new Date());
		objPrestatario.setFechaActualizacion(new Date());
		objPrestatario.setEstado(AppSettings.ACTIVO);
		
		String passwordEncoded = encoder.encode(objPrestatario.getLogin());
		objPrestatario.setPassword(passwordEncoded);
		
		Usuario objSalida = prestatarioService.insertaActualizaUsuario(objPrestatario);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de Prestatario con el ID >>> " + objPrestatario.getIdUsuario() + 
										" >>> NOM >> "+ objPrestatario.getNombres());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/validaLoginRegistra")
		public String validaLogin(@RequestParam(name = "login")String login) {
			List<Usuario> lstSalida = prestatarioService.listaUsuarioPorLoginIgual(login);
			if (lstSalida.isEmpty()) {
				 return "{\"valid\":true}";
			}
			else {
				return "{\"valid\":false}";
			}
	}
	
	@GetMapping("/validaDniRegistra")
	public String validaDni(@RequestParam(name = "dni")String dni) {
		List<Usuario> lstSalida = prestatarioService.listaUsuarioPorDniIgual(dni);
		if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		}
		else {
			return "{\"valid\":false}";
		}
}
}
