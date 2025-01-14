package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Usuario;
import com.prestamo.service.PrestatarioService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudPrestatario")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class PrestatarioCrudController {
	
	@Autowired
	private PrestatarioService prestatarioService;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/listaPrestatarioPorLoginLike/{var}/{id}")
	@ResponseBody
	public ResponseEntity<?> listaPrestatarioPorLoginLike(@PathVariable("var") String nombre,
														  @PathVariable("id") int id
														  ){
		List<Usuario> lstSalida = null;
		if (nombre.equals("todos")) {
			lstSalida =prestatarioService.listaPrestatariosDeUnPrestamista(id);
		}else {
			String nombreLike = nombre + "%";
			lstSalida =prestatarioService.listaPrestatariosDeUnPrestamistaLike(id,nombreLike);
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping("/registraPrestatario")
	@ResponseBody
	public ResponseEntity<?> registra(@RequestBody Usuario objPrestatario){
		HashMap<String, Object> salida = new HashMap<>();
		try {
			objPrestatario.setIdUsuario(0);
			objPrestatario.setFechaRegistro(new Date());
			objPrestatario.setFechaActualizacion(new Date());
			objPrestatario.setEstado(AppSettings.ACTIVO);
			
			String passwordEncoded = encoder.encode(objPrestatario.getLogin());
			objPrestatario.setPassword(passwordEncoded);
			
			Usuario objSalida = prestatarioService.insertaActualizaUsuario(objPrestatario);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + " Prestatario ID==> " + objPrestatario.getIdUsuario());
			}
		}catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaPrestatario")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaPrestatario(@RequestBody Usuario obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			obj.setFechaActualizacion(new Date());

			Usuario objSalida = prestatarioService.insertaActualizaUsuario(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Prestatario ID ==> " + obj.getIdUsuario());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminaPrestatario/{id}")
	@ResponseBody
	@Transactional
	public ResponseEntity<Map<String, Object>> eliminaPrestatario(@PathVariable("id") int idUsuario) {
		Map<String, Object> salida = new HashMap<>();
		try {
			prestatarioService.deleteByUsuarioId(idUsuario);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + "Prestatario ID ==> " + idUsuario + ".");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/validaLoginActualiza")
	public String validaLogin(@RequestParam(name = "login")String login,
							  @RequestParam(name = "idUsuario") int idUsuario) {
		List<Usuario> lstSalida = prestatarioService.listaUsuarioPorLoginIgualActualiza(login, idUsuario);
		if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		}
		else {
			return "{\"valid\":false}";
		}
	}

	@GetMapping("/validaDniActualiza")
	public String validaDni(@RequestParam(name = "dni")String dni,
							@RequestParam(name = "idUsuario") int idUsuario) {
		List<Usuario> lstSalida = prestatarioService.listaUsuarioPorDniIgualActualiza(dni, idUsuario);
		if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		}
		else {
			return "{\"valid\":false}";
		}
	}
	
	
}


