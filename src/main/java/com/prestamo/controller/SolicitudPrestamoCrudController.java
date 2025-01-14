package com.prestamo.controller;


import java.math.BigDecimal;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.entity.SolicitudPrestamo;
import com.prestamo.service.SolicitudPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudSolicitudPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class SolicitudPrestamoCrudController {
	
	@Autowired
	private SolicitudPrestamoService solicitudService;
	
	@GetMapping("/listaSolicitudPrestamoPorCapitalLike/{var}")
	@ResponseBody
    public ResponseEntity<?> listaSolicitudPrestamoPorCapitalLike(@PathVariable("var") String filtro) {
        List<SolicitudPrestamo> lstSalida = null;
        if ("todos".equals(filtro)) {
            lstSalida = solicitudService.listaSolicitudPrestamos();
        } else {
            // Intenta convertir el filtro a entero
            try {
                int capital = Integer.parseInt(filtro);
                lstSalida = solicitudService.listaSolicitudPrestamoPorCapitalLike(capital);
            } catch (NumberFormatException e) {
                // En caso de que no se pueda convertir a entero (debería ser una cadena)
                return ResponseEntity.badRequest().body("El filtro debe ser un número entero o 'todos'");
            }
        }
        
        return ResponseEntity.ok(lstSalida);
    }
	
	@PostMapping("/registraSolicitudPrestamo")
	@ResponseBody
	public ResponseEntity<?> registra(@RequestBody SolicitudPrestamo objSolicitud){
		HashMap<String, Object> salida = new HashMap<>();
		objSolicitud.setFechaRegistro(new Date());
		objSolicitud.setFechaActualizacion(new Date());
		objSolicitud.setEstado(AppSettings.ACTIVO);
		
		DataCatalogo estadoSolicitud = new DataCatalogo();
        estadoSolicitud.setIdDataCatalogo(12);  // Suponiendo que el constructor o el método setId acepta un id
        objSolicitud.setEstadoSolicitud(estadoSolicitud);
        
    
        

        Date fechaInicio = objSolicitud.getFechaInicioPrestamo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);

        String diasDescripcion = objSolicitud.getDias().getDescripcion();
        if (diasDescripcion != null && !diasDescripcion.isEmpty()) {
            try {
                int dias = Integer.parseInt(diasDescripcion);
                calendar.add(Calendar.DAY_OF_YEAR, dias);
                Date fechaFin = calendar.getTime();
                objSolicitud.setFechaFinPrestamo(fechaFin);
            } catch (NumberFormatException e) {
                // Manejar el caso en que la descripción no sea un número válido
                throw new IllegalArgumentException("La descripción de los días no es un número válido: " + diasDescripcion, e);
            }
        } else {
            // Manejar el caso en que la descripción sea nula o vacía
            throw new IllegalArgumentException("La descripción de los días no puede ser nula o vacía");
        }
        
        
     

        // Calcular monto a pagar basado en el capital y los días
        int capital = objSolicitud.getCapital().intValue(); // Convertir a int
        BigDecimal monto = solicitudService.obtenerMontoPorDiasIdYCapital(objSolicitud.getDias().getIdDataCatalogo(), capital);
        objSolicitud.setMontoPagar(monto);
		
		
		SolicitudPrestamo objSalida = solicitudService.insertaActualizaSolicitudPrestamo(objSolicitud);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de solicitud de préstamo con el ID >>> " + objSolicitud.getIdSolicitud() + 
										" >>> CAPITAL >> "+ objSolicitud.getCapital());
		}
		return ResponseEntity.ok(salida);
	}
	
	   @PutMapping("/actualizaSolicitudPrestamo")
	   @ResponseBody
	   public ResponseEntity<Map<String, Object>> actualizaSolicitudPrestamo(@RequestBody SolicitudPrestamo obj) {
	       Map<String, Object> salida = new HashMap<>();
	       try {
	           // Realizar validaciones u operaciones necesarias antes de la actualización

	           // Realizar la actualización en el servicio
	           SolicitudPrestamo objSalida = solicitudService.insertaActualizaSolicitudPrestamo(obj);

	           if (objSalida == null) {
	               salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
	           } else {
	               salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Solicitud de ID ==> " + obj.getIdSolicitud() + ".");
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	           salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
	       }
	       return ResponseEntity.ok(salida);
	   }
	   
	   @DeleteMapping("/eliminaSolicitudPrestamo/{id}")
	   @ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaSolicitudPrestamo(@PathVariable("id") int idSolicitud) {
			Map<String, Object> salida = new HashMap<>();
			try {
				solicitudService.eliminaSolicitudPrestamo(idSolicitud);
				salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Solicitud de ID ==> " + idSolicitud + "." );
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
}
