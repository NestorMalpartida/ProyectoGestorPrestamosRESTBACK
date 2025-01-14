package com.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.prestamo.entity.UsuarioHasRol;
import com.prestamo.entity.UsuarioHasRolPK;

public interface UsuarioTieneRolRepository extends JpaRepository<UsuarioHasRol, UsuarioHasRolPK> {
	
	@Modifying
	@Transactional
	@Query("DELETE from UsuarioHasRol uhr WHERE uhr.usuario.idUsuario = :idUsuario")
	public void deleteByUsuarioId(@Param("idUsuario") int idUsuario);
}
