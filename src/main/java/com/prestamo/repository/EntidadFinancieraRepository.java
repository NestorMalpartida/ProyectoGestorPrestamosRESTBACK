package com.prestamo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.EntidadFinanciera;

public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Integer> {

	@Query("Select e from EntidadFinanciera e where e.nombre = ?1")
	public List<EntidadFinanciera> findEntidadFinancieraByNombreIgual(String nombre);
	
	public List<EntidadFinanciera> findByTipoEntidadIdDataCatalogo(Integer idDataCatalogo);
	
	@Query("select e from EntidadFinanciera e where str(e.tipoEntidad.idDataCatalogo) like %?1%")
	public abstract List<EntidadFinanciera> findByEntidad(String entidad);
	
	@Query("select e from EntidadFinanciera e where e.nombre = ?1 and e.idEntidadFinanciera != ?2")
	public abstract List<EntidadFinanciera> findByEntidadActualiza(String nombre, int idEntidadFinanciera);
	
	@Query("SELECT ef FROM EntidadFinanciera ef WHERE "
	         + "ef.nombre LIKE %:nombre% AND "
	         + "ef.gerente LIKE %:gerente% AND "
	         + "ef.fechaRegistro >= :fechaInicio AND "
	         + "ef.fechaRegistro <= :fechaFin AND "
	         + "(:idTipoEntidad = -1 OR ef.tipoEntidad.idDataCatalogo = :idTipoEntidad) AND "
	         + "ef.estado = :estado")
	public abstract List<EntidadFinanciera> listaEntidadConsulta(String nombre, String gerente, Date fechaInicio, Date fechaFin,int idTipoEntidad, int estado);
}
