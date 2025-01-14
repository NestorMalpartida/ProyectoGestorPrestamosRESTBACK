package com.prestamo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.SolicitudPrestamo;
import com.prestamo.service.SolicitudPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/consultaSolicitudPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class SolicitudPrestamoConsultaController {
	
	@Autowired
	private SolicitudPrestamoService solicitudService;
	
	@GetMapping("/consultaSolicitudPrestamo")
	public List<SolicitudPrestamo> consulta(@RequestParam("capital") int capital,
	                                        @RequestParam("dias")int dias,
	                                        @RequestParam("montoPagar")double montoPagar,
	                                        @RequestParam("usuarioPrestatario")int usuarioPrestatario,
	                                        @RequestParam("estado") int estado){
		List<SolicitudPrestamo> lstSalida = solicitudService.listaSolicitudPrestamoConsulta(capital, dias, montoPagar,usuarioPrestatario, estado);
		return lstSalida;
	}
}
