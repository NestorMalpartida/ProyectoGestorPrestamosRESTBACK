package com.prestamo.service;

import java.util.Date;
import java.util.List;

import com.prestamo.entity.EntidadFinanciera;

public interface EntidadFinancieraService {
	
	public abstract EntidadFinanciera save(EntidadFinanciera ef);
	public abstract List<EntidadFinanciera> findEntidadFinancieraByNombreIgual(String nombre);
	public abstract List<EntidadFinanciera> findAll();
	public abstract List<EntidadFinanciera> listaEntidadFinancieraAcualiza(String nombre, int idEntidadFinanciera);
	
	//Para Consulta
	public abstract List<EntidadFinanciera> listaEntidadConsulta(String nombre, String gerente, Date fecInicio, Date fecFin,int idTipo, int estado);

	
	public List<EntidadFinanciera> listar();
	public List<EntidadFinanciera> listarPorTipo (Integer idTipoEntidad);
	public abstract void eliminarEntidadFinanciera(int idEntidadFinanciera);
	public abstract List<EntidadFinanciera> listaEntidadFinancieraLike(int entidad);

}
