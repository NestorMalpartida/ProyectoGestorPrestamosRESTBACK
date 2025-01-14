package com.prestamo.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.MontoPrestamo;
import com.prestamo.repository.MontoPrestamoRepository;

@Service
public class MontoPrestamoServiceImp implements MontoPrestamoService {

	@Autowired
	MontoPrestamoRepository repository;
	
	
	@Override
	public MontoPrestamo insertaActualizaMonto(MontoPrestamo obj) {
		return repository.save(obj);
	}

	@Override
	public List<MontoPrestamo> listaMontoPrestamo() {
		return repository.findAll();
	}
	

	@Override
	public void eliminaMontoPrestamo(int idMonto) {
		repository.deleteById(idMonto);
		
	}

	@Override
	public List<MontoPrestamo> obtenerMontoPrestamosPorPrimerosDigitosCapital(String capitalDigits) {
		return repository.obtenerMontoPrestamosPorPrimerosDigitosCapital(capitalDigits);
	}
	
	
	@Override
	public List<MontoPrestamo> listaConsultaComplejaMonto(int diasId, int capital, Date fechaInicio, Date fechaFin,
			int estado) {
		return repository.consultaComplejaMontoPrestamo(diasId, capital, fechaInicio, fechaFin, estado);
	}





}
