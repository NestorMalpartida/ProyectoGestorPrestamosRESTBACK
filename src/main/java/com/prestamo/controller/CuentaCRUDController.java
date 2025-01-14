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

import com.prestamo.entity.Cuenta;
import com.prestamo.service.CuentaService;
import com.prestamo.util.AppSettings;

/**
 * @author Nestor Malpartida
 * 
 */

@RestController
@RequestMapping("/url/CuentaCrud")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class CuentaCRUDController {

	@Autowired
	private CuentaService servicioCuenta;
	
	@GetMapping("/findCuentaByNumeroLike/{var}")
	@ResponseBody
	public ResponseEntity<?> findCuentaByNumeroLike(@PathVariable("var")String numero){
		List<Cuenta> lstCuenta = null; 
		if ( numero.equals("todos")) {
			lstCuenta = servicioCuenta.listaCuenta();
		}
		else {
			lstCuenta = servicioCuenta.findCuentaByNumeroLike(numero + "%");
		}
		return ResponseEntity.ok(lstCuenta);
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Cuenta c) {
		Map<String, Object> salida = new HashMap<>();
		c.setFechaRegistro(new Date());
		c.setFechaActualizacion(new Date());
		c.setEstado(AppSettings.ACTIVO);
		
		Cuenta objSalida = servicioCuenta.insertaActualizaCuenta(c);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de cuenta con el ID >>> " + c.getIdCuenta() + 
										" >>> DES >> "+ c.getNumero());
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> update(@RequestBody Cuenta c) {
		Map<String, Object> salida = new HashMap<>();
		try {
			c.setFechaActualizacion(new Date());
			
			Cuenta objSalida = servicioCuenta.insertaActualizaCuenta(c);
			
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Cuenta de ID ==> " + c.getIdCuenta() + ".");
			}
		}catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/deleteById/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id")int idCuenta){
		Map<String, Object> salida = new HashMap<>();
		try {
			servicioCuenta.deleteById(idCuenta);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Cuenta de ID ==> " + idCuenta + "." );
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/findCuentaByNumeroIgualActualizar")
	public String findCuentaByNumeroIgualActualizar(@RequestParam(name = "numero")String numero,
													@RequestParam(name = "idCuenta")int idCuenta) {
		List<Cuenta> lstSalida = servicioCuenta.findCuentaByNumeroIgualActualizar(numero, idCuenta);
		if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		 }else {
			 return "{\"valid\":false}";
		 }
	}
	
}
