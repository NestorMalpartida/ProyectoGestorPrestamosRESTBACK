package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prestamo.entity.Usuario;
import com.prestamo.entity.UsuarioHasRol;
import com.prestamo.entity.UsuarioHasRolPK;
import com.prestamo.repository.PrestatarioRepository;
import com.prestamo.repository.UsuarioTieneRolRepository;

@Service
public class PrestatarioServiceImpl implements PrestatarioService {
	
	@Autowired
	private PrestatarioRepository prestatarioRepository;
	
	@Autowired
	private UsuarioTieneRolRepository usuarioTieneRolRepository;
	

	@Override
	@Transactional
	public Usuario insertaActualizaUsuario(Usuario obj) {
		
		Usuario objUsuario = prestatarioRepository.save(obj);
		
		UsuarioHasRolPK usuarioHasRolPK = new UsuarioHasRolPK();
		usuarioHasRolPK.setIdRol(4);
		usuarioHasRolPK.setIdUsuario(objUsuario.getIdUsuario());
		
		UsuarioHasRol objUsuarioHasRol = new UsuarioHasRol();
		objUsuarioHasRol.setUsuarioHasRolPk(usuarioHasRolPK);
		
		usuarioTieneRolRepository.save(objUsuarioHasRol);
		
		return objUsuario;
	}

	@Override
	public List<Usuario> listaUsuario() {
		return prestatarioRepository.findAll();
	}

	@Override
	public List<Usuario> listaUsuarioPorLoginIgual(String login) {
		return prestatarioRepository.listaUsuarioPorLoginIgual(login);
	}

	@Override
	public List<Usuario> listaUsuarioPorDniIgual(String dni) {
		return prestatarioRepository.listaUsuarioPorDniIgual(dni);
	}

	@Override
	public void eliminaUsuario(int idUsuario) {
		prestatarioRepository.deleteById(idUsuario);
		
	}

	@Override
	public List<Usuario> listaUsuarioPorLoginLike(String login) {
		return prestatarioRepository.listaUsuarioPorLoginLike(login);
	}

	@Override
	public List<Usuario> listaUsuarioPorLoginIgualActualiza(String login, int idUsuario) {
		return prestatarioRepository.listaUsuarioPorLoginIgualActualiza(login, idUsuario);
	}

	@Override
	public List<Usuario> listaUsuarioPorDniIgualActualiza(String dni, int idUsuario) {
		return prestatarioRepository.listaUsuarioPorDniIgualActualiza(dni, idUsuario);
	}

	@Override
	public List<Usuario> listaPrestatariosDeUnPrestamista(int idUsuario) {
		return prestatarioRepository.listaPrestatariosDeUnPrestamista(idUsuario);
	}

	/*@Override
	public List<Usuario> listaUsuarioPorLoginLikeTest( String login) {
		return prestatarioRepository.listaUsuarioPorLoginLikeTest(login);
	}*/

	@Override
	public List<Usuario> listaPrestatariosDeUnPrestamistaLike(int idUsuario, String login) {
		return prestatarioRepository.listaPrestatariosDeUnPrestamistaLike(idUsuario, login);
	}

	@Override
	@Transactional
	public void deleteByUsuarioId(int idUsuario) {
		usuarioTieneRolRepository.deleteByUsuarioId(idUsuario);
		
		prestatarioRepository.deleteById(idUsuario);
		
	}
	
	@Override
	public List<Usuario> listaUsuarioPorLoginLikeCompleja(String login, String nombres, String apellidos,
			String direccion, int estado) {
		return prestatarioRepository.listaUsuarioPorLoginLikeCompleja(login, nombres, apellidos, direccion, estado);
	}

	@Override
	public List<Usuario> listaPrestatariosDeUnPrestamistaCompleja(String login, int idUsuario, String nombres,
			String apellidos, String direccion, int estado) {
		return prestatarioRepository.listaPrestatariosDeUnPrestamistaCompleja(login, idUsuario, nombres, apellidos, direccion, estado);
	}

}
