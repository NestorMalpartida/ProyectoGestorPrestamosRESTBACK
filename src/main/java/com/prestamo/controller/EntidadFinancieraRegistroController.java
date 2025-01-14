package com.prestamo.controller;

import java.util.ArrayList;
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

import com.prestamo.entity.EntidadFinanciera;
import com.prestamo.service.EntidadFinancieraService;
import com.prestamo.util.AppSettings;

/***
 * @author Malpartida Nestor
*/
@RestController
@RequestMapping("/url/EntidadFinancieraRegistro")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class EntidadFinancieraRegistroController {

	@Autowired
	private EntidadFinancieraService servicioEntidadFinanciera;
	
	@PostMapping
	public ResponseEntity<?> registro(@RequestBody EntidadFinanciera objEntidadFinanciera){
		HashMap<String, Object> salida = new HashMap<>();
		objEntidadFinanciera.setFechaActualizacion(new Date());
		objEntidadFinanciera.setFechaRegistro(new Date());
		objEntidadFinanciera.setEstado(AppSettings.ACTIVO);
		
		EntidadFinanciera objSalida = servicioEntidadFinanciera.save(objEntidadFinanciera);
		
		if ( objSalida == null) {
			salida.put("mensaje", "Error al registrar la Entidad Financiera!");
		}else {
			salida.put("mensaje", "Registro de la Entidad Financiera exitoso >> ID >> "+objEntidadFinanciera.getIdEntidadFinanciera() + " >> Nombre >> "+objEntidadFinanciera.getNombre());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping
	public ResponseEntity<List<EntidadFinanciera>> findAll(){
		return ResponseEntity.ok(servicioEntidadFinanciera.findAll());
	}
	
	@GetMapping("/validaNombreEntidadFinanciera")
	public String validaEntidadFinanciera(@RequestParam(name = "nombre")String nombre) {
		List<EntidadFinanciera> lstSalida = servicioEntidadFinanciera.findEntidadFinancieraByNombreIgual(nombre);
		if ( lstSalida.isEmpty()) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		}
	}
	
	//
	@GetMapping("/listar")
	public ResponseEntity<List<EntidadFinanciera>> listarPorTipo(@RequestParam(name="idDataCatalogo",required = false) Integer idDataCatalogo){
		
		List<EntidadFinanciera> lstSalida = new ArrayList<>();
		
		try {
			if (idDataCatalogo!=null) {
				lstSalida = servicioEntidadFinanciera.listarPorTipo(idDataCatalogo);
			}else {
				lstSalida = servicioEntidadFinanciera.listar();
			}
				
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return ResponseEntity.ok(lstSalida);
	}
}
