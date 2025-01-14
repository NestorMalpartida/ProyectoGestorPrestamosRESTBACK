package com.prestamo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.prestamo.entity.Usuario;

public interface PrestatarioService {

	public abstract List<Usuario> listaUsuarioPorLoginIgual(String login);
	public abstract List<Usuario> listaUsuarioPorDniIgual(String dni);
	public abstract List<Usuario> listaUsuarioPorLoginIgualActualiza(String login, int idUsuario);
	public abstract List<Usuario> listaUsuarioPorDniIgualActualiza(String dni, int idUsuario);
	
	//Metodos para El Crud
	public abstract Usuario insertaActualizaUsuario(Usuario obj);
	public abstract List<Usuario> listaUsuario();
	public abstract void eliminaUsuario(int idUsuario);
	public abstract List<Usuario> listaUsuarioPorLoginLike(String login);
	public abstract List<Usuario> listaPrestatariosDeUnPrestamista(int idUsuario);
	//public abstract List<Usuario> listaUsuarioPorLoginLikeTest(String login);
	public abstract List<Usuario> listaPrestatariosDeUnPrestamistaLike(int idUsuario, String login);
	public void deleteByUsuarioId(@Param("idUsuario") int idUsuario);
	public abstract List<Usuario> listaUsuarioPorLoginLikeCompleja(String login, String nombres, String apellidos, String direccion, int estado);
	public abstract List<Usuario> listaPrestatariosDeUnPrestamistaCompleja(String login, int idUsuario, String nombres, String apellidos, String direccion, int estado);
}
