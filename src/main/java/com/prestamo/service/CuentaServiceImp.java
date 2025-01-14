package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.Cuenta;
import com.prestamo.repository.CuentaRepository;

@Service
public class CuentaServiceImp implements CuentaService {

	@Autowired
    private CuentaRepository repository;

	
	@Override
	public Cuenta insertaActualizaCuenta(Cuenta obj) {
		return repository.save(obj);
	}

	@Override
	public List<Cuenta> listaCuenta() {
		return repository.findAll();
	}

	@Override
	public List<Cuenta> listaCuentaPorNumeroIgual(String numero) {
		return repository.listaCuentaPorNumeroIgual(numero);
	}

	@Override
	public Cuenta validarNumeroExiste(String numero) {
		return repository.findByNumero(numero);
	}

	/**----------------------------CRUD----------------------------**/
	
	@Override
	public void deleteById(int idCuenta) {
		repository.deleteById(idCuenta);
	}

	@Override
	public List<Cuenta> findCuentaByNumeroLike(String numero) {
		return repository.findCuentaByNumeroLike(numero);
	}

	@Override
	public List<Cuenta> findCuentaByNumeroIgualActualizar(String numero, int idCuenta) {
		return repository.findCuentaByNumeroIgualActualizar(numero, idCuenta);
	}

	/**--------------------------Consulta--------------------------**/
	
	@Override
	public List<Cuenta> consultaCuentaByNumeroTipoEntidadMoneda(String numero, int tipo, int entidad, int moneda) {
		return repository.consultaCuentaByNumeroTipoEntidadMoneda(numero, tipo, entidad, moneda);
	}

}
