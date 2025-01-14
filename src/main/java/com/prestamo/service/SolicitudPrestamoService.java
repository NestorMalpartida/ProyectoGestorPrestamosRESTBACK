package com.prestamo.service;

import java.math.BigDecimal;
import java.util.List;

import com.prestamo.entity.SolicitudPrestamo;

public interface SolicitudPrestamoService {

	public abstract List<Integer> obtenerCapitalesPorDiasId(int diasId);
	public abstract BigDecimal obtenerMontoPorDiasIdYCapital(int diasId, int capital);
	
	public abstract SolicitudPrestamo insertaActualizaSolicitudPrestamo(SolicitudPrestamo obj);
	public abstract void eliminaSolicitudPrestamo(int idSolicitud);
	public abstract List<SolicitudPrestamo> listaSolicitudPrestamos(); 
	public abstract List<SolicitudPrestamo> listaSolicitudPrestamoPorCapitalLike(int capital);
	
	public abstract List<SolicitudPrestamo> listaSolicitudPrestamoConsulta(int capital, int dias,double montoPagar,int usuarioPrestatario, int estado);
}	
