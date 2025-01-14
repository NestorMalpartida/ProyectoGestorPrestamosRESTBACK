package com.prestamo.service;

import java.util.Date;
import java.util.List;

import com.prestamo.entity.MontoPrestamo;

public interface MontoPrestamoService {

	public abstract MontoPrestamo insertaActualizaMonto(MontoPrestamo obj);
	public abstract List<MontoPrestamo> listaMontoPrestamo();
	public abstract void eliminaMontoPrestamo(int idMonto);
	public abstract List<MontoPrestamo> obtenerMontoPrestamosPorPrimerosDigitosCapital(String capitalDigits);
	public abstract List<MontoPrestamo> listaConsultaComplejaMonto(int diasId,int capital, Date fechaInicio, Date fechaFin,int estado);

}
