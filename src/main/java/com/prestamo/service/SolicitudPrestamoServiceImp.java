package com.prestamo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.SolicitudPrestamo;
import com.prestamo.repository.MontoPrestamoRepository;
import com.prestamo.repository.SolicitudPrestamoRepository;

@Service
public class SolicitudPrestamoServiceImp implements SolicitudPrestamoService {

	@Autowired
	private SolicitudPrestamoRepository repo;
	
	@Autowired
    private MontoPrestamoRepository montoPrestamoRepository;

	@Override
	public SolicitudPrestamo insertaActualizaSolicitudPrestamo(SolicitudPrestamo obj) {
		return repo.save(obj);
	}

	@Override
	public List<SolicitudPrestamo> listaSolicitudPrestamos() {
		return repo.findAll();
	}

	@Override
	public List<Integer> obtenerCapitalesPorDiasId(int diasId) {
		return montoPrestamoRepository.findCapitalsByDiasId(diasId);
	}

	@Override
	public BigDecimal obtenerMontoPorDiasIdYCapital(int diasId, int capital) {
		return montoPrestamoRepository.findMontoByDiasIdAndCapital(diasId, capital);
	}
	
	@Override
    public List<SolicitudPrestamo> listaSolicitudPrestamoPorCapitalLike(int capital) {
        return repo.listaSolicitudPrestamoPorCapitalLike(capital);
    }
	
	@Override
	public void eliminaSolicitudPrestamo(int idSolicitud) {
		repo.deleteById(idSolicitud);
	}	
	
	@Override
	public List<SolicitudPrestamo> listaSolicitudPrestamoConsulta(int capital, int dias,double montoPagar,int usuarioPrestatario, int estado){
		return repo.listaSolicitudPrestamoConsulta(capital, dias, montoPagar,usuarioPrestatario, estado);
	}
	
}
