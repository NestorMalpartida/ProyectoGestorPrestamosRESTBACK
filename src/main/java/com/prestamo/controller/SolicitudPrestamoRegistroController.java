package com.prestamo.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.entity.SolicitudPrestamo;
import com.prestamo.service.SolicitudPrestamoService;
import com.prestamo.util.AppSettings;

/**
 * @autor KIRA PATRICIO
 */
@RestController
@RequestMapping("/url/solicitudPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class SolicitudPrestamoRegistroController {

	@Autowired
	private SolicitudPrestamoService solicitudService;
	
	@Autowired
    private SolicitudPrestamoService solicitudPrestamoService;

	@GetMapping("/lista")
	public ResponseEntity<List<SolicitudPrestamo>> lista(){
		List<SolicitudPrestamo> lstSalida = solicitudService.listaSolicitudPrestamos();
		return ResponseEntity.ok(lstSalida);
	}
	
	
    @GetMapping("/dias/{diasId}/capitales")
    public ResponseEntity<List<Integer>> obtenerCapitalesPorDiasId(@PathVariable int diasId) {
        List<Integer> capitales = solicitudPrestamoService.obtenerCapitalesPorDiasId(diasId);
        return ResponseEntity.ok(capitales);
    }
    
    @GetMapping("/dias/{diasId}/capital/{capital}/monto")
    public ResponseEntity<BigDecimal> obtenerMontoPorDiasIdYCapital(@PathVariable int diasId, @PathVariable int capital) {
        BigDecimal monto = solicitudPrestamoService.obtenerMontoPorDiasIdYCapital(diasId, capital);
        return ResponseEntity.ok(monto);
    }

	@PostMapping("/registrar")
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
        BigDecimal monto = solicitudPrestamoService.obtenerMontoPorDiasIdYCapital(objSolicitud.getDias().getIdDataCatalogo(), capital);
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



	}
