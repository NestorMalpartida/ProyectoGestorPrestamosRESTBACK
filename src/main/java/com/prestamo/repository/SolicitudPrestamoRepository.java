package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.SolicitudPrestamo;

public interface SolicitudPrestamoRepository extends JpaRepository<SolicitudPrestamo, Integer> {
	@Query("select s from SolicitudPrestamo s where s.capital = ?1")
    public abstract List<SolicitudPrestamo> listaSolicitudPrestamoPorCapitalLike(int capital);
	
	@Query("select s from SolicitudPrestamo s where "
	           + "(?1 = -1 or s.capital = ?1) and "
	           + "(?2 = -1 or s.dias.idDataCatalogo = ?2) and "
	           + "s.montoPagar = ?3 and "
	           + "(?4 = -1 or s.usuarioPrestatario.idUsuario = ?4) and "
	           + "s.estado = ?5")
	public abstract List<SolicitudPrestamo> listaSolicitudPrestamoConsulta(int capital, int dias, double montoPagar, int usuarioPrestatario, int estado);
	
}

