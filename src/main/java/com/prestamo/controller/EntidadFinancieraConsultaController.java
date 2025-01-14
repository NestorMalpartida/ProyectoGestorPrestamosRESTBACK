package com.prestamo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/url/entidadFConsulta")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class EntidadFinancieraConsultaController {

	@Autowired
	private EntidadFinancieraService servicio;
	
	@GetMapping("/consultaEntidadFinanciera")
    public List<EntidadFinanciera> informe(@RequestParam(value = "nombre", required = false) String nombre,
                                           @RequestParam(value = "gerente", required = false) String gerente,
                                           @RequestParam(value = "fecInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecIni,
                                           @RequestParam(value = "fecFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecFin,
                                           @RequestParam(value = "idTipo", required = false, defaultValue = "-1") int tipo,
                                           @RequestParam(value = "estado", required = false, defaultValue = "-1") int estado) {
        List<EntidadFinanciera> salida = servicio.listaEntidadConsulta(
                nombre != null ? "%" + nombre + "%" : "%",
                gerente != null ? "%" + gerente + "%" : "%",
                fecIni != null ? fecIni : new Date(0),
                fecFin != null ? fecFin : new Date(),
                tipo,
                estado
        );
        return salida;
    }
}
