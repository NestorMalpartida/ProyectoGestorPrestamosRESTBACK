package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamo.entity.Usuario;

public interface PrestatarioRepository extends JpaRepository<Usuario, Integer> {
	

	@Query("select u from Usuario u where u.login = ?1")
	public abstract List<Usuario> listaUsuarioPorLoginIgual(String login);
	
	@Query("select u from Usuario u where u.dni = ?1")
	public abstract List<Usuario> listaUsuarioPorDniIgual(String dni);
	
	@Query("select u from Usuario u where u.login like ?1")
	public abstract List<Usuario> listaUsuarioPorLoginLike(String login);
	
	@Query("Select r from Usuario r, UsuarioHasRol u where r.idUsuario = u.usuario.idUsuario and u.rol.idRol = 4 and r.usuarioSuperior =?1 and r.login like ?2")
	public abstract List<Usuario> listaPrestatariosDeUnPrestamistaLike(int idUsuario, String login);
	
	@Query("select u from Usuario u where u.login = ?1 and u.idUsuario != ?2")
	public abstract List<Usuario> listaUsuarioPorLoginIgualActualiza(String login, int idUsuario);
	
	@Query("select u from Usuario u where u.dni = ?1 and u.idUsuario != ?2")
	public abstract List<Usuario> listaUsuarioPorDniIgualActualiza(String dni, int idUsuario);
	
	/*@Query("Select r from Usuario r, UsuarioHasRol u where r.idUsuario = u.usuario.idUsuario and u.rol.idRol = 4 and r.usuarioSuperior =?1")
	public abstract List<Usuario> listaPrestatariosDeUnPrestamista(int idUsuario);*/
	
	@Query("Select u from Usuario u where u.usuarioSuperior =?1")
	public abstract List<Usuario> listaPrestatariosDeUnPrestamista(int idUsuario);
	
	@Query("select u from Usuario u where u.login like ?1 and"
			+" u.nombres = ?2 and"
			+" u.apellidos = ?3 and"
			+" u.direccion = ?4 and"
			+" u.estado = ?5")
	public abstract List<Usuario> listaUsuarioPorLoginLikeCompleja(String login, String nombres, String apellidos, String direccion, int estado);
	
	@Query("select u from Usuario u where u.login like ?1 and"
			+" u.usuarioSuperior = ?2 and"
			+" u.nombres = ?3 and"
			+" u.apellidos = ?4 and"
			+" u.direccion = ?5 and"
			+" u.estado = ?6")
	public abstract List<Usuario> listaPrestatariosDeUnPrestamistaCompleja(String login, int idUsuario, String nombres, String apellidos, String direccion, int estado);
	
}