package com.prestamo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Cuenta;
import com.prestamo.service.CuentaService;
import com.prestamo.util.AppSettings;

/**
 * @author Nestor Malpartida
 * 
 */

@RestController
@RequestMapping("/url/CuentaConsulta")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class CuentaConsultaController {

	@Autowired
	private CuentaService servicioCuenta;
	
	@GetMapping("/consultaComplejaCuenta")
	public List<Cuenta> consultaCuenta(	@RequestParam("numero") String numero,
										@RequestParam("tipo") int tipo,
										@RequestParam("entidad") int entidad,
										@RequestParam("moneda") int moneda) {
		List<Cuenta> lstSalida = servicioCuenta.consultaCuentaByNumeroTipoEntidadMoneda("%"+numero+"%", tipo, entidad, moneda);
		return lstSalida;
	}
	
	
}
