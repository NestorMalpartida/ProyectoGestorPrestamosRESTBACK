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

import com.prestamo.entity.Grupo;
import com.prestamo.service.GrupoService;
import com.prestamo.util.AppSettings;

@RestController
	@RequestMapping("/url/crudGrupo")
	@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
	public class GrupoCrudController {

		@Autowired
		private GrupoService grupoService;
		
		@GetMapping("/listaGrupoPorNombreLike/{var}")
		@ResponseBody
		public ResponseEntity<?> listaGrupoPorNombreLike(@PathVariable("var") String nombre){
			List<Grupo> lstSalida = null;
			if (nombre.equals("todos")) {
				lstSalida =grupoService.listaGrupos();
			}else {
				lstSalida =grupoService.listaGrupoPorDescripcionLike(nombre +  "%");
			}
			return ResponseEntity.ok(lstSalida);
		}
		
		@PostMapping("/registraGrupo")
		@ResponseBody
		public ResponseEntity<?> insertaGrupo(@RequestBody Grupo obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				obj.setIdGrupo(0);
				obj.setFechaActualizacion(new Date());
				obj.setFechaRegistro(new Date());
				obj.setEstado(AppSettings.ACTIVO);
				
				Grupo objSalida = grupoService.insertarActualizarGrupo(obj);
				if (objSalida == null) {
					salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
				} else {
					salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + "Se registro GRUPO con el ID " + obj.getIdGrupo() + ".");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			}
			return ResponseEntity.ok(salida);
		}

		@PutMapping("/actualizaGrupo")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaGrupo(@RequestBody Grupo obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				
				obj.setFechaActualizacion(new Date());

				Grupo objSalida = grupoService.insertarActualizarGrupo(obj);
				if (objSalida == null) {
					salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
				} else {
					salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + "Se actualizo GRUPO con el ID==> " + obj.getIdGrupo() + ".");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		
		@DeleteMapping("/eliminaGrupo/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaGrupo(@PathVariable("id") int idGrupo) {
			Map<String, Object> salida = new HashMap<>();
			try {
				grupoService.eliminaGrupo(idGrupo);
				salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Se elimino GRUPO con el ID ==> " + idGrupo + "." );
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		@GetMapping("/validaDescripcionActualiza")
		public String validaDescripcion(@RequestParam(name = "descripcion")String descripcion,
										@RequestParam(name = "idGrupo")int idGrupo) {
			 List<Grupo> lstSalida =grupoService.listaGrupoPorDescripcionIgualActualiza(descripcion, idGrupo);
			 if (lstSalida.isEmpty()) {
				 return "{\"valid\":true}";
			 }else {
				 return "{\"valid\":false}";
			 }
				
		}
		
		
	}
