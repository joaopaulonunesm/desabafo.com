package com.desabafo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desabafo.models.Comentario;
import com.desabafo.repositories.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	public Comentario save(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}

	public void delete(Comentario comentario) {
		comentarioRepository.delete(comentario);
	}

	public Comentario findOne(Long id) {
		return comentarioRepository.findOne(id);
	}

	public List<Comentario> findAll() {
		return comentarioRepository.findAll();
	}
}