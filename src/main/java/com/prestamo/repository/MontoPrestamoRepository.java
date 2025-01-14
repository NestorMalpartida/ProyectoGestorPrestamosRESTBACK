package com.prestamo.repository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamo.entity.MontoPrestamo;

public interface MontoPrestamoRepository extends JpaRepository<MontoPrestamo, Integer>{
	@Query("SELECT m.capital FROM MontoPrestamo m WHERE m.dias.id = :diasId")
    List<Integer> findCapitalsByDiasId(@Param("diasId") int diasId);

	@Query("SELECT m.monto FROM MontoPrestamo m WHERE m.dias.id = :diasId AND m.capital = :capital")
    BigDecimal findMontoByDiasIdAndCapital(@Param("diasId") int diasId, @Param("capital") int capital);
	
	@Query("SELECT mp FROM MontoPrestamo mp WHERE CAST(mp.capital AS string) LIKE CONCAT(?1, '%')")
    List<MontoPrestamo> obtenerMontoPrestamosPorPrimerosDigitosCapital(String capitalDigits);
	

	@Query("SELECT mp FROM MontoPrestamo mp WHERE "
            + "(:diasId = -1 or mp.dias.idDataCatalogo = :diasId)  AND "
            + "(:capital = 0 or mp.capital = :capital) AND "
            + "mp.fechaActualizacion >= :fechaInicio AND "
            + "mp.fechaActualizacion <= :fechaFin AND "
            + "mp.estado = :estado")
       List<MontoPrestamo> consultaComplejaMontoPrestamo(int diasId, int capital,Date fechaInicio, Date fechaFin,int estado);
	
}
