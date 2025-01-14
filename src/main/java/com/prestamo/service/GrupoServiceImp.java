package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.Grupo;
import com.prestamo.repository.GrupoRepository;

@Service
public class GrupoServiceImp implements GrupoService{

	@Autowired
	private GrupoRepository repo;
	
	@Override
	public Grupo insertarActualizarGrupo(Grupo bean) {
		// TODO Auto-generated method stub
		return repo.save(bean);
	}

	@Override
	public List<Grupo> listaGrupos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Grupo> listaGrupoPorNombreIgual(String nombre) {
		// TODO Auto-generated method stub
		return repo.listaGrupoPorNombreIgual(nombre);
	}

	@Override
	public List<Grupo> listaGrupoPorDescripcionIgualActualiza(String descripcion, int idGrupo) {
		return repo.listaGrupoPorDescripcionIgualActualiza(descripcion, idGrupo);
	}

	@Override
	public List<Grupo> listaGrupoPorDescripcionLike(String nombre) {
		return repo.listaGrupoPorDescripcionLike(nombre);
	}

	@Override
	public void eliminaGrupo(int idGrupo) {
		repo.deleteById(idGrupo);
	}
	
	@Override
	public List<Grupo>consultaGrupoEF(String descripcion, int estado, int idUbigeo) {
		return repo.consultaGrupoEF(descripcion, estado, idUbigeo);
	}
}
