package com.prestamo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.EntidadFinanciera;
import com.prestamo.repository.EntidadFinancieraRepository;

@Service
public class EntidadFinancieraServiceImpl implements EntidadFinancieraService {

	@Autowired
	private EntidadFinancieraRepository repo;
	
	@Override
	public EntidadFinanciera save(EntidadFinanciera obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<EntidadFinanciera> findEntidadFinancieraByNombreIgual(String nombre){
		return repo.findEntidadFinancieraByNombreIgual(nombre);
	}
	
	@Override
	public List<EntidadFinanciera> findAll(){
		return repo.findAll();
	}

	@Override
	public List<EntidadFinanciera> listarPorTipo(Integer idTipoEntidad) {
		return repo.findByTipoEntidadIdDataCatalogo(idTipoEntidad);
	}

	@Override
	public List<EntidadFinanciera> listar() {
		return repo.findAll();
	}

	@Override
	public List<EntidadFinanciera> listaEntidadFinancieraAcualiza(String nombre, int idEntidadFinanciera) {
		// TODO Auto-generated method stub
		return repo.findByEntidadActualiza(nombre, idEntidadFinanciera);
	}

	@Override
	public void eliminarEntidadFinanciera(int idEntidadFinanciera) {
		repo.deleteById(idEntidadFinanciera);
	}

	@Override
	public List<EntidadFinanciera> listaEntidadFinancieraLike(int entidad) {
		// TODO Auto-generated method stub
		return repo.findByEntidad(String.valueOf(entidad));
	}

	@Override
	public List<EntidadFinanciera> listaEntidadConsulta(String nombre, String gerente, Date fecInicio, Date fecFin,
			int idTipo, int estado) {
		//Powered By: Harrinson Flores
		if (nombre.equals("%") && gerente.equals("%") && fecInicio.equals(new Date(0)) && fecFin.equals(new Date()) && idTipo == -1 && estado == -1) {
            return repo.findAll();
        } else {
            return repo.listaEntidadConsulta(nombre, gerente, fecInicio, fecFin, idTipo, estado);
        }
	}
	
}
