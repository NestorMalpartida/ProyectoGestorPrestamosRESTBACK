package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Grupo;

public interface GrupoService {
	
	//>>> VALIDACIONES >>>
	public abstract List<Grupo> listaGrupoPorNombreIgual(String nombre);
	public abstract List<Grupo> listaGrupoPorDescripcionIgualActualiza(String descripcion, int idGrupo);
	
	//>>> CURD >>>
	public abstract Grupo insertarActualizarGrupo(Grupo bean);
	public abstract List<Grupo> listaGrupos();
	public abstract List<Grupo> listaGrupoPorDescripcionLike(String nombre);
	public abstract void eliminaGrupo(int idGrupo);
	
	//CONSULTA EF
	public abstract List<Grupo> consultaGrupoEF(String descripcion, int estado, int idUbigeo);
			
}
