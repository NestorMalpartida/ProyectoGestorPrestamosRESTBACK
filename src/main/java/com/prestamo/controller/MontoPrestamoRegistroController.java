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
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.MontoPrestamo;
import com.prestamo.service.MontoPrestamoService;
import com.prestamo.util.AppSettings;

/**
 * @author Nacor Picharde
 */
@RestController
@RequestMapping("/url/montoPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class MontoPrestamoRegistroController {
	
	@Autowired
	private MontoPrestamoService montoService;
	
	@GetMapping
	public ResponseEntity<List<MontoPrestamo>> lista(){
		List<MontoPrestamo> lstSalida = montoService.listaMontoPrestamo();
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping
	public ResponseEntity<?> registra(@RequestBody MontoPrestamo objMonto){
		HashMap<String, Object> salida = new HashMap<>();
		objMonto.setFechaRegistro(new Date());
		objMonto.setFechaActualizacion(new Date());
		objMonto.setEstado(AppSettings.ACTIVO);
		
		MontoPrestamo objSalida = montoService.insertaActualizaMonto(objMonto);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de ejemplo con el ID >>> " + objMonto.getIdMontoPrestamo() + 
										" >>> DES >> "+ objMonto.getCapital());
		}
		return ResponseEntity.ok(salida);
	}
	

}
