package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
	@Query("select g from Grupo g where g.descripcion = ?1")
	public abstract List<Grupo> listaGrupoPorNombreIgual(String nombre);
	
	@Query("select g from Grupo g where g.descripcion like ?1")
	public abstract List<Grupo> listaGrupoPorDescripcionLike(String nombre);
	
	@Query("select g from Grupo g where g.descripcion = ?1 and g.idGrupo != ?2 ")
	public abstract List<Grupo> listaGrupoPorDescripcionIgualActualiza(String descripcion, int idGrupo);
	
	@Query("select g from Grupo g where "
			+ " g.descripcion like ?1  and "
			+ " g.estado = ?2 and "
			+ " (?3 = -1 or g.ubigeo.idUbigeo = ?3)")
	public abstract List<Grupo> consultaGrupoEF(String descripcion, int estado, int idUbigeo);
}