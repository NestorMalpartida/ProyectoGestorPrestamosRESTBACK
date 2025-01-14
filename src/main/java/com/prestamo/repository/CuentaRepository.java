package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{
	
	@Query("select e from Cuenta e where e.numero = ?1")
	public abstract List<Cuenta> listaCuentaPorNumeroIgual(String numero);
	public abstract Cuenta findByNumero (String numero);

	/**----------------------------CRUD----------------------------**/
	
	@Query("Select c from Cuenta c where c.numero like ?1")
	public abstract List<Cuenta> findCuentaByNumeroLike(String numero);
	
	@Query("Select c from Cuenta c where c.numero = ?1 and c.idCuenta != ?2")
	public abstract List<Cuenta> findCuentaByNumeroIgualActualizar(String numero, int codigo);
	
	/**--------------------------Consulta--------------------------**/
	
	@Query("Select c from Cuenta c where "
								+ "c.numero like ?1 and "
								+ "(?2 = -1 or c.entidadFinanciera.tipoEntidad.idDataCatalogo = ?2) and "
								+ "(?3 = -1 or c.entidadFinanciera.idEntidadFinanciera = ?3) and "
								+ "(?4 = -1 or c.tipoMoneda.idDataCatalogo = ?4)")
	public abstract List<Cuenta> consultaCuentaByNumeroTipoEntidadMoneda(String numero, int tipo, int entidad, int moneda);
	
}
