package com.desabafo.services;

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
		return comentarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro ao buscar um comentário por ID"));
	}

}