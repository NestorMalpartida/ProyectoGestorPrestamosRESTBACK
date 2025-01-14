package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.prestamo.entity.EntidadFinanciera;
import com.prestamo.service.EntidadFinancieraService;
import com.prestamo.util.AppSettings;

/*
 *
 * Powered By: Harrinson Flores
 * 
 * */

@RestController
@RequestMapping("/url/crudEntidadFinanciera")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class EntidadFinancieraCrudController {

	@Autowired
	private EntidadFinancieraService servicio;
	
	@GetMapping("/listaEntidadLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaEntidadLike(@PathVariable("var") int entidad){
		List<EntidadFinanciera> lstSalida = null;
		if(entidad == 0) {
			lstSalida = servicio.listar();
		}else {
			lstSalida = servicio.listaEntidadFinancieraLike(entidad);
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping("/registraEntidadFinanciera")
	@ResponseBody
	public ResponseEntity<?> insertaEntidad(@RequestBody EntidadFinanciera bean){
		Map<String, Object> salida = new HashMap<>();
		try {
			bean.setFechaRegistro(new Date());
			bean.setFechaActualizacion(new Date());
			bean.setEstado(AppSettings.ACTIVO);
			EntidadFinanciera ef = servicio.save(bean);
			if(ef ==null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + " Entidad Financiera de ID ==> " + bean.getIdEntidadFinanciera() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaEntidadFinanciera")
	@ResponseBody
	public ResponseEntity<?> actualizarEntidad(@RequestBody EntidadFinanciera bean){
		Map<String, Object> salida = new HashMap<>();
		try {
			bean.setFechaActualizacion(new Date());
			EntidadFinanciera ef = servicio.save(bean);
			if(ef ==null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			}else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Entidad Financiera de ID ==> " + bean.getIdEntidadFinanciera() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminarEntidadFinanciera/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminarEntidad(@PathVariable("id") int idEntidadFinanciera){
		Map<String, Object> salida = new HashMap<>();
		try {
			servicio.eliminarEntidadFinanciera(idEntidadFinanciera);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Entidad Financiera de ID ==> " + idEntidadFinanciera + ".");
		} catch (Exception e) {
			salida.put("salida", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/validaNombreActualiza")
	public String validaNombreActualiza(@RequestParam(name="nombre") String nombre,
										@RequestParam(name="idEntidadFinanciera") int idEntidadFinanciera) {
		List<EntidadFinanciera> salida = servicio.listaEntidadFinancieraAcualiza(nombre, idEntidadFinanciera);
		if(salida.isEmpty()) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		}
	}
}
