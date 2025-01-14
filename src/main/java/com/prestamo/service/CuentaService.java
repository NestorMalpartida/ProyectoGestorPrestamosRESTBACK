package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Cuenta;

public interface CuentaService {

	public abstract Cuenta insertaActualizaCuenta(Cuenta obj);
	public abstract Cuenta validarNumeroExiste(String numero);
	public abstract List<Cuenta> listaCuenta();
	public abstract List<Cuenta> listaCuentaPorNumeroIgual(String numero);
	
	/**----------------------------CRUD----------------------------**/
	public abstract void deleteById(int idCuenta);
	
	public abstract List<Cuenta> findCuentaByNumeroLike(String numero);
	
	public abstract List<Cuenta> findCuentaByNumeroIgualActualizar(String numero, int idCuenta);
	
	/**--------------------------Consulta--------------------------**/
	
	public abstract List<Cuenta> consultaCuentaByNumeroTipoEntidadMoneda(String numero, int tipo, int entidad, int moneda);

}
