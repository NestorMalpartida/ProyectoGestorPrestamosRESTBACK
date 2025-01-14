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

import com.prestamo.entity.MontoPrestamo;
import com.prestamo.service.MontoPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/consultaMontoPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class MontoPrestamoConsultaController {
	@Autowired
    private MontoPrestamoService montoPrestamoService;
	
    @GetMapping("/consultaMontoPrestamo")
    public List<MontoPrestamo> consultaMontoPrestamo(
            @RequestParam(value = "dias", required = false, defaultValue = "-1") int dias,
            @RequestParam(value = "capital", required = false, defaultValue = "-1") int capital,           
            @RequestParam(value = "fecInicio", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecInicio,
            @RequestParam(value = "fecFin", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecFin,
            @RequestParam(value = "estado", required = false, defaultValue = "-1") int estado) {

        List<MontoPrestamo> salida = montoPrestamoService.listaConsultaComplejaMonto(
                dias,
                capital,
                fecInicio != null ? fecInicio : new Date(0),
                fecFin != null ? fecFin : new Date(),
                estado
               
        );
        return salida;
    }
    
}
